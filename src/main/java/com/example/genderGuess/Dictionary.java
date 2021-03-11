package com.example.genderGuess;

import com.example.genderGuess.Service.ResourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.TreeMap;

public class Dictionary {

    Logger logger = LoggerFactory.getLogger(Dictionary.class);

    TreeMap<Character, Long> femaleDictionary;
    TreeMap<Character, Long> maleDictionary;

    @Autowired
    ResourceService resourceService;
    Resource resource;

    boolean maleDictionaryCreated = false;
    boolean femaleDictionaryCreated = false;

    public void dictionary() {
        logger.info("inside Constructor");
        femaleDictionary = new TreeMap<>();
        maleDictionary = new TreeMap<>();
        try {
            while (!maleDictionaryCreated && !femaleDictionaryCreated) {
                TreeMap<Character, Long> currentDictionary = null;
                if (!femaleDictionaryCreated) {
                    resource = resourceService.getFemaleResource();
                    currentDictionary = femaleDictionary;
                    currentDictionary.put('A', 0L);
                    logger.info("female dictionary assigned");
                }
                if (!maleDictionaryCreated) {
                    resource = resourceService.getMaleResource();
                    currentDictionary = maleDictionary;
                    currentDictionary.put('A', 0L);
                    logger.info("male dictionary assigned");
                }

                InputStream inputStream = resource.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));

                long lineCount = 0L;
                Character current;

                while (br.readLine() != null && currentDictionary != null) {

                    current = br.readLine().charAt(0);

                    if (currentDictionary.lastKey()<current) {
                        currentDictionary.put(current, lineCount);
                    }
                    lineCount++;

                }
                if (currentDictionary == femaleDictionary) femaleDictionaryCreated = true;
                if (currentDictionary == maleDictionary) maleDictionaryCreated = true;
            }
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
