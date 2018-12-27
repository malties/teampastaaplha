package sample;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class costVarience extends Application {
    @Override
    public void start(Stage stage) throws Exception{
        //Defining the x axis
        NumberAxis xAxis = new NumberAxis(10000, 20000, 1000);
        xAxis.setLabel("cost");

        //Defining the y axis
        NumberAxis yAxis = new NumberAxis   (0, 8, 2);
        yAxis.setLabel("time");

        //Creating the line chart
        LineChart linechart = new LineChart(xAxis, yAxis);

        //Prepare XYChart.Series objects by setting data
        XYChart.Series series = new XYChart.Series();
        series.setName("Cost variance   ");

        series.getData().add(new XYChart.Data(10000, 1));
        series.getData().add(new XYChart.Data(11000, 2));
        series.getData().add(new XYChart.Data(13000, 3));
        series.getData().add(new XYChart.Data(17000, 4));
        series.getData().add(new XYChart.Data(19000, 5));
        series.getData().add(new XYChart.Data(20000, 6));

        //Setting the data to Line chart
        linechart.getData().add(series);

        //Creating a Group object
        Group root = new Group(linechart);

        //Creating a scene object
        Scene scene = new Scene(root, 600, 400);

        //Setting title to the Stage
        stage.setTitle("Line Chart");

        //Adding scene to the stage
        stage.setScene(scene);

        //Displaying the contents of the stage
        stage.show();
    }
    public static void main(String args[]){
        launch(args);
    }
}