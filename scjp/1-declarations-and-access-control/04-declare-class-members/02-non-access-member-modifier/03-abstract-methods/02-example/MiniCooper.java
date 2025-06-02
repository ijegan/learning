package com.example.models;

public class MiniCooper extends Car {
    // subclass should implement abstract methods from super class
    @Override
    public void goUpHill() {
        System.out.println("Mini cooper!");
    }

    // subclass has access to methods from superclass
    void method1() {
        MiniCooper mc = new MiniCooper();
        mc.getType();
        mc.doCarThings();
        mc.goUpHill();
    }


    // combination of private & final is valid
    private final void display(){
        System.out.println("Mini cooper!!!");
    }
}
