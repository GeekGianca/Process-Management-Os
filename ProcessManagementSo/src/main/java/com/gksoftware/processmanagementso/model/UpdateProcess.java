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
public class UpdateProcess {
    private String id_simulation;
    private String id_process;
    private int status;

    public UpdateProcess() {
    }

    public UpdateProcess(String id_simulation, String id_process, String status) {
        this.id_simulation = id_simulation;
        this.id_process = id_process;
        switch (status) {
            case "Ready":
                this.status = 0;
                break;
            case "Running":
                this.status = 1;
                break;
            case "Finished":
                this.status = 2;
                break;
        }

    }

    public String getId_simulation() {
        return id_simulation;
    }

    public void setId_simulation(String id_simulation) {
        this.id_simulation = id_simulation;
    }

    public String getId_process() {
        return id_process;
    }

    public void setId_process(String id_process) {
        this.id_process = id_process;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "{" + "\"id_simulation\":\"" + id_simulation + "\", \"id_process\":\"" + id_process + "\", \"status\":\"" + status + "\"}";
    }
}
