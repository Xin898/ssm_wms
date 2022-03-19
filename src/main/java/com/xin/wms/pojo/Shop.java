package com.xin.wms.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Shop implements Serializable {

    private Integer id;
    private String shopName;
    private String shopPlatform;
    private String customerUrl;
}
