package com.netzme.test;

import com.netzme.test.bean.DbPropBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

    @Autowired
    private DbPropBean propBean;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
