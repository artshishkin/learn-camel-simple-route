package com.artarkatesoft.learncamel.mock;

import org.apache.camel.builder.RouteBuilder;

public class SampleMockRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("direct:sampleInput")
                .log("Received message is ${body} and Headers are ${headers}")
                .to("mock:sampleOutput");
    }
}
