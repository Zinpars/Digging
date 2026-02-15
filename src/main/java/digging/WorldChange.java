package digging;

import java.util.List;

public record WorldChange(
        List<Tile> changedTiles,
        List<Resource> removedResources
) {}
