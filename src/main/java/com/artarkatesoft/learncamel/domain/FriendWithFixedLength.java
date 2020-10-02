package com.artarkatesoft.learncamel.domain;

import org.apache.camel.dataformat.bindy.annotation.DataField;
import org.apache.camel.dataformat.bindy.annotation.FixedLengthRecord;

import java.util.Objects;

@FixedLengthRecord(ignoreTrailingChars = true)
public class FriendWithFixedLength {
    @DataField(pos = 1, length = 3)
    private String id;
    @DataField(pos = 2, length = 10, trim = true, align = "L")
    private String firstName;
    @DataField(pos = 3, length = 15, trim = true, align = "L")
    private String lastName;

    public FriendWithFixedLength() {
    }

    public FriendWithFixedLength(String id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FriendWithFixedLength friend = (FriendWithFixedLength) o;
        return Objects.equals(id, friend.id) &&
                Objects.equals(firstName, friend.firstName) &&
                Objects.equals(lastName, friend.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName);
    }

    @Override
    public String toString() {
        return "Friend{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
