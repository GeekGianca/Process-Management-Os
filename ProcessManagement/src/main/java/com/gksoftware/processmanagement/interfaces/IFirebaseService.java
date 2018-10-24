/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gksoftware.processmanagement.interfaces;

import java.util.HashMap;

import com.gksoftware.processmanagement.model.Process;
import com.gksoftware.processmanagement.model.ProcessComplete;

/**
 *
 * @author Geek-Programmer
 */
public interface IFirebaseService {
    void setQuoteFromClassAvailable(Process process, String listName);
    void setQuoteFromClassExecuted(ProcessComplete processComplete);
    HashMap<String, String> getQuoteFromHTTP();
}
