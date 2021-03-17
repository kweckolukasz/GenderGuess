package com.example.genderGuess.Service.GuessingAlgorithm;

import com.example.genderGuess.Model.Name;

import java.util.List;

public interface GuessingAlgorithm {


    List<Name> guessGenderFromGivenNames(List<Name> names);


}
