package com.example.mystart;

import java.util.ArrayList;

public final class Cash {

    private static Cash INSTANCE;
    private ArrayList<Event> events = new ArrayList();

    private Cash() {
    }

    public static Cash getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Cash();
        }
        return INSTANCE;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public void addEvent(String numb, String desc, String time) {
        events.add(new Event(numb, desc, time));
    }
}
