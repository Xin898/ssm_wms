package com.xin.wms.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

//用户操作记录DO
@Data
public class UserOperationRecordDO implements Serializable {

    /**
     * 记录ID
     */
    private Integer id;

    /**
     * 执行操作的用户ID
     */
    private Integer userID;

    /**
     * 执行操作的用户名
     */
    private String userName;

    /**
     * 操作的名称
     */
    private String operationName;

    /**
     * 执行操作的时间
     */
    private Date operationTime;

    /**
     * 执行操作结果
     */
    private String operationResult;

}
