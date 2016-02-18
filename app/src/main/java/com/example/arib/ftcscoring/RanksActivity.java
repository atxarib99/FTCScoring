package com.example.arib.ftcscoring;

import android.app.Activity;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;


public class RanksActivity extends Activity {

    int teamNumber = 0;
    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ranks_main);
        Intent intent = getIntent();
        teamNumber = Integer.parseInt(intent.getStringExtra(MainActivity.TEAM1));
        score = Integer.parseInt(intent.getStringExtra(MainActivity.SCORE1));
        TextView teamNumberView = (TextView) findViewById(R.id.teamNumber);
        TextView scoreNumberView = (TextView) findViewById(R.id.scoreNumber);
        teamNumberView.setText(teamNumber + "");
        scoreNumberView.setText(score + "");
        SharedPreferences sharedPreferences = getSharedPreferences("special", Context.MODE_PRIVATE);
        boolean activated = sharedPreferences.getBoolean("specialS", false);
        if(activated) {
            teamNumberView.setRotationX(90);
            scoreNumberView.setRotationX(90);
        }
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
    }

}