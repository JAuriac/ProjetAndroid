package com.julian.testapi;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Event extends AppCompatActivity {
    EditText tagText;
    ProgressBar progressBar;

    ArrayList<String> EventNames = new ArrayList<>();
    ArrayList<String> LastMatches = new ArrayList<>();
    ArrayList<String> FirstMaches = new ArrayList<>();

    static final String API_KEY = "/?apikey=c2eU3jUSzazkm9JmkOy4";
    static final String API_URL = "http://aligulac.com/api/v1/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        tagText = (EditText) findViewById(R.id.tagText);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        new RetrieveFeedTask().execute();
    }


    class RetrieveFeedTask extends AsyncTask<Void, Void, String> {

        private Exception exception;

        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
        }

        protected String doInBackground(Void... urls) {

            try {
                String urlString ="http://aligulac.com/api/v1/event/?apikey=c2eU3jUSzazkm9JmkOy4&latest__isnull=false&type=event&order_by=-latest";
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

            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerViewEvent);
            // set a LinearLayoutManager with default vertical orientation
            //GridLayoutManager GridLayoutManager = new GridLayoutManager(MainActivity.this, 10);
            //recyclerView.setLayoutManager(GridLayoutManager);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
            recyclerView.setLayoutManager(linearLayoutManager);

            try{
                JSONObject obj = new JSONObject(response);
                JSONArray eventArray = obj.getJSONArray("objects");

                for (int i = 0; i < eventArray.length(); i++) {
                    JSONObject eventDetail = eventArray.getJSONObject(i);
                    Log.i("fullname :",eventDetail.getString("fullname"));
                    Log.i("latest :",eventDetail.getString("latest"));
                    Log.i("earliest :",eventDetail.getString("earliest"));
                    Log.i("======= :",String.valueOf(i));

                    EventNames.add(eventDetail.getString("fullname"));
                    LastMatches.add(eventDetail.getString("latest"));
                    FirstMaches.add(eventDetail.getString("earliest"));
                }
            }

            catch (JSONException e) {
                e.printStackTrace();
            }
            CustomAdapterEvent customAdapter = new CustomAdapterEvent(Event.this, EventNames,LastMatches,FirstMaches);
            recyclerView.setAdapter(customAdapter); // set the Adapter to RecyclerView

            progressBar.setVisibility(View.GONE);
            Log.i("INFO", response);
        }
    }
}

