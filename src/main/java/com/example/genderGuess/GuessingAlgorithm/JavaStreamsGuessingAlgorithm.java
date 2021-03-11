package com.example.genderGuess.GuessingAlgorithm;

import com.example.genderGuess.Dictionary;
import com.example.genderGuess.Service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
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

    @Override
    public Map<String, String> guessGenderFromGivenNames(Map<String, String> names) {

        TreeMap<Character, Long> dictionary = dictionaryRepo.getFemaleDictionary();


        names.entrySet().forEach(entry -> {
            if (!entry.getValue().isEmpty()) return;
            boolean maleDbChecked = false;
            boolean femaleDbChecked = false;

            do {
                String nextDbToRun = "";
                //Female names ends with 'a' check given names if they are, for those that are search females first
                if ((entry.getKey().charAt(entry.getKey().length() - 1) == 'A') && !femaleDbChecked) {
                    nextDbToRun = femaleDb;
                    resource = resourceService.getFemaleResource();
                } else if (!maleDbChecked) {
                    nextDbToRun = maleDb;
                    resource = resourceService.getMaleResource();
                } else if (!femaleDbChecked) {
                    nextDbToRun = femaleDb;
                    resource = resourceService.getFemaleResource();
                }

                try (InputStream namesStream = resource.getInputStream()) {

                    BufferedReader br = new BufferedReader(new InputStreamReader(namesStream, StandardCharsets.UTF_8));
                    String nextLine;
                    char startChar = entry.getKey().charAt(0);
                    Long start = dictionary.get(dictionary.floorKey(startChar));
                    if (start == null) start = 0L;
                    String finalNextDbToRun = nextDbToRun;
                    br.lines().skip(start).forEach(l -> {
                                if (l.equals(entry.getKey())) {
                                    if (finalNextDbToRun.equals(femaleDb)) {
                                        entry.setValue(FEMALE);
                                    } else {
                                        entry.setValue(MALE);
                                    }
                                }
                            }
                    );
                    while ((nextLine = br.readLine()) != null) {
                        if (nextLine.equals(entry.getKey())) {
                            if (nextDbToRun.equals(femaleDb)) {
                                entry.setValue(FEMALE);
                            } else {
                                entry.setValue(MALE);
                            }
                        }
                    }
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (nextDbToRun.equals(maleDb)) maleDbChecked = true;
                else if (nextDbToRun.equals(femaleDb)) femaleDbChecked = true;

                if (maleDbChecked && femaleDbChecked && entry.getValue().isEmpty()) entry.setValue(INCONCLUSIVE);

            } while (entry.getValue().isEmpty());

        });

        return names;
    }
}