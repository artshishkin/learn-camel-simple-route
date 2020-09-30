package com.artarkatesoft.learncamel.routes.process;

import org.apache.camel.builder.RouteBuilder;

public class ModifyDirectProcessorRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("direct:processorInput")
                .log("Received message before process is ${body} and Headers are ${headers}")
                .process(exchange -> {
                    String colonSeparated = exchange.getIn().getBody(String.class).replace(",", ":");
                    exchange.getIn().setBody(colonSeparated);
                })
                .log("Received message after process is ${body} and Headers are ${headers}")
                .to("file:data/output_direct?fileName=colonSeparated.txt")
                .to("mock:outputSeparated");
    }
}
