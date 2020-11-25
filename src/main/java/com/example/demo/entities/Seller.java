package com.example.demo.entities;

import java.util.List;
import java.util.UUID;

public class Seller {
    private UUID id;
    private String firstName;
    private String lastName;
    private List<Thing> addedClothing;

    public Seller(){

    }

    public Seller(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
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

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public List<Thing> getAddedClothing() {
        return addedClothing;
    }

    public void setAddedClothing(List<Thing> addedClothing) {
        this.addedClothing = addedClothing;
    }

    @Override
    public String toString() {
        return "Seller{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
