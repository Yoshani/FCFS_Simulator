package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.text.DecimalFormat;

import static sample.SceneTwoController.processes;

public class SceneThreeController {
    @FXML private TableView<Process> tableView;
    @FXML private TableColumn<Process, String> processNameColumn;
    @FXML private TableColumn<Process, String> arrivalTimeColumn;
    @FXML private TableColumn<Process, String> burstTimeColumn;
    @FXML private TableColumn<Process, String> waitTimeColumn;
    @FXML private TableColumn<Process, String> turnaroundTimeColumn;
    @FXML private TableColumn<Process, String> colorColumn;
    @FXML private Label labelwt;
    @FXML private Label labeltat;
    @FXML private double totalWait, totalTat;
    @FXML private Button gantt;
    @FXML private Button backButton;

    private static DecimalFormat df = new DecimalFormat("0.00");

    public void initialize() {

        processNameColumn.setCellValueFactory(new PropertyValueFactory<Process, String>("processName"));
        arrivalTimeColumn.setCellValueFactory(new PropertyValueFactory<Process, String>("arrivalTime"));
        burstTimeColumn.setCellValueFactory(new PropertyValueFactory<Process, String>("burstTime"));
        colorColumn.setCellValueFactory(new PropertyValueFactory<Process, String>("color"));
        waitTimeColumn.setCellValueFactory(new PropertyValueFactory<Process, String>("waitTime"));
        turnaroundTimeColumn.setCellValueFactory(new PropertyValueFactory<Process, String>("turnaroundTime"));

        setTimes();
        tableView.setItems(processes);
        calculateAvg();
        setColorColumn();
    }

    @FXML public void setTimes(){
        double testTime,serviceTime;
        serviceTime=Double.parseDouble(processes.get(0).getArrivalTime());
        for(Process job:processes){
            testTime=serviceTime - Double.parseDouble(job.getArrivalTime());
            if(testTime>0){
                job.setWaitTime(df.format(testTime));
            }
            else{
                job.setWaitTime("0");
            }
            if(job.getWaitTime().equals("0")){
                job.setxValue(job.getArrivalTime());
            }else {
                job.setxValue(df.format(serviceTime));
            }

            job.setTurnaroundTime(df.format(Double.parseDouble(job.getWaitTime())+Double.parseDouble(job.getBurstTime())));
            totalWait += Double.parseDouble(job.getWaitTime());
            totalTat += Double.parseDouble(job.getTurnaroundTime());

            if(testTime>0){
                serviceTime += Double.parseDouble(job.getBurstTime());
            }else {
                serviceTime += (Double.parseDouble(job.getBurstTime()) - testTime);
            }
        }

       /* processes.get(0).setWaitTime("0");
        for (int i = 1; i < processes.size(); i++) {
            processes.get(i).setWaitTime(df.format(Double.parseDouble(processes.get(i - 1).getBurstTime()) + Double.parseDouble(processes.get(i - 1).getWaitTime())));
            totalWait += Double.parseDouble(processes.get(i).getWaitTime());
        }
        for (int i = 0; i < processes.size(); i++) {
            processes.get(i).setTurnaroundTime(df.format(Double.parseDouble(processes.get(i).getBurstTime()) + Double.parseDouble(processes.get(i).getWaitTime())));
            totalTat += Double.parseDouble(processes.get(i).getTurnaroundTime());
        }*/
    }
    @FXML public void setColorColumn(){
        colorColumn.setCellFactory(column -> {
            return new TableCell<Process, String>() {
                int color = 0;
                String colors[] = new String[]{"deeppink","chartreuse","red","yellow","cyan","darkviolet","chocolate","salmon","mediumspringgreen","blue","hotpink","deepskyblue","khaki","fuchsia","black","orange","forestgreen","cornflowerblue","crimson","midnightblue","mediumvioletred"};
                @Override
                protected void updateItem(String item, boolean empty) {

                    super.updateItem(item, empty);
                    if (item == null || empty) {
                        setText(null);
                        setStyle("");
                    } else {
                        if(getIndex() > -1){
                            String color = colors[getIndex() % 20];
                            setStyle("-fx-background-color: "+ color + ";");
                        }
                    }
                }
            };
        });
    }
    @FXML public void calculateAvg(){
        labelwt.setText(df.format(totalWait/processes.size()));
        labeltat.setText(df.format(totalTat/processes.size()));
    }
    @FXML public void ganttButtonPressed(){
        GanttChartDisplay gcd= new GanttChartDisplay();
        gcd.init();
    }
    @FXML public void backButtonPushed(ActionEvent event) throws IOException
    {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("SceneTwo.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();

    }

}
