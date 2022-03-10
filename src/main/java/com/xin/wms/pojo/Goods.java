package com.xin.wms.pojo;

import java.io.Serializable;
import lombok.Data;


//货物描述
@Data
public class Goods implements Serializable {

    private Integer id;// 货物ID
    private String name;// 货物名
    private String type;// 货物类型
    private String size;// 货物规格
    private Double value;// 货物价值

}


