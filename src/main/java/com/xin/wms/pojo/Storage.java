package com.xin.wms.pojo;

import lombok.Data;

import java.io.Serializable;

//仓库存储
@Data
public class Storage implements Serializable {

    private Integer goodsID;// 货物ID
    private String goodsName;// 货物名称
    private String goodsSize;// 货物规格
    private String goodsType;// 货物类型
    private Double goodsValue;// 货物价值
    private Integer repositoryID;// 仓库ID
    private Long number;// 库存数量

}
