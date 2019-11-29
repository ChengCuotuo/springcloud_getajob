package cn.nianzuochen.getajob;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@MapperScan("cn.nianzuochen.getajob.mapper")
@SpringBootApplication
@EnableSwagger2
@EnableEurekaClient
public class Service_B_Application {
    public static void main(String[] args) {
        SpringApplication.run(Service_B_Application.class, args);
    }
}
