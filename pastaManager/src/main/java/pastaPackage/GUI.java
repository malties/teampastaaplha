package pastaPackage;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLOutput;
import com.sun.java.swing.*;
import javafx.stage.WindowEvent;

import java.util.ArrayList;
import java.util.Optional;

public class GUI extends Application {

    private ImportJSON importMemeberJSON = new ImportJSON();
    private ImportProjectJSON importProjectJSON = new ImportProjectJSON();
    private Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
    private Image matrix;

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
        importButton.setOnAction(event -> showImportWindow());

        // Prints all team members' information
        Label printAllLabel = new Label("View all team member's data");
        Button printAllButton = new Button("Show All Members");
        printAllButton.setOnAction(event -> {
            if(importMemeberJSON.getJsonFile() == null){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(null);
                alert.setHeaderText(null);
                alert.setContentText("Data missing!\nPlease import data first.");
                alert.showAndWait();
            }else{
                printAllTeamMembers();
            }
        });

        // Prints project information
        Label printProjectLabel = new Label("View project information");
        Button printProject = new Button("Show project information");
        printProject.setOnAction(event -> {
            if(importProjectJSON.getJsonFile() == null || importMemeberJSON.getJsonFile() == null){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(null);
                alert.setHeaderText(null);
                alert.setContentText("Data missing!\nPlease import data first.");
                alert.showAndWait();
            }else{
                showProjectInformation();
            }
        });

        // Search for team member information
        Label searchLabel = new Label("Search for team member information");
        Button searchButton = new Button("Search team member data");
        searchButton.setOnAction(event -> searchByTeamMember());

        // Opens Risk Matrix window
        Label rmLabel = new Label("Risk Matrix");
        Button riskMatrixButton = new Button("Risk Matrix");
        riskMatrixButton.setOnAction(event -> {
            if(matrix == null){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(null);
                alert.setHeaderText(null);
                alert.setContentText("Risk matrix missing!\nPlease import risk matrix first.");
                alert.showAndWait();
            }else{
                showRiskMatrix(matrix);
            }
        });

        // Opens Cost Variance window
        Label cvLabel = new Label("Cost Variance");
        Button costVarianceButton = new Button("Cost Variance");
        costVarianceButton.setOnAction(event -> {
            if(importProjectJSON.getJsonFile() == null || importMemeberJSON.getJsonFile() == null){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(null);
                alert.setHeaderText(null);
                alert.setContentText("Data missing!\nPlease import data first.");
                alert.showAndWait();
            }else{
                try {
                    showCostVariance();
                } catch(Exception e){
                    e.printStackTrace();
                    e.getStackTrace();
                }
            }
        });

        // Opens Schedule Variance window
        Label svLabel = new Label("Schedule Variance");
        Button scheduleVarianceButton = new Button("Schedule Variance");
        scheduleVarianceButton.setOnAction(event -> {
            if(importProjectJSON.getJsonFile() == null || importMemeberJSON.getJsonFile() == null){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(null);
                alert.setHeaderText(null);
                alert.setContentText("Data missing!\nPlease import data first.");
                alert.showAndWait();
            }else{
                try {
                    showScheduleVariance();
                } catch(Exception e){
                    e.printStackTrace();
                    e.getStackTrace();
                }
            }
        });

        // Opens Earned Value window
        Label evLabel = new Label("Earned Value");
        Button earnedValueButton = new Button("Earned Value");
        earnedValueButton.setOnAction(event -> {
            if(importProjectJSON.getJsonFile() == null || importMemeberJSON.getJsonFile() == null){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(null);
                alert.setHeaderText(null);
                alert.setContentText("Data missing!\nPlease import data first.");
                alert.showAndWait();
            }else{
                try {
                    showEarnedValue();
                } catch(Exception e){
                    e.printStackTrace();
                    e.getStackTrace();
                }
            }
        });

        // Opens Project analysis Window
        VBox layout = new VBox(20);
        layout.getChildren().addAll(importLabel, importButton, printAllLabel, printAllButton,
                printProjectLabel, printProject, searchLabel, searchButton, rmLabel, riskMatrixButton,evLabel, earnedValueButton, svLabel,
                scheduleVarianceButton, cvLabel, costVarianceButton);
        layout.setAlignment(Pos.BASELINE_CENTER);
        layout.setSpacing(6);

