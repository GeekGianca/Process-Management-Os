/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gksoftware.processmanagement.services;

import com.gksoftware.processmanagement.model.ProcessTable;
import com.gksoftware.processmanagement.queues.Queue;
import com.jfoenix.controls.JFXProgressBar;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;


/**
 *
 * @author Geek-Programmer
 */
public class ExecuteProcessServiceFacade {
    private ObservableList<ProcessTable> bindingList;
    private JFXProgressBar progressBar;
    private TableView<ProcessTable> bindigTable;
    private Label labelPercentage;
    private Label currentNameProcess;
    private Label currentPidProcess;
    private Queue queue;
    private Thread threadProcess;
    private Button buttonStart;
    private Task executeTask;
    private Queue<ThreadProcess> readyProcess;

    public ExecuteProcessServiceFacade(ObservableList<ProcessTable> bindingList, JFXProgressBar progressBar, TableView<ProcessTable> bindigTable, Label labelPercentage, Label currentNameProcess, Label currentPidProcess, Button buttonStart, Queue<ThreadProcess> readyProcess) {
        this.bindingList = bindingList;
        this.progressBar = progressBar;
        this.bindigTable = bindigTable;
        this.labelPercentage = labelPercentage;
        this.currentNameProcess = currentNameProcess;
        this.currentPidProcess = currentPidProcess;
        this.buttonStart = buttonStart;
        this.readyProcess = readyProcess;
    }

    public ObservableList<ProcessTable> getBindingList() {
        return bindingList;
    }

    public void setBindingList(ObservableList<ProcessTable> bindingList) {
        this.bindingList = bindingList;
    }

    public JFXProgressBar getProgressBar() {
        return progressBar;
    }

    public void setProgressBar(JFXProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    public TableView<ProcessTable> getBindigTable() {
        return bindigTable;
    }

    public void setBindigTable(TableView<ProcessTable> bindigTable) {
        this.bindigTable = bindigTable;
    }

    public Label getLabelPercentage() {
        return labelPercentage;
    }

    public void setLabelPercentage(Label labelPercentage) {
        this.labelPercentage = labelPercentage;
    }

    public Label getCurrentNameProcess() {
        return currentNameProcess;
    }

    public void setCurrentNameProcess(Label currentNameProcess) {
        this.currentNameProcess = currentNameProcess;
    }

    public Label getCurrentPidProcess() {
        return currentPidProcess;
    }

    public void setCurrentPidProcess(Label currentPidProcess) {
        this.currentPidProcess = currentPidProcess;
    }

    public Queue getQueue() {
        return queue;
    }

    public void setQueue(Queue queue) {
        this.queue = queue;
    }

    public Thread getThreadProcess() {
        return threadProcess;
    }

    public void setThreadProcess(Thread threadProcess) {
        this.threadProcess = threadProcess;
    }

    public Button getButtonStart() {
        return buttonStart;
    }

    public void setButtonStart(Button buttonStart) {
        this.buttonStart = buttonStart;
    }

    public Queue<ThreadProcess> getReadyProcess() {
        return readyProcess;
    }

    public void setReadyProcess(Queue<ThreadProcess> readyProcess) {
        this.readyProcess = readyProcess;
    }
    
    private void executeService(){
        this.progressBar.setProgress(0);
        executeTask = startExecuteProcess();
        this.progressBar.progressProperty().unbind();;
        this.progressBar.progressProperty().bind(executeTask.progressProperty());
        executeTask.messageProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            try {
                String formatterValue[] = newValue.split("-");
                String percentage = formatterValue[0];
                String processname = formatterValue[1];
                String processpid = formatterValue[2];
                labelPercentage.setText(percentage+"%");
                currentNameProcess.setText(processname);
                currentPidProcess.setText(processpid);
            } catch (ArrayIndexOutOfBoundsException aiobe) {
                labelPercentage.setText(newValue);
            }
        });
        threadProcess = new Thread(executeTask);
        threadProcess.start();
    }

    private Task startExecuteProcess() {
        return new Task() {
            @Override
            protected Object call() throws Exception {
                return true;
            }
        };
    }
}
