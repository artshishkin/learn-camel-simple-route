package com.artarkatesoft.learncamel.routes.errorhandling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataModifierWithException {

    Logger log = LoggerFactory.getLogger(DataModifierWithException.class);

    public String map(String input) {
        //will throw NullPointer exception
        return input.replace(",", "*");
    }

    public String mapOnException(String input) {
        log.info("Trying to map object {}", input);
        return input.replace(",", "*");
    }
}
