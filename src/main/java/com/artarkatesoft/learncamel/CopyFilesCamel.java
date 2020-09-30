package com.artarkatesoft.learncamel;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class CopyFilesCamel {
    public static void main(String[] args) {
        CamelContext context = new DefaultCamelContext();

        try {
            context.addRoutes(new RouteBuilder() {
                @Override
                public void configure() throws Exception {
                    from("file:data/input?noop=true")
                            .to("log:?level=INFO&showBody=true&showHeaders=true")
                            .to("file:data/output")
                            .to("file:data/output_reserve");

                    from("file:data/input2?noop=true")
                            .to("file:data/output2");
                }
            });

            context.start();
            Thread.sleep(1500);
            context.stop();
        } catch (Exception exception) {
            exception.printStackTrace();
        }


    }

}
