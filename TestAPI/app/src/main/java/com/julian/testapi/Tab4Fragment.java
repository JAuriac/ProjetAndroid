package com.julian.testapi;

import android.os.AsyncTask;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Tab4Fragment extends Fragment {
    ArrayList<String> Pseudos;
    ArrayList<String> Races;
    ArrayList<String> Ratios;
    ArrayList<String> Wins;
    ArrayList<String> Loses;
    ArrayList<String> Teams;
    ArrayList<String> Names;
    ArrayList<String> Nationalities;
    ArrayList<String> Earnings;

    private static DecimalFormat df = new DecimalFormat("0.00");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mContainer = inflater.inflate(R.layout.fragment, container, false);

        new Tab4Fragment.RetrieveFeedTask().execute();
        return mContainer;
    }

    class RetrieveFeedTask extends AsyncTask<Void, Void, String> {

        private Exception exception;

        protected void onPreExecute() {
            Pseudos = new ArrayList<>();
            Races = new ArrayList<>();
            Ratios = new ArrayList<>();
            Wins = new ArrayList<>();
            Loses = new ArrayList<>();
            Teams = new ArrayList<>();
            Names = new ArrayList<>();
            Nationalities = new ArrayList<>();
            Earnings = new ArrayList<>();
        }

        protected String doInBackground(Void... urls) {

            try {
                String urlString ="http://aligulac.com/api/v1/player/?apikey=c2eU3jUSzazkm9JmkOy4&current_rating__isnull=false&current_rating__decay__lt=4&order_by=-current_rating__rating&limit=10&race=Z";
                //String urlString = API_URL+"event"+API_KEY;
                Log.d("URL String",urlString);
                URL url = new URL(urlString);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    bufferedReader.close();
                    return stringBuilder.toString();
                }
                finally{
                    urlConnection.disconnect();
                }
            }
            catch(Exception e) {
                Log.e("ERROR", e.getMessage(), e);
                return null;
            }
        }

        protected void onPostExecute(String response) {
            if(response == null) {
                response = "THERE WAS AN ERROR";
            }
            RecyclerView recyclerView = (RecyclerView) getView().findViewById(R.id.recyclerViewRanking);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

            try{
                JSONObject obj = new JSONObject(response);
                JSONArray playerArray = obj.getJSONArray("objects");

                for (int i = 0; i < playerArray.length(); i++) {
                    JSONObject playerDetail = playerArray.getJSONObject(i);

                    Pseudos.add(playerDetail.getString("tag"));
                    Races.add(playerDetail.getString("race"));

                    JSONObject wr = playerDetail.getJSONObject("form");
                    JSONArray total = wr.getJSONArray("total");
                    float win = total.getInt(0);
                    float lose = total.getInt(1);
                    float ratio = 100*win/(win+lose);
                    Ratios.add(String.valueOf(df.format(ratio))+"%");
                    Wins.add(String.valueOf(win));
                    Loses.add(String.valueOf(lose));
                    try{
                        JSONArray teams = playerDetail.getJSONArray("current_teams");
                        JSONObject team=teams.getJSONObject(0);
                        JSONObject teamDetail = team.getJSONObject("team");
                        Teams.add(teamDetail.getString("name"));
                    }
                    catch(JSONException d){
                        Teams.add("Currently with no team");
                    }

                    Names.add(playerDetail.getString("name"));
                    Nationalities.add(playerDetail.getString("country"));
                    Earnings.add(playerDetail.getString("total_earnings")+"$");
                }
            }

            catch (JSONException e) {
                e.printStackTrace();
            }
            CustomAdapterRanking customAdapter = new CustomAdapterRanking(getActivity(), Pseudos,Races,Ratios,Wins,Loses,Teams,Names,Nationalities,Earnings);
            recyclerView.setAdapter(customAdapter); // set the Adapter to RecyclerView // set the Adapter to RecyclerView


            Log.i("INFO", response);
        }
    }
}
