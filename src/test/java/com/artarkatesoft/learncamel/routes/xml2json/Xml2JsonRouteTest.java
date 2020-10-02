package com.artarkatesoft.learncamel.routes.xml2json;

import org.apache.camel.RoutesBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class Xml2JsonRouteTest {

    @Nested
    class Xml2JsonDefaultRouteTest extends CamelTestSupport {

        @Override
        protected RoutesBuilder createRouteBuilder() throws Exception {
            return new Xml2JsonDefaultRoute();
        }

        @Test
        void testXml2JsonMarshalling() throws InterruptedException {
            //given
            String direct = "<?xml version='1.0' encoding='UTF-8'?><employee><id>123</id><name>Art</name><joinDate>01.10.2020</joinDate></employee>";
            String expectedJson = "{\"com.artarkatesoft.learncamel.domain.Employee\":{\"id\":123,\"name\":\"Art\",\"joinDate\":\"01.10.2020\"}}";

            MockEndpoint mockEndpoint = getMockEndpoint("mock:marshalEmployeeXml2Json");
            mockEndpoint.expectedBodiesReceived(expectedJson);

            //when
            template.sendBody("direct:marshalEmployeeXml2Json", direct);

            //then
            assertMockEndpointsSatisfied();
        }
    }

    @Nested
    class Xml2JsonCustomRouteTest extends CamelTestSupport {

        @Override
        protected RoutesBuilder createRouteBuilder() throws Exception {
            return new Xml2JsonCustomRoute();
        }

        @Test
        void testXml2JsonMarshalling() throws InterruptedException {
            //given
            String direct = "<?xml version='1.0' encoding='UTF-8'?><employee><id>123</id><name>Art</name><joinDate>01.10.2020</joinDate></employee>";
            String expectedJson = "{\"id\":\"123\",\"name\":\"Art\",\"joinDate\":\"01.10.2020\"}";

            MockEndpoint mockEndpoint = getMockEndpoint("mock:marshalEmployeeXml2Json");
            mockEndpoint.expectedBodiesReceived(expectedJson);

            //when
            template.sendBody("direct:marshalEmployeeXml2Json", direct);

            //then
            assertMockEndpointsSatisfied();
        }

        @Test
        void testJson2XmlMarshalling() throws InterruptedException {
            //given
            String directJson = "{\"id\":\"123\",\"name\":\"Art\",\"joinDate\":\"01.10.2020\"}";
            String expectedXml = "<?xml version='1.0' encoding='UTF-8'?><employee><id>123</id><name>Art</name><joinDate>01.10.2020</joinDate></employee>";

            MockEndpoint mockEndpoint = getMockEndpoint("mock:marshalEmployeeJson2Xml");
            mockEndpoint.expectedBodiesReceived(expectedXml);

            //when
            template.sendBody("direct:marshalEmployeeJson2Xml", directJson);

            //then
            assertMockEndpointsSatisfied();
        }
    }
}