package com.gksoftware.processmanagement;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import com.gksoftware.processmanagement.model.Process;
import com.gksoftware.processmanagement.model.ProcessComplete;
import com.gksoftware.processmanagement.model.ProcessEntity;
import com.gksoftware.processmanagement.model.ProcessTable;
import com.gksoftware.processmanagement.model.TableDeleteProcess;
import com.gksoftware.processmanagement.queues.*;
import com.gksoftware.processmanagement.services.CallRestService;
import com.gksoftware.processmanagement.threads.ExecuteProcessServiceFacade;
import com.gksoftware.processmanagement.threads.LoadProcessServiceFacade;
import com.gksoftware.processmanagement.threads.ThreadProcess;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXProgressBar;
import com.jfoenix.controls.JFXSnackbar;
import java.util.ArrayList;
import java.util.List;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.geometry.Insets;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import org.springframework.stereotype.Component;

@Component
public class FXMLController implements Initializable {

    private Process process;
    private Alert alert;
    private PriorityQueue priorityQueue = new PriorityQueue();
    private Queue queue = new Queue();
    private LoadProcessServiceFacade ptService;
    private Queue<ThreadProcess> readyProcess = new Queue<>();
    private ExecuteProcessServiceFacade epsf;
    private List<Process> wsProcess = new ArrayList<>();
    private CallRestService rest = new CallRestService();

    @FXML
    private AnchorPane homeAnchor;

    @FXML
    private Label procAvailable;

    @FXML
    private Label processEliminated;

    @FXML
    private JFXTextField thinput;

    @FXML
    private JFXCheckBox checkPriority;

    @FXML
    private JFXProgressBar progressProcessBar;

    @FXML
    private Button btnStart;

    @FXML
    private Button btnStop;

    @FXML
    private JFXListView<HBox> listNewProcess;
    private JFXPopup popupList = new JFXPopup();

    @FXML
    private Label progressStatus;
    @FXML
    private Label currentPid;
    @FXML
    private Label currentNameProcess;

    /**
     * Components for table process in the view
     */
    @FXML
    private TableView<ProcessTable> tableProcess;
    @FXML
    private TableColumn<ProcessTable, String> pidCol;
    @FXML
    private TableColumn<ProcessTable, String> processCol;
    @FXML
    private TableColumn<ProcessTable, String> burstCol;
    @FXML
    private TableColumn<ProcessTable, String> priorityCol;
    @FXML
    private TableColumn<ProcessTable, String> burstResidueCol;
    @FXML
    private TableColumn<ProcessTable, String> charactersCol;
    @FXML
    private TableColumn<ProcessTable, String> stateProcessCol;
    @FXML
    private TableColumn<ProcessTable, String> replacedCol;
    private ObservableList<ProcessTable> observerTableProcess;

    @FXML
    private Button btnContinue;
    @FXML
    private Label lblExecute;
    @FXML
    private Button btnAvailable;
    @FXML
    private Button btnExecuted;
    @FXML
    private Button btnDelete;
    @FXML
    private AnchorPane backgroundAnchor;
    @FXML
    private JFXTextField processInputDel;
    @FXML
    private JFXTextField charactersInputDel;
    @FXML
    private JFXTextField processInputDel11;
    @FXML
    private JFXTextField priorityInputDel;
    @FXML
    private JFXButton btnRestore;
    @FXML
    private TableColumn<TableDeleteProcess, String> pidDelCol;
    @FXML
    private TableColumn<TableDeleteProcess, String> processDelCol;
    @FXML
    private TableColumn<TableDeleteProcess, String> burstDelCol;
    @FXML
    private TableColumn<TableDeleteProcess, String> priorityDelCol;
    @FXML
    private TableColumn<TableDeleteProcess, String> charactersDelCol;
    @FXML
    private TableColumn<TableDeleteProcess, String> replacedDelCol;
    @FXML
    private TableColumn<TableDeleteProcess, String> stateDelCol;
    @FXML
    private TableColumn<TableDeleteProcess, String> datetimeDelCol;
    @FXML
    private AnchorPane deleteAnchor;
    @FXML
    private TableView<TableDeleteProcess> tableDelete;
    private ObservableList<TableDeleteProcess> observerDeleteTable;
    @FXML
    private Label generalQuantum;
    @FXML
    private AnchorPane anchorExecuted;
    @FXML
    private JFXTextField totaltimeExecution;
    @FXML
    private TableView<ProcessComplete> tableExecuted;
    @FXML
    private TableColumn<ProcessComplete, String> pidFinished;
    @FXML
    private TableColumn<ProcessComplete, String> processFinished;
    @FXML
    private TableColumn<ProcessComplete, String> burstFinished;
    @FXML
    private TableColumn<ProcessComplete, String> priorityFinished;
    @FXML
    private TableColumn<ProcessComplete, String> timeArrivalFinished;
    @FXML
    private TableColumn<ProcessComplete, String> turnaroundFinished;
    @FXML
    private TableColumn<ProcessComplete, String> endtimeFinished;
    private ObservableList<ProcessComplete> observableComplete;
    @FXML
    private Label lblAvailableCharts;
    @FXML
    private AnchorPane anchorCharts;
    @FXML
    private LineChart<?, ?> chartProcess;
    @FXML
    private NumberAxis y;
    @FXML
    private CategoryAxis x;
    @FXML
    private JFXComboBox<String> processList;

