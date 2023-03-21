package br.com.uburu.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "br.com.uburu.spring")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
