package com.moringaschool.online_exchange.auth;

public class Users {
    String userId;
    String userObject;
    String UserBrand;
    String UserName;
    int imageObj;

    public Users(String userId, String userObject, String userBrand, String userName,   int imageObj) {
        this.userId = userId;
        this.userObject = userObject;
        UserBrand = userBrand;
        UserName = userName;
        this.imageObj=imageObj;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserObject() {
        return userObject;
    }

    public String getUserBrand() {
        return UserBrand;
    }

    public String getUserName() {
        return UserName;
    }
}
