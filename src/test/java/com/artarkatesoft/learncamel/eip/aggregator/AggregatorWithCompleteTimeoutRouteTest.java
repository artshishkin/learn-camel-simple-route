package com.artarkatesoft.learncamel.eip.aggregator;

import org.apache.camel.RoutesBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.Test;

class AggregatorWithCompleteTimeoutRouteTest extends CamelTestSupport {
    @Override
    protected RoutesBuilder createRouteBuilder() throws Exception {
        return new AggregatorWithCompleteTimeoutRoute();
    }

    @Test
    void aggregateWithCompleteTimeout() throws InterruptedException {
        //given
        MockEndpoint mockEndpoint = getMockEndpoint("mock:timeoutAggregator");
        mockEndpoint.expectedBodiesReceived("12", "345", "67");

        //when
        template.sendBodyAndHeader("direct:timeoutAggregator", "1", "aggregatorId", 1);
        template.sendBodyAndHeader("direct:timeoutAggregator", "2", "aggregatorId", 1);
        Thread.sleep(3000);
        template.sendBodyAndHeader("direct:timeoutAggregator", "3", "aggregatorId", 1);
        template.sendBodyAndHeader("direct:timeoutAggregator", "4", "aggregatorId", 1);
        template.sendBodyAndHeader("direct:timeoutAggregator", "5", "aggregatorId", 1);
        template.sendBodyAndHeader("direct:timeoutAggregator", "6", "aggregatorId", 1);
        template.sendBodyAndHeader("direct:timeoutAggregator", "7", "aggregatorId", 1);

        //then
        assertMockEndpointsSatisfied();
    }
}