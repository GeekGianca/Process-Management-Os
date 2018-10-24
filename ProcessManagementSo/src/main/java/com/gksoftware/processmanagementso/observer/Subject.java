/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gksoftware.processmanagementso.observer;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Geek-Programmer
 */
public class Subject {
    private List<ObservableProcess> observers = new ArrayList<>();
    private String state;
    
    public void add(ObservableProcess o){
        observers.add(o);
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
        
    }
    
    private void execute(){
        observers.forEach((observer) -> {
            observer.update();
        });
    }
}
