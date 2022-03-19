package com.xin.wms.exception;

import com.xin.wms.common.service.Interface.ShopsManageService;

public class ShopsManageServiceException extends BusinessException {

    ShopsManageServiceException(){
        super();
    }

    public ShopsManageServiceException(Exception e){
        super(e);
    }

    ShopsManageServiceException(Exception e, String exceptionDesc){
        super(e, exceptionDesc);
    }

}
