package digging;

import java.awt.*;
import javafx.scene.image.Image;

public class Tile {
    private BaseTile base;
    private Resource resource;
    private final int x;
    private final int y;

    private final static Image dirt = new Image("Dirt.png");
    private final static Image empty = new Image("Empty.png");
    private final static Image sky = new Image("Sky.png");

    public Tile( int x, int y, BaseTile base, Resource resource) {
        this.base = base;
        this.resource = resource;
        this.x = x;
        this.y = y;
    }

    public int getX() { return x; }
    public int getY() { return y; }

    public int getStaminaCost() {
        return base.getStaminaCost();
    }

    public void onStep(Player player) {
        if(resource != null) resource.onStep(player);
    }

    public BaseTile getBase() { return base; }

    public void setBase(BaseTile base) {
        this.base = base;
    }

    public Resource getResource() { return resource; }

    public void removeResource() {
        resource = null;
    }

    public Image getTileImage() {
        switch (base.getLayerDepth()) {
            case -1: return empty;
            case 0: return sky;
            case 1:
            case 2:
            default: return dirt;
        }
    }
}
