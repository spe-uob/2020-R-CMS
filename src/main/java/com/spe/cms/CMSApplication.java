package com.spe.cms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

@EnableOpenApi
@SpringBootApplication
public class CMSApplication {

    public static void main(String[] args) {
        SpringApplication.run(CMSApplication.class, args);
    }

}
