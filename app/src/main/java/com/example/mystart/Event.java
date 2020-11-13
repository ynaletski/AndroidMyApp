package com.example.mystart;

public class Event {

    private String number;
    private String description;
    private String date_time;

    Event(String number, String description, String date_time) {
        this.number = number;
        this.description = description;
        this.date_time = date_time;
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

    public void setDate_time(String date_time) {
        this.date_time = date_time;
    }

    public String getDate_time() {
        return date_time;
    }

}
