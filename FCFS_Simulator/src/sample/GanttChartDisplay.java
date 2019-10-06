package sample;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.GanttChart.ExtraData;
import static sample.SceneTwoController.processes;

public class GanttChartDisplay  {

    public void init() {
        Stage stage=new Stage();

        stage.setTitle("Gantt Chart Display");

        final NumberAxis xAxis = new NumberAxis();
        final CategoryAxis yAxis = new CategoryAxis();

        final GanttChart<Number,String> chart = new GanttChart<Number,String>(xAxis,yAxis);
        xAxis.setLabel("");
        xAxis.setTickLabelFill(Color.CHOCOLATE);
        xAxis.setMinorTickCount(4);

        chart.setTitle("Gantt Chart");
        chart.setLegendVisible(false);
        chart.setBlockHeight( 150);

        XYChart.Series series = new XYChart.Series();
        for(int i = 0; i < processes.size(); i++) {
            series.getData().add(new XYChart.Data(Double.parseDouble(processes.get(i).getxValue()), "", new ExtraData(Double.parseDouble(processes.get(i).getBurstTime()), "status-"+i)));
        }

        chart.getData().addAll(series);

        chart.getStylesheets().add(getClass().getResource("ganttchart.css").toExternalForm());

        Scene scene  = new Scene(chart,620,350);
        stage.setScene(scene);
        stage.show();
    }


}