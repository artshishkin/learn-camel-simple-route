package com.artarkatesoft.learncamel.routes.bean;

import com.artarkatesoft.learncamel.CommaToStarBean;
import org.apache.camel.builder.RouteBuilder;

public class ModifyBeanRoute extends RouteBuilder {

    private final String beanMethodName;

    public ModifyBeanRoute() {
        beanMethodName = "map";
    }

    public ModifyBeanRoute(String beanMethodName) {
        this.beanMethodName = beanMethodName;
    }

    @Override
    public void configure() throws Exception {
        from("direct:inputBean")
                .log("Before bean method message is ${body}")
                .bean(new CommaToStarBean(), beanMethodName)
                .log("After bean method message is ${body}")
                .to("mock:outputBean");
    }
}
