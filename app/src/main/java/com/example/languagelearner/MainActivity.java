package com.example.languagelearner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void openNumberList(View view) {
        Intent i = new Intent(this, NumbersActivity.class);
        startActivity(i);
    }

    public void openColor(View view){
                Intent y = new Intent(MainActivity.this, ColorsActivity.class);
                startActivity(y);

            }

    public void openCityName(View view) {
        Intent p = new Intent(this, TestMainActivity2.class);
        startActivity(p);

    }


    public void openFamilyName(View view){
        Intent z = new Intent(this,Family_membersActivity.class);
        startActivity(z);

        }
        public void  openPhrasesList(View view){
        Intent x = new Intent(this,PhrasesActivity.class);
        startActivity(x);

    }
}