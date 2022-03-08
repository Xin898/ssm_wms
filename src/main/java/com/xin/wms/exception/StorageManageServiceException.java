package com.xin.wms.exception;

/*
 * StorageManageService 异常
 *
 * @author xin
 */
public class StorageManageServiceException extends BusinessException{

    StorageManageServiceException(){
        super();
    }

    public StorageManageServiceException(Exception e){
        super(e);
    }

    StorageManageServiceException(Exception e, String exceptionDesc){
        super(e, exceptionDesc);
    }
}
