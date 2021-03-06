package com.xin.wms.pojo;

import lombok.Data;

import java.io.Serializable;

//用户操作记录
@Data
public class UserOperationRecordDTO implements Serializable {

    //操作记录ID
    private Integer id;

    //执行操作的用户ID
    private Integer userID;

    //执行操作的用户名
    private String userName;

    //操作的名称
    private String operationName;

    //操作执行的时间
    private String operationTime;

    //操作执行的结果
    private String operationResult;

}
