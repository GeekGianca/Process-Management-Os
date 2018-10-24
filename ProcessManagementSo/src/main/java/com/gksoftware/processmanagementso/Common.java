/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gksoftware.processmanagementso;

import com.jfoenix.controls.JFXListView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import com.gksoftware.processmanagementso.model.Process;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;

/**
 * @author Geek-Programmer
 */
public class Common {
    public static int processAvailable;
    public static int processExecuted;
    public static int wsAvailable;
    public static int processEliminated;
    public static int chartsAvailables;
    public static long thmillis;
    public static long start;
    public static long end;
    public static List<Process> serviceListTime = new ArrayList<>();
    
    public static int QUANTUM = 5;
    public static final String REPLACEMENT_CHAR = "x";

    public static void loadRecentlyAddProcess(JFXListView<HBox> listView, Process process) {
        try {
            HBox hboxContainer = new HBox();
            Label lbl = new Label(process.getName());
            lbl.setFont(new Font("Century Gothic", 15));
            lbl.setStyle("-fx-font-family: bold");
            lbl.setTextFill(Color.web("#000000"));
            Label lbl2 = new Label(String.valueOf(process.getPriority()));
            lbl2.setFont(new Font("Century Gothic", 18));
            lbl2.setStyle("-fx-font-family: Bold Italic");
            lbl2.setTextFill(Color.web("#ff0000"));
            Label lbl3 = new Label(String.valueOf(process.getPid()));
            lbl3.setFont(new Font("Century Gothic", 18));
            lbl3.setStyle("-fx-font-family: Regular");
            lbl3.setTextFill(Color.web("#000000"));
            ImageView iv = new ImageView(new Image(new FileInputStream("C:/Users/Gianc/Documents/JavaProjects/ProcessManagement/ProcessManagementOs/ProcessManagement/src/main/resources/images/icons8_Server_48px.png")));
            iv.setFitWidth(20);
            iv.setFitHeight(30);
            hboxContainer.getChildren().add(iv);
            hboxContainer.getChildren().add(lbl3);
            hboxContainer.getChildren().add(lbl);
            hboxContainer.getChildren().add(lbl2);
            hboxContainer.setAlignment(Pos.CENTER_LEFT);
            hboxContainer.setPrefWidth(248);
            hboxContainer.setSpacing(15);
            listView.getItems().add(hboxContainer);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Common.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Process convertToProcess(String pid, String processName, int priority, String charReplaced, String characters) {
        Process process = null;
        if (!pid.isEmpty() && !processName.isEmpty() && priority > -1 && !charReplaced.isEmpty() && !characters.isEmpty()) {
            process = new Process();
            process.setPid(pid);
            process.setName(processName);
            process.setPriority(priority);
            process.setCharacters(characters);
            process.setCharacterReplaced(String.valueOf(charReplaced));
        }
        return process;
    }
    
    public static Alert showAlert(String message, Alert.AlertType type){
        Alert alert = new Alert(type);
        alert.setHeaderText(message);
        return alert;
    }
    
    

}
