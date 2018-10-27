/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gksoftware.processmanagementso.services;

import com.gksoftware.processmanagementso.model.ProcessList;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.client.RestTemplate;

/**
 * @author Geek-Programmer
 */
public class CallRestService {
    private ProcessList processList;
    
    public boolean callrestService(int idprocess) {
        RestTemplate restTemplate = new RestTemplate();
        processList = restTemplate.getForObject("http://api-cpu.herokuapp.com/api/process/simulation?id="+idprocess, ProcessList.class);        
        return processList.getProcessList() != null;
    }
    
    public List<String> getSimulations(){
        List<String> list = new ArrayList<>();
        RestTemplate restTemplate = new RestTemplate();
        int count = restTemplate.getForObject("https://api-cpu-simulator.firebaseio.com/api/max_simulation.json", Integer.class);
        for (int i = 0; i < count-1; i++) {
            list.add(String.format("Simulation %s", i+1));
        }
        return list;
    }

    public ProcessList getProcessList() {
        return processList;
    }
    
}
