package digging;



public class CopperResource extends Resource{


    public CopperResource() {
        type = "copper";
    }

    @Override
    public void onStep(Player player) {
        player.addMoney(1);
    }
}
