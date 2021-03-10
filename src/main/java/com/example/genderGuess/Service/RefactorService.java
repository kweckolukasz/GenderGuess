package com.example.genderGuess.Service;

import java.util.Map;

public interface RefactorService {

    Map<String, String> refactorGivenMultipleNameString(String providedName);

    Map<String, String> refactorGivenSingleNameString(String providedName);

}
