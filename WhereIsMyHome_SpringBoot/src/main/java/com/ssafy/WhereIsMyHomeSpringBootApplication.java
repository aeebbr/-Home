package com.ssafy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.ssafy.*"})
public class WhereIsMyHomeSpringBootApplication { 

	public static void main(String[] args) { 
		SpringApplication.run(WhereIsMyHomeSpringBootApplication.class, args);
	}

}
