package org.wjy.gameforu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ServicePermissionApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServicePermissionApplication.class,args);
    }
}
