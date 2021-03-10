package com.example.genderGuess.Service;

import com.example.genderGuess.GuessingAlgorithm.GuessingAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

@Service
public class BasicRefactorService implements RefactorService {

    @Autowired
    GuessingAlgorithm algorithm;

    Map<String,String> names;

    @Override
    public Map<String, String> refactorGivenMultipleNameString(String providedName) {

        names = cleanUpFormatAndExportGivenString(providedName);
        return names;
    }

    @Override
    public Map<String, String> refactorGivenSingleNameString(String providedName) {

        LinkedHashMap<String,String> namesMap = (LinkedHashMap<String, String>) cleanUpFormatAndExportGivenString(providedName);
        Map.Entry<String, String> firstEntry = namesMap.entrySet().iterator().next();
        names.put(firstEntry.getKey(), firstEntry.getValue());
        return names;

    }

    private Map<String,String> cleanUpFormatAndExportGivenString(String providedName){

        names = new LinkedHashMap<>();

        providedName = providedName
                .trim()
                .replaceAll(" +", " ")
                .replaceAll(" ", "_")
                .toUpperCase(Locale.ROOT);

        StringBuilder sb = new StringBuilder(providedName);
        sb.append('_');
        providedName = sb.toString();

        char[] chars = providedName.toCharArray();

        int start = 0;
        for (int i = 0; i < chars.length; i++){
            char character = chars[i];
            if (!(Character.isLetter(character)||character=='_')) throw new IllegalArgumentException("Name should consist of letters, whitespace is allowed if you provide multiple names");
            if (character == '_'){
                names.put(
                        providedName.substring(start,i),
                        ""
                );
                start = i+1;
            }

        }
        return names;
    }


}
