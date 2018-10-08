/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gksoftware.processmanagement.model;


/**
 * @author Geek-Programmer
 */

public class Process{

    private String pid;
    private String name;
    private int priority;
    private String characters;
    private String characterReplaced;
    private int burst;
    private int burstResidue;
    private Process next;//pointer to the next

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

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getCharacters() {
        return characters;
    }

    public void setCharacters(String characters) {
        this.characters = characters;
    }

    public String getCharacterReplaced() {
        return characterReplaced;
    }

    public void setCharacterReplaced(String characterReplaced) {
        this.characterReplaced = characterReplaced;
    }

    public int getBurst() {
        return burst;
    }

    public void setBurst(int burst) {
        this.burst = burst;
    }

    public int getBurstResidue() {
        return burstResidue;
    }

    public void setBurstResidue(int burstResidue) {
        this.burstResidue = burstResidue;
    }

    public Process getNext() {
        return next;
    }

    public void setNext(Process next) {
        this.next = next;
    }

    public Process() {
    }

    public Process(String pid, String name, int priority, String characters, String characterReplaced, int burst, int burstResidue) {
        this.pid = pid;
        this.name = name;
        this.priority = priority;
        this.characters = characters;
        this.characterReplaced = characterReplaced;
        this.burst = burst;
        this.burstResidue = burstResidue;
    }

    @Override
    public String toString() {
        return "<Process>\n\t" 
                    + "<pid>" + pid + "</pid>\n"
                    + "<name>" + name + "</name>\n"
                    + "<priority>" + priority + "</priority>\n"
                    + "<characters>" + characters + "</characters>\n"
                    + "<characterReplaced>" + characterReplaced + "</characterReplaced>\n"
                    + "<burst>" + burst + "</burst>\n"
                    + "<burstResidue>" + burstResidue + "</burstResidue>\n"
                + "</Process>";
    }
    
}
