package com.xin.wms.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/*
 * 用户账户信息 数据传输对象
 *
 * @author xin
 * @09/02/2022
 */
@Data
public class UserInfoDTO implements Serializable {

    /**
     * 用户ID
     */
    private Integer userID;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户密码（已加密）
     */
    private String password;

    /**
     * 是否为初次登陆
     */
    private boolean firstLogin;

    /**
     * 用户登陆的IP
     */
    private String accessIP;

    /**
     * 用户角色
     */
    private List<String> role = new ArrayList<>();

    /**
     * 用户指派的仓库
     */
    private Integer repositoryBelong;

    /**
     * 用户账户属性的 getter 以及 setter
     */

}
