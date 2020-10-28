package com.example.demo.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.UUID;

public class Seller {
    private UUID id;
    private String firstName;
    private String lastName;
    private List<Thing> addedClothing;

    public Seller(){

    }

    public Seller(String firstName, String lastName, List<Thing> addedClothing) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.addedClothing = addedClothing;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAddedClothing(List<Thing> addedClothing) {
        this.addedClothing = addedClothing;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public List<Thing> getAddedClothing(){
        return addedClothing;
    }


    @Override
    public String toString() {
        return "Seller{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", addedClothing=" + addedClothing +
                '}';
    }
}
