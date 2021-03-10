package com.example.genderGuess.Controller;

import com.example.genderGuess.GuessingAlgorithm.GuessingAlgorithm;
import com.example.genderGuess.Service.RefactorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/names")
public class NamesController {

    @Autowired
    RefactorService refactorService;

    @Autowired
    GuessingAlgorithm guessingAlgorithm;

    Map<String,String> namesMap;

    @GetMapping("/{providedNames}")
    @ResponseBody
    public String guessGender(@PathVariable String providedNames){

        String genderOfAll = "";
        final String female = "FEMALE";
        final String male = "MALE";
        final String inconclusive = "INCONCLUSIVE";

        try {
            namesMap = refactorService.refactorGivenMultipleNameString(providedNames);
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }

        namesMap = guessingAlgorithm.guessGenderFromGivenNames(namesMap);

        AtomicInteger atomicScore = new AtomicInteger();
        namesMap.forEach((name, gender) -> {
            if (gender.equals(female)) atomicScore.getAndIncrement();
            else if (gender.equals(male)) atomicScore.getAndDecrement();
        });
        int score = atomicScore.intValue();

        if (score>0) genderOfAll = female;
        if (score<0) genderOfAll = male;
        if (score==0) genderOfAll = inconclusive;

        return genderOfAll;
    }
}
