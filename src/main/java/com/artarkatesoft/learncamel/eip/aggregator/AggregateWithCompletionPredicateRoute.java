package com.artarkatesoft.learncamel.eip.aggregator;

import com.artarkatesoft.learncamel.eip.aggregator.strategy.PredicateAggregationStrategy;
import org.apache.camel.builder.RouteBuilder;

public class AggregateWithCompletionPredicateRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("direct:completionPredicate")
                .log("Received message is ${body} and the header is ${header.aggregationId}")
                .aggregate().header("aggregationId").aggregationStrategy(new PredicateAggregationStrategy())
                .completionPredicate(body().contains("order-confirm")).eagerCheckCompletion()
                .log("Final Message is ${body}")
                .to("mock:completionPredicate");
    }
}
