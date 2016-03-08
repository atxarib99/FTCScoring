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
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.net.Inet4Address;
import java.util.ArrayList;


public class RanksActivity extends Activity {

    private static final String LOG_TAG = RanksActivity.class.getSimpleName();
    int teamNumber1 = 0;
    int score1 = 0;
    int teamNumber2 = 0;
    int score2 = 0;
    int teamNumber3 = 0;
    int score3 = 0;
    int teamNumber4 = 0;
    int score4;
    int teamNumber5 = 0;
    int score5 = 0;
    int teamNumber6 = 0;
    int score6 = 0;
    int teamNumber7 = 0;
    int score7 = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ranks_main);
        Intent intent = getIntent();
        Log.e(LOG_TAG, intent.toString());
        //charat42
        String gettingIntent = intent.toString();
        char activity = gettingIntent.charAt(42);
        Log.e(LOG_TAG, activity + "");

        teamNumber1 = Integer.parseInt(intent.getStringExtra(MainActivity.TEAM1));
        score1 = Integer.parseInt(intent.getStringExtra(MainActivity.SCORE1));
        teamNumber2 = Integer.parseInt(intent.getStringExtra(MainActivity.TEAM2));
        score2 = Integer.parseInt(intent.getStringExtra(MainActivity.SCORE2));
        teamNumber3 = Integer.parseInt(intent.getStringExtra(MainActivity.TEAM3));
        score3 = Integer.parseInt(intent.getStringExtra(MainActivity.SCORE3));
        teamNumber4 = Integer.parseInt(intent.getStringExtra(MainActivity.TEAM4));
        score4 = Integer.parseInt(intent.getStringExtra(MainActivity.SCORE4));
        teamNumber5 = Integer.parseInt(intent.getStringExtra(MainActivity.TEAM5));
        score5 = Integer.parseInt(intent.getStringExtra(MainActivity.SCORE5));
        teamNumber6 = Integer.parseInt(intent.getStringExtra(MainActivity.TEAM6));
        score6 = Integer.parseInt(intent.getStringExtra(MainActivity.SCORE6));
        teamNumber7 = Integer.parseInt(intent.getStringExtra(MainActivity.TEAM7));
        score7 = Integer.parseInt(intent.getStringExtra(MainActivity.SCORE7));

        TextView teamNumberView = (TextView) findViewById(R.id.teamNumber1);
        TextView scoreNumberView = (TextView) findViewById(R.id.scoreNumber1);
        TextView teamNumberView2 = (TextView) findViewById(R.id.teamNumber2);
        TextView scoreNumberView2 = (TextView) findViewById(R.id.scoreNumber2);
        TextView teamNumberView3 = (TextView) findViewById(R.id.teamNumber3);
        TextView scoreNumberView3 = (TextView) findViewById(R.id.scoreNumber3);
        TextView teamNumberView4 = (TextView) findViewById(R.id.teamNumber4);
        TextView scoreNumberView4 = (TextView) findViewById(R.id.scoreNumber4);
        TextView teamNumberView5 = (TextView) findViewById(R.id.teamNumber5);
        TextView scoreNumberView5 = (TextView) findViewById(R.id.scoreNumber5);
        TextView teamNumberView6 = (TextView) findViewById(R.id.teamNumber6);
        TextView scoreNumberView6 = (TextView) findViewById(R.id.scoreNumber6);
        TextView teamNumberView7 = (TextView) findViewById(R.id.teamNumber7);
        TextView scoreNumberView7 = (TextView) findViewById(R.id.scoreNumber7);
        teamNumberView.setText(teamNumber1 + "");
        scoreNumberView.setText(score1 + "");
        teamNumberView2.setText(teamNumber2 + "");
        scoreNumberView2.setText(score2 + "");
        teamNumberView3.setText(teamNumber3 + "");
        scoreNumberView3.setText(score3 + "");
        teamNumberView4.setText(teamNumber4 + "");
        scoreNumberView4.setText(score4 + "");
        teamNumberView5.setText(teamNumber5 + "");
        scoreNumberView5.setText(score5 + "");
        teamNumberView6.setText(teamNumber6 + "");
        scoreNumberView6.setText(score6 + "");
        teamNumberView7.setText(teamNumber7 + "");
        scoreNumberView7.setText(score7 + "");
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