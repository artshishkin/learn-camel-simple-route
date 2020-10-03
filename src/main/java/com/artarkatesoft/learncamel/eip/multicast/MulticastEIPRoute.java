package com.artarkatesoft.learncamel.eip.multicast;

import org.apache.camel.builder.RouteBuilder;

public class MulticastEIPRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("file:data/eip/multicast/input?fileName=multi.txt&noop=true")
                .to("log:?level=INFO&showBody=true&showHeaders=true")
                .multicast().stopOnException().parallelProcessing()
                .to("file:data/eip/multicast/output/x", "file:data/eip/multicast/output/y", "file:data/eip/multicast/output/z")
                .end()
                .to("mock:w");
    }
}
