package br.com.fabiofnc.apileilao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
@EnableAutoConfiguration
@ComponentScan
public class ApiLeilaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiLeilaoApplication.class, args);
	}

}
