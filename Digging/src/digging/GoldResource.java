package digging;

public class GoldResource extends Resource{


    public GoldResource() {
        type = "gold";
    }

    @Override
    public void onStep(Player player) {
        player.addMoney(5);
    }
}