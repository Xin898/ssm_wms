package com.xin.wms.pojo;

import java.io.Serializable;

//用户账户信息
public class UserInfoDO implements Serializable {

    //用户ID
    private Integer userID;

    //用户名
    private String userName;

    //用户密码
    private String password;

    //是否为初次登录
    private int firstLogin;

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getFirstLogin() {
        return firstLogin;
    }

    public void setFirstLogin(int firstLogin) {
        this.firstLogin = firstLogin;
    }

    @Override
    public String toString() {
        return "UserInfoDO{" +
                "userID=" + userID +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", firstLogin=" + firstLogin +
                '}';
    }
}
