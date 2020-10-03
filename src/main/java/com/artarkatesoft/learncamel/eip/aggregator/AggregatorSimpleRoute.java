package com.artarkatesoft.learncamel.eip.aggregator;

import com.artarkatesoft.learncamel.eip.aggregator.strategy.AggregatorSimpleRouteStrategy;
import org.apache.camel.builder.RouteBuilder;

public class AggregatorSimpleRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("direct:simpleAggregator")
                .log("Received message ${body} and key ${header.aggregatorId}")
//                .aggregate(header("aggregatorId"), (oldExchange, newExchange) -> null);
                .aggregate(header("aggregatorId"), new AggregatorSimpleRouteStrategy()).completionSize(3)
                .log("Aggregated message ${body} and key ${header.aggregatorId}")
                .to("mock:simpleAggregator");
    }
}
