package com.artarkatesoft.learncamel.routes.fixed_length;

import com.artarkatesoft.learncamel.domain.FriendWithFixedLength;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.bindy.fixed.BindyFixedLengthDataFormat;

public class UnmarshalFixedLengthRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        BindyFixedLengthDataFormat dataFormat = new BindyFixedLengthDataFormat(FriendWithFixedLength.class);
        dataFormat.setLocale("us");

        from("file:data/fixedlength/input?fileName=friends-fixed-length.txt&noop=true")
                .log("Input message ${body}")
                .unmarshal(dataFormat)
                .log("Output message ${body}")
                .to("direct:output");
    }
}
