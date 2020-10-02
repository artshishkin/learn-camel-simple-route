package com.artarkatesoft.learncamel.routes.errorhandling;

public class DataModifierWithException {
    public String map(String input) {
        //will throw NullPointer exception
        return input.replace(",","*");
    }
}
