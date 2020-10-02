package com.artarkatesoft.learncamel.routes.errorhandling.onexception;

import com.artarkatesoft.learncamel.routes.errorhandling.DataModifierWithException;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;

public class OnExceptionHandlerRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        onException(NullPointerException.class)
                .log(LoggingLevel.INFO, "Nullpointer Exception in Bean caught")
                .redeliveryDelay(100)
                .backOffMultiplier(2)
                .maximumRedeliveries(8)
                .maximumRedeliveryDelay(2000);

        onException(RuntimeException.class).log(LoggingLevel.WARN, "Runtime Exception in Bean caught");
        onException(Exception.class).log(LoggingLevel.ERROR, "Exception in Bean caught");

        from("direct:exception")
                .bean(new DataModifierWithException(), "mapOnException")
                .log("Message is ${body}")
                .to("mock:exception");
    }
}
