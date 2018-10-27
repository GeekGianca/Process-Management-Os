/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gksoftware.firebasetest;

/**
 *
 * @author Geek-Programmer
 */
class ProcessCpu {
    private String characters;
    private String charactersReplaced;
    private String name;
    private String pid;
    private int priority;
    private int s_id;
    private String status;

    public String getCharacters() {
        return characters;
    }

    public void setCharacters(String characters) {
        this.characters = characters;
    }

    public String getCharactersReplaced() {
        return charactersReplaced;
    }

    public void setCharactersReplaced(String charactersReplaced) {
        this.charactersReplaced = charactersReplaced;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getS_id() {
        return s_id;
    }

    public void setS_id(int s_id) {
        this.s_id = s_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ProcessCpu(String characters, String charactersReplaced, String name, String pid, int priority, int s_id, String status) {
        this.characters = characters;
        this.charactersReplaced = charactersReplaced;
        this.name = name;
        this.pid = pid;
        this.priority = priority;
        this.s_id = s_id;
        this.status = status;
    }

    public ProcessCpu() {
    }

    @Override
    public String toString() {
        return "ProcessFirebase{" + "characters=" + characters + ", charactersReplaced=" + charactersReplaced + ", name=" + name + ", pid=" + pid + ", priority=" + priority + ", s_id=" + s_id + ", status=" + status + '}';
    }
}
