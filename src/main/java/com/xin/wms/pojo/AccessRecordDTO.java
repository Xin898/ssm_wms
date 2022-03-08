package com.xin.wms.pojo;

import java.io.Serializable;

/*
 * @author xin
 */
public class AccessRecordDTO implements Serializable {

    // 登入登出的的id
    private Integer id;

    // 登入用户ID
    private Integer userID;

    //登陆用户名
    private String userName;

    //登入或登出时间
    private String accessTime;

    //用户登入或登出对应的IP地址
    private String accessIP;

    //记录类型，登入或登出
    private String accessType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getAccessTime() {
        return accessTime;
    }

    public void setAccessTime(String accessTime) {
        this.accessTime = accessTime;
    }

    public String getAccessIP() {
        return accessIP;
    }

    public void setAccessIP(String accessIP) {
        this.accessIP = accessIP;
    }

    public String getAccessType() {
        return accessType;
    }

    public void setAccessType(String accessType) {
        this.accessType = accessType;
    }

    @Override
    public String toString() {
        return "AccessRecordDTO{" +
                "id=" + id +
                ", userID=" + userID +
                ", userName='" + userName + '\'' +
                ", accessTime='" + accessTime + '\'' +
                ", accessIP='" + accessIP + '\'' +
                ", accessType='" + accessType + '\'' +
                '}';
    }
}
