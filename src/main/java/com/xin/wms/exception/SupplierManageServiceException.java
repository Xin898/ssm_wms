package com.xin.wms.exception;

/*
 * SupplierManageService 异常
 *
 * @author xin
 */
public class SupplierManageServiceException extends BusinessException{

    SupplierManageServiceException(){
        super();
    }

    public SupplierManageServiceException(Exception e){
        super(e);
    }

    SupplierManageServiceException(Exception e, String exceptionDesc){
        super(e, exceptionDesc);
    }

}
