package com.xin.wms.pojo;

import lombok.Data;

import java.io.Serializable;

//子仓库描述
@Data
public class Repository implements Serializable {

    private Integer id;// 仓库ID
    private String address;// 仓库地址
    private String status;// 仓库状态
    private String area;// 仓库面积
    private String desc;// 仓库描述
    private Integer adminID;//仓库管理员ID
    private String adminName; //仓库管理员名字

}
