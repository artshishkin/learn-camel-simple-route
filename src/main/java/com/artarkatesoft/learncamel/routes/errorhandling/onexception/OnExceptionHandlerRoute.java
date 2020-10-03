package com.artarkatesoft.learncamel.routes.errorhandling.onexception;

import com.artarkatesoft.learncamel.processors.GenerateErrorResponseProcessor;
import com.artarkatesoft.learncamel.routes.errorhandling.DataModifierWithException;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.OnExceptionDefinition;

public class OnExceptionHandlerRoute extends RouteBuilder {

    private final boolean handleException;

    private boolean messageToKafka = false;

    public OnExceptionHandlerRoute() {
        this(false);
    }

    public OnExceptionHandlerRoute(boolean handleException) {
        this.handleException = handleException;
    }

    public boolean isMessageToKafka() {
        return messageToKafka;
    }

    public void setMessageToKafka(boolean messageToKafka) {
        this.messageToKafka = messageToKafka;
    }

    @Override
    public void configure() throws Exception {

        OnExceptionDefinition definition = onException(NullPointerException.class)
                .log(LoggingLevel.WARN, "Nullpointer Exception in Bean caught")
                .redeliveryDelay(100)
                .backOffMultiplier(2)
                .maximumRedeliveries(6)
                .maximumRedeliveryDelay(1000);

        if (handleException)
            definition
                    .handled(true)
                    .process(new GenerateErrorResponseProcessor());
        if (messageToKafka)
            definition
                    .to("kafka:errortopic");

        onException(RuntimeException.class).log(LoggingLevel.WARN, "Runtime Exception in Bean caught");
        onException(Exception.class).log(LoggingLevel.ERROR, "Exception in Bean caught");

        from("direct:exception")
                .bean(new DataModifierWithException(), "mapOnException")
                .log("Message is ${body}")
                .to("mock:exception");
    }
}
