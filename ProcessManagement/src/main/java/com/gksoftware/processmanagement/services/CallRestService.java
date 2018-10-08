/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gksoftware.processmanagement.services;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.gksoftware.processmanagement.model.Process;

/**
 * @author Geek-Programmer
 */
@Component
public class CallRestService {
    public void callrestService() {
        RestTemplate restTemplate = new RestTemplate();
        Process process = restTemplate.getForObject("https://process-rest-api.herokuapp.com/process", Process.class);
        System.out.println(process.toString());
    }
}
