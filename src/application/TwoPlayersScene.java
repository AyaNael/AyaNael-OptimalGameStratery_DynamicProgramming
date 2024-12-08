package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class TwoPlayersScene {

    private int[] coins; // Array of coin values
    private String playerOneName; // Name of Player One
    private String playerTwoName; // Name of Player Two
    private int playerOneScore = 0; // Score of Player One
    private int playerTwoScore = 0; // Score of Player Two
    private boolean isPlayerOneTurn; // Track whose turn it is
    private Label currentPlayerLabel;
    private VBox playerOneScoreBox;
    private VBox playerTwoScoreBox;
    private Label playerOneTotalScore;
    private Label playerTwoTotalScore;
    private VBox playerOneCoinsBox;
    private VBox playerTwoCoinsBox;
    private int startIndex = 0; // Initial edge index
    private int endIndex; // Ending edge index
    private Button playAgainBt;
    private Main mainGameScene;

    public TwoPlayersScene(int[] coins, String playerOneName, String playerTwoName, Main mainGameScene) {
        this.coins = coins;
        this.playerOneName = playerOneName;
        this.playerTwoName = playerTwoName;
        this.endIndex = coins.length - 1; // Set initial end index
        this.mainGameScene = mainGameScene;
    }

    public BorderPane createLayout(String firstPlayer) {
        if (coins == null || coins.length == 0) {
            throw new IllegalStateException("Coins array cannot be null or empty.");
        }

        isPlayerOneTurn = firstPlayer.equals(playerOneName);

        currentPlayerLabel = new Label("Current Player: " + firstPlayer);
        currentPlayerLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #FFD700;");

        // Add scoreboxes
        Label playerOneTitle = new Label(playerOneName + " Score:");
        playerOneTitle.setStyle("-fx-font-size: 18px; -fx-text-fill: #00FF00;");
        playerOneTotalScore = new Label("Total: 0");
        playerOneTotalScore.setStyle("-fx-font-size: 18px; -fx-text-fill: #00FF00;");
        playerOneCoinsBox = new VBox(5);

        Label playerTwoTitle = new Label(playerTwoName + " Score:");
        playerTwoTitle.setStyle("-fx-font-size: 18px; -fx-text-fill: #0000FF;");
        playerTwoTotalScore = new Label("Total: 0");
        playerTwoTotalScore.setStyle("-fx-font-size: 18px; -fx-text-fill: #0000FF;");
        playerTwoCoinsBox = new VBox(5);

        playerOneScoreBox = new VBox(10, playerOneTitle, playerOneCoinsBox, playerOneTotalScore);
        playerOneScoreBox.setAlignment(Pos.CENTER);
        playerTwoScoreBox = new VBox(10, playerTwoTitle, playerTwoCoinsBox, playerTwoTotalScore);
        playerTwoScoreBox.setAlignment(Pos.CENTER);

        // Coin container using GridPane
        GridPane coinGrid = new GridPane();
        coinGrid.setAlignment(Pos.CENTER);
        coinGrid.setHgap(10);
        coinGrid.setVgap(10);

        for (int i = 0; i < coins.length; i++) {
            Button coinButton = createCoinButton(coins[i], i);
            coinGrid.add(coinButton, i % 10, i / 10); // Place buttons in rows of 10
        }

        // Play Again Button
        playAgainBt = new Button("Play Again");
        playAgainBt.setStyle("-fx-background-color: #32CD32; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 16px;");
        playAgainBt.setOnAction(e -> {
            MainMenuScene mainMenuScene = new MainMenuScene(mainGameScene);
            mainGameScene.setLayout(mainMenuScene.createLayout());
        });
        playAgainBt.setVisible(false); // Initially hidden until the game ends

        VBox mainCenterLayout = new VBox(20, currentPlayerLabel, coinGrid);
        mainCenterLayout.setAlignment(Pos.CENTER);

        // Main layout
        BorderPane mainLayout = new BorderPane();
        mainLayout.setRight(playerTwoScoreBox);
        mainLayout.setLeft(playerOneScoreBox);
        mainLayout.setCenter(mainCenterLayout);
        mainLayout.setBottom(playAgainBt);
        BorderPane.setAlignment(playAgainBt, Pos.CENTER);
        mainLayout.setPadding(new Insets(50));
        mainLayout.setStyle("-fx-background-color: #2F4F4F;");

        return mainLayout;
    }

    private Button createCoinButton(int coinValue, int index) {
        Button coinButton = new Button(String.valueOf(coinValue));
        coinButton.setStyle("-fx-background-color: #FFD700; -fx-text-fill: black; -fx-font-weight: bold;-fx-background-radius: 25;");
        coinButton.setPrefSize(50, 50);

        coinButton.setOnMouseClicked(event -> handleCoinClick(index, coinButton));
        return coinButton;
    }

    private void handleCoinClick(int index, Button coinButton) {
        if (index != startIndex && index != endIndex) return;

        int selectedValue = coins[index];

        if (isPlayerOneTurn) {
            playerOneScore += selectedValue;
            playerOneTotalScore.setText("Total: " + playerOneScore);
            addCoinToVBox(playerOneCoinsBox, selectedValue, true);
            coinButton.setStyle("-fx-background-color: #00FF00; -fx-text-fill: white; -fx-font-weight: bold;");
        } else {
            playerTwoScore += selectedValue;
            playerTwoTotalScore.setText("Total: " + playerTwoScore);
            addCoinToVBox(playerTwoCoinsBox, selectedValue, false);
            coinButton.setStyle("-fx-background-color: #0000FF; -fx-text-fill: white; -fx-font-weight: bold;");
        }

        coinButton.setDisable(true);

        if (index == startIndex) {
            startIndex++;
        } else {
            endIndex--;
        }

        isPlayerOneTurn = !isPlayerOneTurn;
        currentPlayerLabel.setText("Current Player: " + (isPlayerOneTurn ? playerOneName : playerTwoName));

        if (startIndex > endIndex) {
            defineTheWinner();
        }
    }

    private void addCoinToVBox(VBox vbox, int coinValue, boolean isPlayerOne) {
        Label coinLabel = new Label(String.valueOf(coinValue));
        String color = isPlayerOne ? "#98FB98" : "#ADD8E6";
        coinLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: " + color + ";");
        vbox.getChildren().add(coinLabel);
    }

    private void defineTheWinner() {
        String winner;
        if (playerOneScore > playerTwoScore) {
            winner = playerOneName + " 🎉🎉🎉";
            currentPlayerLabel.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; -fx-text-fill: #32CD32;");
            currentPlayerLabel.setText("Congratulations! Winner: " + winner);
        } else if (playerTwoScore > playerOneScore) {
            winner = playerTwoName + " 🎉🎉🎉";
            currentPlayerLabel.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; -fx-text-fill: #32CD32;");
            currentPlayerLabel.setText("Congratulations! Winner: " + winner);
        } else {
            winner = "No Body Wins!!!";
            currentPlayerLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #FF6347;");
            currentPlayerLabel.setText("" + winner);
        }

        playAgainBt.setVisible(true); // Show the Play Again button
    }
}
