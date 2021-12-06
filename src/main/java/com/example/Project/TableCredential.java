package com.example.Project;

public class TableCredential {
    public String userID;
    public String name;
    public String password;
    public String category;

    public TableCredential(String userID, String name, String password, String category)
    {
        this.userID = userID;
        this.name=name;;
        this.password = password;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String status) {
        this.password = password;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}