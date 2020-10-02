package com.artarkatesoft.learncamel.routes.csv;

import com.artarkatesoft.learncamel.domain.Friend;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UnmarshalCsvRouteTest extends CamelTestSupport {

    @Override
    protected RoutesBuilder createRouteBuilder() throws Exception {
        return new UnmarshalCsvRoute();
    }

    @Test
    void unmarshalCsvTest() throws InterruptedException {
        //when
        Object body = consumer.receiveBody("direct:output");
        List<Friend> friends = (List<Friend>) body;

        //then
        assertEquals(2, friends.size());
        assertAll(
                () -> assertEquals("02", friends.get(1).getId()),
                () -> assertEquals("Kate", friends.get(1).getFirstName()),
                () -> assertEquals("Shyshkina", friends.get(1).getLastName())
        );
    }
}