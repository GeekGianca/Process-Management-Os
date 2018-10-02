package com.gksoftware.processmanagement;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class FXMLController implements Initializable {

    @FXML
    private HBox hboxContainer;
    
    @FXML
    private JFXTextField processName;
    
    @FXML
    private JFXTextField priority;
    
    @FXML
    private JFXListView<Label> listNewProcess;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
    }
    
    @FXML
    private void createNewProcess(ActionEvent event) {
        try {
            System.out.println(processName.getText());
            Label lbl = new Label(processName.getText());
            lbl.setGraphic(new ImageView(new Image(new FileInputStream("C:/Users/Gianc/Documents/JavaProjects/ProcessManagement/ProcessManagementOs/ProcessManagement/src/main/resources/images/icons8_Server_48px.png"))));
            listNewProcess.getItems().add(lbl);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeVbox();
    }    

    private void initializeVbox() {
        /*Node[] nodes = new Node[10];
        for (int i = 0; i < nodes.length; i++) {
            try {
                nodes[i] = 
                //vboxItems.getChildren().add(nodes[i]);
            } catch (IOException ex) {
                Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }*/
    }
}
