package com.artarkatesoft.learncamel.eip.contentbasedrouter;

import org.apache.camel.builder.RouteBuilder;

public class ContentBasedRouterEIPRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("file:data/eip/content_based_router/input?noop=true")

                .to("log:?level=INFO&showBody=true&showHeaders=true")

                .choice()
                    .when(header("CamelFileName").endsWith(".txt"))
                        .to("file:data/eip/content_based_router/output/txt")
                    .when(header("CamelFileName").endsWith(".json"))
                        .to("file:data/eip/content_based_router/output/json")
                    .when(header("CamelFileName").endsWith(".html"))
                        .to("file:data/eip/content_based_router/output/html")
                    .otherwise()
                        .to("file:data/eip/content_based_router/output/other")
                        .stop()
                .end()

                .to("file:data/eip/content_based_router/output/all")
        ;
    }
}
