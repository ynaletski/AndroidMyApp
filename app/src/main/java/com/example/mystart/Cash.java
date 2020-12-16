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

    public void addEvent(String numberEvent, String descriptionEvent, String timeEvent) {
        boolean validate = true;
        for (int i = 0; i < events.size(); i++) {
            if (getEvents(i).getDescription().equals(descriptionEvent) && getEvents(i).getNumber().equals(numberEvent)) {
                validate = false;
                break;
            }
        }
        if (validate) {
            events.add(new Event(numberEvent, descriptionEvent, timeEvent));
        }
    }

    public Event getEvents(int position) {
        return events.get(position);
    }
}
