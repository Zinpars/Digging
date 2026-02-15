package digging;

public class Player {
    private int tileX;
    private int tileY;
    private int stamina;
    private int money = 0;
    private int bombs = 0;

    private int maxStamina = 5;
    private int maxBombs = 0;
    private int oreValueUpgrade = 1;
    private int bombRadius = 2;

    public Player() {
        reset();
    }

    public int getTileX() { return tileX; }
    public int getTileY() { return tileY; }

    public void move(int dx, int dy) {
        tileX += dx;
        tileY += dy;
    }

    public int getStamina() {
        return stamina;
    }
    public int getMoney() {
        return money;
    }
    public int getBombs() {
        return bombs;
    }

    public void addMoney(int amount) {
        if (amount <= 0) return;
        money += amount * oreValueUpgrade;
    }

    public boolean spendMoney(int amount) {
        if (amount <= 0) return false;
        if (money < amount) return false;
        money -= amount;
        return true;
    }
    public void spendBomb() {
        bombs --;
    }

    public void consumeStamina(int cost) {
        stamina -= cost;
    }

    public void addMaxStamina(int i) {
        maxStamina += i;
    }

    public void addOreValue(int i) {
        oreValueUpgrade += i;
    }

    public void addBombRadius(int i) {
        bombRadius += i;
    }

    public void reset() {
        tileX = 10;
        tileY = 0;
        stamina = maxStamina;
        bombs = maxBombs;
    }

    public void addMaxBombs(int i) {
        maxBombs += i;
    }

    public int getBombRadius() {
        return bombRadius;
    }

    public int getMaxStamina() {
        return maxStamina;
    }

    public int getMaxBombs() {
        return maxBombs;
    }

    public int getOreValueUpgrade() {
        return oreValueUpgrade;
    }

}
