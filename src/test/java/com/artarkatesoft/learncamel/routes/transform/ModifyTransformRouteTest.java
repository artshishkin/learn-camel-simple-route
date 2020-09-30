package com.artarkatesoft.learncamel.routes.transform;

import org.apache.camel.RoutesBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ModifyTransformRouteTest extends CamelTestSupport {

    @Override
    protected RoutesBuilder createRouteBuilder() throws Exception {
        return new ModifyTransformRoute();
    }

    @Test
    void testTransform() {
        //given
        String expectedMessage = "Art*Shyshkin*1983";
        String directMessage = "Art,Shyshkin,1983";

        //when
        String actual = template.requestBody("direct:transform", directMessage, String.class);

        //then
        assertEquals(expectedMessage, actual);
    }

    @Test
    void testTransform_usingMock() throws InterruptedException {
        //given
        String expectedMessage = "Art*Shyshkin*1983";
        String directMessage = "Art,Shyshkin,1983";
        MockEndpoint mockEndpoint = getMockEndpoint("mock:transform");
        mockEndpoint.expectedBodiesReceived(expectedMessage);

        //when
        template.sendBody("direct:transform", directMessage);

        //then
        assertMockEndpointsSatisfied(500, TimeUnit.MILLISECONDS);
    }
}