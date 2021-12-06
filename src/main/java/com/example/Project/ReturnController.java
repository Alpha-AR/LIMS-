package com.example.Project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReturnController {
    public void initialize(){
        loadTable();
    }

    Connection conReturn = DbConnection.ConnectDB();

    @FXML
    private TableColumn<TableReturn, String> eIDCol;

    @FXML
    private TableColumn<TableReturn, String> eNameCol;

    @FXML
    private TableColumn<TableReturn, String> dateIssuedCol;

    @FXML
    private Button logoutButton;

    @FXML
    private Button returnItemButton;

    @FXML
    private TextField returnItemTextField;

    @FXML
    private TableView<TableReturn> tableReturn;

    @FXML
    private Button updateButton;

    @FXML
    void getSelected(MouseEvent event) {

    }

    @FXML
    void logoutButtonPress(ActionEvent event) {
        try {
            final Node source = (Node) event.getSource();
            final Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        } catch (Exception var9) {
        }

    }

    @FXML
    void returnItemUserButtonPress(ActionEvent event) {
        String query = "UPDATE inventory  SET e_status= 'Available' , d_issue = NULL where e_id=?";
        String logQuery = "INSERT into logs(e_id,u_id,time,action) values(?,?,?,'Returned')";

        PreparedStatement pst,pst1 ;
        try {
            System.out.println("###### return item statement###");
            pst = conReturn.prepareStatement(query);
            pst.setString(1, returnItemTextField.getText());
            System.out.println(query);
            pst.executeUpdate();
            System.out.println("###### return item statement completed##");

            System.out.println("###### log  statement###");
            pst1 = conReturn.prepareStatement(logQuery);
            pst1.setString(1,this.returnItemTextField.getText());
            pst1.setString(2,LoginController.s1);
            pst1.setString(3, String.valueOf(java.time.LocalDate.now()));
            System.out.println(logQuery);
            pst1.executeUpdate();
            System.out.println("###### log completed##");



        } catch (Exception e) {
            System.out.println("Exception while executing return item statement "+e);
        }
    }

    @FXML
    void updateButtonPress(ActionEvent event) {
        loadTable();

    }
    void loadTable(){
        this.obRlist.clear();

        try {
            ResultSet rs1 = conReturn.createStatement().executeQuery("select e_id, e_name, d_issue from inventory where e_status = '"+ LoginController.s1 + "'");

            while (rs1.next())
                this.obRlist.add(new TableReturn(rs1.getString("e_id"), rs1.getString("e_name"),  rs1.getString("d_issue")) );

        } catch (SQLException var4) {
            var4.printStackTrace();
        }

        this.eIDCol.setCellValueFactory(new PropertyValueFactory("id"));
        this.eNameCol.setCellValueFactory(new PropertyValueFactory("name"));
        this.dateIssuedCol.setCellValueFactory(new PropertyValueFactory("date"));
        this.tableReturn.setItems(this.obRlist);
    }

    ObservableList<TableReturn> obRlist = FXCollections.observableArrayList();
}