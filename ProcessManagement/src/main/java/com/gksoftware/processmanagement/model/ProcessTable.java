/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gksoftware.processmanagement.model;

/**
 *
 * @author Geek-Programmer
 */
public class ProcessTable {
    private String pid;
    private String name;
    private int burst;
    private int priority;
    private int burstresidue;
    private String characters;
    private char characterReplaced;
    private String state;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBurst() {
        return burst;
    }

    public void setBurst(int burst) {
        this.burst = burst;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getBurstresidue() {
        return burstresidue;
    }

    public void setBurstresidue(int burstresidue) {
        this.burstresidue = burstresidue;
    }

    public String getCharacters() {
        return characters;
    }

    public void setCharacters(String characters) {
        this.characters = characters;
    }

    public char getCharacterReplaced() {
        return characterReplaced;
    }

    public void setCharacterReplaced(char characterReplaced) {
        this.characterReplaced = characterReplaced;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public ProcessTable() {
    }

    public ProcessTable(String pid, String name, int burst, int priority, int burstresidue, String characters, char characterReplaced, String state) {
        this.pid = pid;
        this.name = name;
        this.burst = burst;
        this.priority = priority;
        this.burstresidue = burstresidue;
        this.characters = characters;
        this.characterReplaced = characterReplaced;
        this.state = state;
    }
    
}
