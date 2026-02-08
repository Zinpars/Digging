package digging;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static javafx.scene.paint.Color.WHITE;

public class Main extends Application {
    public static final int TILE_SIZE = 50;
    public final int WORLD_WIDTH = 1200;
    public final int WORLD_HEIGHT = 1000;
    private ImageView playerView;
    private Text staminaText;
    private Text moneyText;
    private ImageView[][] tileViews;
    private Pane entityLayer;
    Map<Resource, ImageView> resourceViews = new HashMap<>();

    private final Game game = new Game();
    private final Player player = game.getPlayer();
    private World world = game.getWorld();
    Hud hud = new Hud(game, () -> {
        WorldChange change = game.useBomb();
        if (change == null) return;

        for (Tile tile : change.changedTiles()) {
            updateTile(tile.getX(), tile.getY());
        }

        for (Resource r : change.removedResources()) {
            removeResourceView(r);
        }

        updateHUD();
    });




    private final static Image copperImage = new Image("copper_ore.png");
    private final static Image playerImage = new Image("slime.png");

    @Override
    public void start(Stage stage) {
        // Create world layers
        Pane pane = new Pane();
        Pane tileLayer   = new Pane();
        entityLayer = new Pane();
        Pane effectLayer = new Pane();

        pane.getChildren().addAll(
                tileLayer,
                entityLayer,
                effectLayer
        );

        // Create player
        playerView = new ImageView(playerImage);
        playerView.setFitWidth(TILE_SIZE);
        playerView.setFitHeight(TILE_SIZE);
        updatePlayerView();

        entityLayer.getChildren().add(playerView);

        //Create tileViews
        tileViews = new ImageView[world.getWidth()][world.getHeight()];

        for (int y = 0; y < world.getHeight(); y++) {
            for (int x = 0; x < world.getWidth(); x++) {


                ImageView tile = new ImageView();

                    tile.setImage(world.getTile(x,y).getTileImage());


                tile.setFitWidth(TILE_SIZE);
                tile.setFitHeight(TILE_SIZE);
                tile.setLayoutX(x * TILE_SIZE);
                tile.setLayoutY(y * TILE_SIZE);

                tileViews[x][y] = tile;
                tileLayer.getChildren().add(tile);

            }
        }

        resetTileViews();
        buildResourceViews();

        // Create menu
        VBox vbox = new VBox();
        vbox.setStyle("-fx-background-color: #2b2b2b; -fx-text-fill: #FFFFFF");
        vbox.setPrefSize(200, 1000);
        vbox.getChildren().add(hud.getView());


        //Setup stage and scene
        BorderPane borderPane = new BorderPane();
        borderPane.setLeft(pane);
        borderPane.setRight(vbox);

        Scene scene = new Scene(borderPane, WORLD_WIDTH, WORLD_HEIGHT);
        scene.getStylesheets().add(
                getClass().getResource("styles.css").toExternalForm()
        );
        stage.setScene(scene);
        stage.show();

        //Create movement keys
        scene.setOnKeyPressed(e -> {
            Resource pickedUp = null;
            switch (e.getCode()) {
                //case W, UP -> tryMove(0, -1);
                case S, DOWN -> pickedUp = game.tryMovePlayerAndCollect(0, 1);
                case A, LEFT -> pickedUp = game.tryMovePlayerAndCollect(-1, 0);
                case D, RIGHT -> pickedUp = game.tryMovePlayerAndCollect(1, 0);
                case G -> player.addMoney(1000);
            }


            if (pickedUp != null) {
                ImageView view = resourceViews.remove(pickedUp);
                entityLayer.getChildren().remove(view);
            }

            updatePlayerView();
            updateTileViews();
            updateHUD();
            if (player.getStamina() <= 0) {
                resetGame();
            }
        });
    }

    void buildResourceViews() {
        resourceViews.clear();
        entityLayer.getChildren().clear();

        for (Tile tile : game.getWorld().getAllTiles()) {
            Resource r = tile.getResource();
            if (r == null) continue;

            ImageView view = createResourceView(r);
            view.setLayoutX(tile.getX() * TILE_SIZE);
            view.setLayoutY(tile.getY() * TILE_SIZE);

            resourceViews.put(r, view);
            entityLayer.getChildren().add(view);
        }
        entityLayer.getChildren().add(playerView);
    }

    private ImageView createResourceView(Resource r) {
        Image image = switch (r.getType()) {
            case "copper" -> copperImage;
            default -> playerImage;
        };
        ImageView view = new ImageView(image);
        view.setFitWidth(TILE_SIZE);
        view.setFitHeight(TILE_SIZE);
        return view;
    }


    public void resetGame() {
       game.resetRun();
        world = game.getWorld();
        // 3️⃣ Update tile visuals
        resetTileViews();       // or update existing ImageViews

        buildResourceViews();

        // 4️⃣ Update player visual
        updatePlayerView();

        // 5️⃣ Update HUD
        updateHUD();
    }

    void resetTileViews() {
        entityLayer.getChildren().clear(); // removes all resources + player

        for (Tile tile : world.getAllTiles()) {
            tileViews[tile.getX()][tile.getY()].setImage(tile.getTileImage());
        }
    }

    private void updateTileViews() {
        for (Tile tile : world.getAllTiles()) {
            if (tile.getY() == 0) continue;
            tileViews[tile.getX()][tile.getY()].setImage(tile.getTileImage());
        }
    }

    private void updateTile(int x, int y) {
        if (y == 0) return;
        tileViews[x][y].setImage(world.getTile(x, y).getTileImage());
    }

    private void removeResourceView(Resource r) {
        ImageView view = resourceViews.remove(r);
        entityLayer.getChildren().remove(view);
    }

    void updatePlayerView() {
        playerView.setLayoutX(player.getTileX() * TILE_SIZE);
        playerView.setLayoutY(player.getTileY() * TILE_SIZE);
    }

    void updateHUD() {
        hud.updateStamina();
        hud.updateMoney();
        hud.updateBombs();
    }

    public static void main (String[]args){
        launch(args);
    }
}