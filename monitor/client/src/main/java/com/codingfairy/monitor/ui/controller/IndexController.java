package com.codingfairy.monitor.ui.controller;

import com.codingfairy.monitor.bean.ProbeStatus;
import com.codingfairy.monitor.bean.Process;
import com.codingfairy.monitor.core.StatusModel;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ProgressBarTableCell;
import javafx.scene.control.cell.PropertyValueFactory;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by cuihao on 2017-08-24.
 *
 */
public class IndexController extends BaseController {

    @FXML
    private TableView<ProbeStatus> statusTable;

    @FXML
    private TableView<Process> processTable;

    private StatusModel model;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initProcessTable();
        initStatusTable();
    }

    public void setStatus(StatusModel model) {
        this.model = model;
        statusTable.setItems(model.getProbeStatuses());
        processTable.setItems(model.getProcesses());
        model.setStatusTable(statusTable);
    }

    @FXML
    public void startSearch() {
        if (model != null) {
            model.startSearch();
        }
    }

    private void initProcessTable() {
        ObservableList<TableColumn<Process, ?>> processCols = processTable.getColumns();
        processCols.get(0).setCellValueFactory(new PropertyValueFactory<>("status"));
        TableColumn<Process,Double> processCol = new TableColumn<>("进度");
        processCol.setPrefWidth(475);
        processCol.setCellValueFactory(new PropertyValueFactory<>("progress"));
        processCol.setCellFactory(ProgressBarTableCell.forTableColumn());
        processCols.set(1,processCol);
        processCols.get(2).setCellValueFactory(new PropertyValueFactory<>("percent"));
        processCols.get(3).setCellValueFactory(new PropertyValueFactory<>("lastUpdate"));
    }

    private void initStatusTable() {
        ObservableList<TableColumn<ProbeStatus, ?>> statusColumns = statusTable.getColumns();
        statusColumns.get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        statusColumns.get(1).setCellValueFactory(new PropertyValueFactory<>("status"));
        statusColumns.get(2).setCellValueFactory(new PropertyValueFactory<>("ip"));
        TableColumn<ProbeStatus,String> actionCol = new TableColumn<>("操作");
        actionCol.setPrefWidth(250);
        actionCol.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));
        actionCol.setCellFactory(param -> new TableCell<ProbeStatus,String>() {
            final Button btn = new Button("管理");
            @Override
            public void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                    setText(null);
                } else {
                    btn.setOnAction(event -> {
                        ProbeStatus probeStatus = getTableView().getItems().get(getIndex());
                        try {
                            URI uri = new URI(probeStatus.getIp());
                            Desktop.getDesktop().browse(uri);
                        } catch (URISyntaxException | IOException e) {
                            e.printStackTrace();
                        }
                    });
                    setGraphic(btn);
                    setText(null);
                }
            }
        });
        statusColumns.add(actionCol);
    }

}
