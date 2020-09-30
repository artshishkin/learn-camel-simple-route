package com.artarkatesoft.learncamel.file;

import org.apache.camel.builder.RouteBuilder;

public class CopyFilesRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("file:data/input?noop=true")
                .to("log:?level=INFO&showBody=true&showHeaders=true")
                .to("file:data/output")
                .to("file:data/output_reserve");

        from("file:data/input2?noop=true")
                .to("file:data/output2");
    }
}
