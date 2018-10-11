/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gksoftware.processmanagement.threads;

import com.gksoftware.processmanagement.model.ProcessTable;
import com.gksoftware.processmanagement.queues.Queue;
import com.jfoenix.controls.JFXProgressBar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.ObservableList;
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
    private Label lblPid;
    private Label lblName;
    private ObservableList<ProcessTable> bindingList;
    private Label execute;
    private Label progresslbl;
    private long timeArrival;

    public ExecuteProcessServiceFacade(Queue<ThreadProcess> queue,
            JFXProgressBar progress,
            Label lblPid,
            Label lblName,
            ObservableList<ProcessTable> bindingList,
            Label execute,
            Label progressLbl) {
        this.queue = queue;
        this.progress = progress;
        this.lblName = lblName;
        this.lblPid = lblPid;
        this.bindingList = bindingList;
        this.execute = execute;
        this.progresslbl = progressLbl;
        this.timeArrival = 0;
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
                System.out.println("Quantity Items: " + queue.size());
                System.out.println("IsSuspendend: " + isSuspend);
                tp = queue.peek().getElement();
                System.out.println("Stater Process---->" + tp.getProcess().toString());
                tp.setBindingList(bindingList);
                tp.setLblName(lblName);
                tp.setLblPid(lblPid);
                tp.setExecute(execute);
                tp.setProgress(progress);
                tp.setTimeArrive(timeArrival);
                tp.setProgressLbl(progresslbl);
                tp.execute();
                tp.gettProcess().join();
                System.out.println("Process Endend--->" + tp.getProcess().toString());
                this.timeArrival = tp.getTimeArrive();
                if (tp.getProcess().getBurstResidue() > 0) {
                    queue.push(tp);
                    queue.pop();
                } else {
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

    @Override
    protected void finalize() throws Throwable {
        System.out.println("End Thread");
    }

}
