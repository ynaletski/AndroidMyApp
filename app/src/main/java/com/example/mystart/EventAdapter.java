package com.example.mystart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

class EventAdapter extends ArrayAdapter<Event> {

    Cash cash = Cash.getInstance();

    private LayoutInflater inflater;
    private int layout;
    private ArrayList<Event> eventList;

    EventAdapter(Context context, int resource, ArrayList<Event> events) {
        super(context, resource, events);
        this.eventList = events;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }

    public View getView(final int position, View convertView, ViewGroup parent) {

        final ViewHolder viewHolder;
        if(convertView==null){
            convertView = inflater.inflate(this.layout, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else viewHolder = (ViewHolder) convertView.getTag();

        final Event event = eventList.get(position);

        viewHolder.number.setText(event.getNumber());
        viewHolder.description.setText(event.getDescription());
        viewHolder.textTimeDate.setText(event.getDate_time());

        viewHolder.button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cash.getArray().remove(position);
                notifyDataSetChanged();
            }
        });

        return convertView;
    }

    private static class ViewHolder {
        final Button button_delete;
        final TextView number, description, textTimeDate;
        ViewHolder(View view){
            button_delete = (Button) view.findViewById(R.id.button_delete);
            number = (TextView) view.findViewById(R.id.number);
            description = (TextView) view.findViewById(R.id.description);
            textTimeDate = (TextView) view.findViewById(R.id.textTimeDate);
        }
    }

}
