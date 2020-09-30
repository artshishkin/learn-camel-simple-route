package com.artarkatesoft.learncamel.routes.transform;

import org.apache.camel.builder.RouteBuilder;

public class ModifyTransformRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("direct:transform")
                .transform(body().regexReplaceAll(",", "*"))
                .to("mock:transform");
    }
}
