package com.codingfairy.monitor.main;

import com.codingfairy.monitor.ui.UIManager;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by cuihao on 2017-08-24.
 * Application entrance
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent parent = UIManager.instance().initialize(primaryStage);
        if (parent != null) {
            Scene scene = new Scene(parent);
            primaryStage.setScene(scene);
            primaryStage.show();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
