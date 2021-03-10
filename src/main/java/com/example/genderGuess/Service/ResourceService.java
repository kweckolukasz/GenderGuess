package com.example.genderGuess.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class ResourceService {


    private final Resource femaleResource = new ClassPathResource("static/female_names.txt");
    private final Resource maleResource = new ClassPathResource("static/male_names.txt");


    public Resource getFemaleResource() {
        return femaleResource;
    }

    public Resource getMaleResource() {
        return maleResource;
    }
}
