package com.xin.wms.security.service.Interface;

import com.xin.wms.exception.UserAccountServiceException;

import java.util.Map;

public interface AccountService {


    void passwordModify(Integer userID, Map<String, Object> passwordInfo) throws UserAccountServiceException;


}
