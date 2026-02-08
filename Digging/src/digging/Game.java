package digging;

public class Game {
    private World world;
    private final Player player = new Player();

    private int staminaUpgradeCost = 1;
    private int oreValueUpgradeCost = 5;
    private int bombsUpgradeCost = 10;
    private int bombRadiusUpgradeCost = 50;

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

    public boolean buyStaminaUpgrade() {
        if (!player.spendMoney(staminaUpgradeCost)) return false;

        player.addMaxStamina(1);
        return true;
    }

    public boolean buyOreValueUpgrade() {
        if (!player.spendMoney(oreValueUpgradeCost)) return false;

        player.addOreValue(1);
        return true;
    }

    public boolean buyBombUpgrade() {
        if (!player.spendMoney(bombsUpgradeCost)) return false;

        player.addMaxBombs(1);
        return true;
    }

    public WorldChange useBomb() {
        return world.useBomb(player);
    }

    public boolean buyBombRadiusUpgrade() {
        if (!player.spendMoney(bombRadiusUpgradeCost)) return false;

        player.addBombRadius(1);
        return true;
    }
}

