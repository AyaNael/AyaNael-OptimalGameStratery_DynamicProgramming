package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {
    private Stage primaryStage; // Store the primary stage
    private Scene currentScene; // Store the current scene

    @Override
    public void start(Stage stage) {
        this.primaryStage = stage; // Assign the primary stage

        // Initialize with the main menu scene
        MainMenuScene mainMenuScene = new MainMenuScene(this);
        setLayout(mainMenuScene.createLayout());

        // Configure the stage
        primaryStage.setTitle("Optimal Game Strategy - Coin Setup");
        primaryStage.show();
    }

    public void setLayout(Pane layout) {
        System.out.println("Switching layout. New layout type: " + layout.getClass().getName());
        
        // Always recreate the Scene to ensure proper display
        currentScene = new Scene(layout, 700, 600);
        primaryStage.setScene(currentScene);
        System.out.println("Scene recreated with new layout.");
    }




    // Method to open the Random Scene
    public void openRandomBtScene() {
        RandomScene randomScene = new RandomScene();
        Scene scene = randomScene.createScene(primaryStage, this);
        primaryStage.setScene(scene);
    }

    // Method to open the Manual Scene
    public void openManualBtScene() {
        ManualScene manualScene = new ManualScene();
        Scene scene = manualScene.createScene(primaryStage, this);
        primaryStage.setScene(scene);
    }

    // Method to open the From File Scene
    public void openfromFileBtScene() {
        FromFileScene fromFileScene = new FromFileScene();
        Scene scene = fromFileScene.createScene(primaryStage, this);
        primaryStage.setScene(scene);
    }

    // Method to show the Play Mode scene
    public void showPlayMode(int[] coins) {
        PlayingWayScene playingWayScene = new PlayingWayScene(coins, this);
        Pane layout = playingWayScene.createLayout();
        setLayout(layout);
    }

    // Getter for primary stage
    public Stage getPrimaryStage() {
        return primaryStage;
    }
    

    public static void main(String[] args) {
        launch(args);
    }
}
