package com.example.android.jokeslibrary;

public class JokeFactory {


    private final static String[] jokes = {"Can a kangaroo jump higher than a house? \n" +
            "- Of course, a house does not jump at all.\n"};

    public static String getJoke() {
        return jokes[0];
    }
}
