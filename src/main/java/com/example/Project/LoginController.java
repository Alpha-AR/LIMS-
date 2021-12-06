package com.example.Project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import com.example.Project.DbConnection;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {

    @FXML
    public Button loginButton;

    @FXML
    public PasswordField passwordInput;

    @FXML
    public TextField usernameInput;

    public static String s1;
    public String s2;

    @FXML
    void loginButtonPress(ActionEvent event) throws SQLException {
        try {
            Connection conLogin = DbConnection.ConnectDB();
            System.out.println("# LOGIN CONTROLLER: connection complete");
            s1= this.usernameInput.getText();
            s2= this.passwordInput.getText();
            System.out.println("# LOGIN CONTROLLER: username: "+s1);
            System.out.println("# LOGIN CONTROLLER: password: "+s2);
            String query = "Select * from credentials where u_id=? and u_pw=? and u_cat=?";
            PreparedStatement pst = conLogin.prepareStatement(query);
            pst.setString(1, s1);
            pst.setString(2, s2);
            pst.setString(3, "Admin");
            ResultSet rs = pst.executeQuery();
            System.out.println("####### Result set executed " );

            Parent workViewParent;
            Scene workScene;
            Stage window;
            if (rs.next()) {
                System.out.println("# LOGIN CONTROLLER: inside rs.next(true) block");
                URL resource = this.getClass().getResource("inventory_page.fxml");
                System.out.println("# LOGIN CONTROLLER: Resource found at: "+resource);
                FXMLLoader loader = new FXMLLoader(resource);
                Parent root = loader.load();
                System.out.println("# LOGIN CONTROLLER: Parent root loaded");
                workScene = new Scene(root);
                window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setTitle("Inventory");
                window.setScene(workScene);
                window.show();
                conLogin.close();
                System.out.println("CONNECTION CLOSED ###");
            }
            else {
                pst.setString(3,"User");
                rs = pst.executeQuery();
                if(rs.next()) {
                    System.out.println("# LOGIN CONTROLLER: inside rs.next()-else block");
                    URL resource = this.getClass().getResource("return_page.fxml");
                    System.out.println("# LOGIN CONTROLLER: Resource found at: "+resource);
                    FXMLLoader loader = new FXMLLoader(resource);
                    Parent root = loader.load();
                    System.out.println("# LOGIN CONTROLLER: Parent root loaded");
                    workScene = new Scene(root);
                    window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    window.setTitle("User Return Page");
                    window.setScene(workScene);
                    window.show();
                    conLogin.close();
                    System.out.println("CONNECTION CLOSED ###");
                }
                conLogin.close();
                System.out.println("CONNECTION CLOSED ###");
            }
        } catch (Exception var9) {
        }
    }
}