/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gksoftware.processmanagement.services;

import com.gksoftware.processmanagement.Common;
import com.gksoftware.processmanagement.model.ProcessTable;
import com.gksoftware.processmanagement.queues.PriorityQueue;
import com.gksoftware.processmanagement.queues.Queue;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXProgressBar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

import com.gksoftware.processmanagement.model.Process;
/**
 *
 * @author Geek-Programmer
 */
public class LoadProcessServiceFacade {

    private ObservableList<ProcessTable> bindingList;
    private JFXProgressBar progressBar;
    private TableView<ProcessTable> bindigTable;
    private Label labelPercentage;
    private Label currentNameProcess;
    private Label currentPidProcess;
    private Task processTask;
    private JFXCheckBox checkForm;
    private int sizeSelected;
    private PriorityQueue priorityQueue;
    private Queue queue;
    private Thread threadProcess;
    private Button buttonStart;
    private Queue<ThreadProcess> readyProcess;

    /**
     * Constructor for class
     *
     * @param bindingList get data from table in the view
     * @param progressBar show progress in the view
     * @param bindigTable set datatable in the view
     * @param labelPercentage Show percentage current
     * @param currentNameProcess Show process current
     * @param currentPidProcess Show pid process current
     * @param checkForm Check type of schedule process
     * @param priorityQueue Queue by priority
     * @param queue Normal queue
     * @param btnStart Binding to view
     * @param readyProcess Obtain process ready
     */
    public LoadProcessServiceFacade(ObservableList<ProcessTable> bindingList, JFXProgressBar progressBar, TableView<ProcessTable> bindigTable, Label labelPercentage, Label currentNameProcess, Label currentPidProcess, JFXCheckBox checkForm, PriorityQueue priorityQueue, Queue queue, Button btnStart, Queue<ThreadProcess> readyProcess) {
        this.bindingList = bindingList;
        this.progressBar = progressBar;
        this.bindigTable = bindigTable;
        this.labelPercentage = labelPercentage;
        this.currentNameProcess = currentNameProcess;
        this.currentPidProcess = currentPidProcess;
        this.checkForm = checkForm;
        this.priorityQueue = priorityQueue;
        this.queue = queue;
        this.buttonStart = btnStart;
        this.readyProcess = readyProcess;
        startProcess();
    }

    /**
     * Getter for Label Name Process
     *
     * @return current of name process
     */
    public Label getCurrentNameProcess() {
        return currentNameProcess;
    }

    /**
     * Getter for current pid process
     *
     * @return pid process
     */
    public Label getCurrentPidProcess() {
        return currentPidProcess;
    }

    /**
     * Set the data in the priority queue
     *
     * @param priorityQueue
     */
    public void setPriorityQueue(PriorityQueue priorityQueue) {
        this.priorityQueue = priorityQueue;
    }

    /**
     * Set the queue
     *
     * @param queue
     */
    public void setQueue(Queue queue) {
        this.queue = queue;
    }

    /**
     * Return CheckBox
     *
     * @return CheckBox
     */
    public JFXCheckBox getCheckForm() {
        return checkForm;
    }

    /**
     * Set the CheckBox
     *
     * @param checkForm
     */
    public void setCheckForm(JFXCheckBox checkForm) {
        this.checkForm = checkForm;
    }

    public int getSizeSelected() {
        return sizeSelected;
    }

    public void setSizeSelected(int sizeSelected) {
        this.sizeSelected = sizeSelected;
    }

    public Label getLabelPercentage() {
        return labelPercentage;
    }

    public void setLabelPercentage(Label labelPercentage) {
        this.labelPercentage = labelPercentage;
    }

    public TableView<ProcessTable> getBindigTable() {
        return bindigTable;
    }

    public void setBindigTable(TableView<ProcessTable> bindigTable) {
        this.bindigTable = bindigTable;
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

    public LoadProcessServiceFacade() {
    }

    public Task getProcessTask() {
        return processTask;
    }

    private void stop() {
        try {
            Thread.sleep(Common.thmillis);
        } catch (InterruptedException ex) {
            Logger.getLogger(LoadProcessServiceFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void startProcess() {
        this.progressBar.setProgress(0.0D);
        processTask = initTask();
        this.progressBar.progressProperty().unbind();
        this.progressBar.progressProperty().bind(processTask.progressProperty());
        processTask.messageProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
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
        threadProcess = new Thread(processTask);
        threadProcess.start();
        if (checkForm.isSelected()) {
            sizeSelected = priorityQueue.size();
        } else {
            sizeSelected = queue.size();
        }
    }

    private Task initTask() {
        return new Task() {
            @Override
            protected Object call() throws Exception {
                double pTotal = 100 / sizeSelected;
                double processCounter = 0;
                double rest = 0;
                for (int i = 0; i < sizeSelected; i++) {
                    stop();
                    Process pRuntime;
                    if (checkForm.isSelected()) {
                        if (!priorityQueue.isEmpty()) {
                            pRuntime = (Process) priorityQueue.peek().getElement();
                            bindingList.add(new ProcessTable(pRuntime.getPid(), pRuntime.getName(), pRuntime.getBurst(), pRuntime.getPriority(), pRuntime.getBurst(), pRuntime.getCharacters(), pRuntime.getCharacterReplaced().charAt(0), "Waiting"));
                            priorityQueue.pop();
                            System.out.println(pRuntime.toString());
                        }
                    } else {
                        if (!queue.isEmpty()) {
                            pRuntime = (Process) queue.peek().getElement();
                            bindingList.add(new ProcessTable(pRuntime.getPid(), pRuntime.getName(), pRuntime.getBurst(), pRuntime.getPriority(), pRuntime.getBurst(), pRuntime.getCharacters(), pRuntime.getCharacterReplaced().charAt(0), "Waiting"));
                            queue.pop();
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
                int processCounterSize = bindingList.size();
                int counterTime = 0;
                stop();
                updateProgress(0.0D, 100);
                updateMessage(String.valueOf(0.0 + "%"));
                float percetageByProcess = 100 / processCounterSize;
                double progressPercentage = 0.0D;
                while (counterTime < processCounterSize) {
                    Process pProcess = new Process();
                    ProcessTable process;
                    process = bindingList.get(counterTime);
                    pProcess.convertProcess(process);
                    pProcess.setPositionView(counterTime);
                    ThreadProcess ttProcess = new ThreadProcess(pProcess);
                    System.out.println(pProcess.toString());
                    readyProcess.push(ttProcess);
                    process.setState("Ready");
                    process.setBurst(pProcess.getBurstTime());
                    process.setBurstresidue(pProcess.getBurstResidue());
                    
                    System.out.println(process.toString());
                    bindingList.set(counterTime, process);
                    progressPercentage += percetageByProcess;
                    updateProgress(progressPercentage, 100);
                    updateMessage(String.valueOf(progressPercentage + "-" + process.getName() + "-" + process.getPid()));
                    rest = (100 - progressPercentage);
                    stop();
                    System.out.println("Pos List: "+counterTime);
                    counterTime++;
                }
                updateProgress(rest+progressPercentage, 100);
                stop();
                updateProgress(0.0D, 100);
                return true;
            }

            @Override
            protected void succeeded() {
                super.succeeded();
                System.out.println("Finished...");
                labelPercentage.setText(0+"%");
                currentNameProcess.setText("");
                currentPidProcess.setText("");
                buttonStart.setDisable(false);
            }

            @Override
            protected void running() {
                super.running();
                System.out.println("Running...");
            }
            
        };
    }

}
