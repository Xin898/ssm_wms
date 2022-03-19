package com.xin.wms.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class RepositoryAdmin implements Serializable {

    private Integer id;// 仓库管理员ID
    private String name;// 姓名
    private String sex;// 性别
    private String tel;// 联系电话
    private String address;// 地址
    private Date birth;// 出生日期
    private Integer repositoryBelongID;// 所属仓库ID

}
