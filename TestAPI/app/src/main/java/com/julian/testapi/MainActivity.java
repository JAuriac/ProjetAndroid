package com.julian.testapi;

import android.os.AsyncTask;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

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
            responseView.setText("");
        }

        protected String doInBackground(Void... urls) {
            //String email = "email";
            String table = tableText.getText().toString();
            String tag = tagText.getText().toString();
            String country = countryText.getText().toString();
            // Do some validation here

            try {
//                Log.d("table",table);
//                Log.d("tag",tag);
//                Log.d("country",country);
                String urlString = "";
                if (table.equals("player")) //table == "player"   Objects.equals(table, "player")
                {
                    //if (!tag.isEmpty() && !country.isEmpty()){urlString = "http://aligulac.com/api/v1/"+table+"/?apikey=c2eU3jUSzazkm9JmkOy4&tag="+tag+"&country="+country;}
                    if (!tag.isEmpty() && !country.isEmpty()){urlString = API_URL+table+API_KEY+"&tag="+tag+"&country="+country;}
                    else if (tag.isEmpty() && !country.isEmpty()){urlString = API_URL+table+API_KEY+"&country="+country;}
                    else if (!tag.isEmpty() && country.isEmpty()){urlString = API_URL+table+API_KEY+"&tag="+tag;}
                    else {urlString = API_URL+table+API_KEY;}
                }
                if (table.equals("match")) //table == "player"   Objects.equals(table, "player")
                {
                    //if (!tag.isEmpty() && !country.isEmpty()){urlString = "http://aligulac.com/api/v1/"+table+"/?apikey=c2eU3jUSzazkm9JmkOy4&tag="+tag+"&country="+country;}
                    if (1 == 0){}
                    else {urlString = API_URL+table+API_KEY;}
                }
                if (table.equals("event")) //table == "player"   Objects.equals(table, "player")
                {
                    //if (!tag.isEmpty() && !country.isEmpty()){urlString = "http://aligulac.com/api/v1/"+table+"/?apikey=c2eU3jUSzazkm9JmkOy4&tag="+tag+"&country="+country;}
                    if (1 == 0){}
                    else {urlString = API_URL+table+API_KEY;}
                }
                if (table.equals("eventadjacency")) //table == "player"   Objects.equals(table, "player")
                {
                    //if (!tag.isEmpty() && !country.isEmpty()){urlString = "http://aligulac.com/api/v1/"+table+"/?apikey=c2eU3jUSzazkm9JmkOy4&tag="+tag+"&country="+country;}
                    if (1 == 0){}
                    else {urlString = API_URL+table+API_KEY;}
                }
                if (table.equals("rating")) //table == "player"   Objects.equals(table, "player")
                {
                    //if (!tag.isEmpty() && !country.isEmpty()){urlString = "http://aligulac.com/api/v1/"+table+"/?apikey=c2eU3jUSzazkm9JmkOy4&tag="+tag+"&country="+country;}
                    if (1 == 0){}
                    else {urlString = API_URL+table+API_KEY;}
                }
                if (table.equals("period")) //table == "player"   Objects.equals(table, "player")
                {
                    //if (!tag.isEmpty() && !country.isEmpty()){urlString = "http://aligulac.com/api/v1/"+table+"/?apikey=c2eU3jUSzazkm9JmkOy4&tag="+tag+"&country="+country;}
                    if (1 == 0){}
                    else {urlString = API_URL+table+API_KEY;}
                }
                if (table.equals("group")) //table == "player"   Objects.equals(table, "player")
                {
                    //if (!tag.isEmpty() && !country.isEmpty()){urlString = "http://aligulac.com/api/v1/"+table+"/?apikey=c2eU3jUSzazkm9JmkOy4&tag="+tag+"&country="+country;}
                    if (1 == 0){}
                    else {urlString = API_URL+table+API_KEY;}
                }
                if (table.equals("groupmembership")) //table == "player"   Objects.equals(table, "player")
                {
                    //if (!tag.isEmpty() && !country.isEmpty()){urlString = "http://aligulac.com/api/v1/"+table+"/?apikey=c2eU3jUSzazkm9JmkOy4&tag="+tag+"&country="+country;}
                    if (1 == 0){}
                    else {urlString = API_URL+table+API_KEY;}
                }
                if (table.equals("earnings")) //table == "player"   Objects.equals(table, "player")
                {
                    //if (!tag.isEmpty() && !country.isEmpty()){urlString = "http://aligulac.com/api/v1/"+table+"/?apikey=c2eU3jUSzazkm9JmkOy4&tag="+tag+"&country="+country;}
                    if (1 == 0){}
                    else {urlString = API_URL+table+API_KEY;}
                }
                if (table.equals("alias")) //table == "player"   Objects.equals(table, "player")
                {
                    //if (!tag.isEmpty() && !country.isEmpty()){urlString = "http://aligulac.com/api/v1/"+table+"/?apikey=c2eU3jUSzazkm9JmkOy4&tag="+tag+"&country="+country;}
                    if (1 == 0){}
                    else {urlString = API_URL+table+API_KEY;}
                }
                if (table.equals("message")) //table == "player"   Objects.equals(table, "player")
                {
                    //if (!tag.isEmpty() && !country.isEmpty()){urlString = "http://aligulac.com/api/v1/"+table+"/?apikey=c2eU3jUSzazkm9JmkOy4&tag="+tag+"&country="+country;}
                    if (1 == 0){}
                    else {urlString = API_URL+table+API_KEY;}
                }
                if (table.equals("story")) //table == "player"   Objects.equals(table, "player")
                {
                    //if (!tag.isEmpty() && !country.isEmpty()){urlString = "http://aligulac.com/api/v1/"+table+"/?apikey=c2eU3jUSzazkm9JmkOy4&tag="+tag+"&country="+country;}
                    if (1 == 0){}
                    else {urlString = API_URL+table+API_KEY;}
                }
                //URL url = new URL(API_URL + "earning/" + "?apiKey=" + API_KEY);
                //String urlString = "http://aligulac.com/api/v1/earning/?apikey=c2eU3jUSzazkm9JmkOy4";
                //String urlString = "http://aligulac.com/api/v1/player/?apikey=c2eU3jUSzazkm9JmkOy4&tag=Clem";
                //String urlString = "http://aligulac.com/api/v1/"+table+"/?apikey=c2eU3jUSzazkm9JmkOy4&tag=Clem";
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

