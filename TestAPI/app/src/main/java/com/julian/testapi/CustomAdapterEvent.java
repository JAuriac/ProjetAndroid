package com.julian.testapi;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class CustomAdapterEvent extends RecyclerView.Adapter<CustomAdapterEvent.MyViewHolder> {
    Context context;

    ArrayList<String> EventNames;
    ArrayList<String> LastMatches;
    ArrayList<String> FirstMaches;

    public CustomAdapterEvent(Context context, ArrayList<String> EventNames,ArrayList<String> LastMatches,ArrayList<String> FirstMaches) {
        this.context = context;

        this.EventNames = EventNames;
        this.LastMatches = LastMatches;
        this.FirstMaches = FirstMaches;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlayoutevent, parent, false);
        MyViewHolder vh = new MyViewHolder(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        // set the data in items
        holder.eventname.setText("Event Name :"+EventNames.get(position));
        holder.firstmatch.setText("Latest Match :"+LastMatches.get(position));
        holder.lastmatch.setText("First Match : "+FirstMaches.get(position));
        // implement setOnClickListener event on item view.
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // display a toast with person name on item click
                Toast.makeText(context, EventNames.get(position), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return EventNames.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView eventname,firstmatch,lastmatch;// init the item view's

        public MyViewHolder(View itemView) {
            super(itemView);

            // get the reference of item view's
            eventname = (TextView) itemView.findViewById(R.id.eventname);
            firstmatch = (TextView) itemView.findViewById(R.id.firstmatch);
            lastmatch = (TextView) itemView.findViewById(R.id.lastmatch);
        }
    }
}