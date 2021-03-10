package com.example.genderGuess.Controller;

import com.example.genderGuess.GuessingAlgorithm.GuessingAlgorithm;
import com.example.genderGuess.Service.RefactorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/name")
public class NameController {

    @Autowired
    RefactorService refactorService;
    @Autowired
    GuessingAlgorithm guessingAlgorithm;

    Map<String,String> givenName;

    @GetMapping
    @ResponseBody
    public String home(){
        return "Hello world!";
    }

    @GetMapping("/{providedName}")
    @ResponseBody
    public String guessGender(@PathVariable String providedName){

        try {
            givenName = refactorService.refactorGivenSingleNameString(providedName);
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }

        givenName = guessingAlgorithm.guessGenderFromGivenNames(givenName);

        return givenName.entrySet().iterator().next().getValue();
    }
}
