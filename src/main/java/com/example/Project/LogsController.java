package com.example.Project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LogsController {

    public void initialize(){
        loadTable();
    }



    Connection conLogs = DbConnection.ConnectDB();

    @FXML
    private TableColumn<TableLogs, String> SN;

    @FXML
    private TableColumn<TableLogs, String> actionCol;

    @FXML
    private TableColumn<TableLogs, String> eIdCol;

    @FXML
    private TableColumn<TableLogs, String> issuerCol;

    @FXML
    private Button prevPage;

    @FXML
    private TableView<TableLogs> tableLogs;

    @FXML
    private TableColumn<TableLogs, String> timeCol;

    @FXML
    private TableColumn<TableLogs, String> userCol;

    @FXML
    void getSelected(MouseEvent event) {

    }
//changed Inv to Log
    @FXML
    void prevButtonPress(ActionEvent event) {
        try {
            Parent workViewParentLog;
            Scene workSceneLog;
            Stage windowLog;
            URL resource = this.getClass().getResource("credential_page.fxml");
            System.out.println("# LOGS CONTROLLER: Resource found at: " + resource);
            FXMLLoader loader = new FXMLLoader(resource);
            Parent rootLog = loader.load();
            System.out.println("# LOGS CONTROLLER: Parent root loaded");
            workSceneLog = new Scene(rootLog);
            windowLog = (Stage) ((Node) event.getSource()).getScene().getWindow();
            windowLog.setTitle("Credentials");
            windowLog.setScene(workSceneLog);
            windowLog.show();
            conLogs.close();
        }
        catch(Exception var9){
        }
    }
    void loadTable(){
        this.objjlist.clear();

        try {
            ResultSet rs1 = conLogs.createStatement().executeQuery("select * from logs");

            while (rs1.next())
                this.objjlist.add(new TableLogs(rs1.getString("sn"), rs1.getString("e_id"), rs1.getString("u_id"), rs1.getString("x_u_id"), rs1.getString("time"), rs1.getString("action") ) );

        } catch (SQLException var4) {
            var4.printStackTrace();
        }

        this.SN.setCellValueFactory(new PropertyValueFactory("sn"));
        this.eIdCol.setCellValueFactory(new PropertyValueFactory("eId"));
        this.userCol.setCellValueFactory(new PropertyValueFactory("uId"));
        this.issuerCol.setCellValueFactory(new PropertyValueFactory("issuerID"));
        this.timeCol.setCellValueFactory(new PropertyValueFactory("time"));
        this.actionCol.setCellValueFactory(new PropertyValueFactory("action"));

        this.tableLogs.setItems(this.objjlist);
    }

    ObservableList<TableLogs> objjlist = FXCollections.observableArrayList();
}
