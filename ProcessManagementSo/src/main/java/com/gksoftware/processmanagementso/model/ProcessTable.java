/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gksoftware.processmanagementso.model;

/**
 *
 * @author Geek-Programmer
 */
public class ProcessTable {

    private String pid;
    private String name;
    private long burst;
    private int priority;
    private long burstresidue;
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

    public long getBurst() {
        return burst;
    }

    public void setBurst(long burst) {
        this.burst = burst;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public long getBurstresidue() {
        return burstresidue;
    }

    public void setBurstresidue(long burstresidue) {
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

    @Override
    public String toString() {
        return "ProcessTable{\n" + "\t\tpid=" + pid + ",\n\t\tname=" + name + ",\n\t\tburst=" + burst + ",\n\t\tpriority=" + priority + ",\n\t\tburstresidue=" + burstresidue + ",\n\t\tcharacters=" + characters + ",\n\t\tcharacterReplaced=" + characterReplaced + ",\n\t\tstate=" + state + "\n" + '}';
    }

}
