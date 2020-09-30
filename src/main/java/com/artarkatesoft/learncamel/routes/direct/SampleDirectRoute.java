package com.artarkatesoft.learncamel.routes.direct;

import org.apache.camel.builder.RouteBuilder;

public class SampleDirectRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("direct:sampleInput")
                .log("Received message is `${body}`; headers are `${headers}`")
                .to("file:data/output_direct?fileName=directFile.txt");
    }
}
