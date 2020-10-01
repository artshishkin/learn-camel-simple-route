package com.artarkatesoft.learncamel.routes.bean;

import org.apache.camel.RoutesBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ModifyBeanRouteTest {

    String expectedMessage = "Art*Shyshkin*1983";
    String directMessage = "Art,Shyshkin,1983";

    @Nested
    class DefaultBeanMethodTest extends CamelTestSupport {
        @Override
        protected RoutesBuilder createRouteBuilder() throws Exception {
            return new ModifyBeanRoute();
        }

        @Test
        void beanTransformTest() throws InterruptedException {
            //given
            MockEndpoint mockEndpoint = getMockEndpoint("mock:outputBean");
            mockEndpoint.expectedBodiesReceived(expectedMessage);

            //when
            template.sendBody("direct:inputBean", directMessage);

            //then
            assertMockEndpointsSatisfied();
        }
    }

    @Nested
    class SecondaryMapBeanMethodTest extends CamelTestSupport {
        @Override
        protected RoutesBuilder createRouteBuilder() throws Exception {
            return new ModifyBeanRoute("mapSecondary");
        }

        @Test
        void beanTransformTest() throws InterruptedException {
            //given
            MockEndpoint mockEndpoint = getMockEndpoint("mock:outputBean");
            mockEndpoint.expectedBodiesReceived(expectedMessage);

            //when
            template.sendBody("direct:inputBean", directMessage);

            //then
            assertMockEndpointsSatisfied();
        }

    }

    @Nested
    class PrimaryMapBeanMethodTest extends CamelTestSupport {
        @Override
        protected RoutesBuilder createRouteBuilder() throws Exception {
            return new ModifyBeanRoute("map");
        }

        @Test
        void beanTransformTest() throws InterruptedException {
            //given
            MockEndpoint mockEndpoint = getMockEndpoint("mock:outputBean");
            mockEndpoint.expectedBodiesReceived(expectedMessage);

            //when
            template.sendBody("direct:inputBean", directMessage);

            //then
            assertMockEndpointsSatisfied();
        }
    }
}
