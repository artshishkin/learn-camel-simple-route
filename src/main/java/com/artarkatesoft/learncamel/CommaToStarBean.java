package com.artarkatesoft.learncamel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommaToStarBean {

    Logger log = LoggerFactory.getLogger(CommaToStarBean.class);

    public String map(String input) {
        log.info("Inside `map`");
        return input.replace(",", "*");
    }

    public String mapSecondary(String input) {
        log.info("Inside `mapSecondary`");
        return input.replace(",", "*");
    }
}
