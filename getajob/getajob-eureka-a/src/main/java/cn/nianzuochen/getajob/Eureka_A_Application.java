package cn.nianzuochen.getajob;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class Eureka_A_Application {
    public static void main(String[] args) {
        SpringApplication.run(Eureka_A_Application.class, args);
    }
}
