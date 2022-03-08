package com.xin.wms.exception;

/*
 * SystemLogService 异常
 *
 * @author xin
 */
public class SystemLogServiceException extends BusinessException{

    public SystemLogServiceException(){
        super();
    }

    public SystemLogServiceException(Exception e){
        super(e);
    }

    public SystemLogServiceException(Exception e, String exceptionDesc){
        super(e, exceptionDesc);
    }

    public SystemLogServiceException(String exceptionDesc){
        super(exceptionDesc);
    }
}
