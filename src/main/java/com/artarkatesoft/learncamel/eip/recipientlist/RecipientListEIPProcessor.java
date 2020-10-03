package com.artarkatesoft.learncamel.eip.recipientlist;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import static com.artarkatesoft.learncamel.eip.recipientlist.RecipientListEIPRoute.RECIPIENT_LIST_OUTPUT_FOLDER;

public class RecipientListEIPProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        String type = exchange.getIn().getHeader("type", String.class);
        String recipientUri = "file:" + RECIPIENT_LIST_OUTPUT_FOLDER + "/" + type;
        exchange.getIn().setHeader("recipientUri", recipientUri);
    }
}
