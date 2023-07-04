package org.wjy.gameforu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

// Access Control List Module entry
@SpringBootApplication
@CrossOrigin
public class ServiceAclApplication {

    public static void main(String[] args){
        SpringApplication.run(ServiceAclApplication.class, args);
    }
}
