/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gksoftware.firebasetest;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author Geek-Programmer
 */
public class Main {

    public static void main(String[] args) throws FileNotFoundException, IOException {
//        FileInputStream serviceAccount = new FileInputStream("api-cpu-simulator-firebase-adminsdk-z23kt-66f23b7203.json");
//        FirebaseOptions options = new FirebaseOptions.Builder()
//                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
//                .setDatabaseUrl("https://api-cpu-simulator.firebaseio.com")
//                .build();
//        FirebaseApp.initializeApp(options);
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference reference = database.getReference("api/cpu_list/simulation_1/DQQLFWK8S");
//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot ds) {
//                System.out.println(ds.getValue().toString());
//            }
//
//            @Override
//            public void onCancelled(DatabaseError de) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//            }
//        });
//        Map<String, ProcessCpu> process = new HashMap<>();
//        process.put("Test", new ProcessCpu("Test para nuevo proceso", "a", "Test", "126546", 0, 3, "Waiting"));
//        reference.setValueAsync(process);
        UpdateProcess process = new UpdateProcess("simulation_1", "DQQLFWK8S", "Ready");
        System.out.println(process.toString());
        SingletonRequest request = SingletonRequest.getInstance(process.toString());
        request.executeRequest();
    }
}
