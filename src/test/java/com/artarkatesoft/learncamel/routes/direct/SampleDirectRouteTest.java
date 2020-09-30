package com.artarkatesoft.learncamel.routes.direct;

import org.apache.camel.Exchange;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class SampleDirectRouteTest extends CamelTestSupport {

    private Logger log = LoggerFactory.getLogger(SampleDirectRouteTest.class);

    private final Path dirPath = Path.of("data/output_direct");

    @Override
    @BeforeEach
    public void setUp() throws Exception {
        if (Files.exists(dirPath))
            Files.walk(dirPath)
                    .sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .forEach(File::delete);

        log.warn("Directory id {}deleted", Files.notExists(dirPath) ? "" : "NOT ");
        super.setUp();
    }


    @Override
    protected RoutesBuilder createRouteBuilder() throws Exception {
        return new SampleDirectRoute();
    }

    @Test
    void testFileIsCreatingFromDirect() throws InterruptedException {

        template.sendBody("direct:sampleInput", "1234,Art,Hello");
//        Thread.sleep(500);
        Exchange receive = consumer.receive("file:data/output_direct", 500);
        assertAll(
                () -> assertTrue(Files.exists(dirPath)),
                () -> assertTrue(Files.exists(dirPath.resolve("directFile.txt"))),
                () -> assertEquals(Files.readString(dirPath.resolve("directFile.txt")), "1234,Art,Hello")
        );
        assertEquals("directFile.txt", receive.getIn().getHeader("CamelFileName"));
        assertEquals("1234,Art,Hello", receive.getMessage().getBody(String.class));
    }
}