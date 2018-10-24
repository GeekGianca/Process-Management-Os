/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gksoftware.processmanagementso.threads;

import com.gksoftware.processmanagementso.Common;
import static com.gksoftware.processmanagementso.Common.REPLACEMENT_CHAR;
import com.gksoftware.processmanagementso.model.Process;
import com.gksoftware.processmanagementso.model.ProcessTable;
import com.jfoenix.controls.JFXProgressBar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;

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
    private Label progressLbl;
    private ObservableList<ProcessTable> bindingList;
    private boolean isSuspend = false;
    private int counterTime;
    private long timeArrive;
    private DoubleProperty progressBar;
    private int timeEnded;

    public ThreadProcess(Process process) {
        this.process = process;
        this.counterTime = 0;
        this.progressBar = new SimpleDoubleProperty(0);
    }

    public void setExecute(Label execute) {
        this.execute = execute;
    }

    public void setBindingList(ObservableList<ProcessTable> bindingList) {
        this.bindingList = bindingList;
    }

    public void setProgressLbl(Label progressLbl) {
        this.progressLbl = progressLbl;
    }

    public double getProgressBar() {
        if (progressBar != null) {
            return progressBar.get();
        }
        return 0;
    }

    public void setProgressBar(double progressBar) {
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

    public int getTimeEnded() {
        return timeEnded;
    }

    public void setTimeEnded(int timeEnded) {
        this.timeEnded = timeEnded;
    }

    private DoubleProperty progressProperty() {
        if (progressBar == null) {
            progressBar = new SimpleDoubleProperty(0);
        }
        return progressBar;
    }

    @Override
    public void run() {
        try {
            process.setCicle(process.getCicle() + 1);
            boolean isRunning = true;
            ProcessTable pt = bindingList.get(process.getPositionView());
            pt.setState("Running");
            showLabels();
            while (isRunning && (counterTime != Common.QUANTUM)) {
                if (process.getBurstResidue() > 0) {
                    executeTask(pt);
                    showLabels();
                    //Set turnAround
                    if (process.getBurstResidue() == 0) {//no more residue end task
                        setTimeArrive(getTimeArrive() + 1);
                        isRunning = false;
                        setterStatusProcessView(pt, "Finished");
                        process.setTimeArrival(getTimeArrive());
                        long endTimeProcess = (Common.QUANTUM * process.getCicle() * 1000);
                        process.setEndtime(endTimeProcess);
                        process.setTurnAround(process.getEndtime() - process.getTimeArrival());
                        Common.processExecuted++;
                    } else {
                        if (counterTime == Common.QUANTUM) {
                            //Termina el giro
                            if (process.getPriority() == 1 || process.getPriority() == 2) {
                                isRunning = false;
                                setterStatusProcessView(pt, "Ready");
                            } else {
                                counterTime = 0;
                            }
                        } else {
                            if ((counterTime + 1) == Common.QUANTUM && process.getBurstResidue() > 0) {
                                setterStatusProcessView(pt, "Ready");
                            } else {
                                setterStatusProcessView(pt, "Running");
                            }
                        }
                    }
                } else {
                    setTimeArrive(getTimeArrive() + 1);
                    isRunning = false;
                    setterStatusProcessView(pt, "Finished");
                    process.setTimeArrival(getTimeArrive());
                    long endTimeProcess = (Common.QUANTUM * process.getCicle() * 1000);
                    process.setEndtime(endTimeProcess);
                    process.setTurnAround(process.getEndtime() - process.getTimeArrival());
                    Common.processExecuted++;
                }
                synchronized (this) {
                    while (isSuspend) {
                        System.out.println("Synchronize Suspend...");
                        wait();
                    }
                }
                counterTime++;
                System.out.println("Burst: ------>" + counterTime);
                Thread.sleep(Common.thmillis);
                setTimeEnded(getTimeEnded() + 1);
            }
            counterTime = 0;
            System.out.println("End Task Process");
            Platform.runLater(() -> {
                progressLbl.setText(0 + "%");
                lblName.setText("");
                lblPid.setText("");
                setProgressBar(0);
                progress.progressProperty().unbind();
            });

        } catch (InterruptedException ex) {
            Logger.getLogger(ThreadProcess.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public synchronized void execute() {
        System.out.println("Executing Process");
        tProcess = new Thread(this);
        tProcess.start();
        progressBar.set(0.0D);
        progress.progressProperty().bind(progressProperty());
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
        if (position > 0 && position <= characters.length()) {
            newcharacter = characters.subSequence(0, position - 1) + REPLACEMENT_CHAR + characters.substring(position, characters.length());
        } else {
            newcharacter = REPLACEMENT_CHAR + characters.substring(position, characters.length());
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
            progressLbl.setText(String.valueOf(getProgressBar() + "%"));
            lblName.setText(process.getName());
            lblPid.setText(process.getPid());
            execute.setText(String.valueOf(Common.processExecuted));
            progressBar.set(getProgressBar());
        });
    }

    private void executeTask(ProcessTable pt) {
        String newcharacters = changeCharacters(process.getCharacters(), process.getPositionsChange(process.getCurrentPositionReplaced()) + 1);
        process.setCharacters(newcharacters);
        pt.setCharacters(newcharacters);
        process.setCurrentPositionReplaced(process.getCurrentPositionReplaced() + 1);
        process.setBurstResidue(process.getBurstResidue() - 1);
        pt.setBurstresidue(process.getBurstResidue());
        bindingList.set(process.getPositionView(), pt);
        double valueBar = 100 / process.getBurst();
        double percentageBar = 100 - (valueBar * process.getBurstResidue());
        System.out.println("Progress: " + percentageBar);
        setProgressBar(percentageBar);
    }
}
