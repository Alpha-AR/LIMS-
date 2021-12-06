package com.example.Project;

public class TableReturn {
    public String id;
    public String name;
    public String date;

    public TableReturn(String id, String name,String date)
    {
        this.id = id;
        this.name=name;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}