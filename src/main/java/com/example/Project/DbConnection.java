package com.example.Project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    Connection con;

    public static Connection ConnectDB() {
        try {
            Connection con = DriverManager.getConnection("jdbc:sqlite:/home/user/IdeaProjects/demo1/src/main/java/com/example/Project/ILMS.sqlite");
            System.out.println("#### Database connection established!! #### : ");
            return con;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
