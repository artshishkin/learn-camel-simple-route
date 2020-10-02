package com.artarkatesoft.learncamel.routes.gson;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;

public class MarshalUsingGsonRoute extends RouteBuilder {

    static final String DIRECT_MARSHAL_EMPLOYEE_2_JSON = "direct:marshalEmployee2Json";
    static final String MOCK_MARSHAL_EMPLOYEE_2_JSON = "mock:marshalEmployee2Json";

    @Override
    public void configure() throws Exception {
        from(DIRECT_MARSHAL_EMPLOYEE_2_JSON)
                .log("Message before marshal ${body}")
                .marshal().json(JsonLibrary.Gson)
                .log("Message after marshal ${body}")
                .to("log:?level=INFO&showBody=true")
                .to(MOCK_MARSHAL_EMPLOYEE_2_JSON);
    }
}
