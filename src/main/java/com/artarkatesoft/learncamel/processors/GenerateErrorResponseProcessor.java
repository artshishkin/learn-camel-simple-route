package com.artarkatesoft.learncamel.processors;

import org.apache.camel.Endpoint;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class GenerateErrorResponseProcessor implements Processor {

    Logger log = LoggerFactory.getLogger(GenerateErrorResponseProcessor.class);

    @Override
    public void process(Exchange exchange) throws Exception {

        Exception exception = exchange.getException();
        log.warn("Actual exception is " + exception);//null
        log.warn("Exception handled is {}", exchange.getProperty(Exchange.EXCEPTION_HANDLED));//null
        log.warn("Exception caught is {}", exchange.getProperty(Exchange.EXCEPTION_CAUGHT));

        exception = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);

        log.warn("Actual exception message is " + exception.getMessage());
        log.warn("Actual exception class is " + exception.getClass());

        Endpoint fromEndpoint = exchange.getFromEndpoint();
        log.warn("From endpoint: " + fromEndpoint);
        log.warn("Failure endpoint: " +
                exchange.getProperty(Exchange.FAILURE_ENDPOINT));

        exchange.getIn().setBody("Exception happened in the route");
    }
}
