package com.xin.wms.pojo;

import java.io.Serializable;
import java.util.Date;

/*
 * 用户登入登出记录
 *
 * @author xin
 */
public class AccessRecordDO implements Serializable {


     //登入登出记录ID
     //仅当该记录从数据库取出时有效
    private Integer id;

    //登陆用户ID
    private Integer userID;

    //登陆用户名
    private String userName;

    //记录类型，登入或登出
    private String accessType;

    //登入或登出时间
    private Date accessTime;

    //用户登入或登出对应的IP地址
    private String accessIP;

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

    public String getAccessType() {
        return accessType;
    }

    public void setAccessType(String accessType) {
        this.accessType = accessType;
    }

    public Date getAccessTime() {
        return accessTime;
    }

    public void setAccessTime(Date accessTime) {
        this.accessTime = accessTime;
    }

    public String getAccessIP() {
        return accessIP;
    }

    public void setAccessIP(String accessIP) {
        this.accessIP = accessIP;
    }

    @Override
    public String toString() {
        return "AccessRecordDO{" +
                "id=" + id +
                ", userID=" + userID +
                ", userName='" + userName + '\'' +
                ", accessType='" + accessType + '\'' +
                ", accessTime=" + accessTime +
                ", accessIP='" + accessIP + '\'' +
                '}';
    }
}
