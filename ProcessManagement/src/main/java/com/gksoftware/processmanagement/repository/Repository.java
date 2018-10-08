/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gksoftware.processmanagement.repository;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.stereotype.Component;

import com.gksoftware.processmanagement.model.Process;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.transformation.FilteredList;

/**
 *
 * @author Geek-Programmer
 */

@Component
public class Repository {
    
    private ObservableList<Process> allProcessList = FXCollections.observableArrayList();
    
    private FilteredList<Process> completeProcesses = new FilteredList<>(allProcessList);
    private FilteredList<Process> activeProcesses = new FilteredList<>(allProcessList);
    private FilteredList<Process> deleteProcesses = new FilteredList<>(allProcessList);
    
    private ListProperty<Process> processProperty = new SimpleListProperty<>(allProcessList);
    
    public void showAllProcess(){
        processProperty.set(allProcessList);
    }
    
    public void showCompleteProcess(){
        processProperty.set(completeProcesses);
    }
    
    public void showActiveProcess(){
        processProperty.set(activeProcesses);
    }
    
    public void addProcess(Process process){
        allProcessList.add(process);
    }
    
    public void deleteProcess(Process process){
        allProcessList.remove(process);
        deleteProcesses.add(process);
    }
}
