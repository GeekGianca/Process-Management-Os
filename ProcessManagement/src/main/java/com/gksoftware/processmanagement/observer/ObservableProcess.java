/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gksoftware.processmanagement.observer;

/**
 *
 * @author Geek-Programmer
 */
public abstract class ObservableProcess {
    protected Subject subject;
    public abstract void update();
}
