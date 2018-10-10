/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gksoftware.processmanagement.services;

import com.gksoftware.processmanagement.model.ProcessTable;
import com.jfoenix.controls.JFXProgressBar;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;

/**
 *
 * @author Geek-Programmer
 */
public class ProcessTaskService extends Task<Object>{
    
    private ObservableList<ProcessTable> bindingList;
    private JFXProgressBar progressBar;

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
    
    public ProcessTaskService() {
    }
    
    
    @Override
    protected Object call() throws Exception {
        return true;
    }
    
}
