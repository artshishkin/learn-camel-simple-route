package com.artarkatesoft.learncamel.eip.multicast;

import com.artarkatesoft.learncamel.routes.helper.Helper;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertTrue;

class MulticastEIPRouteTest extends CamelTestSupport {
    @Override
    protected RoutesBuilder createRouteBuilder() throws Exception {
        return new MulticastEIPRoute();
    }

    @Override
    @BeforeEach
    public void setUp() throws Exception {
        Helper.deleteDirectory(Path.of("data/eip/multicast/output"));
        super.setUp();
    }

    @Test
    void testMulticast() throws InterruptedException {
        //when
        Thread.sleep(1500);

        //then
        assertTrue(Files.exists(Path.of("data/eip/multicast/output/x/multi.txt")));
        assertTrue(Files.exists(Path.of("data/eip/multicast/output/y/multi.txt")));
        assertTrue(Files.exists(Path.of("data/eip/multicast/output/z/multi.txt")));


    }
}