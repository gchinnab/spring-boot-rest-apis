package com.chinna.learn.springboot.todos_rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;

@SpringBootApplication
public class TodosRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodosRestApplication.class, args);
	}

}
