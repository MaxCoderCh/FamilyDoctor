package com.familydoctor.doctorsubject;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.retry.annotation.EnableRetry;

@EnableHystrix
@EnableFeignClients
@EnableEurekaServer
@EnableCaching
@EnableRetry
@SpringBootApplication
public class DoctorsubjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(DoctorsubjectApplication.class, args);
    }
}
