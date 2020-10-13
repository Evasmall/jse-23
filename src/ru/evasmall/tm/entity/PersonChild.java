package ru.evasmall.tm.entity;

import java.time.LocalDate;

public class PersonChild extends Person {

    private Integer countChild;

    public PersonChild(Integer countChild) {
        this.countChild = countChild;
    }

    public PersonChild(String firstName, String lastName, LocalDate birthDate, String email, Integer countChild) {
        super(firstName, lastName, birthDate, email);
        this.countChild = countChild;
    }

}
