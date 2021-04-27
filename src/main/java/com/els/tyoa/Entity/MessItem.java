package com.els.tyoa.Entity;

import java.sql.Timestamp;

public class MessItem {
    private int id;
    private String title;
    private String content;
    private String date;
    private boolean islook;
    private String param;
    private int handler;
    private int requester;
    private int root;

    public MessItem(int id, String title, String content, String date,boolean islook,String param,int handler,int requester,int root) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.date = date;
        this.islook = islook;
        this.param = param;
        this.handler = handler;
        this.requester = requester;
        this.root = root;
    }

    public MessItem() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isIslook() {
        return islook;
    }

    public void setIslook(boolean islook) {
        this.islook = islook;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public int getHandler() {
        return handler;
    }

    public void setHandler(int handler) {
        this.handler = handler;
    }

    public int getRequester() {
        return requester;
    }

    public void setRequester(int requester) {
        this.requester = requester;
    }

    public int getRoot() {
        return root;
    }

    public void setRoot(int root) {
        this.root = root;
    }
}
