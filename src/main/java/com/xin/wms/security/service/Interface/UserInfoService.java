package com.xin.wms.security.service.Interface;

import com.xin.wms.exception.UserInfoServiceException;
import com.xin.wms.pojo.UserInfoDTO;

import java.util.List;
import java.util.Set;

public interface UserInfoService {

    UserInfoDTO getUserInfo(Integer userID) throws UserInfoServiceException;

    UserInfoDTO getUserInfo(String userName) throws UserInfoServiceException;

    UserInfoDTO getUserInfo();

    List<UserInfoDTO> getAllUserInfo() throws UserInfoServiceException;

    void updateUserInfo();

    boolean insertUserInfo();

    Set<String> getUserRoles();


    void updateUserInfo(UserInfoDTO userInfoDTO) throws UserInfoServiceException;

    void deleteUserInfo(Integer userID) throws UserInfoServiceException;

    boolean insertUserInfo(UserInfoDTO userInfoDTO) throws UserInfoServiceException;

    Set<String> getUserRoles(Integer userID) throws UserInfoServiceException;
}
