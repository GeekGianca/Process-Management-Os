/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gksoftware.processmanagement.threads;

import com.gksoftware.processmanagement.Common;
import static com.gksoftware.processmanagement.Common.REPLACEMENT_CHAR;
import com.gksoftware.processmanagement.model.Process;
import com.gksoftware.processmanagement.model.ProcessTable;
import com.jfoenix.controls.JFXProgressBar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

/**
 *
 * @author Geek-Programmer
 */
public class ThreadProcess implements Runnable {

    private Thread tProcess;
    private Process process;
    private JFXProgressBar progress;
    private Label lblPid;
    private Label lblName;
    private Label execute;
    private ObservableList<ProcessTable> bindingList;
    private TableView<ProcessTable> bindigTable;
    private boolean isSuspend = false;
    private int counterTime;
    private long timeArrive;
    private DoubleProperty progressBar;

    public ThreadProcess(Process process) {
        this.process = process;
        this.counterTime = -1;
    }

    public void setExecute(Label execute) {
        this.execute = execute;
    }
    
    public void setBindingList(ObservableList<ProcessTable> bindingList) {
        this.bindingList = bindingList;
    }

    public void setBindigTable(TableView<ProcessTable> bindigTable) {
        this.bindigTable = bindigTable;
    }

    public final double getProgressBar() {
        if (progressBar != null) {
            return progressBar.get();
        }
        return 0;
    }

    public final void setProgressBar(double progressBar) {
        this.progressBar.set(progressBar);
    }

    public Thread gettProcess() {
        return tProcess;
    }

    public Label getLblPid() {
        return lblPid;
    }

    public void setLblPid(Label lblPid) {
        this.lblPid = lblPid;
    }

    public Label getLblName() {
        return lblName;
    }

    public void setLblName(Label lblName) {
        this.lblName = lblName;
    }

    public Process getProcess() {
        return process;
    }

    public long getTimeArrive() {
        return timeArrive;
    }

    public void setTimeArrive(long timeArrive) {
        this.timeArrive = timeArrive;
    }

    public JFXProgressBar getProgress() {
        return progress;
    }

    public void setProgress(JFXProgressBar progress) {
        this.progress = progress;
    }

    private final DoubleProperty progressProperty() {
        if (progressBar == null) {
            progressBar = new SimpleDoubleProperty(0);
        }
        return progressBar;
    }

    @Override
    public void run() {
        try {
            boolean isRunning = true;
            ProcessTable pt = bindingList.get(process.getPositionView());
            System.out.println("Process On Table: " + pt.getName());
            pt.setState("Running");
            showLabels();
            double progressPercentage = 0;
            if (process.getBurst() == 0) {
                progressPercentage = 1;
            } else {
                progressPercentage = 0.1 / process.getBurst();
            }
            double percent = 0;
            System.out.println(process.toString());
            while (isRunning && (counterTime < process.getBurst())) {
                counterTime++;
                System.out.println("Running Service????");
                process.setBurstResidue(process.getBurstResidue() - Common.thmillis);
                pt.setBurstresidue(process.getBurstResidue());
                bindingList.set(process.getPositionView(), pt);
                setProgressBar(getProgressBar()+progressPercentage);
                Thread.sleep(Common.thmillis);
                synchronized (this) {
                    while (isSuspend) {
                        System.out.println("Synchronize Suspend...");
                        wait();
                    }
                }
//                if (process.getBurst() == 1) {
//                    isRunning = false;
//                    if (process.getBurstResidue() == 0) {
//                        setterStatusProcessView(pt, "Finished");
//                    } else {
//                        setterStatusProcessView(pt, "Ready");
//                    }
//                } else {
//                    
//                }
                if (process.getPriority() == 1 || process.getPriority() == 2) {
                    isRunning = false;
                    if (process.getBurstResidue() == 0) {
                        setterStatusProcessView(pt, "Finished");
                        Common.processExecuted ++;
                    } else {
                        setterStatusProcessView(pt, "Ready");
                    }
                } else {
                    if (process.getBurstResidue() == 0) {
                        isRunning = false;
                        setterStatusProcessView(pt, "Finished");
                        Common.processExecuted ++;
                    }
                }
            }
            setProgressBar(0);
            counterTime = -1;

        } catch (InterruptedException ex) {
            Logger.getLogger(ThreadProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public synchronized void execute() {
        if (tProcess == null) {
            System.out.println("Executing Process");
            tProcess = new Thread(this);
            tProcess.start();
            System.out.println(progressProperty().get());
            System.out.println(progress);
            progress.progressProperty().bind(progressBar);
        }
    }

    public void suspend() {
        isSuspend = true;
    }

    public synchronized void resume() {
        isSuspend = false;
        notify();
    }

    public String changeCharacters(String characters, int position) {
        String newcharacter = characters;
        if (position <= characters.length()) {
            newcharacter = characters.subSequence(0, position - 1) + REPLACEMENT_CHAR + characters.substring(position, characters.length());
        }
        return newcharacter;
    }

    private void setterStatusProcessView(ProcessTable pt, String state) {
        pt.setState(state);
        bindingList.set(process.getPositionView(), pt);
        showLabels();
    }

    private void showLabels() {
        Platform.runLater(() -> {
            lblName.setText(process.getName());
            lblPid.setText(process.getPid());
            execute.setText(String.valueOf(Common.processExecuted));
        });
    }
}
