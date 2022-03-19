package com.xin.wms.pojo;

import lombok.Data;

import java.io.Serializable;

//用户账户信息
@Data
public class UserInfoDO implements Serializable {

    //用户ID
    private Integer userID;

    //用户名
    private String userName;

    //用户密码
    private String password;

    //是否为初次登录
    private int firstLogin;

}
