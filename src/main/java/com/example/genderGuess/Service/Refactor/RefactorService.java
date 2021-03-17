package com.example.genderGuess.Service.Refactor;

import com.example.genderGuess.Model.Name;

import java.util.List;
import java.util.Map;

public interface RefactorService {

    List<Name> cleanUpFormatAndExportGivenString(String providedName);

}
