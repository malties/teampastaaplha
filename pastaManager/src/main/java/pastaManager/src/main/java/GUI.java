package pastaManager.src.main.java;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GUI extends Application {

    ImportJSON importJSON = new ImportJSON();
    Button searchButton;
    Button importButton;
    Button printAllButton;

    public static void main(String[] args) {




        launch(args);
    }

    Stage stage;
    Scene homeScene;

    @Override
    public void start(Stage primaryStage) {

        stage = primaryStage;

        StackPane homeLayout = new StackPane();

        homeScene = new Scene(homeLayout, 1280, 800);

        stage.setScene(homeScene);
        stage.setTitle("Pasta Manager");
        stage.show();

        // Imports JSON file
        Label importLabel = new Label("Import JSON file");
        importButton = new Button("Import");
        importButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {
                    importJSON();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        // Prints all team members' information
        Label printAllLabel = new Label("View all team member's data");
        printAllButton = new Button("SHow All");
        printAllButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                printAllTeamMembers();
            }
        });

        // Search for team member information
        Label searchLabel = new Label("Search for team member information");
        searchButton = new Button("Search team member data");
        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                searchByTeamMember();
            }
        });

        VBox layout = new VBox(20);
        layout.getChildren().addAll(importLabel, importButton, printAllLabel, printAllButton, searchLabel, searchButton);
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setSpacing(20);

        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.show();

    }

    public void importJSON() throws Exception{

        importJSON.addTeamMembers(importJSON.getJsonArray());

    }

    public void printAllTeamMembers() {
        // A new window
        final Stage window = new Stage();
        window.setTitle("Team Member Information");
        window.setMinHeight(600);
        window.setMinWidth(600);

        Label label = new Label("All team members:");
        Text text = new Text();
        text.setText(importJSON.printTeamMemberData());


        // Adds elements to window
        VBox layout = new VBox(20);
        layout.getChildren().addAll(label, text);
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setSpacing(20);

        // Makes a new scene, adds to window
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.show();
    }

    public void searchByTeamMember() {
        
        // A new window
        final Stage window = new Stage();
        window.setTitle("Team Member Information");
        window.setMinHeight(600);
        window.setMinWidth(600);
        
        // Elements of the search window
        Label label = new Label("Search for info by user ID");
        final TextField input = new TextField();
        input.setPromptText("Enter the user ID");
        final Text text = new Text();
        Button searchUid = new Button("Search");
        searchUid.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                // Search
                int id;
                try {
                    id = Integer.parseInt(input.getText());

                    text.setText(importJSON.search(id));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        Button close = new Button("Close window");
        close.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                window.close();
            }
        });

        // Adds elements to window
        VBox layout = new VBox(20);
        layout.getChildren().addAll(label, input, searchUid, text, close);
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setSpacing(20);

        // Makes a new scene, adds to window
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.show();
        



     }

}
