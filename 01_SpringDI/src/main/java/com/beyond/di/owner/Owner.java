package com.beyond.di.owner;

import com.beyond.di.pet.Pet;

public class Owner {

    private String name;

    private int age;

    private Pet pet;

    public Owner(String name, int age, Pet pet) {
        super();
        this.name = name;
        this.age = age;
        this.pet = pet;
    }

    public Owner() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    @Override
    public String toString() {
        return "Owner [name=" + name + ", age=" + age + ", pet=" + pet + "]";
    }


}