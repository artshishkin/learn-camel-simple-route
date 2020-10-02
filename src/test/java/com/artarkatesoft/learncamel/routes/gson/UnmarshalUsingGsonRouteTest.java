package com.artarkatesoft.learncamel.routes.gson;

import com.artarkatesoft.learncamel.domain.Employee;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.Test;

import static com.artarkatesoft.learncamel.routes.gson.UnmarshalUsingGsonRoute.DIRECT_UNMARSHAL_JSON_2_EMPLOYEE;
import static com.artarkatesoft.learncamel.routes.gson.UnmarshalUsingGsonRoute.MOCK_UNMARSHAL_JSON_2_EMPLOYEE;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UnmarshalUsingGsonRouteTest extends CamelTestSupport {

    @Override
    protected RoutesBuilder createRouteBuilder() throws Exception {
        return new UnmarshalUsingGsonRoute();
    }

    @Test
    void unmarshalJson2EmployeeTest() {
        //given
        String employeeJson = "{\"id\":\"123\",\"name\":\"Art\",\"joinDate\":\"02.10.2020\"}";

        //when
        Employee employee = template.requestBody(DIRECT_UNMARSHAL_JSON_2_EMPLOYEE, employeeJson, Employee.class);

        //then
        assertAll(
                () -> assertEquals("123", employee.getId()),
                () -> assertEquals("Art", employee.getName()),
                () -> assertEquals("02.10.2020", employee.getJoinDate())
        );
    }

    @Test
    void unmarshalJson2EmployeeTest_usingMock() throws InterruptedException {
        //given
        String employeeJson = "{\"id\":\"123\",\"name\":\"Art\",\"joinDate\":\"02.10.2020\"}";

        Employee employee = new Employee();
        employee.setId("123");
        employee.setName("Art");
        employee.setJoinDate("02.10.2020");

        MockEndpoint mockEndpoint = getMockEndpoint(MOCK_UNMARSHAL_JSON_2_EMPLOYEE);
        mockEndpoint.expectedBodiesReceived(employee);

        //when
        template.sendBody(DIRECT_UNMARSHAL_JSON_2_EMPLOYEE, employeeJson);

        //then
        assertMockEndpointsSatisfied();
    }
}