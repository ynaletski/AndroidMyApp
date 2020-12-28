package com.example.mystart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {

    private final LayoutInflater inflater;
    private final ArrayList<Event> events = new ArrayList<>();
    private final removeClickListener removeClickListener;

    EventAdapter(Context context, removeClickListener removeClickListener) {

        this.inflater = LayoutInflater.from(context);
        this.removeClickListener = removeClickListener;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.event_item, parent, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, final int position) {
        final Event event = events.get(position);

        holder.number.setText(event.getNumber());
        holder.description.setText(event.getDescription());
        holder.textTimeDate.setText(event.getDateTime());

        holder.deleteEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                removeClickListener.removeEvent(position);
                notifyItemRangeChanged(position, getItemCount());

            }
        });
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public void insertData(ArrayList<Event> newEvents) {
        if (newEvents != null) {
            DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new EventDiffUtil(events, newEvents));
            this.events.clear();
            this.events.addAll(newEvents);
            diffResult.dispatchUpdatesTo(this);
        }
    }

    static class EventViewHolder extends RecyclerView.ViewHolder {

        final Button deleteEvent;
        final TextView number, description, textTimeDate;

        public EventViewHolder(@NonNull View itemView) {
            super(itemView);

            deleteEvent = itemView.findViewById(R.id.buttonDelete);
            number = itemView.findViewById(R.id.number);
            description = itemView.findViewById(R.id.description);
            textTimeDate = itemView.findViewById(R.id.timeDate);
        }
    }

}