package com.example.models;

public class Employee {

    // instance variables, declared outside the method within class
    // initialized when class is initialized
    // transient and final valid
    private final String name;
    private transient String title;
    private String manager;
    private static String department;
    private volatile String location;

    // all access specifier applicable
    public String role1;
    protected String role2;
    String role3;

    // abstract, synchronized, strictfp, native not valid
    /*
    abstract String test1;
    synchronized String test2;
    strictfp String test3;
    native String test4;
     */

    Employee(String name) {
        this.name = name;
    }

    // local variables live on stack
    // if the variable passed is an object, object lives in heap(locally declared object reference)
    public void doSomething(String name, String title1) {

        // instance variable
        System.out.println(this.name);

        String city, state;
        final String country;


        // local variables cannot be accessed without initialization
        /*
         System.out.println(city);
         System.out.println(state);
        */

        // recommended to initialize local variable
        int postalCode = 1000;


        // access specifiers not allowed inside method
        /*
        public int i = 10;
        private int j = 20;
        protected int k = 20;

        transient String str2;
        volatile String str3;

        synchronized String str;
        strictfp String st1;

        static String str;
        */

    }
}
