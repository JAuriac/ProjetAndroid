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

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class PlayerSearch extends AppCompatActivity {
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
        setContentView(R.layout.activity_playersearch);

        responseView = (TextView) findViewById(R.id.responseView);
        tagText = (EditText) findViewById(R.id.tagText);
        countryText = (EditText) findViewById(R.id.countryText);
        //ratingText = (EditText) findViewById(R.id.ratingText);
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
            String tag = tagText.getText().toString();
            String country = countryText.getText().toString();
            //String rating = ratingText.getText().toString();

            try {
                String urlString = API_URL+"player"+API_KEY;
                if (!tag.isEmpty()){urlString += "&tag="+tag;}
                if (!country.isEmpty()){urlString += "&country="+country;}
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
