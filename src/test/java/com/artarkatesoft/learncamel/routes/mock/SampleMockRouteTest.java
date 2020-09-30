package com.artarkatesoft.learncamel.routes.mock;

import org.apache.camel.RoutesBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.Test;

class SampleMockRouteTest extends CamelTestSupport {

    @Override
    protected RoutesBuilder createRouteBuilder() throws Exception {
        return new SampleMockRoute();
    }

    @Test
    void testMockRoute() throws InterruptedException {
        //given
        String messageText = "Art,1234";

        MockEndpoint mockEndpoint = getMockEndpoint("mock:sampleOutput");
        mockEndpoint.expectedMessageCount(1);
        mockEndpoint.expectedBodiesReceived(messageText);

        //when
        template.sendBody("direct:sampleInput", messageText);

        //then
        mockEndpoint.assertIsSatisfied(1000);


    }
}