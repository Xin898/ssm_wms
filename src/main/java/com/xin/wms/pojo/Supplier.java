package com.xin.wms.pojo;

import lombok.Data;

import java.io.Serializable;

//供应商
@Data
public class Supplier implements Serializable {

    private Integer id;// 供应商ID
    private String name;// 供应商名
    private String personInCharge;// 负责人
    private String tel;// 联系电话
    private String email;// 电子邮件
    private String address;// 供应商地址

}
