package com.example.genderGuess.Service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

class BasicRefactorServiceTest {

    @Autowired
    RefactorService refactorService;

    String singleName = "Jan";
    String singleName2 = " aNDrzeJ";
    String name = "Jan Maria";
    String name2 = "Oktawiusz ślązak MacieK Andrzej";


    @Test
    void refactorGivenMultipleNameString() {
        assertThat(refactorService.refactorGivenSingleNameString(name)).contains(entry("JAN",""), entry("MARIA",""));
        assertThat(refactorService.refactorGivenSingleNameString(name2)).contains(entry("OKTAWIUSZ",""), entry("ŚLĄZAK",""), entry("MACIEK", ""), entry("ANDRZEJ", ""));
    }

    @Test
    void refactorGivenSingleNameString() {
        assertThat(refactorService.refactorGivenSingleNameString(singleName)).contains(entry("JAN",""));
        assertThat(refactorService.refactorGivenSingleNameString(singleName2)).contains(entry("ANDRZEJ",""));
    }
}