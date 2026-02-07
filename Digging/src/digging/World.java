package digging;

import java.util.ArrayList;
import java.util.List;

public class World {
    public Tile[][] tiles;
    private final int width = 20;
    private final int height = 100;
    private static double copperChance = 0.15;

    private final BaseTile empty = new BaseTile(-1, 0);
    private final BaseTile sky = new BaseTile(0, 0);
    private final BaseTile dirtLayer1 = new BaseTile(1, 1);
    private final BaseTile dirtLayer2 = new BaseTile(2, 2);
    private final BaseTile dirtLayer3 = new BaseTile(3, 3);

    List<Layer> layers = List.of(
            new Layer(-10, 0, sky, 0.0),
            new Layer(1, 10, dirtLayer1, 0.15),
            new Layer(11, 120, dirtLayer2, 0.25),
            new Layer(121, 10000, dirtLayer3, 0.35)
    );

    public World () {
        generateWorld();
    }

    public void generateWorld() {
        tiles = new Tile[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Layer layer = getLayerForY(y);

                BaseTile base = layer.baseTile();
                Resource resource = null;

                if (Math.random() < layer.resourceChance()) {
                   // resource = createRandomResourceForLayer(layer);
                    resource = new CopperResource();
                }

                tiles[x][y] = new Tile(x, y, base, resource);
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Tile getTile(int x, int y) {
        return tiles[x][y];
    }

    public Resource tryMoveAndCollect(Player player, int dx, int dy) {
        int newX = player.getTileX() + dx;
        int newY = player.getTileY() + dy;

        if (newX < 0 || newX >= width || newY < 0 || newY >= height) {
            return null;
        }
        Tile newTile = tiles[newX][newY];
        player.move(dx, dy);
        player.consumeStamina(newTile.getStaminaCost());
        return collectTile(player, newTile);
    }

    public Resource collectTile(Player player, Tile tile) {
        Resource r = tile.getResource();
        if (r != null) {
            r.onStep(player);
            tile.removeResource();
        }
        tile.setBase(empty);
        return r;
    }

    public List<Tile> getAllTiles() {
        List<Tile> result = new ArrayList<>(width * height);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                result.add(tiles[x][y]);
            }
        }
        return result;
    }

    private Layer getLayerForY(int y) {
        for (Layer layer : layers) {
            if (y >= layer.startY() && y <= layer.endY()) {
                return layer;
            }
        }
        throw new IllegalStateException("No layer for y=" + y);
    }

    public void useBomb(Player player) {
        if (player.getBombs() <= 0) return;

        for (int i = 0; i < 3; i++) {
            collectTile(player, getTile(player.getTileX() + i, player.getTileY()));
        }
    }
}
