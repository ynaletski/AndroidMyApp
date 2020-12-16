package com.example.mystart;

import androidx.recyclerview.widget.DiffUtil;

import java.util.ArrayList;

class EventDiffUtil extends DiffUtil.Callback {

    private final ArrayList<Event> oldList;
    private final ArrayList<Event> newList;

    public EventDiffUtil(ArrayList<Event> oldList, ArrayList<Event> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        Event oldEvent = oldList.get(oldItemPosition);
        Event newEvent = newList.get(newItemPosition);
        return oldEvent.hashCode() == newEvent.hashCode();

    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        Event oldEvent = oldList.get(oldItemPosition);
        Event newEvent = newList.get(newItemPosition);
        //return oldEvent.getDescription().equals(newEvent.getDescription());
        return oldEvent.equals(newEvent);
    }

}
