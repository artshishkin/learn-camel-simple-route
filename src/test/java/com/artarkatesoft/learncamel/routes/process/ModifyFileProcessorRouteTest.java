package com.artarkatesoft.learncamel.routes.process;

import org.apache.camel.RoutesBuilder;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ModifyFileProcessorRouteTest extends CamelTestSupport {

    private Logger log = LoggerFactory.getLogger(ModifyFileProcessorRouteTest.class);

    private final Path dirPath = Path.of("data/output_process");

    @Override
    protected RoutesBuilder createRouteBuilder() throws Exception {
        return new ModifyFileProcessorRoute();
    }

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

    @Test
    void processorTest() throws InterruptedException, IOException {
        //given
        Path filePath = dirPath.resolve("outputProcess.txt");
        String expectedFileContent = "Art:123:Shyshkin\n" +
                "Kate:456:Shyshkina";

        //when
        Thread.sleep(2000);

        //then
        assertTrue(Files.exists(filePath));
        String fileContent = Files.readString(filePath);
        assertEquals(expectedFileContent, fileContent);
    }
}