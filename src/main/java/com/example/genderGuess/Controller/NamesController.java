package com.example.genderGuess.Controller;

import com.example.genderGuess.GuessingAlgorithm.GuessingAlgorithm;
import com.example.genderGuess.Model.GENDERS;
import com.example.genderGuess.Model.Name;
import com.example.genderGuess.Service.RefactorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/names")
public class NamesController {

    @Autowired
    RefactorService refactorService;

    @Autowired
    GuessingAlgorithm guessingAlgorithm;

    List<Name> names;

    @GetMapping("/{providedNames}")
    @ResponseBody
    public String guessGender(@PathVariable String providedNames){

        String genderOfAll = "";

        try {
            names = refactorService.cleanUpFormatAndExportGivenString(providedNames);
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }

        names = guessingAlgorithm.guessGenderFromGivenNames(names);

        int score = 0;

        for (Name name :
                names) {
            if (name.getGender().equals(GENDERS.MALE)) score--;
            if (name.getGender().equals(GENDERS.FEMALE)) score++;
        }


        if (score<0) genderOfAll = GENDERS.MALE.toString();
        if (score>0) genderOfAll = GENDERS.FEMALE.toString();
        if (score==0) genderOfAll = GENDERS.INCONCLUSIVE.toString();

        return genderOfAll;
    }
}
