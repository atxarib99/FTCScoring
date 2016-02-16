package com.example.arib.ftcscoring;

import android.app.Activity;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class MainActivity extends Activity {

    public final static String TEAM1 = "com.example.dhuka_844963.team1";
    public final static String SCORE1 = "com.example.dhuka_844963.score1";
    private static final String FILE_NAME = "teams";
    public final static String LOG_TAG = MainActivity.class.getSimpleName();
    static ArrayList<Team> teams = new ArrayList<>();
    HomeWatcher mHomeWatcher = new HomeWatcher(this);
    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */


    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TabHost tabHost = (TabHost) findViewById(R.id.tabHost);
        tabHost.setup();

        TabHost.TabSpec tabSpec = tabHost.newTabSpec("PitScouting");
        tabHost.addTab(tabHost.newTabSpec("PitScouting").setIndicator("Pit").setContent(R.id.PitScouting));

        tabSpec = tabHost.newTabSpec("MatchScouting");
        tabHost.addTab(tabHost.newTabSpec("MatchScouting").setIndicator("Match").setContent(R.id.MatchScouting));

        mHomeWatcher.setOnHomePressedListener(new OnHomePressedListener() {
            @Override
            public void onHomePressed() {
            }

            @Override
            public void onHomeLongPressed() {

            }
        });

    }


    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, RanksActivity.class);
            Team temp = findBestTeam();
            intent.putExtra(TEAM1, temp.getTeamNumber() + "");
            intent.putExtra(SCORE1, Math.round(temp.score) + "");
            Context context = getApplicationContext();
            CharSequence text = temp.getTeamNumber() + ", " + temp.score;
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();


            startActivity(intent);
            return true;
        }
        if(id == R.id.action_help) {
            Intent intent = new Intent(this, HelpActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    public void clearFields(View view) {
        EditText driverAbility = (EditText) findViewById(R.id.driverAbility_field);
        EditText robotStructure = (EditText) findViewById(R.id.robotStructure_field);
        EditText durability = (EditText) findViewById(R.id.durability_field);
        EditText autonomousScore = (EditText) findViewById(R.id.autonomousScore_field);
        EditText autonomousConsistency = (EditText) findViewById(R.id.autonomousConsistency_field);
        EditText teleOpScore = (EditText) findViewById(R.id.teleOpScore_field);
        EditText teleOpConsistency = (EditText) findViewById(R.id.teleOpConsistency_field);
        EditText endGameScore = (EditText) findViewById(R.id.endGameScore_field);
        EditText endGameConsistency = (EditText) findViewById(R.id.endGameConsistency_field);
        CheckBox pushBot = (CheckBox) findViewById(R.id.pushBot);
        EditText allianceTotalScore = (EditText) findViewById(R.id.allianceTotalScore_field);
        EditText teamNumber = (EditText) findViewById(R.id.name_field);
        driverAbility.setText("");
        robotStructure.setText("");
        durability.setText("");
        autonomousScore.setText("");
        autonomousConsistency.setText("");
        teleOpScore.setText("");
        teleOpConsistency.setText("");
        endGameScore.setText("");
        endGameConsistency.setText("");
        pushBot.setChecked(false);
        allianceTotalScore.setText("");
        teamNumber.setText("");

    }

    public void addScore(View view) {
        double driverAbility;
        double robotStructure;
        double durability;
        double autonomousScore;
        double autonomousConsistency;
        double teleOpScore;
        double teleOpConsistency;
        double endGameScore;
        double endGameConsistency;
        double alliancePartnerScore;
        int teamNumber;
        boolean allGood = true;

        try {
            driverAbility = Integer.parseInt(((EditText) findViewById(R.id.driverAbility_field)).getText() + "");
            robotStructure = Integer.parseInt(((EditText) findViewById(R.id.robotStructure_field)).getText() + "");
            durability = Integer.parseInt(((EditText) findViewById(R.id.durability_field)).getText() + "");
            autonomousScore = Integer.parseInt(((EditText) findViewById(R.id.autonomousScore_field)).getText() + "");
            autonomousConsistency = Integer.parseInt(((EditText) findViewById(R.id.autonomousConsistency_field)).getText() + "");
            teleOpScore = Integer.parseInt(((EditText) findViewById(R.id.teleOpScore_field)).getText() + "");
            teleOpConsistency = Integer.parseInt(((EditText) findViewById(R.id.teleOpConsistency_field)).getText() + "");
            endGameScore = Integer.parseInt(((EditText) findViewById(R.id.endGameScore_field)).getText() + "");
            endGameConsistency = Integer.parseInt(((EditText) findViewById(R.id.endGameConsistency_field)).getText() + "");
            alliancePartnerScore = Integer.parseInt(((EditText) findViewById(R.id.allianceTotalScore_field)).getText() + "");
            teamNumber = Integer.parseInt(((EditText) findViewById(R.id.name_field)).getText() + "");
        } catch (NumberFormatException e){
            Context context = getApplicationContext();
            CharSequence text = "Error: Incorrect Input";
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            allGood = false;
            driverAbility = 0;
            robotStructure = 0;
            durability = 0;
            autonomousConsistency = 0;
            autonomousScore = 0;
            teleOpScore = 0;
            teleOpConsistency = 0;
            endGameScore = 0;
            endGameConsistency = 0;
            alliancePartnerScore = 0;
            teamNumber = 0;

        }
        boolean pushBot = ((CheckBox) findViewById(R.id.pushBot)).isChecked();
        double score = 0.0;

        if(allGood) {
            double score1 = autonomousScore + (autonomousConsistency / 10);
            double score2 = teleOpScore + (teleOpConsistency / 10);
            double score3 = endGameScore + (endGameConsistency / 10);
            double score4 = (driverAbility + robotStructure + durability) / 3;
            score = score1 + score2 + score3;
            score = score * (alliancePartnerScore / 10);
            score += score4;
            double finalScore = Math.round(score);
            if(pushBot) {
                finalScore /= 2;
            }

            Team t;
            if (findTeamByNumber(teamNumber)) {
                t = getTeamByNumber(teamNumber);
                t.addNewScore(finalScore);
            } else {
                t = new Team(finalScore, teamNumber);
                teams.add(t);
            }
            Context context = getApplicationContext();
            CharSequence text = t.getTeamNumber() + ", " + t.score;
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

        



    }

    private AlertDialog AskOption() {
        AlertDialog restart = new AlertDialog.Builder(this)
                .setTitle("Restart")
                .setMessage("Are you sure you want to wipe current competition data?")
                .setPositiveButton("Restart", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        teams = new ArrayList<Team>();
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();

        return restart;

    }

    public void reset(View view) {

        AlertDialog ask = AskOption();
        ask.show();
    }

    public Team findBestTeam() {
        int currentHigh = 0;
        boolean done = false;
        Team returnable = new Team(0, 0);
        for (int i = 0; i < teams.size(); i++) {
            if (teams.get(i).score > currentHigh) {
                returnable.score = teams.get(i).score;
                returnable.teamNumber = teams.get(i).teamNumber;
                currentHigh = (int) teams.get(i).score;
            }
        }
        return returnable;
    }

    private boolean findTeamByNumber(int teamNumber) {
        for (Team t : teams) {
            if (t.getTeamNumber() == teamNumber) {
                return true;
            }
        }
        return false;
    }

    private Team getTeamByNumber(int teamNumber) {
        for (Team t : teams) {
            if (t.getTeamNumber() == teamNumber) {
                return t;
            }
        }
        return new Team(0, 0);
    }
}