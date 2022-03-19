package com.xin.wms.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Customer implements Serializable {

    private Integer id;// 客户ID
    private String name;// 客户名
    private String personInCharge;// 负责人
    private String tel;// 联系电话
    private String email;// 电子邮件
    private String address;// 地址

}
