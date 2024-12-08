package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MainMenuScene {
    private Main mainGameScene;

    public MainMenuScene(Main mainGameScene) {
        this.mainGameScene = mainGameScene;
    }

    public BorderPane createLayout() {
    	// Set Background Color for the Main Scene
    			BackgroundFill backgroundFill = new BackgroundFill(javafx.scene.paint.Color.DARKSLATEGRAY, CornerRadii.EMPTY,
    					Insets.EMPTY);
    			Background background = new Background(backgroundFill); 
        // Header
        Image coinLogo = new Image(getClass().getResourceAsStream("coinLogo.png"));
        ImageView imageView = new ImageView(coinLogo);
        imageView.setFitWidth(100);
        imageView.setPreserveRatio(true);

        Label headLb = new Label("Win Maximum Coins!");
        headLb.setStyle("-fx-font-size: 26px; -fx-font-weight: bold; -fx-text-fill: #FFD700;");

        Label insertWayLb = new Label("Choose your strategy for coins:");
        insertWayLb.setStyle("-fx-font-size: 20px; -fx-text-fill: #FFFFFF;");

        VBox topBox = new VBox(10, imageView, headLb, insertWayLb);
        topBox.setAlignment(Pos.CENTER);

        // Buttons
        Button randomBT = createStyledButton("Random");
        Button manualBT = createStyledButton("Manual");
        Button fromFileBT = createStyledButton("From File");
        Button closeBT = createCloseButton();

        // Button Actions
        randomBT.setOnAction(e -> mainGameScene.openRandomBtScene());
        manualBT.setOnAction(e -> mainGameScene.openManualBtScene());
        fromFileBT.setOnAction(e -> mainGameScene.openfromFileBtScene());
     // VBox for Top Contents
     		VBox vBox = new VBox(10);
     		vBox.setAlignment(Pos.CENTER);
     		vBox.getChildren().addAll(imageView, headLb, insertWayLb);
 
     	// HBox for Game Option Buttons
    		HBox hBoxBt = new HBox(20, randomBT, manualBT, fromFileBT);
    		hBoxBt.setPadding(new Insets(30));
    		hBoxBt.setAlignment(Pos.CENTER);

    		// BorderPane Layout
    		BorderPane bp = new BorderPane();
    		bp.setBackground(background);
    		bp.setTop(vBox);
    		bp.setCenter(hBoxBt);
    		BorderPane.setAlignment(closeBT, Pos.BOTTOM_CENTER);
    		BorderPane.setMargin(closeBT, new Insets(10, 0, 20, 0));
    		bp.setBottom(closeBT);


        return bp;
    }

    private Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setStyle(
            "-fx-background-color: #FFD700; -fx-text-fill: #000; -fx-font-size: 16px; -fx-font-weight: bold; "
            + "-fx-border-color: #FFA500; -fx-border-width: 2px; -fx-border-radius: 10px; -fx-background-radius: 10px; "
            + "-fx-padding: 10px 20px;");
        return button;
    }

    private Button createCloseButton() {
        Button closeBT = new Button("Close");
        closeBT.setStyle("-fx-background-color: #FF6347; -fx-text-fill: white; -fx-font-weight: bold;");
        closeBT.setOnAction(e -> {
            if (mainGameScene.getPrimaryStage() != null) {
                mainGameScene.getPrimaryStage().close();
            }
        });
        return closeBT;
    }
}
