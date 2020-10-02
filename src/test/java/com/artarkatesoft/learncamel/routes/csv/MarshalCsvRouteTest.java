package com.artarkatesoft.learncamel.routes.csv;

import com.artarkatesoft.learncamel.domain.Friend;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MarshalCsvRouteTest extends CamelTestSupport {

    @Override
    protected RoutesBuilder createRouteBuilder() throws Exception {
        return new MarshalCsvRoute();
    }

    @Test
    void marshalPojo2CsvTest() {
        //given
        List<Friend> friends = Arrays.asList(
                new Friend("01", "Art", "Shyshkin"),
                new Friend("02", "Kate", "Shyshkina")
        );
        String expectedCsvContent = "id,first_name,last_name\r\n" +
                "01,Art,Shyshkin\r\n" +
                "02,Kate,Shyshkina\r\n";

        //when
        String savedCsvContent = template.requestBody("direct:marshalPojo2Csv", friends, String.class);

        //then
        assertTrue(Files.exists(Path.of("data/csv/output/fileOut.csv")));
        assertEquals(expectedCsvContent, savedCsvContent);

    }
}