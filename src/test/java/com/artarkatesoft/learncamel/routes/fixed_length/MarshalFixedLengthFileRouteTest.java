package com.artarkatesoft.learncamel.routes.fixed_length;

import com.artarkatesoft.learncamel.domain.FriendWithFixedLength;
import com.artarkatesoft.learncamel.routes.helper.Helper;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.hamcrest.MatcherAssert.assertThat;

class MarshalFixedLengthFileRouteTest extends CamelTestSupport {

    public static final Path DIR_PATH = Path.of("data/fixedlength/output");

    @Override
    protected RoutesBuilder createRouteBuilder() throws Exception {
        return new MarshalFixedLengthFileRoute();
    }

    @Override
    @BeforeEach
    public void setUp() throws Exception {
        Helper.deleteDirectory(DIR_PATH);
        super.setUp();
    }

    @Test
    void marshallFixedSizeFileTest() {
        //given
        List<FriendWithFixedLength> friends = createStubFriendList();

        //when
        String content = template.requestBody("direct:marshalFixedLength", friends, String.class);

        //then
        assertThat(content, CoreMatchers.containsString("004First4    Last4          04Sep198733^ 4000.00"));
    }

    private FriendWithFixedLength createStubFriend(int id) {
        FriendWithFixedLength friend = new FriendWithFixedLength();
        friend.setId(String.format("%03d", id));
        friend.setFirstName("First" + id);
        friend.setLastName("Last" + id);
        friend.setSalary(new BigDecimal(id * 1000));
        friend.setAge(37 - id);
        friend.setBirthDate(LocalDate.of(2020, 10, 2).minusYears(friend.getAge()).minusWeeks(id));
        return friend;
    }

    private List<FriendWithFixedLength> createStubFriendList() {
        return IntStream.rangeClosed(1, 5)
                .mapToObj(this::createStubFriend)
                .collect(Collectors.toList());
    }
}
