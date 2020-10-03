package com.artarkatesoft.learncamel.eip.aggregator;

import org.apache.camel.RoutesBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.Test;

class AggregatorWithCompleteIntervalRouteTest extends CamelTestSupport {
    @Override
    protected RoutesBuilder createRouteBuilder() throws Exception {
        return new AggregatorWithCompleteIntervalRoute();
    }

    @Test
    void aggregateWithCompleteInterval() throws InterruptedException {
        //given
        MockEndpoint mockEndpoint = getMockEndpoint("mock:intervalAggregator");
        mockEndpoint.expectedBodiesReceived("12", "345", "6","78","9","012","3","4");

        //when
        template.sendBodyAndHeader("direct:intervalAggregator", "1", "aggregatorId", 1);
        template.sendBodyAndHeader("direct:intervalAggregator", "2", "aggregatorId", 1);
        Thread.sleep(300);
        template.sendBodyAndHeader("direct:intervalAggregator", "3", "aggregatorId", 1);
        template.sendBodyAndHeader("direct:intervalAggregator", "4", "aggregatorId", 1);
        template.sendBodyAndHeader("direct:intervalAggregator", "5", "aggregatorId", 1);
        template.sendBodyAndHeader("direct:intervalAggregator", "6", "aggregatorId", 1);
        Thread.sleep(200);
        template.sendBodyAndHeader("direct:intervalAggregator", "7", "aggregatorId", 1);
        template.sendBodyAndHeader("direct:intervalAggregator", "8", "aggregatorId", 1);
        Thread.sleep(100);
        template.sendBodyAndHeader("direct:intervalAggregator", "9", "aggregatorId", 1);
        Thread.sleep(100);
        template.sendBodyAndHeader("direct:intervalAggregator", "0", "aggregatorId", 1);
        template.sendBodyAndHeader("direct:intervalAggregator", "1", "aggregatorId", 1);
        template.sendBodyAndHeader("direct:intervalAggregator", "2", "aggregatorId", 1);
        template.sendBodyAndHeader("direct:intervalAggregator", "3", "aggregatorId", 1);
        Thread.sleep(100);
        template.sendBodyAndHeader("direct:intervalAggregator", "4", "aggregatorId", 1);

        //then
        assertMockEndpointsSatisfied();
    }
}