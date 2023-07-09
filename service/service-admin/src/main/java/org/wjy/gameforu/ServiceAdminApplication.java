package org.wjy.gameforu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.CrossOrigin;

// Access Control List Module entry
@SpringBootApplication
@CrossOrigin
@EnableDiscoveryClient // 服务发现配置
public class ServiceAdminApplication {

    public static void main(String[] args){
        SpringApplication.run(ServiceAdminApplication.class, args);
    }
}
