package com.xin.wms.common.util;


import org.springframework.stereotype.Component;

/**
 * Response 工厂类
 * @author xin
 */
@Component
public class ResponseFactory {

    /**
     * 生成一个 Response 对象
     *
     * @return response 对象
     */
    public static Response newInstance() {
        return new Response();
    }
}
