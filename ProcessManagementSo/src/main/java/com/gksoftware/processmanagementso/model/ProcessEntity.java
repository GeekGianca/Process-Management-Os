/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gksoftware.processmanagementso.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import java.io.Serializable;

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

    public ProcessEntity(String pid, String simulationProcess, String name, int priority, String characters,
            String charactersReplaced, int quantum) {
        this.pid = pid;
        this.simulationProcess = simulationProcess;
        this.name = name;
        this.priority = priority;
        this.characters = characters;
        this.charactersReplaced = charactersReplaced;
        this.quantum = quantum;
    }

    public ProcessEntity() {
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((characters == null) ? 0 : characters.hashCode());
        result = prime * result + ((charactersReplaced == null) ? 0 : charactersReplaced.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((pid == null) ? 0 : pid.hashCode());
        result = prime * result + priority;
        result = prime * result + quantum;
        result = prime * result + ((simulationProcess == null) ? 0 : simulationProcess.hashCode());
        return result;
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
        ProcessEntity other = (ProcessEntity) obj;
        if (characters == null) {
            if (other.characters != null) {
                return false;
            }
        } else if (!characters.equals(other.characters)) {
            return false;
        }
        if (charactersReplaced == null) {
            if (other.charactersReplaced != null) {
                return false;
            }
        } else if (!charactersReplaced.equals(other.charactersReplaced)) {
            return false;
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        if (pid == null) {
            if (other.pid != null) {
                return false;
            }
        } else if (!pid.equals(other.pid)) {
            return false;
        }
        if (priority != other.priority) {
            return false;
        }
        if (quantum != other.quantum) {
            return false;
        }
        if (simulationProcess == null) {
            if (other.simulationProcess != null) {
                return false;
            }
        } else if (!simulationProcess.equals(other.simulationProcess)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ProcessEntity [pid=" + pid + ", simulationProcess=" + simulationProcess + ", name=" + name
                + ", priority=" + priority + ", characters=" + characters + ", charactersReplaced=" + charactersReplaced
                + ", quantum=" + quantum + "]";
    }

}
