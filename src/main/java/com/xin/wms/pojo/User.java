package com.xin.wms.pojo;

import lombok.Data;

import java.io.Serializable;

//系统用户
@Data
public class User implements Serializable {

    private Integer id;// 用户ID
    private String userName;// 用户名
    private String password;// 用户密码

}
