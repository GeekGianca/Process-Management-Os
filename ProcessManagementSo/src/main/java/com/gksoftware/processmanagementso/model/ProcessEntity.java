/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gksoftware.processmanagementso.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Geek-Programmer
 */
@JacksonXmlRootElement(localName = "Process")
public class ProcessEntity implements Serializable {

    /**
     * @serialVersion
     */
    private static final long serialVersionUID = -8899094488583446792L;

    //@JacksonXmlProperty(isAttribute = true)
    @JacksonXmlProperty
    private String pid;
    @JacksonXmlProperty
    private String simulationProcess;
    @JacksonXmlProperty
    private String name;
    @JacksonXmlProperty
    private int priority;
    @JacksonXmlProperty
    private String characters;
    @JacksonXmlProperty
    private String charactersReplaced;
    @JacksonXmlProperty
    private int quantum;
    @JacksonXmlProperty
    private int s_id;

    public int getS_id() {
        return s_id;
    }

    public void setS_id(int s_id) {
        this.s_id = s_id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getSimulationProcess() {
        return simulationProcess;
    }

    public void setSimulationProcess(String simulationProcess) {
        this.simulationProcess = simulationProcess;
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

    public String getCharactersReplaced() {
        return charactersReplaced;
    }

    public void setCharactersReplaced(String charactersReplaced) {
        this.charactersReplaced = charactersReplaced;
    }

    public int getQuantum() {
        return quantum;
    }

    public void setQuantum(int quantum) {
        this.quantum = quantum;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public ProcessEntity(String pid, String simulationProcess, String name, int priority, String characters, String charactersReplaced, int quantum, int s_id) {
        this.pid = pid;
        this.simulationProcess = simulationProcess;
        this.name = name;
        this.priority = priority;
        this.characters = characters;
        this.charactersReplaced = charactersReplaced;
        this.quantum = quantum;
        this.s_id = s_id;
    }

    public ProcessEntity() {
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.pid);
        hash = 31 * hash + Objects.hashCode(this.simulationProcess);
        hash = 31 * hash + Objects.hashCode(this.name);
        hash = 31 * hash + this.priority;
        hash = 31 * hash + Objects.hashCode(this.characters);
        hash = 31 * hash + Objects.hashCode(this.charactersReplaced);
        hash = 31 * hash + this.quantum;
        hash = 31 * hash + this.s_id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ProcessEntity other = (ProcessEntity) obj;
        if (this.priority != other.priority) {
            return false;
        }
        if (this.quantum != other.quantum) {
            return false;
        }
        if (this.s_id != other.s_id) {
            return false;
        }
        if (!Objects.equals(this.pid, other.pid)) {
            return false;
        }
        if (!Objects.equals(this.simulationProcess, other.simulationProcess)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.characters, other.characters)) {
            return false;
        }
        if (!Objects.equals(this.charactersReplaced, other.charactersReplaced)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ProcessEntity{" + "pid=" + pid + ", simulationProcess=" + simulationProcess + ", name=" + name + ", priority=" + priority + ", characters=" + characters + ", charactersReplaced=" + charactersReplaced + ", quantum=" + quantum + ", s_id=" + s_id + '}';
    }

}
