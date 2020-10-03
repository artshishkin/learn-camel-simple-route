package com.artarkatesoft.learncamel.eip.recipientlist;

import org.apache.camel.builder.RouteBuilder;

public class RecipientListEIPRoute extends RouteBuilder {

    private static final String RECIPIENT_LIST_BASE_FOLDER = "data/eip/recipient_list";
    public static final String RECIPIENT_LIST_INPUT_FOLDER = RECIPIENT_LIST_BASE_FOLDER + "/input";
    public static final String RECIPIENT_LIST_OUTPUT_FOLDER = RECIPIENT_LIST_BASE_FOLDER + "/output";

    @Override
    public void configure() throws Exception {
        from("file:" + RECIPIENT_LIST_INPUT_FOLDER + "?noop=true&recursive=true")
                .setHeader("type", xpath("/employee/@type"))
                .process(new RecipientListEIPProcessor())
                .recipientList(header("recipientUri"));
    }
}
