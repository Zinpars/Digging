package digging;

public class Player {
    private int tileX;
    private int tileY;
    private int stamina;
    private int maxStamina = 5;
    private int money = 0;

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

    public void addMoney(int amount) {
        if (amount <= 0) return;
        money += amount;
    }

    public boolean spendMoney(int amount) {
        if (amount <= 0) return false;
        if (money < amount) return false;
        money -= amount;
        return true;
    }

    public void consumeStamina(int cost) {
        stamina -= cost;
    }

    public void addMaxStamina(int i) {
        maxStamina += i;
    }

    public void reset() {
        tileX = 10;
        tileY = 0;
        stamina = maxStamina;
    }

}
