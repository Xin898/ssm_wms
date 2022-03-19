package com.xin.wms.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

//入库表
@Data
public class StockInDO implements Serializable {

    // 入库记录
    private Integer id;

    //供应商ID
    private Integer supplierID;

    //供应商名称
    private String supplierName;

    //商品ID
    private Integer goodID;

    // 商品名称
    private String goodName;

    // 入库仓库ID
    private Integer repositoryID;

    //入库数量
    private long number;

    //入库日期
    private Date time;

    //入库经手人
    private String personInCharge;

}
