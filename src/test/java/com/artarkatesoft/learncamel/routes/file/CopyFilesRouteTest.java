package com.artarkatesoft.learncamel.routes.file;

import com.artarkatesoft.learncamel.routes.helper.Helper;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class CopyFilesRouteTest extends CamelTestSupport {

    Logger log = Logger.getLogger(CopyFilesRouteTest.class);

    private final Path dirPath = Path.of("data/output");

    @Override
    protected RoutesBuilder createRouteBuilder() throws Exception {
        return new CopyFilesRoute();
    }

    @Override
    @BeforeEach
    public void setUp() throws Exception {
        Helper.deleteDirectory(dirPath);
        super.setUp();
    }

    @Test
    void testDirectoriesAreCreating() throws InterruptedException {
        Thread.sleep(1500);
        assertAll(
                () -> assertTrue(Files.exists(dirPath)),
                () -> assertTrue(Files.isDirectory(dirPath)),
                () -> assertEquals(2, dirPath.toFile().listFiles().length)
        );
    }
}