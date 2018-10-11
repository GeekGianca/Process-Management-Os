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
import com.gksoftware.processmanagement.model.ProcessTable;
import com.gksoftware.processmanagement.queues.*;
import com.gksoftware.processmanagement.threads.ExecuteProcessServiceFacade;
import com.gksoftware.processmanagement.threads.LoadProcessServiceFacade;
import com.gksoftware.processmanagement.threads.ThreadProcess;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXProgressBar;
import com.jfoenix.controls.JFXSnackbar;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.geometry.Insets;
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
    private Queue<ThreadProcess> executeProcess = new Queue<>();
    private ThreadProcess currentThread;
    private ExecuteProcessServiceFacade epsf;

    @FXML
    private AnchorPane homeAnchor;

    @FXML
    private Label procAvailable;

    @FXML
    private Label processEliminated;

    @FXML
    private JFXTextField pid;

    @FXML
    private JFXTextField processName;

    @FXML
    private JFXTextField priority;

    @FXML
    private JFXTextField characters;

    @FXML
    private JFXTextField charReplaced;

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
    private JFXTextField charReplacement;
    @FXML
    private Label lblExecute;

    @FXML
    private void showPopup(MouseEvent event) {
        int selected = listNewProcess.getSelectionModel().getSelectedIndex();
        if (event.getSource() != MouseButton.PRIMARY) {
            if (selected > -1) {
                popupList.show(listNewProcess, JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.LEFT, event.getX(), event.getY());
            }
        }
        listNewProcess.getSelectionModel().isSelected(-1);
    }

    @FXML
    private void createNewProcess(ActionEvent event) {
        try {
            if (characters.getText().length() <= 255) {
                process = Common.verifyTextInputs(pid.getText(), processName.getText(), Integer.parseInt(priority.getText()), charReplaced.getText(), characters.getText());
                if (process == null) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Input");
                    alert.setContentText("Check the entry of each text box");
                    alert.show();
                } else {
                    Common.processAvailable++;
                    procAvailable.setText(String.valueOf(Common.processAvailable));
                    Common.loadRecentlyAddProcess(listNewProcess, process);
                    clear();
                    priorityQueue.push(process.getPriority(), process, process.getPid());
                    queue.push(process);
                }
            } else {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Unvalid entry");
                alert.setContentText("The string of characters can not have more than 255 positions");
                alert.show();
            }
        } catch (NumberFormatException e) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Input");
            alert.setContentText("Check the entry of each text box");
            alert.show();
        }
        loadAutomaticProcess();
    }

    @FXML
    private void startProcess(ActionEvent event) {
        checkPriority.setDisable(true);
        btnStart.setDisable(true);
        btnStop.setDisable(false);
        if (readyProcess.size() > 0) {
            epsf = new ExecuteProcessServiceFacade(readyProcess, progressProcessBar, currentPid, currentNameProcess, observerTableProcess, tableProcess, lblExecute);
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
        System.out.println("Thread To Stop: "+epsf.getTp().getProcess().getPid());
        epsf.getTp().suspend();
    }

    @FXML
    private void continueProcess(ActionEvent event) {
        btnContinue.setDisable(true);
        btnStop.setDisable(false);
        System.out.println("Thread To Resume: "+epsf.getTp().getProcess().getPid());
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

        } else {
            thinput.setFocusColor(Color.web("#d50000"));
            JFXSnackbar snackbar = new JFXSnackbar(homeAnchor);
            snackbar.show("Error TH(millis) is Empty", 5000);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pid.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            int priorityListener = (int) (Math.random() * 3);
            priority.setText(String.valueOf(priorityListener));
        });
        thinput.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.equals("")) {
                thinput.setFocusColor(Color.web("#edb407"));
            }
        });

        initPopup();
        initTableProcess();
        loadAutomaticProcess();
    }

    private void clear() {
        pid.setText("");
        processName.setText("");
        priority.setText("");
        characters.setText("");
        charReplaced.setText("");
    }

    private void initPopup() {
        JFXButton btn = new JFXButton("Delete");
        btn.setPadding(new Insets(10));
        btn.setOnMouseClicked((MouseEvent event) -> {
            try {
                listNewProcess.getItems().remove(listNewProcess.getSelectionModel().getSelectedIndex());
                popupList.hide();
                Common.processAvailable--;
                Common.processEliminated++;
                procAvailable.setText(String.valueOf(Common.processAvailable));
                processEliminated.setText(String.valueOf(Common.processEliminated));
            } catch (Exception e) {
                Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, e);
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

    private void loadAutomaticProcess() {
        char[] letters = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'ñ', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        pid.setText(String.valueOf(System.currentTimeMillis()));
        String data = "";
        for (int i = 0; i < 15; i++) {
            data += letters[(int) (Math.random() * (26))];
        }
        processName.setText(data);
        data = "";
        for (int i = 0; i < 24; i++) {
            data += letters[(int) (Math.random() * (26))];
        }
        characters.setText(data);
        charReplaced.setText(String.valueOf(letters[(int) (Math.random() * (26))]));
    }

}
/*private Task initProcessTask() {
        return new Task() {
            @Override
            protected Object call() throws Exception {
                double pTotal = 100 / sizeSelected;
                double processCounter = 0;
                double rest = 0;
                for (int i = 0; i < sizeSelected; i++) {
                    Thread.sleep(Common.thmillis);
                    Process pRuntime;
                    if (checkPriority.isSelected()) {
                        if (!pqueue.isEmpty()) {
                            System.out.println("---->Priority: " + pqueue.peek().toString());
                            pRuntime = pqueue.peek();
                            observerTableProcess.add(new ProcessTable(pRuntime.getPid(), pRuntime.getName(), pRuntime.getBurst(), pRuntime.getPriority(), pRuntime.getBurst(), pRuntime.getCharacters(), pRuntime.getCharacterReplaced().charAt(0), "Waiting"));
                            pqueue.poll();
                            System.out.println(pRuntime.toString());
                        }
                    } else {
                        if (!squeue.isEmpty()) {
                            pRuntime = squeue.peek();
                            System.out.println("---->Queue: " + squeue.peek().toString());
                            observerTableProcess.add(new ProcessTable(pRuntime.getPid(), pRuntime.getName(), pRuntime.getBurst(), pRuntime.getPriority(), pRuntime.getBurst(), pRuntime.getCharacters(), pRuntime.getCharacterReplaced().charAt(0), "Waiting"));
                            squeue.pop();
                            System.out.println(pRuntime.toString());
                        }

                    }
                    pRuntime = null;
                    processCounter += pTotal;
                    rest = 100 - processCounter;
                    updateMessage(String.valueOf(processCounter + "%"));
                    updateProgress(processCounter, 100);
                }
                updateProgress(rest + processCounter, 100);
                updateMessage(String.valueOf(rest + processCounter + "%"));
                int processCounterSize = observerTableProcess.size();
                int counterTime = 0;
                Thread.sleep(Common.thmillis);
                updateProgress(0.0D, 100);
                updateMessage(String.valueOf(0 + "%"));
                //progressProcessBar.setProgress(0.0D);
                while (counterTime < processCounterSize) {
                    Thread.sleep(Common.thmillis);
                    ProcessTable process = new ProcessTable();
                    process = observerTableProcess.get(counterTime);
                    process.setState("Running");
                    observerTableProcess.set(counterTime, process);
                    //observerTableProcess.add(process);
                    System.out.println("Col: "+stateProcessCol.getCellData(counterTime));
                    System.out.println("Position: "+counterTime);
                    counterTime++;
                }
                return true;
            }
        };
    }*/
