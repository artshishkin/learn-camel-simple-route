package com.artarkatesoft.learncamel.routes.xmlxstream;

import com.artarkatesoft.learncamel.domain.Employee;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.XStreamDataFormat;

import java.util.Collections;

public class UnmarshalUsingXstreamRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        XStreamDataFormat xStreamDataFormat = new XStreamDataFormat();
        xStreamDataFormat.setAliases(Collections.singletonMap("employee", Employee.class.getName()));
        xStreamDataFormat.setPermissions(Employee.class);

        from("direct:xmlInput")
                .unmarshal(xStreamDataFormat)
                .to("log:?level=INFO&showBody=true");
    }
}
