package com.example.mystart;

public class Event {

    private final int eventId;
    private String number;
    private String description;
    private String dateTime;
    private static int EVENT_ID = 1;

    Event(String number, String description, String dateTime) {
        this.number = number;
        this.description = description;
        this.dateTime = dateTime;
        this.eventId = EVENT_ID;
        EVENT_ID++;
    }

    Event(int eventId, String number, String description, String dateTime){
        this.number = number;
        this.description = description;
        this.dateTime = dateTime;
        this.eventId = eventId;
    }

    public int getId(){
        return eventId;
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
