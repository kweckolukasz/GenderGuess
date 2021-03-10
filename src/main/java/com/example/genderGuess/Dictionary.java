package com.example.genderGuess;

import com.example.genderGuess.Service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.TreeMap;

@Service
public class Dictionary {

    TreeMap<Character, Long> femaleDictionary;
    TreeMap<Character, Long> maleDictionary;

    @Autowired
    ResourceService resourceService;
    Resource resource;

    boolean maleDictionaryCreated = false;
    boolean femaleDictionaryCreated = false;

    @PostConstruct
    private void initialize() {
        femaleDictionary = new TreeMap<>();
        maleDictionary = new TreeMap<>();
        try {
            do {
                TreeMap<Character, Long> currentDictionary = null;
                if (!femaleDictionaryCreated) {
                    resource = resourceService.getFemaleResource();
                    currentDictionary = femaleDictionary;
                } else if (!maleDictionaryCreated) {
                    resource = resourceService.getMaleResource();
                    currentDictionary = maleDictionary;
                }

                InputStream inputStream = resource.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));

                long lineCount = 0L;
                Character current;

                while (br.readLine() != null && currentDictionary != null) {
                    current = br.readLine().charAt(0);
                    if (currentDictionary.isEmpty()) currentDictionary.put(current, lineCount);
                    if (currentDictionary.containsKey(current)) currentDictionary.put(current, lineCount);
                    lineCount++;
                }
            } while (!maleDictionaryCreated && !femaleDictionaryCreated);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public TreeMap<Character, Long> getFemaleDictionary() {
        return femaleDictionary;
    }

    public TreeMap<Character, Long> getMaleDictionary() {
        return maleDictionary;
    }
}
