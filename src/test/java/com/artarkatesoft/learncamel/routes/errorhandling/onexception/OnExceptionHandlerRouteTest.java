package com.artarkatesoft.learncamel.routes.errorhandling.onexception;

import org.apache.camel.CamelExecutionException;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertThrows;

class OnExceptionHandlerRouteTest extends CamelTestSupport {

    @Override
    protected RoutesBuilder createRouteBuilder() throws Exception {
        return new OnExceptionHandlerRoute();
    }

    @Test
    void onExceptionCheck() {
        //given
        String input = null;

        //when
        Executable executable = () -> template.sendBody("direct:exception", input);

        //then
//        assertThrows(RuntimeException.class, executable);
        assertThrows(CamelExecutionException.class, executable);
    }

}
