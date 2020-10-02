package com.artarkatesoft.learncamel.routes.csv;

import com.artarkatesoft.learncamel.domain.User;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.bindy.csv.BindyCsvDataFormat;

public class UnmarshalWithLinkCsvRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        BindyCsvDataFormat dataFormat = new BindyCsvDataFormat(User.class);

        from("file:data/csv/input?fileName=users-with-address.csv&noop=true")
                .log("Message before unmarshal ${body}")
                .unmarshal(dataFormat)
                .log("Message after unmarshal ${body}")
                .to("direct:output");
    }
}
