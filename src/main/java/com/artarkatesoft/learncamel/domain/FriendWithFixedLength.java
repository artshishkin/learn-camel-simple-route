package com.artarkatesoft.learncamel.domain;

import org.apache.camel.dataformat.bindy.annotation.DataField;
import org.apache.camel.dataformat.bindy.annotation.FixedLengthRecord;

import java.math.BigDecimal;
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
    @DataField(pos = 6, length = 8, precision = 2)
    private BigDecimal salary;

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

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "FriendWithFixedLength{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", age=" + age +
                ", salary=" + salary +
                '}';
    }
}
