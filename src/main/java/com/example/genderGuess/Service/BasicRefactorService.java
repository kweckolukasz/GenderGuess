package com.example.genderGuess.Service;

import com.example.genderGuess.GuessingAlgorithm.GuessingAlgorithm;
import com.example.genderGuess.Model.Name;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BasicRefactorService implements RefactorService {

    @Autowired
    GuessingAlgorithm algorithm;

    List<Name> names;


    public List<Name> cleanUpFormatAndExportGivenString(String providedName){

        providedName = cleanUpAndFormat(providedName);

        exportNamesToList(providedName);

        return names;
    }

    private void exportNamesToList(String providedName) {
        char[] chars = providedName.toCharArray();

        int start = 0;
        for (int i = 0; i < chars.length; i++){
            char character = chars[i];
            if (!(Character.isLetter(character)||character=='_')) throw new IllegalArgumentException("Name should consist of letters, whitespace is allowed if you provide multiple names");
            if (character == '_'){
                names.add(
                        new Name(providedName.substring(start,i))
                );
                start = i+1;
            }

        }
    }

    private String cleanUpAndFormat(String providedName) {
        providedName = providedName
                .trim()
                .replaceAll(" +", " ")
                .replaceAll(" ", "_")
                .toUpperCase(Locale.ROOT);

        StringBuilder sb = new StringBuilder(providedName);
        sb.append('_');
        providedName = sb.toString();
        return providedName;
    }


}
