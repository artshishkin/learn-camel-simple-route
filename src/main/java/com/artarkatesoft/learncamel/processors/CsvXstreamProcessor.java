package com.artarkatesoft.learncamel.processors;

import com.artarkatesoft.learncamel.domain.Employee;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.util.StringTokenizer;

public class CsvXstreamProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        String csv = exchange.getIn().getBody(String.class);

        StringTokenizer tokenizer = new StringTokenizer(csv, ",");

        Employee employee = new Employee();
        while (tokenizer.hasMoreElements()) {
            employee.setId((String) tokenizer.nextElement());
            employee.setName((String) tokenizer.nextElement());
            employee.setJoinDate((String) tokenizer.nextElement());
        }
        exchange.getIn().setBody(employee);
    }
}
