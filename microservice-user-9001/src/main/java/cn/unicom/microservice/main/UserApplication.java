package cn.unicom.microservice.main;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author 王长河
 * @create 2019-11-18 15:59
 */
@SpringBootApplication(scanBasePackages = "cn.unicom.microservice")
@MapperScan("cn.unicom.microservice.mapper")
@EnableEurekaClient
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class,args);
    }
}
