package com.example.genderGuess.Model;

public class Name {

    private String name;
    private GENDERS gender = null;
    private boolean isFemaleChecked = false;
    private boolean isMaleChecked = false;
    private GENDERS genderToCheck = null;
    private char startChar;

    public Name(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GENDERS getGender() {
        return gender;
    }

    public void setGender(GENDERS gender) {
        this.gender = gender;
    }

    public boolean isFemaleChecked() {
        return isFemaleChecked;
    }

    public void setFemaleChecked(boolean femaleChecked) {
        isFemaleChecked = femaleChecked;
    }

    public boolean isMaleChecked() {
        return isMaleChecked;
    }

    public void setMaleChecked(boolean maleChecked) {
        isMaleChecked = maleChecked;
    }

    public GENDERS getGenderToCheck() {
        return genderToCheck;
    }

    public void setGenderToCheck(GENDERS genderToCheck) {
        this.genderToCheck = genderToCheck;
    }

    public char getStartChar() {
        return name.charAt(0);
    }
}
