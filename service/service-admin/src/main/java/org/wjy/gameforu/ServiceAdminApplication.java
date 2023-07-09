package org.wjy.gameforu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

// Access Control List Module entry
@SpringBootApplication
//@CrossOrigin config gateway corssorigin not needed here
@EnableDiscoveryClient // 服务发现配置
public class ServiceAdminApplication {

    public static void main(String[] args){

        SpringApplication.run(ServiceAdminApplication.class, args);
    }
}
