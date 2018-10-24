/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gksoftware.processmanagementso.firebase;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Geek-Programmer
 */
public class FirebaseService {
    private Firestore db;
    private DocumentReference docRef;
    public FirebaseService() {
        FileInputStream serviceAccount = null;
        try {
            serviceAccount = new FileInputStream("processmanagement-71c5a-firebase-adminsdk-i3vda-129b1316a7.json");
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();
            FirebaseApp.initializeApp(options);
            db = FirestoreClient.getFirestore();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FirebaseService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FirebaseService.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                serviceAccount.close();
            } catch (IOException ex) {
                Logger.getLogger(FirebaseService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void save(){
        try {
            docRef = db.collection("ProcessList").document("Process001");
            HashMap<String, String> quote = getQuoteFromHTTP();
            ApiFuture<WriteResult> future = docRef.set(quote);
            System.out.println("Successfully update at: "+future.get().getUpdateTime());
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(FirebaseService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private HashMap<String, String> getQuoteFromHTTP() {
        HashMap<String, String> map = new HashMap<>();
        map.put("process", "Process One");
        map.put("state", "Running");
        map.put("quantum", "5");
        return map;
    }
}
