package com.example.genderGuess.GuessingAlgorithm;

import com.example.genderGuess.Dictionary;
import com.example.genderGuess.Model.GENDERS;
import com.example.genderGuess.Model.Name;
import com.example.genderGuess.Service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class JavaStreamsGuessingAlgorithm implements GuessingAlgorithm {

    private String maleDb = "male_names.txt";
    private String femaleDb = "female_names.txt";

    public static final String MALE = "MALE";
    public static final String FEMALE = "FEMALE";
    public static final String INCONCLUSIVE = "INCONCLUSIVE";

    @Autowired
    ResourceService resourceService;

    Resource resource;

    @Autowired
    Dictionary dictionaryRepo;

    TreeMap<Character, Long> dictionary;



    @Override
    public List<Name> guessGenderFromGivenNames(List<Name> names) {

        for (Name name :
                names) {
            setNextGenderToCheck(name);
            setResourceAndDictionaryForCheckingGivenGender(name);
            GuessGenders(name);
        }
        return names;
    }

    private void setNextGenderToCheck(Name name) {
        if ((name.getName().charAt(name.getName().length() - 1) == 'A') && !name.isFemaleChecked())
            name.setGenderToCheck(GENDERS.FEMALE);
        else if (name.isFemaleChecked()) name.setGenderToCheck(GENDERS.MALE);
        else if (name.isMaleChecked()) name.setGenderToCheck(GENDERS.FEMALE);
    }

    private void setResourceAndDictionaryForCheckingGivenGender(Name name) {
        switch (name.getGenderToCheck()){
            case MALE:{
                resource = resourceService.getMaleResource();
                dictionary = dictionaryRepo.getMaleDictionary();
            }
            break;
            case FEMALE:{
                resource = resourceService.getFemaleResource();
                dictionary = dictionaryRepo.getFemaleDictionary();
            }
            break;
        }
    }

    private void GuessGenders(Name name) {
        while(!(name.isFemaleChecked() && name.isMaleChecked())){
            try (InputStream namesStream = resource.getInputStream()) {
                BufferedReader br = new BufferedReader(new InputStreamReader(namesStream, StandardCharsets.UTF_8));
                String nextLine;
                Long start = dictionary.get(dictionary.floorKey(name.getStartChar()));
                if (start == null) start = 0L;
                br.lines().skip(start).forEach(l -> {
                            if (l.equals(name.getName())) {
                                if (name.getGenderToCheck().equals(GENDERS.FEMALE)) name.setGender(GENDERS.FEMALE);
                                if (name.getGenderToCheck().equals(GENDERS.MALE)) name.setGender(GENDERS.MALE);
                            }
                        }
                );
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if ((name.isFemaleChecked() && name.isMaleChecked()) && name.getGender() == null) name.setGender(GENDERS.INCONCLUSIVE);
    }
}