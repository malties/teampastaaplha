package pastaPackage;

import com.sun.java.swing.action.AlignCenterAction;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;

public class GUI extends Application {

    public ImportJSON importMemeberJSON = new ImportJSON();
    public ImportProjectJSON importProjectJSON = new ImportProjectJSON();

    public static void main(String[] args) {




        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        Stage stage = primaryStage;

        StackPane homeLayout = new StackPane();

        Scene homeScene = new Scene(homeLayout, 1280, 1000);

        stage.setScene(homeScene);
        stage.setTitle("Pasta Manager");
        stage.show();

        // Imports JSON file
        Label importLabel = new Label("Import JSON file");
        Button importButton = new Button("Import");
        importButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                showImportWindow();

                /*
                try {
                    importJSON();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                */

            }
        });

        // Prints all team members' information
        Label printAllLabel = new Label("View all team member's data");
        Button printAllButton = new Button("Show All Members");
        printAllButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                printAllTeamMembers();
            }
        });

        // Prints project information
        Label printProjectLabel = new Label("View project information");
        Button printProject = new Button("Show project information");
        printProject.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                showProjectInformation();
            }
        });

        // Search for team member information
        Label searchLabel = new Label("Search for team member information");
        Button searchButton = new Button("Search team member data");
        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                searchByTeamMember();
            }
        });

        // Opens Risk Matrix window
        Label rmLabel = new Label("Risk Matrix");
        Button riskMatrixButton = new Button("Risk Matrix");
        riskMatrixButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                showRiskMatrix();
            }
        });

        // Opens Cost Variance window
        Label cvLabel = new Label("Cost Variance");
        Button costVarianceButton = new Button("Cost Variance");
        costVarianceButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                showCostVariance();
                } catch(Exception e){
                    e.getStackTrace();
                }
            }
        });

        // Opens Schedule Variance window
        Label svLabel = new Label("Schedule Variance");
        Button scheduleVarianceButton = new Button("Schedule Variance");
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
        Button earnedValueButton = new Button("Earned Value");
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

    /*
    private void importJSON() throws Exception{

        importMemeberJSON.addTeamMembers(importMemeberJSON.getJsonArray());
        importProjectJSON.addProjectInformation(importProjectJSON.getJsonPData());

    }
    */

    private void showRiskMatrix(){
        final Stage window = new Stage();
        window.setTitle("Risk Matrix");


        ImageView iv = new ImageView();
        Image riskMatix = new Image("riskMatrix.PNG");
        iv.setImage(riskMatix);

        VBox layout = new VBox();
        layout.getChildren().add(iv);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.show();
    }

    private void showImportWindow(){
        // A new window
        final Stage window = new Stage();
        window.setTitle("Import Files");
        window.setMinHeight(300);
        window.setMinWidth(300);

        Label memberJsonLabel = new Label("Import JSON with member data: ");
        Button memberJsonButton = new Button("Select file");

        Label projectJsonLabel = new Label("Import JSON with project data: ");
        Button projectJsonButton = new Button("Select file");

        final FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JSON", "*.json"));

        memberJsonButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                File file = chooser.showOpenDialog(window);
                if(file != null){
                    openMemberFile(file);
                }

            }
        });

        projectJsonButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                File file = chooser.showOpenDialog(window);
                if(file != null){
                    openProjectFile(file);
                }

            }
        });

        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(memberJsonLabel);
        layout.getChildren().addAll(memberJsonButton);
        layout.getChildren().addAll(projectJsonLabel);
        layout.getChildren().addAll(projectJsonButton);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.show();
    }

    private void openMemberFile(File file){
        importMemeberJSON.setJsonFile(file);
        try{
            importMemeberJSON.addTeamMembers(importMemeberJSON.getJsonArray());
        }catch (Exception e){

        }
    }

    private void openProjectFile(File file){
        importProjectJSON.setJsonFile(file);
        try{
            importProjectJSON.addProjectInformation(importProjectJSON.getJsonPData());
        }catch(Exception e){

        }
    }

    private void showEarnedValue(){
        // TODO
    }

    private void showScheduleVariance() throws Exception{
        // A new window
        final Stage window = new Stage();
        window.setTitle("Schedule Variance");

        Variance var = new Variance(importMemeberJSON.getJsonFile(), importProjectJSON.getJsonFile());
        double weeks = var.getWeeks();
        double maxYMargin = 1.2;
        double maxY = var.getEV(weeks) * maxYMargin;
        Label label = new Label("Schedule Variance:");

        //Defining the x axis
        NumberAxis xAxis = new NumberAxis (0, weeks, 2);
        xAxis.setLabel("Time in weeks");


        //Defining the y axis
        NumberAxis yAxis = new NumberAxis  (0, maxY, 10000);
        yAxis.setLabel("Cost in SEK");

        //Creating the line chart
        LineChart linechart = new LineChart(xAxis, yAxis);


        //Prepare XYChart.Series objects by setting data
        XYChart.Series series = new XYChart.Series();
        series.setName("PV");

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("EV");

        int XInterval = 0;
        ArrayList<Double> SVValues = var.getSV();
        for (int i = 0; i < SVValues.size(); i= i+0){
            series.getData().add(new XYChart.Data(XInterval, SVValues.get(i)));

            i ++;
            series1.getData().add(new XYChart.Data(XInterval, SVValues.get(i)));

            i ++;
            XInterval += 2;

        }

        linechart.getData().addAll(series,series1);

        //Creating a Group object
        Group root = new Group(linechart);

        //window.setMinHeight(600);
        //window.setMinWidth(400);
        Scene scene = new Scene(root, 600, 400);
        window.setScene(scene);
        window.show();
    }

    private void showCostVariance()throws Exception{
        // A new window
        final Stage window = new Stage();
        window.setTitle("Cost Variance");


        Variance var = new Variance(importMemeberJSON.getJsonFile(), importProjectJSON.getJsonFile());
        double weeks = var.getWeeks();
        double maxYMargin = 1.2;
        double maxY = var.getEV(weeks) * maxYMargin;
        Label label = new Label("Cost Variance:");

        //Defining the x axis
        NumberAxis xAxis = new NumberAxis (0, weeks, 2);
        xAxis.setLabel("Time in weeks");


        //Defining the y axis
        NumberAxis yAxis = new NumberAxis  (0, maxY, 10000);
        yAxis.setLabel("Cost in SEK");

        //Creating the line chart
        LineChart linechart = new LineChart(xAxis, yAxis);


        //Prepare XYChart.Series objects by setting data
        XYChart.Series series2 = new XYChart.Series();
        series2.setName("AC");

        XYChart.Series series3 = new XYChart.Series();
        series3.setName("EV");

        int XInterval = 0;
        ArrayList<Double> CVValues = var.getCV();
        for (int i = 0; i < CVValues.size(); i= i+0){
            series2.getData().add(new XYChart.Data(XInterval, CVValues.get(i)));

            i ++;
            series3.getData().add(new XYChart.Data(XInterval, CVValues.get(i)));

            i ++;
            XInterval += 2;

        }

        linechart.getData().addAll(series2,series3);

        //Creating a Group object
        Group root = new Group(linechart);

        //window.setMinHeight(600);
        //window.setMinWidth(400);
        Scene scene = new Scene(root, 600, 400);
        window.setScene(scene);
        window.show();
    }

    private void printAllTeamMembers() {
        // A new window
        final Stage window = new Stage();
        window.setTitle("Team Member Information");
        window.setMinHeight(600);
        window.setMinWidth(600);

        Label label = new Label("All team members:");
        Text text = new Text();
        text.setText(importMemeberJSON.printTeamMemberData());


        // Add elements to window
        VBox layout = new VBox(20);
        layout.getChildren().addAll(label, text);
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setSpacing(20);

        // Makes a new scene, adds to window
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.show();
    }

    private void showProjectInformation(){
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

    private void searchByTeamMember() {
        
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

    public ImportJSON getImportMemeberJSON() {
        return importMemeberJSON;
    }

    public ImportProjectJSON getImportProjectJSON() {
        return importProjectJSON;
    }
}
