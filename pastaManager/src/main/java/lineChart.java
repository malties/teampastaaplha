import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

import java.awt.*;
import java.util.Arrays;



public class lineChart extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));


        primaryStage.setTitle("Pasta");

        primaryStage.setScene(new Scene(root, 300, 275, Color.BLACK));

        //defining x axis of line chart
        NumberAxis xAXis = new NumberAxis(1,4,1);
        xAXis.setLabel("Weeks");

        //defining y axis of chart
        NumberAxis yAxis = new NumberAxis(0,50000,1000);
        xAXis.setLabel("SEK");

        // creating the line chart
        LineChart linechart = new LineChart(xAXis,yAxis);

        //prepping dummy data
        XYChart.Series dummyData = new XYChart.Series();
        dummyData.setName("Earned value ");

        dummyData.getData().add(new XYChart.Data(1, 6000));
        dummyData.getData().add(new XYChart.Data(1.5, 3000));
        dummyData.getData().add(new XYChart.Data(2, 14000));
        dummyData.getData().add(new XYChart.Data(3, 16000));
        dummyData.getData().add(new XYChart.Data(4, 30000));

        //Setting the data to line chart
        linechart.getData().add(dummyData);
        LineChart linechart2 = new LineChart(xAXis,yAxis);

        //prepping dummy data
        XYChart.Series dummyData2 = new XYChart.Series();
        dummyData.setName("Earned value ");

        dummyData.getData().add(new XYChart.Data(1, 6000));
        dummyData.getData().add(new XYChart.Data(1.5, 3000));
        dummyData.getData().add(new XYChart.Data(2, 14000));
        dummyData.getData().add(new XYChart.Data(3, 16000));
        dummyData.getData().add(new XYChart.Data(4, 30000));

        //Setting the data to line chart
        linechart2.getData().add(dummyData);

        Group groot = new Group(linechart);
        //creating scene object
        Scene scene = new Scene(groot,600,400,Color.LIGHTGRAY);
        primaryStage.setTitle("line chart");
        primaryStage.setScene(scene);
        primaryStage.show();




        primaryStage.show();



    }


}

