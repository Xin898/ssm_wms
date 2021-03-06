package com.xin.wms.exception;
/*
 * 业务层异类的父类
 *
 * @author xin
 */
public class BusinessException extends Exception{

    private String exceptionDesc;

    public BusinessException(Exception e){
        super(e);
    }

    BusinessException(Exception e, String exceptionDesc){
        super(e);
        this.exceptionDesc = exceptionDesc;
    }

    BusinessException(String exceptionDesc){
        this.exceptionDesc = exceptionDesc;
    }

    BusinessException(){}

    public String getExceptionDesc() {
        return exceptionDesc;
    }

    public void setExceptionDesc(String exceptionDesc) {
        this.exceptionDesc = exceptionDesc;
    }
}
