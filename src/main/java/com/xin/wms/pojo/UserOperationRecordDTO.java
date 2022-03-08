package com.xin.wms.pojo;

import java.io.Serializable;

//用户操作记录
public class UserOperationRecordDTO implements Serializable {

    //操作记录ID
    private Integer id;

    //执行操作的用户ID
    private Integer userID;

    //执行操作的用户名
    private String userName;

    //操作的名称
    private String operationName;

    //操作执行的时间
    private String operationTime;

    //操作执行的结果
    private String operationResult;

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

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    public String getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(String operationTime) {
        this.operationTime = operationTime;
    }

    public String getOperationResult() {
        return operationResult;
    }

    public void setOperationResult(String operationResult) {
        this.operationResult = operationResult;
    }

    @Override
    public String toString() {
        return "UserOperationRecordDTO{" +
                "id=" + id +
                ", userID=" + userID +
                ", userName='" + userName + '\'' +
                ", operationName='" + operationName + '\'' +
                ", operationTime='" + operationTime + '\'' +
                ", operationResult='" + operationResult + '\'' +
                '}';
    }
}
