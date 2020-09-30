package com.artarkatesoft.learncamel.routes.process;

import com.artarkatesoft.learncamel.processors.ModifyFileExampleProcessor;
import org.apache.camel.builder.RouteBuilder;

public class ModifyFileProcessorRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("file:data/input_comma?noop=true")
                .log("Read file is ${body} and headers are ${headers}")
                .process(new ModifyFileExampleProcessor())
                .to("file:data/output_process?fileName=outputProcess.txt");
    }

}
