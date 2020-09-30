package com.artarkatesoft.learncamel.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.file.GenericFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.Files;
import java.util.stream.Collectors;

public class ModifyFileExampleProcessor implements Processor {

    private Logger log = LoggerFactory.getLogger(ModifyFileExampleProcessor.class);

    @Override
    public void process(Exchange exchange) throws Exception {
        Object body = exchange.getIn().getBody();

        GenericFile<File> genericFile = (GenericFile<File>) body;

        File file = genericFile.getFile();

        String newFileContent = Files.lines(file.toPath())
                .map(row -> row.replace(",", ":"))
                .collect(Collectors.joining("\n"));

        exchange.getIn().setBody(newFileContent);

    }
}
