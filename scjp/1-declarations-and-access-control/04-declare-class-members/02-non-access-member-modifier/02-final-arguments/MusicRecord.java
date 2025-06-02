package org.example.models;

public class MusicRecord {
    private int recNumber;
    private String title;

    public MusicRecord getTrack(final int recNumber, String title) {
        MusicRecord mr = new MusicRecord();

        // recNumber cannot be set since it is final
        //        recNumber = 1;
        title = "modified!!";

        mr.recNumber = recNumber;
        mr.title = title;
        return mr;
    }
}
