package digging;

public class BaseTile {
    private final int layerDepth; // 0 = top, 1 = 2nd layer, etc.// "dirt", "stone", etc.
    private final int staminaCost;

    public BaseTile( int layerDepth, int staminaCost) {
        this.layerDepth = layerDepth;
        this.staminaCost = staminaCost;
    }

    public int getStaminaCost() { return staminaCost; }
    public int getLayerDepth() { return layerDepth; }
}

