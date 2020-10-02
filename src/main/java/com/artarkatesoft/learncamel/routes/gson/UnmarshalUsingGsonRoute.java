package com.artarkatesoft.learncamel.routes.gson;

import com.artarkatesoft.learncamel.domain.Employee;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.gson.GsonDataFormat;

public class UnmarshalUsingGsonRoute extends RouteBuilder {

    static final String DIRECT_UNMARSHAL_JSON_2_EMPLOYEE = "direct:unmarshalJson2Employee";
    static final String MOCK_UNMARSHAL_JSON_2_EMPLOYEE = "mock:unmarshalJson2Employee";

    @Override
    public void configure() throws Exception {

        GsonDataFormat gsonDataFormat = new GsonDataFormat(Employee.class);

        from(DIRECT_UNMARSHAL_JSON_2_EMPLOYEE)
                .log("Message before unmarshal ${body}")
                .unmarshal(gsonDataFormat)
                .log("Message after unmarshal ${body}")
                .to(MOCK_UNMARSHAL_JSON_2_EMPLOYEE);
    }
}
