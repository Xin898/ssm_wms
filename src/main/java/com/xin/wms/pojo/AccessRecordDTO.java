package com.xin.wms.pojo;

import lombok.Data;

import java.io.Serializable;

/*
 * @author xin
 */
@Data
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

}
