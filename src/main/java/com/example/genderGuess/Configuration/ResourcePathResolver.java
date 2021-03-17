package com.example.genderGuess.Configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

public class ResourcePathResolver {


    private String maleResourceLocation;

    private String femaleResourceLocation;

    public String getMaleResourceLocation() {
        return maleResourceLocation;
    }

    public String getFemaleResourceLocation() {
        return femaleResourceLocation;
    }

    public void setMaleResourceLocation(String maleResourceLocation) {
        this.maleResourceLocation = maleResourceLocation;
    }

    public void setFemaleResourceLocation(String femaleResourceLocation) {
        this.femaleResourceLocation = femaleResourceLocation;
    }
}
