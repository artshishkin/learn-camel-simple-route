package com.artarkatesoft.learncamel.routes.errorhandling.defaulterror;

import com.artarkatesoft.learncamel.routes.errorhandling.DataModifierWithException;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;

public class DefaultErrorHandlerRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
//
//        errorHandler(defaultErrorHandler()
//                .maximumRedeliveries(3)
//                .redeliveryDelay(3000)
//                .retryAttemptedLogLevel(LoggingLevel.WARN));
        errorHandler(defaultErrorHandler()
                .maximumRedeliveries(8)
                .redeliveryDelay(100)
                .backOffMultiplier(2)
                .maximumRedeliveryDelay(2000)
                .retryAttemptedLogLevel(LoggingLevel.WARN));

        from("direct:exception")
                .bean(new DataModifierWithException(), "map")
//                .log("Message is ${body}")
                .to("log:?showBody=true&level=INFO")
                .to("mock:errorqueue");
    }
}
