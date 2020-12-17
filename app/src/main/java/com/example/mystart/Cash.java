package com.example.mystart;

import java.util.ArrayList;

public final class Cash {

    private static Cash INSTANCE;
    private final ArrayList<Event> events = new ArrayList<>();

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

    public Event getEvents(int position) {
        return events.get(position);
    }

    private boolean validateEvent(String numberEvent, String descriptionEvent){
        boolean validate = true;
        for (int i = 0; i < events.size(); i++) {
            if (getEvents(i).getDescription().equals(descriptionEvent) && getEvents(i).getNumber().equals(numberEvent)) {
                validate = false;
                break;
            }
        }
        return validate;
    }

    public void addEvent(String numberEvent, String descriptionEvent, String timeEvent) {
        if (validateEvent(numberEvent,descriptionEvent)) {
            events.add(new Event(numberEvent, descriptionEvent, timeEvent));
        }
    }
}
