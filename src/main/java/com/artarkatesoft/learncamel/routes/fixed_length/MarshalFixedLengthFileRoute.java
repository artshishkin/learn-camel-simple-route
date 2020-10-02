package com.artarkatesoft.learncamel.routes.fixed_length;

import com.artarkatesoft.learncamel.domain.FriendWithFixedLength;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.bindy.fixed.BindyFixedLengthDataFormat;

public class MarshalFixedLengthFileRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        BindyFixedLengthDataFormat dataFormat = new BindyFixedLengthDataFormat(FriendWithFixedLength.class);
        dataFormat.setLocale("us");
        from("direct:marshalFixedLength")
                .log("Message before marshalling ${body}")
                .marshal(dataFormat)
                .log("Message after marshalling ${body}")
                .to("file:data/fixedlength/output?fileName=fixedLengthOutput.txt");

    }
}
