package com.example.genderGuess.Service.GuessingAlgorithm;

import com.example.genderGuess.Service.Resource.ResourceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.mockito.Mockito.mock;

@SpringBootTest
@ActiveProfiles("{test}")
class JavaStreamsGuessingAlgorithmTest {

    @Autowired
    ResourceService resourceService;

    @BeforeEach
    void setUp() {

    }

    /*
    FAKE FEMALE NAMES:
                        JULIA
                        ZUZANNA
                        ZOFIA
                        HANNA
                        MAJA
                        LENA
                        OLIWIA
    FAKE MALE NAMES:
                        ŁUKASZ
                        JAN
                        SZYMON
                        ANDRZEJ
                        OKTAWIUSZ
                        PAWEŁ
                        ZENON

     */

    @Test
    void are_resources_exists() {

    }
}