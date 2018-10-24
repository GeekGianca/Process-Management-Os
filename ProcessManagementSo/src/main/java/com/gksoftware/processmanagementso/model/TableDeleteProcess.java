/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gksoftware.processmanagementso.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Geek-Programmer
 */
public class TableDeleteProcess {
    private StringProperty pid;
    private StringProperty name;
    private LongProperty burst;
    private IntegerProperty priority;
    private StringProperty characters;
    private StringProperty characterReplaced;
    private StringProperty state;
    private StringProperty datetime;

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

    public long getBurst() {
        return burst.get();
    }

    public void setBurst(long burst) {
        this.burst.set(burst);
    }

    public int getPriority() {
        return priority.get();
    }

    public void setPriority(int priority) {
        this.priority.set(priority);
    }

    public String getCharacters() {
        return characters.get();
    }

    public void setCharacters(String characters) {
        this.characters.set(characters);
    }

    public String getCharacterReplaced() {
        return characterReplaced.get();
    }

    public void setCharacterReplaced(String characterReplaced) {
        this.characterReplaced.set(characterReplaced);
    }

    public String getState() {
        return state.get();
    }

    public void setState(String state) {
        this.state.set(state);
    }

    public String getDatetime() {
        return datetime.get();
    }

    public void setDatetime(String datetime) {
        this.datetime.set(datetime);
    }

    public TableDeleteProcess() {
    }

    public TableDeleteProcess(String pid, String name, long burst, int priority, String characters, String characterReplaced, String state, String datetime) {
        this.pid = new SimpleStringProperty(pid);
        this.name = new SimpleStringProperty(name);
        this.burst = new SimpleLongProperty(burst);
        this.priority = new SimpleIntegerProperty(priority);
        this.characters = new SimpleStringProperty(characters);
        this.characterReplaced = new SimpleStringProperty(characterReplaced);
        this.state = new SimpleStringProperty(state);
        this.datetime = new SimpleStringProperty(datetime);
    }
    
}
