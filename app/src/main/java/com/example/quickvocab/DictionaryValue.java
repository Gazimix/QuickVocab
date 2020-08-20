package com.example.quickvocab;

public class DictionaryValue {


    private static int numberOfValues = 0;
    private String word;
    private String meaning;

    public DictionaryValue(String word, String meaning) {
        this.word = word;
        this.meaning = meaning;
        ++numberOfValues;
    }

    public static int getNumberOfValues() {
        return numberOfValues;
    }


    public String getWord() {
        return word;
    }

    public String getMeaning() {
        return meaning;
    }
}
