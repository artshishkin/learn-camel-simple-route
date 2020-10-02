package com.artarkatesoft.learncamel.routes.csv;

import com.artarkatesoft.learncamel.domain.Friend;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.bindy.csv.BindyCsvDataFormat;

public class MarshalCsvRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        BindyCsvDataFormat dataFormat = new BindyCsvDataFormat(Friend.class);

        from("direct:marshalPojo2Csv")
                .log("Message body before marshalling ${body}")
                .marshal(dataFormat)
                .log("Message body after marshalling ${body}")
                .to("file:data/csv/output?fileName=fileOut.csv");
    }
}
