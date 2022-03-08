package com.xin.wms.security.listener;

import com.xin.wms.common.service.Interface.SystemLogService;
import com.xin.wms.pojo.UserInfoDTO;
import com.xin.wms.exception.SystemLogServiceException;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;

/*
 * shiro Session 监听器
 * 当shiro的session被创建，注销，或过期时触发
 *
 * @author xin
 * @createde 20220209
 */
public class SessionListener extends SessionListenerAdapter {

    @Autowired
    private SystemLogService systemLogService;

    @Override
    public void onStart(Session session) {
        super.onStart(session);
    }

    @Override
    public void onStop(Session session) {
        super.onStop(session);
        sessionDestroyedLog(session);
    }

    @Override
    public void onExpiration(Session session) {
        super.onExpiration(session);
        sessionDestroyedLog(session);
    }

    /**
     * 当 Shiro Session 被销毁时， 对已登陆的用户记录访问日志
     *
     * @param session 被销毁的 Session
     */
    private void sessionDestroyedLog(Session session) {
        // 判断是否为已经登陆的用户, 判断依据是isAuthentication的值
        UserInfoDTO userInfo = (UserInfoDTO) session.getAttribute("userInfo");
        if (userInfo != null) {
            try {
                // 记录登出日志
                systemLogService.insertAccessRecord(userInfo.getUserID(), userInfo.getUserName(),
                        userInfo.getAccessIP(), SystemLogService.ACCESS_TYPE_LOGOUT);
            } catch (SystemLogServiceException e) {
                // do log
            }
        }
    }

}
