package com.gksoftware.processmanagement;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import javafx.application.Application;

import static javafx.application.Application.launch;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class MainApp extends Application {

    private ConfigurableApplicationContext springContext;
    private Parent root;

    private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void start(final Stage stage) throws Exception {
        //root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));

        stage.initStyle(StageStyle.TRANSPARENT);//remove task bar

        //Move frame
        root.setOnMousePressed((MouseEvent event) -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        root.setOnMouseDragged((MouseEvent event) -> {
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");

        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);

    }

    private static HashMap<String, String> getQuoteFromHTTP() {
        HashMap<String, String> map = new HashMap<>();
        map.put("process", "Process One");
        map.put("state", "Running");
        map.put("quantum", "5");
        return map;
    }

    @Override
    public void init() throws Exception {
        springContext = SpringApplication.run(MainApp.class);
        FXMLLoader fXMLLoader = new FXMLLoader(getClass().getResource("/fxml/Scene.fxml"));
        fXMLLoader.setControllerFactory(springContext::getBean);
        root = fXMLLoader.load();
    }

    @Override
    public void stop() throws Exception {
        springContext.close();
    }

}
/*

    private ConfigurableApplicationContext springContext;
    private Parent root;

    @Override
    public void stop() throws Exception {
        springContext.close();
    }

    @Override
    public void init() throws Exception {
        springContext = SpringApplication.run(MainApp.class);
        FXMLLoader fXMLLoader = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
        fXMLLoader.setControllerFactory(springContext::getBean);
        root = fXMLLoader.load();
    }

    @Override
    public void start(Stage stage) throws Exception {

        stage.initStyle(StageStyle.TRANSPARENT);//remove task bar

        //Move frame
        root.setOnMousePressed((MouseEvent event) -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        root.setOnMouseDragged((MouseEvent event) -> {
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");

        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
 */
 /*
    public static void main(String[] args) {
        Application.launch(args);
    }
 */
