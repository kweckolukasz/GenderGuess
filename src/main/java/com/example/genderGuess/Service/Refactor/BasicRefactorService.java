package com.example.genderGuess.Service.Refactor;

import com.example.genderGuess.Model.Name;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BasicRefactorService implements RefactorService {

    List<Name> names = new ArrayList<>();


    public List<Name> cleanUpFormatAndExportGivenString(String providedName) {

        providedName = cleanUpAndFormat(providedName);
        exportNamesToList(providedName);
        List<Name> exportNames = names;
        names = new ArrayList<>();
        return exportNames;
    }

    private void exportNamesToList(String providedName) {
        char[] chars = providedName.toCharArray();
        String nameToAdd;
        int start = 0;
        for (int i = 0; i < chars.length; i++) {
            char character = chars[i];
            if (character == '_') {
                nameToAdd = providedName.substring(start, i);
                names.add(
                        new Name(nameToAdd)
                );
                start = i + 1;
            }

        }
    }

    private String cleanUpAndFormat(String providedName) {

        if (providedName.chars()
                .mapToObj(c -> (char) c)
                .anyMatch(c -> !Character.isLetter(c) && c != '_' && c != ' '))
            throw new IllegalArgumentException("Name should consist of letters, whitespace is allowed if you provide multiple names");

        providedName = providedName
                .trim()
                .replaceAll(" +", " ")
                .replaceAll(" ", "_")
                .replaceAll("_+", "_")
                .toUpperCase(Locale.ROOT);

        StringBuilder sb = new StringBuilder(providedName);
        sb.append('_');
        if(sb.charAt(0)=='_') sb.deleteCharAt(0);
        providedName = sb.toString();
        return providedName;
    }


}
