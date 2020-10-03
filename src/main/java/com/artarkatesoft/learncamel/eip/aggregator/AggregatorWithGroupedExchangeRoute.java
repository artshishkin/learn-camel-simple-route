package com.artarkatesoft.learncamel.eip.aggregator;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.processor.aggregate.GroupedExchangeAggregationStrategy;

public class AggregatorWithGroupedExchangeRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("direct:groupedExchangeAggregator")
                .log("Received Message is ${body} and headers are ${header.aggregatorId}")
                .aggregate()
                .header("aggregatorId")
                .aggregationStrategy(new GroupedExchangeAggregationStrategy())
                .completionSize(3)
                .log("Aggregated messages are ${body}")
                .to("mock:groupedExchangeAggregator");
    }
}
