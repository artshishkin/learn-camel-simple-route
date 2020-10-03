package com.artarkatesoft.learncamel.eip.aggregator.strategy;

import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;

public class AggregatorSimpleRouteStrategy implements AggregationStrategy {
    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        if (oldExchange == null)
            return newExchange;

        String newBody = newExchange.getIn().getBody(String.class);
        String oldBody = oldExchange.getIn().getBody(String.class);

        newExchange.getIn().setBody(oldBody + newBody);

        return newExchange;
    }
}
