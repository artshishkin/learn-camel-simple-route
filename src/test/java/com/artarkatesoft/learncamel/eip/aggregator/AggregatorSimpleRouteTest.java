package com.artarkatesoft.learncamel.eip.aggregator;

import org.apache.camel.RoutesBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class AggregatorSimpleRouteTest extends CamelTestSupport {

    @Override
    protected RoutesBuilder createRouteBuilder() throws Exception {
        return new AggregatorSimpleRoute();
    }

    @Test
    void aggregateSimple() throws InterruptedException {
        //given
        MockEndpoint mockEndpoint = getMockEndpoint("mock:simpleAggregator");
        mockEndpoint.expectedBodiesReceived("123");

        //when
        template.sendBodyAndHeader("direct:simpleAggregator", "1", "aggregatorId", 1);
        template.sendBodyAndHeader("direct:simpleAggregator", "2", "aggregatorId", 1);
        template.sendBodyAndHeader("direct:simpleAggregator", "4", "aggregatorId", 2);
        template.sendBodyAndHeader("direct:simpleAggregator", "3", "aggregatorId", 1);

        //then
        assertMockEndpointsSatisfied();
    }

    @Test
    void aggregateMultipleMessages() throws InterruptedException {
        //given
        MockEndpoint mockEndpoint = getMockEndpoint("mock:simpleAggregator");
        List<String> aggregatedMessages = Arrays.asList("123", "567");
        mockEndpoint.expectedBodiesReceived(aggregatedMessages);

        //when
        template.sendBodyAndHeader("direct:simpleAggregator", "1", "aggregatorId", 1);
        template.sendBodyAndHeader("direct:simpleAggregator", "2", "aggregatorId", 1);
        template.sendBodyAndHeader("direct:simpleAggregator", "4", "aggregatorId", 2);
        template.sendBodyAndHeader("direct:simpleAggregator", "3", "aggregatorId", 1);
        template.sendBodyAndHeader("direct:simpleAggregator", "5", "aggregatorId", 1);
        template.sendBodyAndHeader("direct:simpleAggregator", "6", "aggregatorId", 1);
        template.sendBodyAndHeader("direct:simpleAggregator", "7", "aggregatorId", 1);
        template.sendBodyAndHeader("direct:simpleAggregator", "8", "aggregatorId", 1);

        //then
        assertMockEndpointsSatisfied();
    }

    @Test
    void aggregateMultipleMessages_DifferentAggregatorIds() throws InterruptedException {
        //given
        MockEndpoint mockEndpoint = getMockEndpoint("mock:simpleAggregator");
        List<String> aggregatedMessages = Arrays.asList("123", "468","579");
        mockEndpoint.expectedBodiesReceived(aggregatedMessages);

        //when
        template.sendBodyAndHeader("direct:simpleAggregator", "1", "aggregatorId", 1);
        template.sendBodyAndHeader("direct:simpleAggregator", "2", "aggregatorId", 1);
        template.sendBodyAndHeader("direct:simpleAggregator", "4", "aggregatorId", 2);
        template.sendBodyAndHeader("direct:simpleAggregator", "3", "aggregatorId", 1);
        template.sendBodyAndHeader("direct:simpleAggregator", "5", "aggregatorId", 1);
        template.sendBodyAndHeader("direct:simpleAggregator", "6", "aggregatorId", 2);
        template.sendBodyAndHeader("direct:simpleAggregator", "7", "aggregatorId", 1);
        template.sendBodyAndHeader("direct:simpleAggregator", "8", "aggregatorId", 2);
        template.sendBodyAndHeader("direct:simpleAggregator", "9", "aggregatorId", 1);

        //then
        assertMockEndpointsSatisfied();
    }

}