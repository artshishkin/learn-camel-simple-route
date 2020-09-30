package com.artarkatesoft.learncamel.routes.file;

import org.apache.camel.RoutesBuilder;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;

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
        if (Files.exists(dirPath))
            Files.walk(dirPath)
                    .sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .forEach(File::delete);

        log.warn("Directory id deleted: " + Files.notExists(dirPath));
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