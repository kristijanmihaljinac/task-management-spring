package com.kristijanmihaljinac.taskmanagementspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class TaskManagementSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskManagementSpringApplication.class, args);

        //BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
        //String password= bCryptPasswordEncoder.encode("admin");//encrypt password
        //System.out.println(password);
    }

}
