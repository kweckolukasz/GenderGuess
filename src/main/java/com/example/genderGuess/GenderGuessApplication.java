package com.example.genderGuess;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

@SpringBootApplication
public class GenderGuessApplication {

	@Bean(initMethod = "dictionary")
	public Dictionary dictionary(){
		return new Dictionary();
	};

	public static void main(String[] args) {
		SpringApplication.run(GenderGuessApplication.class, args);
	}

}
