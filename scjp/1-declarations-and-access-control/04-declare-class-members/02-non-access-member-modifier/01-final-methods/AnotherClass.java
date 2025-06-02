package org.example.models;

public class AnotherClass {
    public void method2() {
        // Superclass has public access
        SuperClass s = new SuperClass();
        // method1 has also public access
        s.method1();
    }
}
