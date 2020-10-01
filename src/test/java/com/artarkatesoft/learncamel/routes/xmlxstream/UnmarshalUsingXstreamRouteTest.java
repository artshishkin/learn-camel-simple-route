package com.artarkatesoft.learncamel.routes.xmlxstream;

import com.artarkatesoft.learncamel.domain.Employee;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnmarshalUsingXstreamRouteTest extends CamelTestSupport {

    @Override
    protected RoutesBuilder createRouteBuilder() throws Exception {
        return new UnmarshalUsingXstreamRoute();
    }

    @Test
    void unmarshalTest() {
        //given
        String directInput = "<?xml version='1.0' encoding='UTF-8'?><employee><id>123</id><name>Art</name><joinDate>01.10.2020</joinDate></employee>";

        //when
        Employee employee = template.requestBody("direct:xmlInput", directInput, Employee.class);

        //then
        assertAll(
                () -> assertEquals("123", employee.getId()),
                () -> assertEquals("Art", employee.getName()),
                () -> assertEquals("01.10.2020", employee.getJoinDate())
        );
    }
}
