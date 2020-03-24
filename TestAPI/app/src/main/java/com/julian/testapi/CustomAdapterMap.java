package com.julian.testapi;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class CustomAdapterMap extends RecyclerView.Adapter<CustomAdapterMap.MyViewHolder>{

    private Context context;
    private int[] mappool;

    public CustomAdapterMap(Context context, int[] mappool) {
        this.context = context;
        this.mappool = mappool;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gridlayoutmap,
                parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.mPlace.setImageResource(mappool[position]);
    }

    @Override
    public int getItemCount() {
        return mappool.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView mPlace;

        public MyViewHolder(View itemView) {
            super(itemView);

            mPlace = itemView.findViewById(R.id.ivPlace);
        }
    }
}

