package com.julian.testapi;

import android.content.Context;
import android.content.SharedPreferences;
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
    EditText tagText;
    TextView responseView;
    ProgressBar progressBar;
    static final String API_KEY = "/?apikey=c2eU3jUSzazkm9JmkOy4";
    static final String API_URL = "http://aligulac.com/api/v1/";
    SharedPreferences sharedpreferences;
    public static final String preference = "pref";
    public static final String Tag = "nameKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playersearch);

        responseView = (TextView) findViewById(R.id.responseView);
        tagText = (EditText) findViewById(R.id.tagText);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        sharedpreferences = getSharedPreferences(preference,
                Context.MODE_PRIVATE);

        Button lastButton = (Button) findViewById(R.id.lastButton);
        lastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Get(v);
            }
        });

        Button queryButton = (Button) findViewById(R.id.queryButton);
        queryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Save(v);
                new RetrieveFeedTask().execute();
            }
        });
    }


    public void Save(View view) {
        String t = tagText.getText().toString();
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(Tag, t);
        editor.commit();
    }

    public void Get(View view) {
        tagText = (EditText) findViewById(R.id.tagText);
        sharedpreferences = getSharedPreferences(preference,
                Context.MODE_PRIVATE);

        if (sharedpreferences.contains(Tag)) {
            tagText.setText(sharedpreferences.getString(Tag, ""));
        }
    }

    class RetrieveFeedTask extends AsyncTask<Void, Void, String> {

        private Exception exception;

        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
            responseView.setText("");
        }

        protected String doInBackground(Void... urls) {
            String tag = tagText.getText().toString();

            try {
                String urlString = API_URL+"player"+API_KEY;
                if (!tag.isEmpty()){urlString += "&tag="+tag;}
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
