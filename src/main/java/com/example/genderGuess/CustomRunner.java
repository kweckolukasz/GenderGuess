package com.example.genderGuess;

import com.example.genderGuess.Service.Dictionary.Dictionary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order
public class CustomRunner implements ApplicationRunner {

    @Autowired
    Dictionary dictionary;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        dictionary.init();
    }
}
