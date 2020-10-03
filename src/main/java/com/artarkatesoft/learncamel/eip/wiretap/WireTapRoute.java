package com.artarkatesoft.learncamel.eip.wiretap;

import org.apache.camel.builder.RouteBuilder;

public class WireTapRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("file:data/eip/wire_tap/input?noop=true")
                .wireTap("file:data/eip/wire_tap/output/debug")
                .to("file:data/eip/wire_tap/output/all");
    }
}
