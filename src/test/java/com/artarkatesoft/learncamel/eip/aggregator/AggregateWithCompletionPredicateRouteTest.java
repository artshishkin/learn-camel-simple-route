package com.artarkatesoft.learncamel.eip.aggregator;

import org.apache.camel.RoutesBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.Test;

class AggregateWithCompletionPredicateRouteTest extends CamelTestSupport {

    @Override
    protected RoutesBuilder createRouteBuilder() throws Exception {
        return new AggregateWithCompletionPredicateRoute();
    }

    @Test
    void testAggregatePredicateCompletion() throws InterruptedException {
        //given
        String orderCreated = "12345,samsung-phone,order-created";
        String orderConfirmed = "12345,samsung-phone,order-confirmed";

        MockEndpoint mockEndpoint = getMockEndpoint("mock:completionPredicate");
        mockEndpoint.expectedBodiesReceived(orderCreated + ":" + orderConfirmed);

        //when
        template.sendBodyAndHeader("direct:completionPredicate", orderCreated, "aggregationId", 123);
        template.sendBodyAndHeader("direct:completionPredicate", orderConfirmed, "aggregationId", 123);

        //then
        assertMockEndpointsSatisfied();
    }
}