    @FXML
    private void handleActionEvent(ActionEvent event) {
        if (event.getSource() == btnAvailable) {
            homeAnchor.toFront();
        } else if (event.getSource() == btnExecuted) {
            anchorExecuted.toFront();
        } else if (event.getSource() == btnDelete) {
            deleteAnchor.toFront();
        }
    }

    @FXML
    private void showPopup(MouseEvent event) {
        int selected = listNewProcess.getSelectionModel().getSelectedIndex();
        if (event.getSource() != MouseButton.PRIMARY) {
            if (selected > -1) {
                popupList.show(listNewProcess, JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.LEFT, event.getX(), event.getY());
                System.out.println(listNewProcess);
            }
        }
        listNewProcess.getSelectionModel().isSelected(-1);
    }

    @FXML
    private void createNewProcess(ActionEvent event) {
        try {
            rest.callrestService(processList.getSelectionModel().getSelectedIndex()+1);
            rest.getProcessList().getProcessList().stream().map((entity) -> {
                process = Common.convertToProcess(entity.getPid(), entity.getName(), entity.getPriority(), entity.getCharactersReplaced(), entity.getCharacters());
                return entity;
            }).forEachOrdered((item) -> {
                Common.QUANTUM = item.getQuantum();
                System.out.println(item.toString());
                if (process == null) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Input");
                    alert.setContentText("Check the entry of each text box");
                    alert.show();
                } else {
                    Common.processAvailable++;
                    procAvailable.setText(String.valueOf(Common.processAvailable));
                    Common.loadRecentlyAddProcess(listNewProcess, process);
                    priorityQueue.push(process.getPriority(), process, process.getPid());
                    queue.push(process);
                    wsProcess.add(process);
                }
            });

        } catch (NumberFormatException e) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Input");
            alert.setContentText("Check the entry of each text box");
            alert.show();
        }
    }

    @FXML
    private void startProcess(ActionEvent event) {
        listNewProcess.getItems().clear();
        checkPriority.setDisable(true);
        btnStart.setDisable(true);
        btnStop.setDisable(false);
        if (readyProcess.size() > 0) {
            epsf = new ExecuteProcessServiceFacade(readyProcess,
                    progressProcessBar,
                    currentPid,
                    currentNameProcess,
                    observerTableProcess,
                    lblExecute,
                    progressStatus,
                    btnStart,
                    observableComplete,
                    chartProcess
            );
            epsf.start();
        } else {
            System.out.println("Queue empty");
        }
    }

    @FXML
    private void stopProcess(ActionEvent event) {
        btnStart.setVisible(false);
        btnContinue.setVisible(true);
        btnContinue.setDisable(false);
        btnStop.setDisable(true);
        System.out.println("Thread To Stop: " + epsf.getTp().getProcess().getPid());
        epsf.getTp().suspend();
    }

    @FXML
    private void continueProcess(ActionEvent event) {
        btnContinue.setDisable(true);
        btnStop.setDisable(false);
        System.out.println("Thread To Resume: " + epsf.getTp().getProcess().getPid());
        epsf.getTp().resume();
    }

    @FXML
    private void loadProcess(ActionEvent event) {
        if (thinput.getText().matches("[0-9]*") && thinput.getText().length() > 0) {

            Common.thmillis = Long.parseLong(thinput.getText());

            ptService = new LoadProcessServiceFacade(
                    observerTableProcess,
                    progressProcessBar,
                    tableProcess,
                    progressStatus,
                    currentNameProcess,
                    currentPid,
                    checkPriority,
                    priorityQueue,
                    queue,
                    btnStart,
                    readyProcess
            );
            thinput.setDisable(true);
            checkPriority.setDisable(true);
            generalQuantum.setText(String.format("%s %s", generalQuantum.getText(), Common.QUANTUM));
        } else {
            thinput.setFocusColor(Color.web("#d50000"));
            JFXSnackbar snackbar = new JFXSnackbar(homeAnchor);
            snackbar.show("Error TH(millis) is Empty", 5000);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rest.getSimulations().forEach(c
                -> processList.getItems().add(c)
        );
        thinput.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.equals("")) {
                thinput.setFocusColor(Color.web("#edb407"));
            }
        });

        lblAvailableCharts.setOnMouseClicked((MouseEvent event) -> {
            anchorCharts.toFront();
        });

        initPopup();

        initTableProcess();

        initTableDelete();

        initCompleteTable();
    }

    private void initPopup() {
        JFXButton btn = new JFXButton("Delete");
        btn.setPadding(new Insets(10));
        btn.setOnMouseClicked((MouseEvent event) -> {
            try {
                int index = listNewProcess.getSelectionModel().getSelectedIndex();
                listNewProcess.getItems().remove(index);
                popupList.hide();
                Common.processAvailable--;
                Common.processEliminated++;
                procAvailable.setText(String.valueOf(Common.processAvailable));
                processEliminated.setText(String.valueOf(Common.processEliminated));
                System.out.println(wsProcess.get(index).toString());
                wsProcess.remove(index);

            } catch (Exception e) {
                Logger.getLogger(FXMLController.class
                        .getName()).log(Level.SEVERE, null, e);
            }
        });
        VBox vBox = new VBox(btn);
        popupList.setPopupContent(vBox);
    }

    private void initTableProcess() {
        observerTableProcess = FXCollections.observableArrayList();
        tableProcess.setItems(observerTableProcess);
        //List Linked
        pidCol.setCellValueFactory(new PropertyValueFactory<>("pid"));
        processCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        burstCol.setCellValueFactory(new PropertyValueFactory<>("burst"));
        priorityCol.setCellValueFactory(new PropertyValueFactory<>("priority"));
        burstResidueCol.setCellValueFactory(new PropertyValueFactory<>("burstresidue"));
        charactersCol.setCellValueFactory(new PropertyValueFactory<>("characters"));
        replacedCol.setCellValueFactory(new PropertyValueFactory<>("characterReplaced"));
        stateProcessCol.setCellValueFactory(new PropertyValueFactory<>("state"));
    }

    private void initTableDelete() {
        observerDeleteTable = FXCollections.observableArrayList();
        tableDelete.setItems(observerDeleteTable);
        //Link to columns with binding
        pidDelCol.setCellValueFactory(new PropertyValueFactory<>("pid"));
        processDelCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        burstDelCol.setCellValueFactory(new PropertyValueFactory<>("burst"));
        priorityDelCol.setCellValueFactory(new PropertyValueFactory<>("priority"));
        charactersDelCol.setCellValueFactory(new PropertyValueFactory<>("characters"));
        replacedDelCol.setCellValueFactory(new PropertyValueFactory<>("charactersReplaced"));
        stateDelCol.setCellValueFactory(new PropertyValueFactory<>("state"));
        datetimeDelCol.setCellValueFactory(new PropertyValueFactory<>("datetime"));
    }

    private void initCompleteTable() {
        observableComplete = FXCollections.observableArrayList();
        tableExecuted.setItems(observableComplete);
        pidFinished.setCellValueFactory(new PropertyValueFactory<>("pid"));
        processFinished.setCellValueFactory(new PropertyValueFactory<>("name"));
        burstFinished.setCellValueFactory(new PropertyValueFactory<>("burst"));
        priorityFinished.setCellValueFactory(new PropertyValueFactory<>("priority"));
        timeArrivalFinished.setCellValueFactory(new PropertyValueFactory<>("timeArrive"));
        turnaroundFinished.setCellValueFactory(new PropertyValueFactory<>("turnAround"));
        endtimeFinished.setCellValueFactory(new PropertyValueFactory<>("endTime"));
    }

}
