package com.artarkatesoft.learncamel.routes.csv;

import com.artarkatesoft.learncamel.domain.Friend;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.bindy.csv.BindyCsvDataFormat;

public class UnmarshalCsvRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        BindyCsvDataFormat dataFormat = new BindyCsvDataFormat(Friend.class);

        from("file:data/csv/input?fileName=file1.csv&noop=true")
                .log("Message before unmarshal ${body}")
                .unmarshal(dataFormat)
                .log("Message after unmarshal ${body}")
                .to("direct:output");
    }
}
