package com.moringaschool.online_exchange.Model;

public class BargainModel {
    String response;
    String id;
    int result;
    String msg;

    public BargainModel(String response, String id, int result, String msg) {
        this.response = response;
        this.id = id;
        this.result = result;
        this.msg = msg;
    }

    public BargainModel() {
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
