package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class PlayerNamesInputScene {

    private int[] coins; // Array of coin values
    private TextField playerOneTf, playerTwoTf; // Text fields for player names
    private Label errorLb, firstTurnPlayerLb; // Error and first-turn player labels
    private RadioButton playerOneRb, playerTwoRb; // Radio buttons for first turn selection
    private String firstPlayer; // To track the first player
    private Main mainGameScene; // Reference to the main application class

    public PlayerNamesInputScene(int[] coins, Main mainGameScene) {
        this.coins = coins;
        this.mainGameScene = mainGameScene;
    }

    public VBox createLayout() {
        // Header Label
        Label headLabel = new Label("Enter Player Names");
        headLabel.setFont(Font.font("Arial", 24));
        headLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: #FFD700;");

        // TextFields for player names
        playerOneTf = createStyledTextField("Player 1 Name");
        playerTwoTf = createStyledTextField("Player 2 Name");

        // Error Label
        errorLb = new Label("");
        errorLb.setStyle("-fx-text-fill: Red; -fx-font-weight: bold; -fx-font-size: 20px");

        // RadioButtons for selecting first player
        firstTurnPlayerLb = new Label("Choose First Turn Player:");
        firstTurnPlayerLb.setStyle("-fx-font-weight: bold; -fx-text-fill: #FFD700;");

        ToggleGroup toggleGroup = new ToggleGroup();
        playerOneRb = new RadioButton("Player 1");
        playerOneRb.setToggleGroup(toggleGroup);
        playerOneRb.setStyle("-fx-text-fill: white; -fx-font-size: 16px;");

        playerTwoRb = new RadioButton("Player 2");
        playerTwoRb.setToggleGroup(toggleGroup);
        playerTwoRb.setStyle("-fx-text-fill: white; -fx-font-size: 16px;");

        // Next Button
        Button nextBt = new Button("Let's Play!");
        nextBt.setStyle("-fx-background-color: #FFD700; -fx-text-fill: black; -fx-font-weight: bold; "
                + "-fx-font-size: 16px; -fx-background-radius: 10px; -fx-padding: 10px 20px; "
                + "-fx-border-color: #FFA500; -fx-border-width: 2px; -fx-border-radius: 10px;");

        nextBt.setOnAction(e -> {
            errorLb.setText(""); // Clear any previous error message
            String playerOneName = playerOneTf.getText();
            String playerTwoName = playerTwoTf.getText();

            if (checkFilledFields() && toggleGroup.getSelectedToggle() != null) {
                firstPlayer = toggleGroup.getSelectedToggle() == playerOneRb ? playerOneName : playerTwoName;
                // Navigate to the TwoPlayersScene
                mainGameScene.setLayout(new TwoPlayersScene(coins, playerOneName, playerTwoName, mainGameScene).createLayout(firstPlayer));
            } else {
                errorLb.setText("Please Fill All Fields and Select the First Player!");
            }
        });

        // Back Button
        Button backBt = new Button("Back");
        backBt.setStyle("-fx-background-color: #FFD700; -fx-text-fill: black; -fx-font-weight: bold; "
                + "-fx-font-size: 16px; -fx-background-radius: 10px; -fx-padding: 10px 20px; "
                + "-fx-border-color: #FFA500; -fx-border-width: 2px; -fx-border-radius: 10px;");
        backBt.setOnAction(e -> mainGameScene.setLayout(new PlayingWayScene(coins,mainGameScene).createLayout()));

        // Layout
        VBox layout = new VBox(20, headLabel, playerOneTf, playerTwoTf, firstTurnPlayerLb, playerOneRb, playerTwoRb, errorLb, nextBt, backBt);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(30));
        layout.setBackground(new Background(new BackgroundFill(Color.web("#2F4F4F"), CornerRadii.EMPTY, Insets.EMPTY)));

        return layout;
    }

    // Helper method to style TextFields consistently
    private TextField createStyledTextField(String promptText) {
        TextField textField = new TextField();
        textField.setPromptText(promptText);
        textField.setStyle("-fx-background-color: #333; -fx-text-fill: white; -fx-background-radius: 10px; "
                + "-fx-font-size: 16px; -fx-padding: 8px 12px;");
        textField.setOnMouseEntered(e -> textField.setStyle("-fx-background-color: #555; -fx-text-fill: white; "
                + "-fx-background-radius: 10px; -fx-font-size: 16px; -fx-padding: 8px 12px;"));
        textField.setOnMouseExited(e -> textField.setStyle("-fx-background-color: #333; -fx-text-fill: white; "
                + "-fx-background-radius: 10px; -fx-font-size: 16px; -fx-padding: 8px 12px;"));
        return textField;
    }

    // Method to check if all fields are filled
    private boolean checkFilledFields() {
        return !playerOneTf.getText().isEmpty() && !playerTwoTf.getText().isEmpty();
    }
}
