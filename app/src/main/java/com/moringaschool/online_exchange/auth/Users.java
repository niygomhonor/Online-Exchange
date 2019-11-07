package com.moringaschool.online_exchange.auth;



public class Users {
    String userId;
    String userObject;
    String UserBrand;
    String UserName;
    int imageObj;

    public Users(){

    }


    public Users(String userId, String userObject, String userBrand, String userName) {
        this.userId = userId;
        this.userObject = userObject;
        UserBrand = userBrand;
        UserName = userName;

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

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUserObject(String userObject) {
        this.userObject = userObject;
    }

    public void setUserBrand(String userBrand) {
        UserBrand = userBrand;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public void setImageObj(int imageObj) {
        this.imageObj = imageObj;
    }
}
