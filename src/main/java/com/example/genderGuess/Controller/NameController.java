package com.example.genderGuess.Controller;

import com.example.genderGuess.Service.GuessingAlgorithm.GuessingAlgorithm;
import com.example.genderGuess.Model.Name;
import com.example.genderGuess.Service.Refactor.RefactorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/name")
public class NameController {

    @Autowired
    RefactorService refactorService;
    @Autowired
    GuessingAlgorithm guessingAlgorithm;

    List<Name> givenNames;


    @GetMapping("/{providedName}")
    @ResponseBody
    public String guessGender(@PathVariable String providedName){

        try {
            givenNames = refactorService.cleanUpFormatAndExportGivenString(providedName);
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }

        givenNames = guessingAlgorithm.guessGenderFromGivenNames(givenNames);

        return givenNames.get(0).getGender().toString();
    }
}
