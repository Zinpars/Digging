package digging;


public abstract class Resource {
    protected String type;

    protected Resource() {
    }

    public void onStep(Player player) {}

    public String getType() {
        return type;
    }

}
