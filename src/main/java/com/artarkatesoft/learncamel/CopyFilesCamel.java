package com.artarkatesoft.learncamel;

import com.artarkatesoft.learncamel.file.CopyFilesRoute;
import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

public class CopyFilesCamel {
    public static void main(String[] args) {
        CamelContext context = new DefaultCamelContext();

        try {
            context.addRoutes(new CopyFilesRoute());

            context.start();
            Thread.sleep(1500);
            context.stop();
        } catch (Exception exception) {
            exception.printStackTrace();
        }


    }

}
