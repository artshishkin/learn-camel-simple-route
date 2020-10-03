package com.artarkatesoft.learncamel.eip.recipientlist;

import com.artarkatesoft.learncamel.routes.helper.Helper;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static com.artarkatesoft.learncamel.eip.recipientlist.RecipientListEIPRoute.RECIPIENT_LIST_OUTPUT_FOLDER;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RecipientListEIPRouteTest extends CamelTestSupport {

    @Override
    @BeforeEach
    public void setUp() throws Exception {
        Helper.deleteDirectory(Path.of(RECIPIENT_LIST_OUTPUT_FOLDER));
        super.setUp();
    }

    @Override
    protected RoutesBuilder createRouteBuilder() throws Exception {
        return new RecipientListEIPRoute();
    }

    @Test
    void testRecipientList() throws InterruptedException {
        //given
        //when
        Thread.sleep(2000);
        //then
        assertTrue(Files.exists(Path.of(RECIPIENT_LIST_OUTPUT_FOLDER).resolve("junior").resolve("employee2.xml")));
        assertTrue(Files.exists(Path.of(RECIPIENT_LIST_OUTPUT_FOLDER).resolve("senior").resolve("employee1.xml")));
    }
}