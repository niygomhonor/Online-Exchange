package com.moringaschool.online_exchange.auth;


import android.net.Uri;

public class Users {
    String userId;
    String userObject;
    String UserBrand;
    String UserName;
    Uri imageObj;

    public Users(){

    }


    public Users(String userId, String userObject, String userBrand, String userName,Uri imageObj) {
        this.userId = userId;
        this.userObject = userObject;
        UserBrand = userBrand;
        UserName = userName;
this.imageObj=imageObj;
    }

    public Uri getImageObj() {
        return imageObj;
    }

    public void setImageObj(Uri imageObj) {
        this.imageObj = imageObj;
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


}
