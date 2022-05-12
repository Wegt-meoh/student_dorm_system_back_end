package com.lasting;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@MapperScan("com.lasting.mapper")
public class StudentDormSystemAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentDormSystemAdminApplication.class, args);
    }

}
