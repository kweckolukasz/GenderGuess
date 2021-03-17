package com.example.genderGuess.Service.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

class DefaultResourceServiceTest {

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