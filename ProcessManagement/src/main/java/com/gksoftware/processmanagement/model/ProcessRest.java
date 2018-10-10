/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gksoftware.processmanagement.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Geek-Programmer
 */
@XmlRootElement(name = "Process")
//ArrayList<ProcessRest>
@XmlAccessorType(XmlAccessType.NONE)
public class ProcessRest {

    @XmlAttribute(name = "pid")
    private String pid;
    @XmlElement(name = "name")
    private String name;
    @XmlElement(name = "priority")
    private int priority;
    @XmlElement(name = "characters")
    private String characters;
    @XmlElement(name = "charactersReplacement")
    private String charactersReplacement;

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

    public String getCharactersReplacement() {
        return charactersReplacement;
    }

    public void setCharactersReplacement(String characters) {
        this.charactersReplacement = characters;
    }

    public String getCharacters() {
        return characters;
    }

    public void setCharacters(String characters) {
        this.characters = characters;
    }

    public ProcessRest() {
    }

    public ProcessRest(String pid, String name, int priority, String characters, String charactersReplacement) {
        this.pid = pid;
        this.name = name;
        this.priority = priority;
        this.characters = characters;
        this.charactersReplacement = charactersReplacement;
    }

    @Override
    public String toString() {
        return "ProcessRest{" + "pid=" + pid + ", name=" + name + ", priority=" + priority + ", characters=" + characters + ", charactersReplacement=" + charactersReplacement + '}';
    }
    
}
