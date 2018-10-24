/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gksoftware.processmanagementso.exceptions;

/**
 *
 * @author Geek-Programmer
 */
public class QueueEmptyException extends Exception {
    
    /**
     * Constant for serialization
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor with message
     *
     * @param message Error message
     */
    public QueueEmptyException(String message) {
        super(message);
    }
}
