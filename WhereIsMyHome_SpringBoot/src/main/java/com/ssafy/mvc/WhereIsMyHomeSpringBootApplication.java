package com.ssafy.mvc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.ssafy"})
@MapperScan(basePackages = { "com.ssafy.**.mapper" })
public class WhereIsMyHomeSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(WhereIsMyHomeSpringBootApplication.class, args);
	}

}
