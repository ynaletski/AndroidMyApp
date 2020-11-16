package com.example.mystart;

public class Event {

    private String number;
    private String description;
    private String dateTime;

    Event(String number, String description, String dateTime) {
        this.number = number;
        this.description = description;
        this.dateTime = dateTime;
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

    public void setDate_time(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getDateTime() {
        return dateTime;
    }

}
