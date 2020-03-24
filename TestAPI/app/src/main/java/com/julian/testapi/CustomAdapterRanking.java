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


public class CustomAdapterRanking extends RecyclerView.Adapter<CustomAdapterRanking.MyViewHolder> {
    Context context;

    ArrayList<String> Pseudos;
    ArrayList<String> Races;
    ArrayList<String> Ratios;
    ArrayList<String> Wins;
    ArrayList<String> Loses;
    ArrayList<String> Teams;
    ArrayList<String> Names;
    ArrayList<String> Nationalities;
    ArrayList<String> Earnings;
    public CustomAdapterRanking(Context context, ArrayList<String> Pseudos,ArrayList<String> Races,ArrayList<String> Ratios,ArrayList<String> Wins,ArrayList<String> Loses,ArrayList<String> Teams,ArrayList<String> Names,ArrayList<String> Nationalities,ArrayList<String> Earnings) {
        this.context = context;
        this.Pseudos = Pseudos;
        this.Races = Races;
        this.Ratios = Ratios;
        this.Wins = Wins;
        this.Loses = Loses;
        this.Teams = Teams;
        this.Names = Names;
        this.Nationalities = Nationalities;
        this.Earnings = Earnings;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlayoutranking, parent, false);
        MyViewHolder vh = new MyViewHolder(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        // set the data in items
        holder.pseudo.setText("Pseudo :"+Pseudos.get(position));
        holder.race.setText("Race :"+Races.get(position));
        holder.ratio.setText("Winrate : "+Ratios.get(position));
        holder.win.setText("W : "+Wins.get(position));
        holder.lose.setText("L : "+Loses.get(position));
        holder.team.setText("Team : "+Teams.get(position));
        holder.name.setText("Name : "+Names.get(position));
        holder.nationality.setText("Country : "+Nationalities.get(position));
        holder.earning.setText("Total Earnings :"+Earnings.get(position));
        // implement setOnClickListener event on item view.
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // display a toast with person name on item click
                Toast.makeText(context, Pseudos.get(position), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return Pseudos.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView pseudo,race,ratio,win,lose,team,name,nationality,earning;// init the item view's

        public MyViewHolder(View itemView) {
            super(itemView);

            // get the reference of item view's
            pseudo = (TextView) itemView.findViewById(R.id.pseudo);
            race = (TextView) itemView.findViewById(R.id.race);
            ratio = (TextView) itemView.findViewById(R.id.ratio);
            win = (TextView) itemView.findViewById(R.id.win);
            lose = (TextView) itemView.findViewById(R.id.lose);
            team = (TextView) itemView.findViewById(R.id.team);
            name = (TextView) itemView.findViewById(R.id.name);
            nationality = (TextView) itemView.findViewById(R.id.nationality);
            earning = (TextView) itemView.findViewById(R.id.earning);

        }
    }
}