package com.julian.testapi;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.view.Menu;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    EditText tableText;
    EditText tagText;
    EditText countryText;
    EditText eventText;
    EditText ratingText;
    TextView responseView;
    ProgressBar progressBar;
    static final String API_KEY = "/?apikey=c2eU3jUSzazkm9JmkOy4";
    static final String API_URL = "http://aligulac.com/api/v1/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        responseView = (TextView) findViewById(R.id.responseView);
        tableText = (EditText) findViewById(R.id.tableText);
        tagText = (EditText) findViewById(R.id.tagText);
        countryText = (EditText) findViewById(R.id.countryText);
        eventText = (EditText) findViewById(R.id.eventText);
        ratingText = (EditText) findViewById(R.id.ratingText);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        Button queryButton = (Button) findViewById(R.id.queryButton);
        queryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new RetrieveFeedTask().execute();
            }
        });

        Button toSearchButton = (Button) findViewById(R.id.toSearchButton);
        toSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchActivity();
            }
        });

        Button toEventButton = (Button) findViewById(R.id.toEventButton);
        toEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchActivityBis();
            }
        });

        Button toRankButton = (Button) findViewById(R.id.toRankButton);
        toRankButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchActivityTri();
            }
        });
    }


    private void launchActivity() {

        Intent intent = new Intent(this, PlayerSearch.class);
        startActivity(intent);
    }
    private void launchActivityBis() {

        Intent intent = new Intent(this, Event.class);
        startActivity(intent);
    }
    private void launchActivityTri() {

        Intent intent = new Intent(this, Ranking.class);
        startActivity(intent);
    }


    class RetrieveFeedTask extends AsyncTask<Void, Void, String> {

        private Exception exception;

        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
            responseView.setText("");
        }

        protected String doInBackground(Void... urls) {
            String table = tableText.getText().toString();
            String tag = tagText.getText().toString();
            String country = countryText.getText().toString();
            String event = eventText.getText().toString();
            String rating = ratingText.getText().toString();

            try {
                String urlString = API_URL+table+API_KEY;
                if (table.equals("player"))
                {
                    if (!tag.isEmpty()){urlString += "&tag="+tag;}
                    if (!country.isEmpty()){urlString += "&country="+country;}
                }
                if (table.equals("match"))
                {
                }
                if (table.equals("event"))
                {
                    if (!country.isEmpty()){urlString += "&id="+event;}
                }
                if (table.equals("eventadjacency"))
                {
                }
                if (table.equals("rating"))
                {
                    if (!rating.isEmpty()){urlString += "&="+event;}
                }
                if (table.equals("period"))
                {
                }
                if (table.equals("group"))
                {
                }
                if (table.equals("groupmembership"))
                {
                }
                if (table.equals("earnings"))
                {
                }
                if (table.equals("alias"))
                {
                }
                if (table.equals("message"))
                {
                }
                if (table.equals("story"))
                {
                }
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
            progressBar.setVisibility(View.GONE);
            Log.i("INFO", response);
            responseView.setText(response);
        }
    }
}

