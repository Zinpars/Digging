package digging;

public class IronResource extends Resource{
    public IronResource() {
        type = "iron";
    }

    @Override
    public void onStep(Player player) {
        player.addMoney(3);
    }
}
