package com.artarkatesoft.learncamel.routes.xml2json;

import com.artarkatesoft.learncamel.domain.Employee;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.model.dataformat.XStreamDataFormat;

import java.util.Collections;

public class Xml2JsonCustomRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        XStreamDataFormat xStreamDataFormat = new XStreamDataFormat();
        xStreamDataFormat.setAliases(Collections.singletonMap("employee", Employee.class.getName()));
        xStreamDataFormat.setPermissions(Employee.class);

        from("direct:marshalEmployeeXml2Json")
                .log("Message before Unmarshal xml ${body}")
                .unmarshal(xStreamDataFormat)
                .log("Message after Unmarshal xml ${body}")
                .to("log:?showBody=true&level=INFO")
                .marshal().json(JsonLibrary.Jackson)
                .to("log:?showBody=true&level=INFO")
                .to("mock:marshalEmployeeXml2Json");

        from("direct:marshalEmployeeJson2Xml")
                .log("Message before Unmarshal json ${body}")
                .unmarshal().json(JsonLibrary.Jackson, Employee.class)
                .log("Message after Unmarshal json ${body}")
                .marshal(xStreamDataFormat)
                .to("log:?showBody=true&level=INFO")
                .to("mock:marshalEmployeeJson2Xml");
    }
}
