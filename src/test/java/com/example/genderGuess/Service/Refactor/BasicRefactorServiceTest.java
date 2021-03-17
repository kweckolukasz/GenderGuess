package com.example.genderGuess.Service.Refactor;

import com.example.genderGuess.Model.Name;
import com.example.genderGuess.Service.Refactor.BasicRefactorService;
import com.example.genderGuess.Service.Refactor.RefactorService;
import org.junit.jupiter.api.*;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class Refactor_Service_Test {

   RefactorService refactorService = new BasicRefactorService();

    String singleName = " _      _____ Jan_";
    String singleName2 = " aNDrzeJ          ";
    String name = "Jan           Maria";
    String name2 = "Oktawiusz_ślązak _ MacieK  Andrzej";
    String wrongSentence = "#nike&reebok is 4runners";
    String name3 = "_underscoreAtStart__andDouble";


    @Nested
    class text_should_contain_upperCase_alphabetic_characters_and_underscores {

        @Test
        void is_refactor_service_not_null(){
            assertThat(refactorService).isNotNull();
        }

        @Test
        void is_refactor_service_returns_correct_objects(){

            assertThat(refactorService.cleanUpFormatAndExportGivenString(singleName)
                    .get(0).getName()).isEqualTo("JAN");

            assertThat(refactorService.cleanUpFormatAndExportGivenString(singleName2)
                    .get(0).getName()).isEqualTo("ANDRZEJ");

            assertThat(refactorService.cleanUpFormatAndExportGivenString(name))
                    .isEqualTo(Arrays.asList(new Name("JAN"), new Name("MARIA")));


            assertThat(refactorService.cleanUpFormatAndExportGivenString(name2))
                    .contains(new Name("OKTAWIUSZ"),new Name("ŚLĄZAK"),new Name("MACIEK"),new Name("ANDRZEJ"));

            assertThat(refactorService.cleanUpFormatAndExportGivenString(name3))
                    .contains(new Name("UNDERSCOREATSTART"),new Name("ANDDOUBLE"));

            assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() ->
                    refactorService.cleanUpFormatAndExportGivenString(wrongSentence))
                    .withMessage("Name should consist of letters, whitespace is allowed if you provide multiple names");

        }


    }


}