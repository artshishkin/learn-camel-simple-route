package com.artarkatesoft.learncamel.routes.gson;

import com.artarkatesoft.learncamel.domain.Employee;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.Test;

import static com.artarkatesoft.learncamel.routes.gson.MarshalUsingGsonRoute.DIRECT_MARSHAL_EMPLOYEE_2_JSON;
import static com.artarkatesoft.learncamel.routes.gson.MarshalUsingGsonRoute.MOCK_MARSHAL_EMPLOYEE_2_JSON;

class MarshalUsingGsonRouteTest extends CamelTestSupport {

    @Override
    protected RoutesBuilder createRouteBuilder() throws Exception {
        return new MarshalUsingGsonRoute();
    }

    @Test
    void marshalEmployee2JsonTest() throws InterruptedException {
        //given
        Employee employee = new Employee();
        employee.setId("123");
        employee.setName("Art");
        employee.setJoinDate("02.10.2020");

        String employeeJson = "{\"id\":\"123\",\"name\":\"Art\",\"joinDate\":\"02.10.2020\"}";

        MockEndpoint mockEndpoint = getMockEndpoint(MOCK_MARSHAL_EMPLOYEE_2_JSON);
        mockEndpoint.expectedBodiesReceived(employeeJson);

        //when
        template.sendBody(DIRECT_MARSHAL_EMPLOYEE_2_JSON, employee);

        //then
        assertMockEndpointsSatisfied();
    }
}