package br.fernanda.rocha.msuserEureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class MsUserEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsUserEurekaApplication.class, args);
	}

}

