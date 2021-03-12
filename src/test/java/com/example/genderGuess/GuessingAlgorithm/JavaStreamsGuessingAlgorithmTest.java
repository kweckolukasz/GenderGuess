package com.example.genderGuess.GuessingAlgorithm;

import com.example.genderGuess.Service.ResourceService;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.HashMap;
import java.util.LinkedHashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class JavaStreamsGuessingAlgorithmTest {

    @Spy
    @InjectMocks
    JavaStreamsGuessingAlgorithm guessingAlgorithm;

    @Mock
    @InjectMocks
    ResourceService resourceService;

    LinkedHashMap<String,String> names = new LinkedHashMap<>();

    String maleDbFake = "fake_male_names.txt";
    String femaleDbFake = "fake_female_names.txt";
    String male = "MALE";
    String female = "FEMALE";
    String inconclusive = "INCONCLUSIVE";

    @BeforeEach
    void setUp() {
        names.put("JULIA", female);names.put("ZUZANNA", female);names.put("ZOFIA", female);names.put("HANNA", female);names.put("MAJA", female);names.put("LENA", female);
        names.put("ALICJA", female);names.put("OLIWIA", female);names.put("ŁUKASZ", male);names.put("JAN", male);names.put("ANDRZEJ", male);names.put("OKTAWIUSZ", male);
        names.put("SZYMON", male);names.put("PAWEŁ", male);names.put("ZENON", male);names.put("MICHAŁ", male);
    }

    @Test
    void areFakeResourcesExists(){
        Resource femaleResource = new ClassPathResource("static/fake_female_names.txt");
        Resource maleResource = new ClassPathResource("static/fake_male_names.txt");
        assertThat(femaleResource.exists()).isTrue();
        assertThat(maleResource.exists()).isTrue();
    }


    @Test
    @DisplayName("are Male Names Discovered Correctly")
    void areMaleNamesDiscoveredCorrectly(){


        Mockito.when(guessingAlgorithm.resourceService.getMaleResource()).thenReturn(new ClassPathResource("static/fake_male_names.txt"));
        Mockito.when(guessingAlgorithm.resourceService.getFemaleResource()).thenReturn(new ClassPathResource("static/fake_female_names.txt"));

        LinkedHashMap<String,String> maleNames = new LinkedHashMap<>();
        maleNames.put("ŁUKASZ",""); maleNames.put("ANDRZEJ",""); maleNames.put("ZENON","");
        LinkedHashMap<String, String> algo = (LinkedHashMap<String, String>) guessingAlgorithm.guessGenderFromGivenNames(maleNames);
        assertThat(algo).isNotEmpty().hasSize(3);
        algo.entrySet().forEach(entry -> {
            assertThat(names).containsExactly(entry);
        });

    }

    @Test
    @DisplayName("are Female Names Discovered Correctly")
    void areFemaleNamesDiscoveredCorrectly(){
        HashMap<String,String> femaleNames = new HashMap<>();
        femaleNames.put("JULIA","");femaleNames.put("ALICJA","");femaleNames.put("HANNA","");
    }

    @Test
    @DisplayName("are Inconclusive Names Discovered Correctly")
    void areInconclusiveNamesDiscoveredCorrectly(){
        HashMap<String,String> inconclusiveNames = new HashMap<>();
        inconclusiveNames.put("FRANEK",""); inconclusiveNames.put("OKTAWIUSZ", "");
    }

    @Test
    @DisplayName("are Mixed Names Discovered Correctly")
    void areMixedNamesDiscoveredCorrectly(){
        HashMap<String,String> maleMixedNames = new HashMap<>();
        HashMap<String,String> femaleMixedNames = new HashMap<>();
        HashMap<String,String> inconclusiveMixedNames = new HashMap<>();
        maleMixedNames.put("SZYMON", "");maleMixedNames.put("ZENON", "");maleMixedNames.put("OLIWIA", "");
        femaleMixedNames.put("ZENON","");femaleMixedNames.put("OKTAWIUSZ","");femaleMixedNames.put("ALICJA","");femaleMixedNames.put("MAJA","");femaleMixedNames.put("JULIA","");
        inconclusiveMixedNames.put("JULIA","");inconclusiveMixedNames.put("HANNA","");inconclusiveMixedNames.put("ŁUKASZ","");inconclusiveMixedNames.put("SZYMON","");
    }
}