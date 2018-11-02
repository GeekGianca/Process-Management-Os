/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gksoftware.processmanagementso.services;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
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

    private SingletonRequest() {
        this.httpClient = HttpClientBuilder.create().build();
        this.request = new HttpPost(this.BASE_URL);
        this.request.addHeader("Content-Type", "application/json");
    }

    public static SingletonRequest getInstance() {
        if (instance == null) {
            instance = new SingletonRequest();
        }
        return instance;
    }

    public void executeRequest(String parameters) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    params = new StringEntity(parameters);
                    request.setEntity(params);
                    response = httpClient.execute(request);
                    System.out.println(response);
                } catch (IOException ex) {
                    Logger.getLogger(SingletonRequest.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }, 5000);
    }
}
