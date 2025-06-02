package com.example.models;

public abstract class Vehicle {
    private String type;

    // illegal combination, abstract methods cannot be final
    // public final abstract void invalid();

    // illegal combination, cannot be private and abstract
    // private abstract void invalid1();

    // illegal combination, cannot be abstract and static
    // abstract static void invalid2();

    // abstract method
    public abstract void goUpHill();

    // nonabstract method
    public String getType() {
        return type;
    }
}
