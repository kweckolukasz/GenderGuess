package com.example.genderGuess.Service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ResourceServiceTest {

    @Autowired
    ResourceService resourceService;


    @Test
    void areFemaleResourceExist() {
        assertThat(resourceService.getFemaleResource().exists()).isTrue();
    }

    @Test
    void getMaleResource(){
        assertThat(resourceService.getMaleResource().exists()).isTrue();
    }
}