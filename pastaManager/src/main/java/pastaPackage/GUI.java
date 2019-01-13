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
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;

public class GUI extends Application {

    ImportJSON importMemeberJSON = new ImportJSON();
    ImportProjectJSON importProjectJSON = new ImportProjectJSON();
    Button searchButton;
    Button importButton;
    Button printAllButton;
    Button printProject;
    Button earnedValueButton;
    Button scheduleVarianceButton;
    Button costVarianceButton;
    Button riskMatrixButton;

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

        // Opens Risk Matrix window
        Label rmLabel = new Label("Risk Matrix");
        riskMatrixButton = new Button("Risk Matrix");
        riskMatrixButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                showRiskMatrix();
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

        // Opens Schedule Variance window
        Label svLabel = new Label("Schedule Variance");
        scheduleVarianceButton = new Button("Schedule Variance");
        scheduleVarianceButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {
                    showScheduleVariance();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        // Opens Earned Value window
        Label evLabel = new Label("Earned Value");
        earnedValueButton = new Button("Earned Value");
        earnedValueButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                showEarnedValue();
            }
        });

        VBox layout = new VBox(20);
        layout.getChildren().addAll(importLabel, importButton, printAllLabel, printAllButton,
                printProjectLabel, printProject, searchLabel, searchButton, rmLabel, riskMatrixButton,
                evLabel, earnedValueButton, svLabel, scheduleVarianceButton, cvLabel, costVarianceButton);
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setSpacing(20);

        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.show();

    }

    public void importJSON() throws Exception{

        importMemeberJSON.addTeamMembers(importMemeberJSON.getJsonArray());
        importProjectJSON.addProjectInformation(importProjectJSON.getJsonPData());

    }

    public void showRiskMatrix(){
        // TODO
    }

    public void showEarnedValue(){
        // TODO
    }

    public void showScheduleVariance() throws Exception{
        // A new window
        final Stage window = new Stage();
        window.setTitle("Schedule Variance");
        //window.setMinHeight(600);
        //window.setMinWidth(400);

        Variance var = new Variance();
        double weeks = var.getWeeks();
        double maxYMargin = 1.2;
        double maxY = var.getEV(weeks) * maxYMargin;
        Label label = new Label("Schedule Variance:");

        //Defining the x axis
        NumberAxis xAxis = new NumberAxis (0, weeks, 2);
        xAxis.setLabel("Time in weeks");


        //Defining the y axis
        NumberAxis yAxis = new NumberAxis  (0, maxY, 100000);
        yAxis.setLabel("Cost in SEK");

        //Creating the line chart
        LineChart linechart = new LineChart(xAxis, yAxis);


        //Prepare XYChart.Series objects by setting data
        XYChart.Series series = new XYChart.Series();
        series.setName("PV");

        XYChart.Series series2 = new XYChart.Series();
        series2.setName("EV");

        int XInterval = 0;
        ArrayList<Double> SVValues = var.getSV();
        for (int i = 0; i < SVValues.size(); i= i+0){
            series.getData().add(new XYChart.Data(XInterval, SVValues.get(i)));
            System.out.println(SVValues.get(i));
            i ++;
            series2.getData().add(new XYChart.Data(XInterval, SVValues.get(i)));
            System.out.println(SVValues.get(i));
            i ++;
            XInterval += 2;

        }

        linechart.getData().addAll(series,series2);

        //Creating a Group object
        Group root = new Group(linechart);

        Scene scene = new Scene(root, 600, 400);
        window.setScene(scene);
        window.show();
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
        text.setText(importMemeberJSON.printTeamMemberData());


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
        text.setText(importProjectJSON.getProjectData() + importMemeberJSON.printProjectData());

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

                    text.setText(importMemeberJSON.search(id));
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
