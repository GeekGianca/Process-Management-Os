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
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;



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
    private TableView<ProcessTable> bindigTable;
    private Label execute;

    public ExecuteProcessServiceFacade(Queue<ThreadProcess> queue, 
            JFXProgressBar progress, 
            Label lblPid, 
            Label lblName, 
            ObservableList<ProcessTable> bindingList, 
            TableView<ProcessTable> bindigTable, Label execute) {
        this.queue = queue;
        this.progress = progress;
        this.lblName = lblName;
        this.lblPid = lblPid;
        this.bindingList = bindingList;
        this.bindigTable = bindigTable;
        this.execute = execute;
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
                System.out.println("IsSuspendend: " + isSuspend);
                tp = queue.peek().getElement();
                tp.setBindigTable(bindigTable);
                tp.setBindingList(bindingList);
                tp.setLblName(lblName);
                tp.setLblPid(lblPid);
                tp.setExecute(execute);
                tp.setProgress(progress);
                tp.execute();
                tp.gettProcess().join();
                queue.pop();
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
