package com.example.mystart;

public class Event {

    private String number;
    private String description;
    private String dateTime;
    private static Integer EVENT_ID = 1;
    private final Integer eventId;

    Event(String number, String description, String dateTime) {
        this.number = number;
        this.description = description;
        this.dateTime = dateTime;
        this.eventId = EVENT_ID;
        EVENT_ID++;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getDateTime() {
        return dateTime;
    }

    @Override
    public int hashCode() {
        return eventId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        Event event = (Event) o;
        return this.description.equals(event.description);
    }

}
