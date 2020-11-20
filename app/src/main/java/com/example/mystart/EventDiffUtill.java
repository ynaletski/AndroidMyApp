package com.example.mystart;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import java.util.ArrayList;

class EventDiffUtilCallback extends DiffUtil.Callback {

    private ArrayList<Event> oldList;
    private ArrayList<Event> newList;

    public EventDiffUtilCallback(ArrayList<Event> oldList, ArrayList<Event> newList){
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
        //oldEvent.getDateTime().equals(newEvent.getDateTime());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        Event oldEvent = oldList.get(oldItemPosition);
        Event newEvent = newList.get(newItemPosition);
        return oldEvent.getDescription().equals(newEvent.getDescription()) &&
                oldEvent.getNumber().equals(newEvent.getNumber());
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        //you can return particular field for changed item.
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}
