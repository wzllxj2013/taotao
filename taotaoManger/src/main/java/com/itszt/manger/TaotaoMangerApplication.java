package com.itszt.manger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class TaotaoMangerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaotaoMangerApplication.class, args);
	}

}
