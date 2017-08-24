package com.codingfairy.monitor.ui.controller;

import com.codingfairy.monitor.bean.ProbeStatus;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by cuihao on 2017-08-24.
 *
 */
public class IndexController extends BaseController {

    @FXML
    private Button searchButton;

    @FXML
    private TableView<ProbeStatus> statusTable;

    @FXML
    private TableView<Process> processTable;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
