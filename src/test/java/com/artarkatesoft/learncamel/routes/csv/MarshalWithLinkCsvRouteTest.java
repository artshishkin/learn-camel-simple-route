package com.artarkatesoft.learncamel.routes.csv;

import com.artarkatesoft.learncamel.domain.Address;
import com.artarkatesoft.learncamel.domain.User;
import com.artarkatesoft.learncamel.routes.helper.Helper;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.artarkatesoft.learncamel.routes.csv.MarshalWithLinkCsvRoute.DIRECT_USERS_WITH_ADDRESSES;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MarshalWithLinkCsvRouteTest extends CamelTestSupport {

    private final Path dirPath = Path.of("data/csv/output");

    @Override
    protected RoutesBuilder createRouteBuilder() throws Exception {
        return new MarshalWithLinkCsvRoute();
    }

    @Override
    @BeforeEach
    public void setUp() throws Exception {
        Helper.deleteDirectory(dirPath);
        super.setUp();
    }

    @Test
    void testMarshalWithLink() {
        //given
        List<User> userList = createStubUserList();

        //when
        String csvContent = template.requestBody(DIRECT_USERS_WITH_ADDRESSES, userList, String.class);


        //then
        assertTrue(Files.exists(dirPath.resolve("users-with-addresses.csv")));
        assertThat(csvContent, CoreMatchers.containsString("05,First Name 5,Last Name 5,addrLine5,city5,state5,country5"));

    }

    private Address createStubAddress(int id) {
        Address address = new Address();
        address.setAddressLine("addrLine" + id);
        address.setCity("city" + id);
        address.setCountry("country" + id);
        address.setState("state" + id);
        return address;
    }

    private User createStubUser(int id) {
        User user = new User();
        user.setId(String.format("%02d", id));
        user.setFirstName("First Name " + id);
        user.setLastName("Last Name " + id);
        user.setAddress(createStubAddress(id));
        return user;
    }

    private List<User> createStubUserList() {
        return IntStream.rangeClosed(1, 5)
                .mapToObj(this::createStubUser)
                .collect(Collectors.toList());
    }
}