package com.codingfairy.monitor.ui;

import com.codingfairy.monitor.core.StatusModel;
import com.codingfairy.monitor.tools.ResourceTools;
import com.codingfairy.monitor.ui.constant.View;
import com.codingfairy.monitor.ui.controller.IndexController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by cuihao on 2017-08-24.
 * UI core controller
 */
public final class UIManager {

    private Parent rootParent;

    private Stage rootStage;

    public static UIManager instance() {
        return ClassHolder.manager;
    }

    public Parent initialize(Stage stage) {
        rootStage  = stage;
        FXMLLoader loader = ResourceTools.getFxmlConfig(View.INDEX);
        try {
            Parent parent = loader.load();
            initMvc(loader);
            rootParent = parent;
            return parent;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void initMvc(FXMLLoader loader) {
        StatusModel statusModel = new StatusModel();
        IndexController controller = loader.getController();
        controller.setStatus(statusModel);
    }

    private UIManager() {}

    private static class ClassHolder {
        private static UIManager manager = new UIManager();
    }

}
