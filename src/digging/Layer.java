package digging;

public record Layer(
        int startY,
        int endY,
        BaseTile baseTile,
        double resourceChance
) {}
