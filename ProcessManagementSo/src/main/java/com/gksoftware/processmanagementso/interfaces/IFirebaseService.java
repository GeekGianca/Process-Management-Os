/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gksoftware.processmanagementso.interfaces;


/**
 *
 * @author Geek-Programmer
 */
public interface IFirebaseService {
//    void setQuoteFromClassAvailable(Process process, String listName);
//    void setQuoteFromClassExecuted(ProcessComplete processComplete);
//    HashMap<String, String> getQuoteFromHTTP();
    void retrieveData();
    boolean updateData();
    void onChildChanged();
    void onChildAdded();
    void onChildRemoved();
}
