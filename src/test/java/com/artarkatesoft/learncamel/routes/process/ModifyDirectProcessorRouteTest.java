package com.artarkatesoft.learncamel.routes.process;

import com.artarkatesoft.learncamel.routes.helper.Helper;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class ModifyDirectProcessorRouteTest extends CamelTestSupport {

    private final Path dirPath = Path.of("data/output_direct");

    @Override
    protected RoutesBuilder createRouteBuilder() throws Exception {
        return new ModifyDirectProcessorRoute();
    }

    @Override
    @BeforeEach
    public void setUp() throws Exception {
        Helper.deleteDirectory(dirPath);
        super.setUp();
    }

    @Test
    void testProcessor_usingConsumer() {
        //given
        String directContent = "Art,123,Shyshkin\n" +
                "Kate,456,Shyshkina";
        String expectedContent = "Art:123:Shyshkin\n" +
                "Kate:456:Shyshkina";
        //when
        template.sendBody("direct:processorInput", directContent);

        //then
        String body = consumer.receive("file:data/output_direct", 1500)
                .getIn().getBody(String.class);

        assertAll(
                () -> assertTrue(Files.exists(dirPath.resolve("colonSeparated.txt"))),
                () -> assertEquals(expectedContent, body)
        );
    }

    @Test
    void testProcessor() {
        //given
        String directContent = "Art,123,Shyshkin\n" +
                "Kate,456,Shyshkina";
        String expectedContent = "Art:123:Shyshkin\n" +
                "Kate:456:Shyshkina";
        //when
        String actualContent = template.requestBody("direct:processorInput", directContent, String.class);

        //then
        assertAll(
                () -> assertTrue(Files.exists(dirPath.resolve("colonSeparated.txt"))),
                () -> assertEquals(expectedContent, actualContent)
        );
    }

    @Test
    void testProcessor_usingMock() throws InterruptedException {
        //given
        String directContent = "Art,123,Shyshkin\n" +
                "Kate,456,Shyshkina";
        String expectedContent = "Art:123:Shyshkin\n" +
                "Kate:456:Shyshkina";

        MockEndpoint mockEndpoint = getMockEndpoint("mock:outputSeparated");
        mockEndpoint.expectedBodiesReceived(expectedContent);

        //when
        template.sendBody("direct:processorInput", directContent);

        //then
        assertMockEndpointsSatisfied();
    }
}