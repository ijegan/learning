package org.example.models;

public class Album {
    private String title;
    private int playCount;

    // synchronized keyword can be used only to methods. Not applicable to variables and class
    public synchronized int count() {
        return playCount;
    }
}
