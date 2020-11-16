package com.example.mystart;

import java.util.ArrayList;

public class Cash {

    private  static Cash INSTANCE;
    private ArrayList<Event> events = new ArrayList();

    private Cash(){
    }

    public static Cash getInstance(){
        if (INSTANCE == null){
            INSTANCE = new Cash();
        }
        return INSTANCE;
    }

    public ArrayList<Event> getArray(){
        return events;
    }

    public void addEventDefault(){
        events.add(new Event("1","Для добавления события нажмите кнопку Добавить событие." +
                                                    " После ввода номера, описания и времени создания события" +
                                                    "подтвердите создание либо отмените, нажав на " +
                                                    "соответствующие кнопки. Для редактирования и удаления события," +
                                                    "так же необходимо нажать на соответствующие кнопки.",
                            "02.02.2020 02:20"));
    }

    public void addEvent(String numb,String desc,String time){
        events.add(new Event(numb,desc,time));
    }
}
