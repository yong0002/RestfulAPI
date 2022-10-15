package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;

@Entity
@EnableAutoConfiguration
@Table(name = "Employees")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private int age;

    private String dob;

    private String telephoneNumber;

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getDob() {
        return dob;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public void setDob(String dob) {
        this.dob = dob;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
