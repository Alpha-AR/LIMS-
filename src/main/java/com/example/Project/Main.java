package com.example.Project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Main extends Application {
    public static void main(String[] args) {
        new DbConnection();
        DbConnection.ConnectDB();
        launch();

    }
    @Override
    public void start(@org.jetbrains.annotations.NotNull Stage stage) throws IOException {
        System.out.println("# STARTING Lab Inventory Management System #");
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login_page.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Lab Inventory Management System");
        stage.initStyle(StageStyle.DECORATED);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}