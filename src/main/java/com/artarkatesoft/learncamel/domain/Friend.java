package com.artarkatesoft.learncamel.domain;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

import java.util.Objects;

@CsvRecord(separator = ",", skipFirstLine = true, generateHeaderColumns = true)
public class Friend {
    @DataField(pos = 1, columnName = "id")
    private String id;
    @DataField(pos = 2, columnName = "first_name")
    private String firstName;
    @DataField(pos = 3, columnName = "last_name")
    private String lastName;

    public Friend() {
    }

    public Friend(String id, String firstName, String lastName) {
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
        Friend friend = (Friend) o;
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
