package com.julian.testapi;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        Button toPoolButton = (Button) findViewById(R.id.toPoolButton);
        toPoolButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchActivityQuad();
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
    private void launchActivityQuad() {

        Intent intent = new Intent(this, MapPool.class);
        startActivity(intent);
    }
}

