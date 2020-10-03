package com.artarkatesoft.learncamel.eip.wiretap;

import com.artarkatesoft.learncamel.routes.helper.Helper;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertTrue;

class WireTapRouteTest extends CamelTestSupport {

    @Override
    protected RoutesBuilder createRouteBuilder() throws Exception {
        return new WireTapRoute();
    }

    @Override
    @BeforeEach
    public void setUp() throws Exception {
        Helper.deleteDirectory(Path.of("data/eip/wire_tap/output"));
        super.setUp();
    }

    @Test
    void testWireTap() throws InterruptedException {
        //when
        Thread.sleep(2000);

        //then
        assertTrue(Files.exists(Path.of("data/eip/wire_tap/output/debug/1.txt")));
        assertTrue(Files.exists(Path.of("data/eip/wire_tap/output/all/1.txt")));
    }
}