        Scene scene = new Scene(layout);

        scene.getStylesheets().add("designColors.css");

        stage.setScene(scene);

        //setting the size of the home screen
        primaryStage.setMinHeight(500);
        primaryStage.setMinWidth(300);
        //centering the application
        primaryStage.centerOnScreen();
        stage.show();

    }

    private void showImportWindow(){
        // A new window
        final Stage window = new Stage();
        window.setTitle("Import Files");
        window.setMinHeight(300);
        window.setMinWidth(300);

        Label memberJsonLabel = new Label("Import JSON with member data: ");
        Button memberJsonButton = new Button("Select file");

        Label projectJsonLabel = new Label("Import JSON with project data:");
        Button projectJsonButton = new Button("Select a file");

        Label matrixLabel = new Label("Import risk matrix:");
        Button matrixButton = new Button("Select an image");

        final FileChooser jsonChooser = new FileChooser();
        jsonChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JSON", "*.json"));

        final FileChooser matrixChooser = new FileChooser();
        matrixChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("JPEG", "*.jpeg"),
                new FileChooser.ExtensionFilter("BMP", "*.bmp")
        );

        memberJsonButton.setOnAction(event -> {

            File file = jsonChooser.showOpenDialog(window);
            if(file != null){
                openMemberFile(file);
            }

        });

        projectJsonButton.setOnAction(event -> {

            File file = jsonChooser.showOpenDialog(window);
            if(file != null){
                openProjectFile(file);
            }

        });

        matrixButton.setOnAction(event -> {

            File file = matrixChooser.showOpenDialog(window);
            if(file != null){
                openMatrixFile(file);
            }

        });

        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(memberJsonLabel);
        layout.getChildren().addAll(memberJsonButton);
        layout.getChildren().addAll(projectJsonLabel);
        layout.getChildren().addAll(projectJsonButton);
        layout.getChildren().addAll(matrixLabel);
        layout.getChildren().addAll(matrixButton);

        Scene scene = new Scene(layout);

        window.setScene(scene);
        window.show();

        window.setOnCloseRequest(event -> {
            if(this.importMemeberJSON.getJsonFile() == null){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "", ButtonType.YES, ButtonType.CANCEL);
                alert.setTitle("Confirmation Dialog");
                alert.setHeaderText(null);
                alert.setContentText("You have not imported any team member data!\nAre you sure that you want to exit?");

                Optional<ButtonType> result = alert.showAndWait();
                if(result.get() == ButtonType.YES){
                    window.close();
                }else{
                    event.consume();
                }
            }else if(this.importProjectJSON.getJsonFile() == null){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "", ButtonType.YES, ButtonType.CANCEL);
                alert.setTitle("Confirmation Dialog");
                alert.setHeaderText(null);
                alert.setContentText("You have not imported any project data!\nAre you sure that you want to exit?");

                Optional<ButtonType> result = alert.showAndWait();
                if(result.get() == ButtonType.YES){
                    window.close();
                }else{
                    event.consume();
                }
            }else if(this.matrix == null){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "", ButtonType.YES, ButtonType.CANCEL);
                alert.setTitle("Confirmation Dialog");
                alert.setHeaderText(null);
                alert.setContentText("You have not imported a risk matrix!\nAre you sure that you want to exit?");

                Optional<ButtonType> result = alert.showAndWait();
                if(result.get() == ButtonType.YES){
                    window.close();
                }else{
                    event.consume();
                }
            }

        });

    }

    private void openMatrixFile(File file){
        this.matrix = new Image(file.toURI().toString());
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

    private void showRiskMatrix(Image image){

        // A new window
        final Stage window = new Stage();
        window.setTitle("Risk Matrix");

        ImageView iv = new ImageView();
        Image riskMatix = image;
        iv.setImage(riskMatix);

        VBox layout = new VBox();
        layout.getChildren().add(iv);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.show();
    }

    public void showEarnedValue()throws Exception{
        showGraph("EV");
    }

    public void showScheduleVariance() throws Exception{
        showGraph("SV");
    }

    public void showCostVariance()throws Exception{
        showGraph("CV");
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

    public void showProjectInformation(){

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
        searchUid.setOnAction(event -> {

            // Search
            int id;
            try {
                id = Integer.parseInt(input.getText());

                text.setText(importMemeberJSON.search(id));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        Button close = new Button("Close window");
        close.setOnAction(event -> window.close());

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

    private void showGraph(String graphName) throws Exception {
        // A new window
        final Stage window = new Stage();
        window.setTitle("Schedule Variance");

        Variance var = new Variance(importMemeberJSON.getJsonFile(), importProjectJSON.getJsonFile());
        //System.out.println(var.plannedSpent(8));
        //System.out.println(var.actualSpent(8));
        double weeks = var.getWeeks();
        double maxYMargin = 1.2;
        double maxY = var.getEV(weeks) * maxYMargin;
        Label label;
        String graphTitle;
        if(graphName.equals("EV")) {
            label = new Label("Earned Value");
            graphTitle = "Earned Value";
        } else if (graphName.equals("CV")){
            label = new Label("Cost Variance");
            graphTitle = "Cost Variance";
        } else if (graphName.equals("SV")) {
            label = new Label("Schedule Variance");
            graphTitle = "Schedule Variance";
        } else {
            throw new Exception("graph name not matching");
        }

        //Defining the x axis
        NumberAxis xAxis = new NumberAxis (0, weeks, 2);
        xAxis.setLabel("Time in weeks");


        //Defining the y axis
        NumberAxis yAxis = new NumberAxis  (0, maxY, maxY/100);
        yAxis.setLabel("Cost in SEK");

        //Creating the line chart
        LineChart linechart = new LineChart(xAxis, yAxis);

        linechart.setPrefSize(primScreenBounds.getWidth() - 100, primScreenBounds.getHeight() - 100);

        XYChart.Series EV = new XYChart.Series();
        EV.setName("EV");

        XYChart.Series mainFunction = new XYChart.Series();
        mainFunction.setName(graphName);

        XYChart.Series delta = new XYChart.Series();
        delta.setName(graphTitle);

        if (graphName.equals("EV")){
            for (int i = -1 ; i<var.getWeeks();i=i+2){
                EV.getData().add(new XYChart.Data(i+1,var.calculateEV(i+1)));
            }
            linechart.getData().addAll(EV);
        } else if (graphName.equals("CV")){
            int XInterval = 0;
            ArrayList<Double> CVValues = var.getCV();
            for (int i = 0; i < CVValues.size(); i= i+0){
                mainFunction.getData().add(new XYChart.Data(XInterval, CVValues.get(i)));

                i ++;
                EV.getData().add(new XYChart.Data(XInterval, CVValues.get(i)));

                i ++;

                if ( i == CVValues.size()-2) {
                    delta.getData().add(new XYChart.Data(XInterval, CVValues.get(i-1)));
                    delta.getData().add(new XYChart.Data(XInterval, CVValues.get(i -2)));
                }

                XInterval += 2;

            }

            linechart.getData().addAll(mainFunction, EV, delta);
            delta.setName( graphTitle + " = " + ((CVValues.get(CVValues.size()-3)) - (CVValues.get(CVValues.size()-4))) + " Kr" );
        }

        else if (graphName.equals("SV")){
            int XInterval = 0;
            ArrayList<Double> SVValues = var.getSV();
            for (int i = 0; i < SVValues.size(); i= i+0){
                mainFunction.getData().add(new XYChart.Data(XInterval, SVValues.get(i)));

                i ++;
                EV.getData().add(new XYChart.Data(XInterval, SVValues.get(i)));

                i ++;

                if ( i == SVValues.size()-2) {
                    delta.getData().add(new XYChart.Data(XInterval, SVValues.get(i-1)));
                    delta.getData().add(new XYChart.Data(XInterval, SVValues.get(i -2)));
                }
                XInterval += 2;

            }
            delta.setName( graphTitle + " = " + ((SVValues.get(SVValues.size()-3)) - (SVValues.get(SVValues.size()-4))) + " Kr");
            linechart.getData().addAll(mainFunction,EV, delta);
        } else {
            throw new Exception("graphName is not matching");
        } Group root = new Group(linechart);

        Scene scene = new Scene(root,primScreenBounds.getWidth() - 100, primScreenBounds.getHeight() - 100);
        window.setScene(scene);
        window.show();
    }

}
