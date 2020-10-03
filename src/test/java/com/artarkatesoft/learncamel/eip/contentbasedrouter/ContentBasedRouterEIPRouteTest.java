package com.artarkatesoft.learncamel.eip.contentbasedrouter;

import com.artarkatesoft.learncamel.routes.helper.Helper;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ContentBasedRouterEIPRouteTest extends CamelTestSupport {

    @Override
    @BeforeEach
    public void setUp() throws Exception {
        Helper.deleteDirectory(Path.of("data/eip/content_based_router/output"));
        super.setUp();
    }

    @Override
    protected RoutesBuilder createRouteBuilder() throws Exception {
        return new ContentBasedRouterEIPRoute();
    }

    @Test
    void testContentBasedRouting() throws InterruptedException {
        //when
        Thread.sleep(1500);
        //then
        assertTrue(Files.exists(Path.of("data/eip/content_based_router/output/txt/1.txt")));
        assertTrue(Files.exists(Path.of("data/eip/content_based_router/output/json/art.json")));
        assertTrue(Files.exists(Path.of("data/eip/content_based_router/output/html/index.html")));
        assertTrue(Files.exists(Path.of("data/eip/content_based_router/output/other/system.log")));
    }
}