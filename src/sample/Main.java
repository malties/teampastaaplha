package sample;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
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

        series2.getData().add(new XYChart.Data(0, 200000));
        series2.getData().add(new XYChart.Data(2, 350000));
        series2.getData().add(new XYChart.Data(4, 400000));
        series2.getData().add(new XYChart.Data(6, 500000));
        series2.getData().add(new XYChart.Data(8, 660000));

        //Setting the data to Line chart
        linechart.getData().addAll(series,series2);


        //Creating a Group object
        Group root = new Group(linechart);


        //Creating a scene object
        Scene scene = new Scene(root, 600, 400);

        //Setting title to the Stage
        stage.setTitle("Cost Variance");

        //Adding scene to the stage
        stage.setScene(scene);



        //Displaying the contents of the stage
        stage.show();
    }
    public static void main(String args[]){
        launch(args);
    }
}