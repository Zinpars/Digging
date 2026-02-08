package digging;

import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class Hud {
    private final Label statsTitle;
    private final Label staminaLabel;
    private final Label moneyLabel;
    private final Label bombLabel;
    private final Label oreValueLabel;
    private final Label bombRadiusLabel;
    private final Label upgradesTitle;
    private final Button bombUse;
    private final Button staminaUpgrade;
    private final Button oreValueUpgrade;
    private final Button bombUpgrade;
    private final Button bombRadiusUpgrade;

   // private final Runnable onUseBomb;
    //private final Game game;
    private final Player player;

    public Hud(Game game, Runnable onUseBomb) {
       // this.game = game;
        player = game.getPlayer();
        //this.onUseBomb = onUseBomb;

        statsTitle = new Label("Stats");
        staminaLabel = new Label("Stamina: 5 / 5");
        moneyLabel = new Label("Money: 0");
        bombLabel = new Label("Bombs: 0 / 0");
        oreValueLabel = new Label("Ore Value: 1");
        bombRadiusLabel = new Label("Bomb Radius: 2");

        statsTitle.getStyleClass().add("hud-title");
        staminaLabel.getStyleClass().add("hud-text");
        moneyLabel.getStyleClass().add("hud-text");
        bombLabel.getStyleClass().add("hud-text");
        oreValueLabel.getStyleClass().add("hud-text");
        bombRadiusLabel.getStyleClass().add("hud-text");

        bombUse = new Button("Use Bomb");

        bombUse.getStyleClass().add("button");

        bombUse.setOnAction(e -> onUseBomb.run());

        upgradesTitle = new Label("Upgrades");
        staminaUpgrade = new Button("Stamina");
        oreValueUpgrade = new Button("Ore Value");
        bombUpgrade = new Button("Bombs");
        bombRadiusUpgrade = new Button("Bomb Radius");

        upgradesTitle.getStyleClass().add("hud-title");
        staminaUpgrade.getStyleClass().add("button");
        oreValueUpgrade.getStyleClass().add("button");
        bombUpgrade.getStyleClass().add("button");
        bombRadiusUpgrade.getStyleClass().add("button");

        staminaUpgrade.setOnAction(e -> {
            game.buyStaminaUpgrade();
            updateStamina();
            updateMoney();
        });
        oreValueUpgrade.setOnAction(e -> {
            game.buyOreValueUpgrade();
            updateMoney();
            updateOreValue();
        });
        bombUpgrade.setOnAction(e -> {
            game.buyBombUpgrade();
            updateMoney();
            updateBombs();
        });
        bombRadiusUpgrade.setOnAction(e -> {
            game.buyBombRadiusUpgrade();
            updateMoney();
            updateBombRadius();
        });

    }

    public VBox getView() {
        VBox vbox = new VBox(10, statsTitle, staminaLabel, moneyLabel, bombLabel, bombUse, oreValueLabel, bombRadiusLabel, upgradesTitle, staminaUpgrade, oreValueUpgrade, bombUpgrade, bombRadiusUpgrade);
        vbox.setPadding(new Insets(10));
        return vbox;
    }

    public void updateStamina() { staminaLabel.setText("Stamina: " + player.getStamina() + " / " + player.getMaxStamina()); }
    public void updateMoney() { moneyLabel.setText("Money: " + player.getMoney()); }
    public void updateBombs() { bombLabel.setText("Bombs: " + player.getBombs() + " / " + player.getMaxBombs()); }
    public void updateOreValue() { oreValueLabel.setText("Ore Value: " + (player.getOreValueUpgrade() + 1)); }
    public void updateBombRadius() {bombRadiusLabel.setText("Bomb Radius: " + player.getBombRadius());}



}

