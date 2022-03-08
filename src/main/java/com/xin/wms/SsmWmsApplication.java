package com.xin.wms;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication
@SpringBootApplication
@MapperScan("com.xin.wms.dao")
public class SsmWmsApplication {

    public static void main(String[] args) {
        //BasicConfigurator.configure();
        //样板代码
        SpringApplication.run(SsmWmsApplication.class,args);
    }
}
