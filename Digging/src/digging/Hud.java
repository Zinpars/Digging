package digging;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
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
    private final Label staminaCostLabel;
    private final Label bombCostLabel;
    private final Label oreValueCostLabel;
    private final Label bombRadiusCostLabel;
    private final Game game;

    private final Player player;

    public Hud(Game game, Runnable onUseBomb) {
        this.game = game;
        player = game.getPlayer();

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
            updateStaminaCost();
        });
        oreValueUpgrade.setOnAction(e -> {
            game.buyOreValueUpgrade();
            updateMoney();
            updateOreValue();
            updateOreValueCost();
        });
        bombUpgrade.setOnAction(e -> {
            game.buyBombUpgrade();
            updateMoney();
            updateBombs();
            updateBombCost();
        });
        bombRadiusUpgrade.setOnAction(e -> {
            game.buyBombRadiusUpgrade();
            updateMoney();
            updateBombRadius();
            updateBombRadiusCost();
        });

        staminaCostLabel = new Label("Cost: 1");
        oreValueCostLabel = new Label("Cost: 5");
        bombCostLabel = new Label("Cost: 10");
        bombRadiusCostLabel = new Label("Cost: 50");

        staminaCostLabel.getStyleClass().add("hud-text");
        oreValueCostLabel.getStyleClass().add("hud-text");
        bombCostLabel.getStyleClass().add("hud-text");
        bombRadiusCostLabel.getStyleClass().add("hud-text");

    }

    public VBox getView() {
        HBox bombBox = new HBox(10, bombLabel, bombUse);
        HBox staminaBox = new HBox(10, staminaUpgrade, staminaCostLabel);
        HBox oreValueBox = new HBox(10, oreValueUpgrade, oreValueCostLabel);
        HBox bombUpgradeBox = new HBox(10, bombUpgrade, bombCostLabel);
        HBox bombRadiusBox = new HBox(10, bombRadiusUpgrade, bombRadiusCostLabel);

        VBox vbox = new VBox(10, statsTitle, staminaLabel, moneyLabel, oreValueLabel, bombRadiusLabel, bombBox, upgradesTitle, staminaBox, oreValueBox, bombUpgradeBox, bombRadiusBox);
        vbox.setPadding(new Insets(10));

        return vbox;
    }

    public void updateStamina() { staminaLabel.setText("Stamina: " + player.getStamina() + " / " + player.getMaxStamina()); }
    public void updateMoney() { moneyLabel.setText("Money: " + player.getMoney()); }
    public void updateBombs() { bombLabel.setText("Bombs: " + player.getBombs() + " / " + player.getMaxBombs()); }
    public void updateOreValue() { oreValueLabel.setText("Ore Value: " + (player.getOreValueUpgrade() + 1)); }
    public void updateBombRadius() {bombRadiusLabel.setText("Bomb Radius: " + player.getBombRadius());}
    public void updateStaminaCost() {staminaCostLabel.setText("Cost: " + game.getStaminaUpgradeCost());}
    public void updateOreValueCost() {oreValueCostLabel.setText("Cost: " + game.getOreValueUpgradeCost());}
    public void updateBombCost() {bombCostLabel.setText("Cost: " + game.getBombsUpgradeCost());}
    public void updateBombRadiusCost() {bombRadiusCostLabel.setText("Cost: " + game.getBombRadiusUpgradeCost());}



}

