package com.julian.testapi;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class PlayerSearch extends AppCompatActivity {
    EditText tagText;
    ProgressBar progressBar;

    ArrayList<String> Pseudos = new ArrayList<>();
    ArrayList<String> Races = new ArrayList<>();
    ArrayList<String> WinRateAll = new ArrayList<>();
    ArrayList<String> WinRateP = new ArrayList<>();
    ArrayList<String> WinRateT = new ArrayList<>();
    ArrayList<String> WinRateZ = new ArrayList<>();
    ArrayList<String> Teams = new ArrayList<>();
    ArrayList<String> Names = new ArrayList<>();
    ArrayList<String> Nationalities = new ArrayList<>();
    ArrayList<String> Earnings = new ArrayList<>();

    static final String API_KEY = "/?apikey=c2eU3jUSzazkm9JmkOy4";
    static final String API_URL = "http://aligulac.com/api/v1/";

    private static DecimalFormat df = new DecimalFormat("0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playersearch);

        //responseView = (TextView) findViewById(R.id.responseView);
        tagText = (EditText) findViewById(R.id.tagText);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        Button queryButton = (Button) findViewById(R.id.queryButton);
        queryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new RetrieveFeedTask().execute();
            }
        });
    }


    class RetrieveFeedTask extends AsyncTask<Void, Void, String> {

        private Exception exception;

        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
            Pseudos = new ArrayList<>();
            Races = new ArrayList<>();
            WinRateAll = new ArrayList<>();
            WinRateP = new ArrayList<>();
            WinRateT = new ArrayList<>();
            WinRateZ = new ArrayList<>();
            Teams = new ArrayList<>();
            Names = new ArrayList<>();
            Nationalities = new ArrayList<>();
            Earnings = new ArrayList<>();
        }

        protected String doInBackground(Void... urls) {
            String tag = tagText.getText().toString();

            try {
                String urlString = API_URL+"player"+API_KEY;
                if (!tag.isEmpty()){urlString += "&tag__iexact="+tag+"&order_by=-current_rating__rating";}
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

            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerViewPlayer);
            // set a LinearLayoutManager with default vertical orientation
            //GridLayoutManager GridLayoutManager = new GridLayoutManager(MainActivity.this, 10);
            //recyclerView.setLayoutManager(GridLayoutManager);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(linearLayoutManager);

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
                    WinRateAll.add(String.valueOf(df.format(ratio))+"%    ");
                    JSONArray p = wr.getJSONArray("P");
                    win = p.getInt(0);
                    lose = p.getInt(1);
                    ratio = 100*win/(win+lose);
                    WinRateP.add(String.valueOf(df.format(ratio))+"%    ");
                    JSONArray t = wr.getJSONArray("T");
                    win = t.getInt(0);
                    lose = t.getInt(1);
                    ratio = 100*win/(win+lose);
                    WinRateT.add(String.valueOf(df.format(ratio))+"%    ");
                    JSONArray z = wr.getJSONArray("Z");
                    win = z.getInt(0);
                    lose = z.getInt(1);
                    ratio = 100*win/(win+lose);
                    float ratio2 = 10/3;
                    WinRateZ.add(String.valueOf(df.format(ratio))+"%    ");
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
            CustomAdapterPlayer customAdapter = new CustomAdapterPlayer(PlayerSearch.this, Pseudos,Races,WinRateAll,WinRateP,WinRateT,WinRateZ,Teams,Names,Nationalities,Earnings);
            recyclerView.setAdapter(customAdapter); // set the Adapter to RecyclerView

            progressBar.setVisibility(View.GONE);
            Log.i("INFO", response);
        }
    }
}
