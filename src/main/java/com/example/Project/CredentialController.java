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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CredentialController {

    public void initialize(){
        loadTable();
    }

    Connection conCredential = DbConnection.ConnectDB();

    @FXML
    private Button addUserButton;

    @FXML
    private TextField userIDTextFeild;

    @FXML
    private RadioButton adminPrivilegeButton;

    @FXML
    private TableColumn<TableInventory, String> categoryCol;

    @FXML
    private Button deleteButton;

    @FXML
    private Button logoutButton;

    @FXML
    private Button nextPageButton;

    @FXML
    private TableColumn<TableInventory, String> passwordCol;

    @FXML
    private TextField passwordTextField;

    @FXML
    private Button prevButton;

    @FXML
    private TableView<TableCredential> tableCredential;

    @FXML
    private Button updateButtonPress;

    @FXML
    private TableColumn<TableInventory, String> userIdCol;

    @FXML
    private TextField userIdDelete;

    @FXML
    private TableColumn<TableInventory, String> userNameCol;

    @FXML
    private TextField usernameTextField;

    @FXML
    void addUserButtonPress(ActionEvent event) {
        String query = "INSERT INTO credentials(u_id,u_name,u_pw,u_cat) VALUES(?,?,?,?)";
        PreparedStatement pst ;
        try {
            System.out.println("###### insert user statement###");
            pst = conCredential.prepareStatement(query);
            String status;
            if(adminPrivilegeButton.isSelected()) status="Admin";
            else status="User";
            pst.setString(1,this.userIDTextFeild.getText());
            pst.setString(2, this.usernameTextField.getText());
            pst.setString(3, this.passwordTextField.getText());
            pst.setString(4, status);
            System.out.println(query);
            pst.executeUpdate();
            System.out.println("###### insert user completed##");

        } catch (Exception e) {
            System.out.println("Exception while executing add statement "+e);
        }
    }

    @FXML
    void deleteUserButtonPress(ActionEvent event) throws SQLException {
        String query = "DELETE FROM credentials where u_id=?";
        PreparedStatement pst;
        try {
            System.out.println("###### delete user statement###");
            pst = conCredential.prepareStatement(query);
            String id= this.userIdDelete.getText();
            pst.setString(1, id);
            System.out.println(query);
            pst.executeUpdate();
            System.out.println("###### delete user completed##");

        } catch (Exception e) {
            System.out.println("Exception while executing delete statement "+e);
        }
    }

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
    void nextPageButtonPress(ActionEvent event) {
        try {
            Parent workViewParentCre;
            Scene workSceneCre;
            Stage windowCre;
            URL resource = this.getClass().getResource("logs_page.fxml");
            System.out.println("# CREDENTIAL CONTROLLER: Resource found at: " + resource);
            FXMLLoader loader = new FXMLLoader(resource);
            Parent rootCre = loader.load();
            System.out.println("# CREDENTIAL CONTROLLER: Parent root loaded");
            workSceneCre = new Scene(rootCre);
            windowCre = (Stage) ((Node) event.getSource()).getScene().getWindow();
            windowCre.setTitle("Logs");
            windowCre.setScene(workSceneCre);
            windowCre.show();
            conCredential.close();
        }
        catch(Exception var9){
        }
    }
//changed Inv to Cre
    @FXML
    void prevButtonPress(ActionEvent event) {
        try {
            Parent workViewParentCre;
            Scene workSceneCre;
            Stage windowCre;
            URL resource = this.getClass().getResource("inventory_page.fxml");
            System.out.println("# CREDENTIAL CONTROLLER: Resource found at: " + resource);
            FXMLLoader loader = new FXMLLoader(resource);
            Parent rootCre = loader.load();
            System.out.println("# CREDENTIAL CONTROLLER: Parent root loaded");
            workSceneCre = new Scene(rootCre);
            windowCre = (Stage) ((Node) event.getSource()).getScene().getWindow();
            windowCre.setTitle("Inventory");
            windowCre.setScene(workSceneCre);
            windowCre.show();
            conCredential.close();
        }
        catch(Exception var9){
        }
    }

    @FXML
    void updateButtonPress(ActionEvent event) {
        loadTable();
    }

    void loadTable(){
        this.objlist.clear();
        //Connection conInventory = DbConnection.ConnectDB();

        try {
            ResultSet rs1 = conCredential.createStatement().executeQuery("select * from credentials");

            while (rs1.next())
                this.objlist.add(new TableCredential( rs1.getString("u_id"), rs1.getString("u_name"), rs1.getString("u_pw"), rs1.getString("u_cat")) );

        } catch (SQLException var4) {
            var4.printStackTrace();
        }

        this.userIdCol.setCellValueFactory(new PropertyValueFactory("UserID"));
        this.userNameCol.setCellValueFactory(new PropertyValueFactory("Name"));
        this.passwordCol.setCellValueFactory(new PropertyValueFactory("Password"));
        this.categoryCol.setCellValueFactory(new PropertyValueFactory("Category"));
        this.tableCredential.setItems(this.objlist);
        //this.labelSuccess.setText("Data Refreshed.");


    }

    ObservableList<TableCredential> objlist = FXCollections.observableArrayList();

}
