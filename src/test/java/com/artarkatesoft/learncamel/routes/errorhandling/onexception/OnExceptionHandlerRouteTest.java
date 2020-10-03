package com.artarkatesoft.learncamel.routes.errorhandling.onexception;

import org.apache.camel.CamelExecutionException;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

class OnExceptionHandlerRouteTest {

    @Nested
    @DisplayName("Test for Approach 2 - Catching/Retrying Exceptions")
    class OnExceptionTest_CatchRetry extends CamelTestSupport {

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

    @Nested
    @DisplayName("Test for Approach 2 - Handling Exceptions")
    class OnExceptionTest_Handling extends CamelTestSupport {

        @Override
        protected RoutesBuilder createRouteBuilder() throws Exception {
            return new OnExceptionHandlerRoute(true);
        }

        @Test
        void onExceptionCheck() {
            //given
            String input = null;

            //when
            String requestBody = template.requestBody("direct:exception", input, String.class);

            //then
            assertEquals("Exception happened in the route", requestBody);
        }
    }

    @Nested
    @DisplayName("Test for Approach 2 - Ignoring Exceptions")
    class OnExceptionTest_Continued extends CamelTestSupport {

        @Override
        protected RoutesBuilder createRouteBuilder() throws Exception {
            OnExceptionHandlerRoute onExceptionHandlerRoute = new OnExceptionHandlerRoute(false);
            onExceptionHandlerRoute.setContinueExecution(true);
            return onExceptionHandlerRoute;
        }

        @Test
        void onExceptionCheck() {
            //given
            String input = null;

            //when
            String requestBody = template.requestBody("direct:exception", input, String.class);

            //then
            assertNull(requestBody);
        }
    }

}
