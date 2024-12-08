package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class PlayingWayScene {
    private Main mainGameScene;
    private int[] coins;

    public PlayingWayScene(int[] coins, Main mainGameScene) {
        this.coins = coins;
        this.mainGameScene = mainGameScene;
    }

    public VBox createLayout() {
        // Header Label
        Label headLb = new Label("How Do You Want to Play?");
        headLb.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #FFD700;");

        // Play Against Computer Button
        Button withComputerBt = createStyledButton("Play With Computer");
        withComputerBt.setOnAction(e -> {
            try {
                PlayWithComputerScene playWithComputerScene = new PlayWithComputerScene(coins, mainGameScene);
                System.out.println("hii");
                mainGameScene.setLayout(playWithComputerScene.createLayout());
                System.out.println("hiiiiii");

            } catch (Exception ex) {
                ex.printStackTrace();
                System.out.println("Error navigating to Play With Computer scene.");
            }
        });

        // Two-Player Mode Button
        Button twoPlayerBt = createStyledButton("Two Players");
        twoPlayerBt.setOnAction(e -> {
            PlayerNamesInputScene playerNamesInputScene = new PlayerNamesInputScene(coins, mainGameScene);
            mainGameScene.setLayout(playerNamesInputScene.createLayout());
        });

        // Back Button
        Button backBt = createStyledButton("Back");
        backBt.setOnAction(e -> {
            MainMenuScene mainMenuScene = new MainMenuScene(mainGameScene);
            mainGameScene.setLayout(mainMenuScene.createLayout());
        });

        // Layout Configuration
        VBox layout = new VBox(20, headLb, withComputerBt, twoPlayerBt, backBt);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #2F4F4F;");

        return layout;
    }

    // Method to create and style buttons consistently
    private Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setStyle(
                "-fx-background-color: #FFD700; -fx-text-fill: #000; -fx-font-size: 16px; -fx-font-weight: bold; "
                        + "-fx-border-color: #FFA500; -fx-border-width: 2px; -fx-border-radius: 10px; -fx-background-radius: 10px; "
                        + "-fx-padding: 10px 20px;");
        return button;
    }
}
