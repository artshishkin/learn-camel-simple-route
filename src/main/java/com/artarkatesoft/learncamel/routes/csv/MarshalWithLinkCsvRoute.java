package com.artarkatesoft.learncamel.routes.csv;

import com.artarkatesoft.learncamel.domain.User;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.bindy.csv.BindyCsvDataFormat;

public class MarshalWithLinkCsvRoute extends RouteBuilder {

    static final String DIRECT_USERS_WITH_ADDRESSES = "direct:usersWithAddresses";

    @Override
    public void configure() throws Exception {

        BindyCsvDataFormat dataFormat = new BindyCsvDataFormat(User.class);

        from(DIRECT_USERS_WITH_ADDRESSES)
                .log("Message before marshalling ${body}")
                .marshal(dataFormat)
                .log("Message after marshalling ${body}")
                .to("file:data/csv/output?fileName=users-with-addresses.csv");
    }
}
