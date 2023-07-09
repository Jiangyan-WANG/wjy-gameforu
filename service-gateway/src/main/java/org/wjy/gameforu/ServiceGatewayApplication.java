package org.wjy.gameforu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient // nacos
public class ServiceGatewayApplication {
    public static void main(String[] args) {

        SpringApplication.run(ServiceGatewayApplication.class,args);
    }
}
