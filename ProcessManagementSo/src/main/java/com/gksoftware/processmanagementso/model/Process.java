/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gksoftware.processmanagementso.model;

import com.gksoftware.processmanagementso.Common;

/**
 * @author Geek-Programmer
 */
public class Process {

    private String pid;
    private String name;
    private int priority;
    private String sid;
    private String characters;
    private String characterReplaced;
    private int burst;//This really is quantity of characters to replace
    private long burstTime;
    private int burstResidue;
    private long timeArrival;
    private long turnAround;
    private long endtime;
    private int cicle;
    private long serviceTime;
    //
    private int positionView;
    private int positionsChange[];
    private int currentPositionReplaced;

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public long getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(long serviceTime) {
        this.serviceTime = serviceTime;
    }

    public int getCicle() {
        return cicle;
    }

    public void setCicle(int cicle) {
        this.cicle = cicle;
    }

    public int getCurrentPositionReplaced() {
        return currentPositionReplaced;
    }

    public void setCurrentPositionReplaced(int currentPositionReplaced) {
        this.currentPositionReplaced = currentPositionReplaced;
    }

    public int getPositionView() {
        return positionView;
    }

    public void setPositionView(int positionView) {
        this.positionView = positionView;
    }

    public int getPositionsChange(int position) {
        return positionsChange[position];
    }

    public void setPositionsChange(int positionsChange, int position) {
        this.positionsChange[position] = positionsChange;
    }

    public void setBurstTime(long burstTime) {
        this.burstTime = burstTime;
    }

    public long getBurstTime() {
        return burstTime;
    }

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

    public long getTimeArrival() {
        return timeArrival;
    }

    public void setTimeArrival(long timeArrival) {
        this.timeArrival = timeArrival*1000;
    }

    public long getTurnAround() {
        return turnAround;
    }

    public void setTurnAround(long turnAround) {
        this.turnAround = turnAround;
    }

    public long getEndtime() {
        return endtime;
    }

    public void setEndtime(long endtime) {
        this.endtime = endtime;
    }

    public Process() {
    }

    public Process(String pid, String name, int priority, String characters, String characterReplaced, int burst, int burstTime, int burstResidue, long timeArrival, long turnAround, long endtime) {
        this.pid = pid;
        this.name = name;
        this.priority = priority;
        this.characters = characters;
        this.characterReplaced = characterReplaced;
        this.burst = burst;
        this.burstTime = burstTime;
        this.burstResidue = burstResidue;
        this.timeArrival = timeArrival;
        this.turnAround = turnAround;
        this.endtime = endtime;
        this.currentPositionReplaced = -1;
        this.cicle = 0;
        this.serviceTime = 0;
    }

    public void convertProcess(ProcessTable process) {
        System.out.println("ConvertProcess");
        this.setPid(process.getPid());
        this.setName(process.getName());
        this.setPriority(process.getPriority());
        this.setCharacters(process.getCharacters());
        this.setCharacterReplaced(String.valueOf(process.getCharacterReplaced()));
        this.setSid(process.getSid());
        System.out.println("Character Replacement: " + getCharacters());
        System.out.println("Character Replaced: " + getCharacterReplaced());
        getBurstByProcess();
    }

    private void getBurstByProcess() {
        System.out.println("getBurstByRpocess");
        int position = 0;
        int counter = 0;
        position = characters.toLowerCase().indexOf(getCharacterReplaced());
        System.out.println("First Position: " + position);
        while (position != -1) {
            counter++;
            position = characters.toLowerCase().indexOf(getCharacterReplaced(), position + 1);
            System.out.println("Next Position: " + position);
        }
        if (counter > 0) {
            System.out.println("Burst: " + counter);
            this.setBurst(counter);
            this.setBurstResidue(counter);
            this.positionsChange = new int[counter];
            savePositionsByCharacters();
        } else {
            this.setBurst(0);
            setBurstResidue(0);
            System.out.println("Burst Time: " + Common.thmillis);
        }
    }

    private void savePositionsByCharacters() {
        int position = 0;
        int counter = 0;
        position = characters.toLowerCase().indexOf(getCharacterReplaced());
        System.out.println("First Position To Array: " + position);
        this.positionsChange[counter] = position;
        while (position != -1) {
            counter++;
            position = characters.toLowerCase().indexOf(getCharacterReplaced(), position + 1);
            if (position > -1) {
                this.positionsChange[counter] = position;
                System.out.println("Position Change: " + position);
            }
            System.out.println("Up..");
        }
        //setBurstTime();
    }

//    private void setBurstTime() {
//        System.out.println("Run...");
//        //BT=TT*((quantityCharactersReplaced))
//        long bt = Common.thmillis * (getBurst());
//        setBurstTime(bt);
//        System.out.println("Burst Time: " + bt);
//    }

    @Override
    public String toString() {
        return "<Process>\n"
                + "\t<pid>" + pid + "</pid>\n"
                + "\t<name>" + name + "</name>\n"
                + "\t<priority>" + priority + "</priority>\n"
                + "\t<characters>" + characters + "</characters>\n"
                + "\t<characterReplaced>" + characterReplaced + "</characterReplaced>\n"
                + "\t<burst>" + burst + "</burst>\n"
                + "\t<burstResidue>" + burstResidue + "</burstResidue>\n"
                + "\t<burstTime>" + burstTime + "</burstTime>\n"
                + "\t<timeArrival>" + timeArrival + "</timeArrival>\n"
                + "\t<turnAround>" + turnAround + "</turnAround>\n"
                + "\t<endTime>" + endtime + "</endTime>\n"
                + "\t<cicle>" + cicle + "</cicle>\n"
                + "\t<simulation_id>" + sid + "</simulation_id>\n"
                + "</Process>\n\n";
    }

}
