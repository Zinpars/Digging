package digging;

public class Game {
    private World world;
    private final Player player = new Player();

    public Game() {
        resetRun();
    }

    public void resetRun() {
        world = new World();
        player.reset();
    }

    public World getWorld() {
        return world;
    }

    public Player getPlayer() {
        return player;
    }

    public Resource tryMovePlayerAndCollect(int dx, int dy) {
        return world.tryMoveAndCollect(player, dx, dy);
    }
}

