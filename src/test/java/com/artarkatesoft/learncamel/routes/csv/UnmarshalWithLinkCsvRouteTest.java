package com.artarkatesoft.learncamel.routes.csv;

import com.artarkatesoft.learncamel.domain.User;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UnmarshalWithLinkCsvRouteTest extends CamelTestSupport {

    @Override
    protected RoutesBuilder createRouteBuilder() throws Exception {
        return new UnmarshalWithLinkCsvRoute();
    }

    @Test
    void unmarshalWithLink() {
        //given
        //when
        Object body = consumer.receiveBody("direct:output");
        List<User> users = (List<User>) body;

        //then
        assertEquals(2, users.size());
        assertEquals("Art", users.get(0).getFirstName());
        assertEquals("Kramatorsk", users.get(0).getAddress().getCity());
        assertEquals("Kate", users.get(1).getFirstName());
        assertEquals("Kyiv", users.get(1).getAddress().getCity());
    }
}