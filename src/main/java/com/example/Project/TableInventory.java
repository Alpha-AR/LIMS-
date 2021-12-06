package com.example.Project;

public class TableInventory {
    public String id;
    public String name;
    public String status;
    public String date;

    public TableInventory(String id, String name, String status, String date)
    {
        this.id = id;
        this.name=name;;
        this.status = status;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}