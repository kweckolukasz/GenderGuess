package com.example.genderGuess;

import com.example.genderGuess.Service.Dictionary.Dictionary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GenderGuessApplication {


    @Autowired
    static Dictionary dictionary;

    public static void main(String[] args) {
        SpringApplication.run(GenderGuessApplication.class, args);

    }


}


