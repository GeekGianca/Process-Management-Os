/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gksoftware.processmanagement.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Geek-Programmer
 */
@JacksonXmlRootElement(localName = "ProcessList")
public class ProcessList implements Serializable {

    /**
     * @serialVersion
     */
    private static final long serialVersionUID = 1L;
    @JacksonXmlProperty(localName = "Process")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<ProcessEntity> processList;
    @JacksonXmlProperty
    private int quantum;

    public List<ProcessEntity> getProcessList() {
        return processList;
    }

    public void setProcessList(List<ProcessEntity> processList) {
        this.processList = processList;
    }

    public int getQuantum() {
        return quantum;
    }

    public void setQuantum(int quantum) {
        this.quantum = quantum;
    }

    public ProcessList(List<ProcessEntity> processList) {
        super();
        this.processList = processList;
    }

    public ProcessList() {
    }

    @Override
    public String toString() {
        String toString = "";
        for (int i = 0; i < this.getProcessList().size(); i++) {
            toString += this.getProcessList().get(i) + "\n";
        }
        return toString;
    }

}
