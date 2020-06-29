package com.example.common.model;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Entity.
 */
public class Person implements Serializable {
    private static final long serialVersionUID = 573172426738952510L;
    private String name; //Поле не может быть null, Строка не может быть пустой
    private LocalDateTime birthday; //Поле не может быть null
    private Long height; //Поле может быть null, Значение поля должно быть больше 0
    private String passportID; //Поле может быть null
    private Country nationality; //Поле может быть null

    public Person(String name, LocalDateTime birthday, Long height, String passportID, Country nationality) {
        this.name = name;
        this.birthday = birthday;
        this.height = height;
        this.passportID = passportID;
        this.nationality = nationality;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public Long getHeight() {
        return height;
    }

    public String getPassportID() {
        return passportID;
    }

    public Country getNationality() {
        return nationality;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", birthday=" + birthday +
                ", height=" + height +
                ", passportID='" + passportID + '\'' +
                ", nationality=" + nationality +
                '}';
    }
}
