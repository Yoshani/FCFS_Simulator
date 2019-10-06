package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;

import static sample.SceneOneController.processNo;

public class SceneTwoController  {
    @FXML private TableView<Process> tableView;
    @FXML private TableColumn<Process, String> processNameColumn;
    @FXML private TableColumn<Process, String> arrivalTimeColumn;
    @FXML private TableColumn<Process, String> burstTimeColumn;
    @FXML private TextField processNameTextField;
    @FXML private TextField arrivalTimeTextField;
    @FXML private TextField burstTimeTextField;
    @FXML private Button addButton;
    @FXML private Button nextButton;
    @FXML private Button backButton;
    @FXML private Button deleteButton;
    @FXML protected static ObservableList<Process> processes;


    @FXML public void nextButtonPushed(ActionEvent event) throws IOException
    {
        if(processes.size()<processNo){
            AlertBox.display("Alert", "Enter "+processNo+" processes");
        }
        else {
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("SceneThree.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(tableViewScene);
            window.show();
        }

    }
    @FXML public void backButtonPushed(ActionEvent event) throws IOException
    {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("SceneOne.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();

    }

    @FXML public void addButtonPushed(ActionEvent event) throws IOException {
        boolean valid = true;
        try {
            Double.parseDouble(burstTimeTextField.getText());
            Double.parseDouble(arrivalTimeTextField.getText());
        } catch (NumberFormatException e) {
            valid = false;
        }
        if(processes.size()<processNo) {
            if (valid) {
                if(processes.size()==0 || Double.parseDouble(arrivalTimeTextField.getText())>=Double.parseDouble(processes.get(processes.size()-1).getArrivalTime())) {
                    Process newProcess = new Process(processNameTextField.getText(), arrivalTimeTextField.getText(), burstTimeTextField.getText(), "", "", "", "");
                    tableView.getItems().add(newProcess);
                    burstTimeTextField.clear();
                    arrivalTimeTextField.clear();
                    processNameTextField.clear();
                }else{
                    AlertBox.display("Alert", "Enter processes in order of arrival");
                }
            } else {
                AlertBox.display("Alert", "Enter valid times");
                burstTimeTextField.clear();
                arrivalTimeTextField.clear();
            }
        }else{
            AlertBox.display("Alert", "Maximum number of processes reached");
            burstTimeTextField.clear();
            arrivalTimeTextField.clear();
            processNameTextField.clear();
        }
    }
    @FXML public void deleteButtonPushed(){
            Process selectedItem = tableView.getSelectionModel().getSelectedItem();
            tableView.getItems().remove(selectedItem);
    }

    @FXML public ObservableList<Process> getProcesses(){
        processes = FXCollections.observableArrayList();
        return processes;
    }


    @FXML public void initialize() {

        processNameColumn.setCellValueFactory(new PropertyValueFactory<Process, String>("processName"));
        arrivalTimeColumn.setCellValueFactory(new PropertyValueFactory<Process, String>("arrivalTime"));
        burstTimeColumn.setCellValueFactory(new PropertyValueFactory<Process, String>("burstTime"));

        tableView.setItems(getProcesses());
        tableView.setEditable(true);

        processNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        arrivalTimeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        burstTimeColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);


    }


}
