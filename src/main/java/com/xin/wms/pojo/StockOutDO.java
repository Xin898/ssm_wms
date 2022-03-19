package com.xin.wms.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

//出库表
@Data
public class StockOutDO implements Serializable {

    private Integer id;
    private Integer customerID;
    private String customerName;
    private Integer goodID;
    private String goodName;
    private Integer repositoryID;
    private long number;
    private Date time;
    private String personInCharge;

}
