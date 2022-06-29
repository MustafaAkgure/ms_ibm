package com.ibm.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class MsIbmApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(MsIbmApplication.class, args);
    }
}
