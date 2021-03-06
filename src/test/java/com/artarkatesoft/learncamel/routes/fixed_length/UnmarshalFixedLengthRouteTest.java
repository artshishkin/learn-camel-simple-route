package com.artarkatesoft.learncamel.routes.fixed_length;

import com.artarkatesoft.learncamel.domain.FriendWithFixedLength;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

class UnmarshalFixedLengthRouteTest extends CamelTestSupport {

    @Override
    protected RoutesBuilder createRouteBuilder() throws Exception {
        return new UnmarshalFixedLengthRoute();
    }

    @Test
    void testUnmarshalFixedLengthFile() {
        //given
        //when
        Object body = consumer.receiveBody("direct:output");
        List<FriendWithFixedLength> friends = (List<FriendWithFixedLength>) body;

        //then
        assertThat(friends.size(), equalTo(2));
        assertThat(friends.get(0).getFirstName(), equalTo("Art"));
        assertThat(friends.get(1).getLastName(), equalTo("Shyshkina"));
        assertThat(friends.get(1).getBirthDate(), equalTo(LocalDate.of(1983, Month.FEBRUARY, 13)));
        assertThat(friends.get(0).getAge(), equalTo(37));

        BigDecimal expectedArtSalary = new BigDecimal("80000.00");
        assertThat(friends.get(0).getSalary(), equalTo(expectedArtSalary));
        BigDecimal expectedKateSalary = new BigDecimal("90000.00");
        assertThat(friends.get(1).getSalary(), equalTo(expectedKateSalary));
    }
}
