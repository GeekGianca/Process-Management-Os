/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gksoftware.processmanagement.services;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.gksoftware.processmanagement.model.ProcessList;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Geek-Programmer
 */
@Component
public class CallRestService {
    private ProcessList processList;
    
    public void callrestService(int idprocess) {
        RestTemplate restTemplate = new RestTemplate();
        processList = restTemplate.getForObject("http://api-cpu.herokuapp.com/api/process/simulation?id="+idprocess, ProcessList.class);        
    }
    
    public List<String> getSimulations(){
        List<String> list = new ArrayList<>();
        RestTemplate restTemplate = new RestTemplate();
        int count = restTemplate.getForObject("https://api-cpu-simulator.firebaseio.com/api/max_simulation.json", Integer.class);
        for (int i = 0; i < count; i++) {
            list.add(String.format("Simulation %s", i+1));
        }
        return list;
    }

    public ProcessList getProcessList() {
        return processList;
    }
    
}
