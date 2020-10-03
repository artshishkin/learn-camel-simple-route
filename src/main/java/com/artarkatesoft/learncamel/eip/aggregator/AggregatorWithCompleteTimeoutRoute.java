package com.artarkatesoft.learncamel.eip.aggregator;

import org.apache.camel.builder.RouteBuilder;

public class AggregatorWithCompleteTimeoutRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("direct:timeoutAggregator")
                .log("Received message is ${body} and headers key is ${headers.aggregatorId}")
                .aggregate()
                .header("aggregatorId")
                .aggregationStrategy(
                        (oldExchange, newExchange) -> {
                            if (oldExchange == null)
                                return newExchange;

                            String newBody = newExchange.getIn().getBody(String.class);
                            String oldBody = oldExchange.getIn().getBody(String.class);

                            newExchange.getIn().setBody(oldBody + newBody);

                            return newExchange;
                        })
                .completionSize(3)
                .completionTimeout(1000)
                .log("Aggregated message is ${body}")
                .to("mock:timeoutAggregator");

    }
}
