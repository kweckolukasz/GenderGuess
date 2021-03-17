package com.example.genderGuess.Service.GuessingAlgorithm;

import com.example.genderGuess.Service.Dictionary.Dictionary;
import com.example.genderGuess.Model.GENDERS;
import com.example.genderGuess.Model.Name;
import com.example.genderGuess.Service.Resource.ResourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.TreeMap;


@Service
public class JavaStreamsGuessingAlgorithm implements GuessingAlgorithm {

    Logger logger = LoggerFactory.getLogger(JavaStreamsGuessingAlgorithm.class.getSimpleName());


    @Autowired
    ResourceService resourceService;

    Resource resource;

    @Autowired
    Dictionary dictionary;

    TreeMap<Character, Long> dictionaryMap;


    @Override
    public List<Name> guessGenderFromGivenNames(List<Name> names) {
        logger.info("guessGenderFromGivenNames start");
        for (Name name :
                names) {
            setNextGenderToCheck(name);
            setResourceAndDictionaryForCheckingGivenGender(name);
            GuessGenders(name);
        }
        return names;
    }

    protected void setNextGenderToCheck(Name name) {
        logger.info("setNextGenderToCheck();");
        if ((name.getName().charAt(name.getName().length() - 1) == 'A') && !name.isFemaleChecked())
            name.setGenderToCheck(GENDERS.FEMALE);
        else if (name.isFemaleChecked()) name.setGenderToCheck(GENDERS.MALE);
        else if (name.isMaleChecked()) name.setGenderToCheck(GENDERS.FEMALE);
    }

    protected void setResourceAndDictionaryForCheckingGivenGender(Name name) {
        logger.info("setResourceAndDictionaryForCheckingGivenGender();");
        switch (name.getGenderToCheck()){
            case MALE:{
                logger.info("setResourceAndDictionaryForCheckingGivenGender(); MALE");
                resource = resourceService.getMaleResource();
                dictionaryMap = dictionary.getMaleDictionary();
            }
            break;
            case FEMALE:{
                logger.info("setResourceAndDictionaryForCheckingGivenGender(); FEMALE");
                resource = resourceService.getFemaleResource();
                dictionaryMap = dictionary.getFemaleDictionary();
            }
            break;
        }
    }

    protected void GuessGenders(Name name) {
        logger.info("GuessGenders();");
        while(!(name.isFemaleChecked() && name.isMaleChecked())){
            try (InputStream namesStream = resource.getInputStream()) {
                BufferedReader br = new BufferedReader(new InputStreamReader(namesStream, StandardCharsets.UTF_8));
                Long start = dictionaryMap.get(dictionaryMap.floorKey(name.getStartChar()));
                if (start == null) start = 0L;
                br.lines().skip(start).forEach(l -> {
                            if (l.equals(name.getName())) {
                                if (name.getGenderToCheck().equals(GENDERS.FEMALE)) {
                                    name.setGender(GENDERS.FEMALE);
                                }
                                if (name.getGenderToCheck().equals(GENDERS.MALE)) {
                                    name.setGender(GENDERS.MALE);
                                }
                                name.setFemaleChecked(true);
                                name.setMaleChecked(true);
                            }
                        }
                );
                if (name.getGender() == null) {
                    if (name.getGenderToCheck().equals(GENDERS.FEMALE)) {
                        name.setFemaleChecked(true);
                        name.setGenderToCheck(GENDERS.MALE);
                        setResourceAndDictionaryForCheckingGivenGender(name);
                    }
                    if (name.getGenderToCheck().equals(GENDERS.MALE)) {
                        name.setMaleChecked(true);
                        name.setGenderToCheck(GENDERS.FEMALE);
                        setResourceAndDictionaryForCheckingGivenGender(name);
                    }
                }
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if ((name.isFemaleChecked() && name.isMaleChecked()) && name.getGender() == null) name.setGender(GENDERS.INCONCLUSIVE);
    }
}