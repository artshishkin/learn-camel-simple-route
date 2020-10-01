package com.artarkatesoft.learncamel.routes.xmlxstream;

import org.apache.camel.RoutesBuilder;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MarshalUsingXstreamRouteTest {

    @Nested
    class DefaultXstreamMarshalTest extends CamelTestSupport {

        @Override
        protected RoutesBuilder createRouteBuilder() throws Exception {
            return new MarshalUsingXstreamRoute();
        }

        @Test
        void marshalXstreamTest() {
            //given
            String csv = "123,Art,01.10.2020";
            String expected = "<?xml version='1.0' encoding='UTF-8'?><com.artarkatesoft.learncamel.domain.Employee><id>123</id><name>Art</name><joinDate>01.10.2020</joinDate></com.artarkatesoft.learncamel.domain.Employee>";

            //when
            String actual = template.requestBody("direct:csvInput", csv, String.class);

            //then
            assertEquals(expected, actual);
        }
    }

    @Nested
    class CustomDefinitionXstreamTest extends CamelTestSupport {

        @Override
        protected RoutesBuilder createRouteBuilder() throws Exception {
            return new MarshalUsingXstreamRoute(false);
        }

        @Test
        void marshalXstreamTest() {
            //given
            String csv = "123,Art,01.10.2020";
            String expected = "<?xml version='1.0' encoding='UTF-8'?><employee><id>123</id><name>Art</name><joinDate>01.10.2020</joinDate></employee>";

            //when
            String actual = template.requestBody("direct:csvInput", csv, String.class);

            //then
            assertEquals(expected, actual);
        }
    }
}
