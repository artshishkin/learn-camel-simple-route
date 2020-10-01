package com.artarkatesoft.learncamel.routes.xmlxstream;

import com.artarkatesoft.learncamel.domain.Employee;
import com.artarkatesoft.learncamel.processors.CsvXstreamProcessor;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.XStreamDataFormat;
import org.apache.camel.spi.DataFormat;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class MarshalUsingXstreamRoute extends RouteBuilder {

    private boolean fullQualifiedName = true;

    public MarshalUsingXstreamRoute() {
    }

    public MarshalUsingXstreamRoute(boolean fullQualifiedName) {
        this.fullQualifiedName = fullQualifiedName;
    }

    @Override
    public void configure() throws Exception {
        if (fullQualifiedName)
            from("direct:csvInput")
                    .process(new CsvXstreamProcessor())
                    .marshal().xstream()
                    .to("log:?level=INFO&showBody=true");
        else
            from("direct:csvInput")
                    .process(new CsvXstreamProcessor())
                    .marshal(populateStreamDef())
                    .to("log:?level=INFO&showBody=true");

    }

    private XStreamDataFormat populateStreamDef() {
        XStreamDataFormat xStreamDataFormat = new XStreamDataFormat();
        Map<String, String> aliases = new HashMap<>();
        aliases.put("employee", Employee.class.getName());
        xStreamDataFormat.setAliases(aliases);
        return xStreamDataFormat;
    }
}
