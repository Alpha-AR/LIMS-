package com.example.Project;

public class TableLogs {
    public String sn;
    public String eId;
    public String uId;
    public String issuerID;
    public String time;
    public String action;

    public TableLogs(String sn, String eId, String uId, String issuerID,String time,String action)
    {
        this.sn = sn;
        this.eId = eId;;
        this.uId = uId;
        this.issuerID = issuerID;
        this.time = time;
        this.action = action;
    }
    public String getSn() {
        return sn;
    }

    public void setSn(String sn) { this.sn = sn; }

    public String getEId() {
        return eId;
    }

    public void setEId(String eId) {
        this.eId = eId;
    }

    public String getUId() {
        return uId;
    }

    public void setUId(String uId) {
        this.uId = uId;
    }

    public String getIssuerID() {
        return issuerID;
    }

    public void setIssuerID(String issuerID) {
        this.issuerID = issuerID;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}