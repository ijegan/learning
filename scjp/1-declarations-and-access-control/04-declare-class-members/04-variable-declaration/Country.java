package com.example.models;

// final classes cannot be subclassed
public final class Country {

    private final String name;

    Country(String name) {
        this.name = name;
    }


    // final methods cannot be over-ridden in subclass
    public final void doSomething() {
        final String state = "Delhi";

        // final variables cannot be modified
        // state = "Punjab";
    }
}
