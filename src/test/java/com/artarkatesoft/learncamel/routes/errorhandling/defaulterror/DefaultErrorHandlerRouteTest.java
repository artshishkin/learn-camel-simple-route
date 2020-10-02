package com.artarkatesoft.learncamel.routes.errorhandling.defaulterror;

import org.apache.camel.CamelExecutionException;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertThrows;

class DefaultErrorHandlerRouteTest extends CamelTestSupport {

    @Override
    protected RoutesBuilder createRouteBuilder() throws Exception {
        return new DefaultErrorHandlerRoute();
    }

    @Test
    void testDefaultErrorHandler() {
        //given
        String input = null;

        //when
        Executable executable = () -> template.sendBody("direct:exception", input);

        //then
//        assertThrows(RuntimeException.class, executable);
        assertThrows(CamelExecutionException.class, executable);
    }
}
