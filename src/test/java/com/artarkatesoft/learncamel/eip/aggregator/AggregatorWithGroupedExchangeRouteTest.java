package com.artarkatesoft.learncamel.eip.aggregator;

import org.apache.camel.Exchange;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AggregatorWithGroupedExchangeRouteTest extends CamelTestSupport {

    @Override
    protected RoutesBuilder createRouteBuilder() throws Exception {
        return new AggregatorWithGroupedExchangeRoute();
    }

    @Test
    void testGroupedExchange() throws InterruptedException {
        //given
        MockEndpoint mockEndpoint = getMockEndpoint("mock:groupedExchangeAggregator");
        mockEndpoint.expectedMessageCount(1);

        //when
        template.sendBodyAndHeader("direct:groupedExchangeAggregator", "1", "aggregatorId", 123);
        template.sendBodyAndHeader("direct:groupedExchangeAggregator", "2", "aggregatorId", 123);
        template.sendBodyAndHeader("direct:groupedExchangeAggregator", "3", "aggregatorId", 123);

        //then
        assertMockEndpointsSatisfied();

        List<Exchange> exchanges = mockEndpoint.getExchanges();
        assertEquals(1, exchanges.size());
        Exchange exchange = exchanges.get(0);
//        List<Exchange> exchangeList = (List<Exchange>) exchange.getProperty(Exchange.GROUPED_EXCHANGE);
        List<Exchange> exchangeList = (List<Exchange>) exchange.getIn().getBody();
        assertEquals("1", exchangeList.get(0).getIn().getBody(String.class));
        assertEquals("2", exchangeList.get(1).getIn().getBody(String.class));
        assertEquals("3", exchangeList.get(2).getIn().getBody(String.class));

    }
}