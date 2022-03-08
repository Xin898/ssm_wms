package com.xin.wms.exception;

/*
 * GoodsManageService 异常
 *
 * @author xin
 */
public class GoodsManageServiceException extends BusinessException{

    GoodsManageServiceException(){
        super();
    }

    public GoodsManageServiceException(Exception e){
        super(e);
    }

    GoodsManageServiceException(Exception e, String exceptionDesc){
        super(e, exceptionDesc);
    }
}
