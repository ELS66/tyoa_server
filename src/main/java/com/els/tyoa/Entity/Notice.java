package com.els.tyoa.Entity;

public class Notice {
    private int id;
    private String mess;
    private String name;

    public Notice(int id, String mess, String name) {
        this.id = id;
        this.mess = mess;
        this.name = name;
    }

    public Notice() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMess() {
        return mess;
    }

    public void setMess(String mess) {
        this.mess = mess;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
