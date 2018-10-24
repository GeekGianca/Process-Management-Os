/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gksoftware.processmanagement.threads;

import com.gksoftware.processmanagement.Common;
import com.gksoftware.processmanagement.model.ProcessComplete;
import com.gksoftware.processmanagement.model.ProcessTable;
import com.gksoftware.processmanagement.queues.Queue;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXProgressBar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 *
 * @author Geek-Programmer
 */
public class ExecuteProcessServiceFacade implements Runnable {

    private Queue<ThreadProcess> queue;
    private Thread thread;
    private boolean isSuspend = false;
    private ThreadProcess tp;
    //
    private JFXProgressBar progress;
    private Button btnStart;
    private Label lblPid;
    private Label lblName;
    private ObservableList<ProcessTable> bindingList;
    private ObservableList<ProcessComplete> observableComplete;
    private Label execute;
    private Label progresslbl;
    private long timeArrival;
    private LineChart<?, ?> chartProcess;
    private XYChart.Series series;

    public ExecuteProcessServiceFacade(
            Queue<ThreadProcess> queue,
            JFXProgressBar progress,
            Label lblPid,
            Label lblName,
            ObservableList<ProcessTable> bindingList,
            Label execute,
            Label progressLbl,
            Button btnStart,
            ObservableList<ProcessComplete> observableComplete,
            LineChart<?, ?> chartProcess) {
        this.queue = queue;
        this.progress = progress;
        this.lblName = lblName;
        this.lblPid = lblPid;
        this.bindingList = bindingList;
        this.execute = execute;
        this.progresslbl = progressLbl;
        this.timeArrival = -1;
        this.btnStart = btnStart;
        this.observableComplete = observableComplete;
        this.chartProcess = chartProcess;
        this.series = new XYChart.Series();
    }

    public void setBtnStart(JFXButton btnStart) {
        this.btnStart = btnStart;
    }

    public Queue<ThreadProcess> getQueue() {
        return queue;
    }

    public void setQueue(Queue<ThreadProcess> queue) {
        this.queue = queue;
    }

    public ThreadProcess getTp() {
        return tp;
    }

    @Override
    public void run() {
        while (!queue.isEmpty()) {
            try {
                Common.start = System.nanoTime();
                tp = queue.peek().getElement();
                tp.setBindingList(bindingList);
                tp.setLblName(lblName);
                tp.setLblPid(lblPid);
                tp.setExecute(execute);
                tp.setProgress(progress);
                tp.setTimeArrive(timeArrival);
                tp.setProgressLbl(progresslbl);
                tp.execute();
                tp.gettProcess().join();
                this.timeArrival = tp.getTimeArrive();
                if (tp.getProcess().getBurstResidue() > 0) {
                    queue.push(tp);
                    queue.pop();
                } else {
                    ProcessComplete pc = new ProcessComplete();
                    pc.convertToProcessEnd(tp.getProcess());
                    observableComplete.add(pc);
                    series.getData().add(new XYChart.Data(pc.getName(), pc.getTurnAround()));
                    queue.pop();
                }
                synchronized (this) {
                    while (isSuspend) {
                        System.out.println("Synchronize Suspend...");
                        wait();
                    }
                }

            } catch (InterruptedException ex) {
                Logger.getLogger(ExecuteProcessServiceFacade.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Platform.runLater(() -> {
            lblName.setText("");
            lblPid.setText("");
            btnStart.setDisable(false);
            btnStart.setVisible(true);
            chartProcess.getData().addAll(series);
        });
    }

    public synchronized void start() {
        System.out.println("Starting With inner");
        if (thread == null) {
            thread = new Thread(this);
            thread.start();
        }
    }

    public void suspend() {
        System.out.println("Suspend..." + isSuspend);
        isSuspend = true;
        tp.suspend();
    }

    public synchronized void resume() {
        isSuspend = false;
        tp.resume();
        notify();
    }

}
