package cn.nianzuochen.getajob;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class Eureka_B_Application {
    public static void main(String[] args) {
        SpringApplication.run(Eureka_B_Application.class, args);
    }
}
