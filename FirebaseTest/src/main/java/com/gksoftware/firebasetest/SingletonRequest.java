/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gksoftware.firebasetest;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

/**
 *
 * @author Geek-Programmer
 */
public class SingletonRequest {
    private static SingletonRequest instance;
    private HttpPost request;
    private final String BASE_URL = "http://api-cpu.herokuapp.com/simulation/process/status";
    private StringEntity params;
    private CloseableHttpClient httpClient;
    private HttpResponse response;
    
    private SingletonRequest(String params) {
        try {
            this.httpClient = HttpClientBuilder.create().build();
            this.request = new HttpPost(this.BASE_URL);
            this.params = new StringEntity(params);
            this.request.addHeader("Content-Type", "application/json");
            this.request.setEntity(this.params);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(SingletonRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static SingletonRequest getInstance(String params){
        if (instance == null) {
            instance = new SingletonRequest(params);
        }
        return instance;
    }
    
    public void executeRequest(){
        try {
            this.response = this.httpClient.execute(this.request);
            System.out.println(this.response);
        } catch (IOException ex) {
            Logger.getLogger(SingletonRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
