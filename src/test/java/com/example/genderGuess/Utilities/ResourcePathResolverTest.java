package com.example.genderGuess.Utilities;

import com.example.genderGuess.Configuration.ResourcePathResolver;
import com.example.genderGuess.Configuration.ResourcePathResolverFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@EnableConfigurationProperties(value = ResourcePathResolver.class)
@ContextConfiguration(classes = ResourcePathResolverFactory.class)
@TestPropertySource("classpath:test/resources/application-test.properties")
class ResourcePathResolverTest {

    @Autowired
    @Qualifier("default")
    ResourcePathResolver resourcePathResolver;

    String maleResource;
    String femaleResource;

    @BeforeEach
    void init() {
        maleResource = resourcePathResolver.getMaleResourceLocation();
        femaleResource = resourcePathResolver.getFemaleResourceLocation();
    }

    @Test
    void is_resourcePathResolver_not_null() {
        assertThat(resourcePathResolver).isNotNull();
    }

    @Test
    void are_resources_not_null() {
        assertThat(maleResource).isNotNull();
        assertThat(femaleResource).isNotNull();
    }

    @Test
    void are_short_resources_set() {
        assertThat(maleResource).contains("static/short_male_names.txt");
        assertThat(femaleResource).contains("static/short_female_names.txt");
    }
}