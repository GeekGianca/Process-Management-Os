/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gksoftware.processmanagement.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Geek-Programmer
 */
public class ProcessComplete {
    private SimpleStringProperty pid;
    private SimpleStringProperty name;
    private SimpleLongProperty timeArrive;
    private SimpleIntegerProperty burst;
    private SimpleIntegerProperty priority;
    private SimpleLongProperty turnAround;
    private SimpleLongProperty endTime;

    public String getPid() {
        return pid.get();
    }

    public void setPid(String pid) {
        this.pid.set(pid);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public long getTimeArrive() {
        return timeArrive.get();
    }

    public void setTimeArrive(long timeArrive) {
        this.timeArrive.set(timeArrive);
    }

    public int getBurst() {
        return burst.get();
    }

    public void setBurst(int burst) {
        this.burst.set(burst);
    }

    public int getPriority() {
        return priority.get();
    }

    public void setPriority(int priority) {
        this.priority.set(priority);
    }

    public long getTurnAround() {
        return turnAround.get();
    }

    public void setTurnAround(long turnAround) {
        this.turnAround.set(turnAround);
    }

    public long getEndTime() {
        return endTime.get();
    }

    public void setEndTime(long endTime) {
        this.endTime.set(endTime);
    }

    public ProcessComplete() {
        this.pid = new SimpleStringProperty("");
        this.name = new SimpleStringProperty("");
        this.timeArrive = new SimpleLongProperty(0);
        this.burst = new SimpleIntegerProperty(0);
        this.priority = new SimpleIntegerProperty(0);
        this.turnAround = new SimpleLongProperty(0);
        this.endTime = new SimpleLongProperty(0);
    }

    public ProcessComplete(String pid, String name, long timeArrive, int burst, int priority, long turnAround, long endTime) {
        this.pid = new SimpleStringProperty(pid);
        this.name = new SimpleStringProperty(name);
        this.timeArrive = new SimpleLongProperty(timeArrive);
        this.burst = new SimpleIntegerProperty(burst);
        this.priority = new SimpleIntegerProperty(priority);
        this.turnAround = new SimpleLongProperty(turnAround);
        this.endTime = new SimpleLongProperty(endTime);
    }
    
    public void convertToProcessEnd(Process process){
        this.setPid(process.getPid());
        this.setName(process.getName());
        this.setTimeArrive(process.getTimeArrival());
        this.setBurst(process.getBurst());
        this.setPriority(process.getPriority());
        this.setTurnAround(process.getTurnAround());
        this.setEndTime(process.getEndtime());
    }
}
