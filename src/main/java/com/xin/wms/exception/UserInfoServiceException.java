package com.xin.wms.exception;

/*
 * UserInfoService 异常
 *
 * @author xin
 */
public class UserInfoServiceException extends BusinessException{

    public UserInfoServiceException(){
        super();
    }

    public UserInfoServiceException(Exception e){
        super(e);
    }

    public UserInfoServiceException(Exception e, String exceptionDesc){
        super(e, exceptionDesc);
    }

    public UserInfoServiceException(String exceptionDesc){
        super(exceptionDesc);
    }
}
