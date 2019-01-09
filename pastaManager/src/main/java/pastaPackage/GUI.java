package pastaPackage;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
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
    Button printProject;
    Button costVarianceButton;


    public static void main(String[] args) {



        launch(args);
    }

    Stage stage;
    Scene homeScene;

    @Override
    public void start(Stage primaryStage) {

        stage = primaryStage;

        StackPane homeLayout = new StackPane();

        homeScene = new Scene(homeLayout, 1280, 1000);

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
        printAllButton = new Button("Show All Members");
        printAllButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                printAllTeamMembers();
            }
        });

        // Prints project information
        Label printProjectLabel = new Label("View project information");
        printProject = new Button("Show project information");
        printProject.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                printProjectInformation();
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

        // Opens Cost Variance window
        Label cvLabel = new Label("Cost Variance");
        costVarianceButton = new Button("Cost Variance");
        costVarianceButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                showCostVariance();
            }
        });

        VBox layout = new VBox(20);
        layout.getChildren().addAll(importLabel, importButton, printAllLabel, printAllButton,
                printProjectLabel, printProject, searchLabel, searchButton, cvLabel, costVarianceButton);
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setSpacing(20);

        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.show();

    }

    public void importJSON() throws Exception{

        importJSON.addTeamMembers(importJSON.getJsonArray());

    }

    public void showEarnedVariance(){
        // TODO
    }

    public void showScheduceVariance(){
        // TODO
    }

    public void showCostVariance(){

        // A new window
        final Stage window = new Stage();
        window.setTitle("Cost Variance");
        //window.setMinHeight(600);
        //window.setMinWidth(400);

        Label label = new Label("Cost Variance:");

        //Defining the x axis
        NumberAxis xAxis = new NumberAxis (0, 8, 2);
        xAxis.setLabel("Time in weeks");

        //Defining the y axis
        NumberAxis yAxis = new NumberAxis  (100000, 800000, 100000);
        yAxis.setLabel("Cost in SEK");

        //Creating the line chart
        LineChart linechart = new LineChart(xAxis, yAxis);


        //Prepare XYChart.Series objects by setting data
        XYChart.Series series = new XYChart.Series();
        series.setName("EV");


        series.getData().add(new XYChart.Data(0, 200000));
        series.getData().add(new XYChart.Data(2, 300000));
        series.getData().add(new XYChart.Data(4, 400000));
        series.getData().add(new XYChart.Data(6, 400000));
        series.getData().add(new XYChart.Data(8, 500000));


        XYChart.Series series2 = new XYChart.Series();
        series2.setName("AC");

        series2.getData().add(new XYChart.Data(0, 100000));
        series2.getData().add(new XYChart.Data(2, 200000));
        series2.getData().add(new XYChart.Data(4, 300000));
        series2.getData().add(new XYChart.Data(6, 300000));
        series2.getData().add(new XYChart.Data(8, 400000));

        //Setting the data to Line chart
        linechart.getData().addAll(series,series2);


        //Creating a Group object
        Group root = new Group(linechart);

        Scene scene = new Scene(root, 600, 400);
        window.setScene(scene);
        window.show();
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

    public void printProjectInformation(){
        // A new window
        final Stage window = new Stage();
        window.setTitle("Project Information");
        window.setMinHeight(300);
        window.setMinWidth(300);

        Label label = new Label("Project Information:");
        Text text = new Text();
        text.setText(importJSON.printProjectData());

        Label labelWeek = new Label("Current week: ");
        Label labelCompletion = new Label("Enter Project Completion %: ");

        MenuItem week1 = new MenuItem("Week 1");
        MenuItem week2 = new MenuItem("Week 2");

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
