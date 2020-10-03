package com.artarkatesoft.learncamel.eip.aggregator;

import com.artarkatesoft.learncamel.eip.aggregator.strategy.AggregatorSimpleRouteStrategy;
import org.apache.camel.builder.RouteBuilder;

public class AggregatorWithCompleteIntervalRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("direct:intervalAggregator")
                .log("Received message is ${body} and headers key is ${headers.aggregatorId}")
                .aggregate()
                .header("aggregatorId")
                .aggregationStrategy(new AggregatorSimpleRouteStrategy())
                .completionSize(3)
                .completionInterval(100)
                .log("Aggregated message is ${body}")
                .to("mock:intervalAggregator");

    }
}
