package com.artarkatesoft.learncamel.domain;

import org.apache.camel.dataformat.bindy.annotation.DataField;
import org.apache.camel.dataformat.bindy.annotation.FixedLengthRecord;

import java.time.LocalDate;

@FixedLengthRecord(ignoreTrailingChars = true)
public class FriendWithFixedLength {
    @DataField(pos = 1, length = 3)
    private String id;
    @DataField(pos = 2, length = 10, trim = true, align = "L")
    private String firstName;
    @DataField(pos = 3, length = 15, trim = true, align = "L")
    private String lastName;
    @DataField(pos = 4, length = 9, pattern = "ddMMMyyyy")
    private LocalDate birthDate;
    @DataField(pos = 5, delimiter = "^")
    private int age;

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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FriendWithFixedLength that = (FriendWithFixedLength) o;
        return age == that.age &&
                Objects.equals(id, that.id) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(birthDate, that.birthDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, birthDate, age);
    }

    @Override
    public String toString() {
        return "FriendWithFixedLength{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", age=" + age +
                '}';
    }
}
