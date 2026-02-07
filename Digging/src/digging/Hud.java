package digging;

import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class Hud {
    private final Label staminaLabel;
    private final Label moneyLabel;
    private final Label bombLabel;
    private final Button bombUse;
    private final Button staminaUpgrade;
    private final Button oreValueUpgrade;
    private final Button bombUpgrade;

    private final Game game;
    private final Player player;

    public Hud(Game game) {
        this.game = game;
        player = game.getPlayer();

        staminaLabel = new Label("Stamina: 5");
        moneyLabel = new Label("Money: 0");
        bombLabel = new Label("Bombs: 0");

        staminaLabel.getStyleClass().add("hud-text");
        moneyLabel.getStyleClass().add("hud-text");
        bombLabel.getStyleClass().add("hud-text");

        bombUse = new Button("Use");

        bombUse.getStyleClass().add("button");

        bombUse.setOnAction(e -> {
            game.useBomb();
            updateBombs();
        });

        staminaUpgrade = new Button("Stamina");
        oreValueUpgrade = new Button("Ore Value");
        bombUpgrade = new Button("Bombs");

        staminaUpgrade.getStyleClass().add("button");
        oreValueUpgrade.getStyleClass().add("button");
        bombUpgrade.getStyleClass().add("button");

        staminaUpgrade.setOnAction(e -> {
            game.buyStaminaUpgrade();
            updateStamina();
            updateMoney();
        });
        oreValueUpgrade.setOnAction(e -> {
            game.buyOreValueUpgrade();
            updateMoney();
        });
        bombUpgrade.setOnAction(e -> {
            game.buyBombUpgrade();
            updateMoney();
        });

    }

    public VBox getView() {
        VBox vbox = new VBox(10, staminaLabel, moneyLabel, bombLabel, bombUse, staminaUpgrade, oreValueUpgrade, bombUpgrade);
        vbox.setPadding(new Insets(10));
        return vbox;
    }

    public void updateStamina() { staminaLabel.setText("Stamina: " + player.getStamina()); }
    public void updateMoney() { moneyLabel.setText("Money: " + player.getMoney()); }
    public void updateBombs() { bombLabel.setText("Bombs: " + player.getBombs()); }



}

