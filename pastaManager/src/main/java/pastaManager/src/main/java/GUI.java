package pastaManager.src.main.java;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class GUI extends Application {

    public static void main(String[] args) {
        /*
        ImportJSON importJSON = new ImportJSON();
        try {
            importJSON.addTeamMembers(importJSON.getJsonArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
        */
        launch(args);
    }


    Stage stage;
    Scene homeScene;

    @Override
    public void start(Stage primaryStage) throws Exception{

        stage = primaryStage;

        StackPane homeLayout = new StackPane();

        homeScene = new Scene(homeLayout, 1280, 800);

        stage.setScene(homeScene);
        stage.setTitle("Pasta Manager");
        stage.show();
    }
}
