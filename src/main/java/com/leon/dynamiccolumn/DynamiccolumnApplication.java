package com.leon.dynamiccolumn;

import com.leon.dynamiccolumn.projectentity.ProjectEntity;
import com.leon.dynamiccolumn.repository.ProjectRepository;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.oas.annotations.EnableOpenApi;

import java.util.ArrayList;
import java.util.Optional;

@EnableOpenApi
@SpringBootApplication
public class DynamiccolumnApplication {

    public static void main(String[] args) {
        SpringApplication.run(DynamiccolumnApplication.class, args);
    }

}
