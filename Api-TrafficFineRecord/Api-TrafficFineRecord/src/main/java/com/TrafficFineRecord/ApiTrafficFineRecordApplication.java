package com.TrafficFineRecord;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ApiTrafficFineRecordApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiTrafficFineRecordApplication.class, args);
	}

}
