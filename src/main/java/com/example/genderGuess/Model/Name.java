package com.example.genderGuess.Model;

import java.util.Objects;

public class Name {

    private String name;
    private GENDERS gender = null;
    private GENDERS genderToCheck;
    private boolean isFemaleChecked = false;
    private boolean isMaleChecked = false;
    private char startChar;

    public Name(String name) {
        this.name = name;
        if (name.charAt(name.length()-1) == 'a') genderToCheck = GENDERS.FEMALE;
        else genderToCheck = GENDERS.MALE;
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

    @Override
    public boolean equals(Object obj) {

        if (obj == null) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Name)) {
            return false;
        }

        Name other = (Name) obj;
        boolean isEqual = true;
         if (!this.name.equals(other.name)) isEqual=false;
         if (!Objects.equals(this.gender, other.gender)) isEqual=false;
         if (!Objects.equals(this.genderToCheck, other.genderToCheck)) isEqual=false;
         return isEqual;
    }
}
