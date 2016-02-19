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
import java.text.DecimalFormat;
import java.util.ArrayList;


public class MainActivity extends Activity {

    public final static String TEAM1 = "com.example.dhuka_844963.team1";
    public final static String SCORE1 = "com.example.dhuka_844963.score1";
    public final static String LOG_TAG = MainActivity.class.getSimpleName();
    static ArrayList<Team> teams = new ArrayList<>();
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
        if (id == R.id.action_bestpit) {
            Intent intent = new Intent(this, RanksActivity.class);
            Team temp = findBestTeamByPit();
            intent.putExtra(TEAM1, temp.getTeamNumber() + "");
            intent.putExtra(SCORE1, Math.round(temp.getPitScore()) + "");
            Context context = getApplicationContext();
            CharSequence text = temp.getTeamNumber() + ", " + temp.getPitScore();
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();


            startActivity(intent);
            return true;
        }
        if(id == R.id.action_help) {
            Intent intent = new Intent(this, HelpActivity.class);
            startActivity(intent);
            return true;
        }
        if(id == R.id.action_bestmatch) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void clearFields(View view) {
        EditText robotStructure = (EditText) findViewById(R.id.robotStructure_field);
        EditText autonomousScore = (EditText) findViewById(R.id.autonomousScore_field);
        EditText autonomousConsistency = (EditText) findViewById(R.id.autonomousConsistency_field);
        EditText teleOpScore = (EditText) findViewById(R.id.teleOpScore_field);
        EditText teleOpConsistency = (EditText) findViewById(R.id.teleOpConsistency_field);
        EditText endGameScore = (EditText) findViewById(R.id.endGameScore_field);
        EditText endGameConsistency = (EditText) findViewById(R.id.endGameConsistency_field);
        EditText allianceTotalScore = (EditText) findViewById(R.id.allianceTotalScore_field);
        EditText teamNumber = (EditText) findViewById(R.id.name_field);
        robotStructure.setText("");
        autonomousScore.setText("");
        autonomousConsistency.setText("");
        teleOpScore.setText("");
        teleOpConsistency.setText("");
        endGameScore.setText("");
        endGameConsistency.setText("");
        allianceTotalScore.setText("");
        teamNumber.setText("");

    }

    public void addPitScore(View view) {
        double robotStructure;
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
            robotStructure = Integer.parseInt(((EditText) findViewById(R.id.robotStructure_field)).getText() + "");
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
            robotStructure = 0;
            autonomousConsistency = 0;
            autonomousScore = 0;
            teleOpScore = 0;
            teleOpConsistency = 0;
            endGameScore = 0;
            endGameConsistency = 0;
            alliancePartnerScore = 0;
            teamNumber = 0;

        }
        double score = 0.0;

        if(allGood) {
            double score1 = autonomousScore + (autonomousConsistency / 10);
            double score2 = teleOpScore + (teleOpConsistency / 10);
            double score3 = endGameScore + (endGameConsistency / 10);
            double score4 = robotStructure;
            score = score1 + score2 + score3;
            score = score * (alliancePartnerScore / 10);
            score += score4;
            DecimalFormat df = new DecimalFormat("###.###");
            double finalScore;
            boolean finished = true;
            try {
                finalScore = Double.parseDouble(df.format(score));
            } catch (NumberFormatException e) {
                Context context = getApplicationContext();
                CharSequence text = "Error incorrect format";
                int duration = Toast.LENGTH_LONG;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                finalScore = 0;
                finished = false;
            }

            if(finished) {

                Team t;
                if (findTeamByNumber(teamNumber)) {
                    t = getTeamByNumber(teamNumber);
                    t.setPitScore(finalScore);
                } else {
                    t = new Team(finalScore, teamNumber);
                    teams.add(t);
                }
                Context context = getApplicationContext();
                CharSequence text = t.getTeamNumber() + ", " + t.getPitScore();
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Context context = getApplicationContext();
                CharSequence text = "Multithread error";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }

            clearFields(view);
        }

    }

    public void addMatchScore(View view) {
        int redAlliance1;
        int redAlliance2;
        int blueAlliance1;
        int blueAlliance2;
        int redAllianceScore;
        int blueAllianceScore;
        boolean allGood = true;
        try {
            redAlliance1 = Integer.parseInt(((EditText) findViewById(R.id.redteam1)).getText() + "");
            redAlliance2 = Integer.parseInt(((EditText) findViewById(R.id.redTeam2)).getText() + "");
            blueAlliance1 = Integer.parseInt(((EditText) findViewById(R.id.blueTeam1)).getText() + "");
            blueAlliance2 = Integer.parseInt(((EditText) findViewById(R.id.blueTeam2)).getText() + "");
            redAllianceScore = Integer.parseInt(((EditText) findViewById(R.id.redAllianceScore)).getText() + "");
            blueAllianceScore = Integer.parseInt(((EditText) findViewById(R.id.blueAllianceScore)).getText() + "");
        } catch (NumberFormatException e) {
            allGood = false;
            redAlliance1 = 0;
            blueAlliance1 = 0;
            redAlliance2 = 0;
            blueAlliance2 = 0;
            blueAllianceScore = 0;
            redAllianceScore = 0;
        }
        if(allGood) {
            if(findTeamByNumber(redAlliance1)) {
                Team team = getTeamByNumber(redAlliance1);
                team.addMatchScore(redAllianceScore);
            } else {
                Team team = new Team(redAllianceScore, redAlliance1);
                teams.add(team);
            }

            if(findTeamByNumber(redAlliance2)) {
                Team team = getTeamByNumber(redAlliance2);
                team.addMatchScore(redAllianceScore);
            } else {
                Team team = new Team(redAllianceScore, redAlliance2);
                teams.add(team);
            }

            if(findTeamByNumber(blueAlliance1)) {
                Team team = getTeamByNumber(blueAlliance1);
                team.addMatchScore(redAllianceScore);
            } else {
                Team team = new Team(blueAllianceScore, blueAlliance1);
                teams.add(team);
            }

            if(findTeamByNumber(blueAlliance2)) {
                Team team = getTeamByNumber(blueAlliance2);
                team.addMatchScore(redAllianceScore);
            } else {
                Team team = new Team(blueAllianceScore, blueAlliance2);
                teams.add(team);
            }

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


    public Team findBestTeamByPit() {
        int currentHigh = 0;
        boolean done = false;
        Team returnable = new Team(0, 0);
        for (int i = 0; i < teams.size(); i++) {
            if (teams.get(i).getPitScore() > currentHigh) {
                returnable.setPitScore(teams.get(i).getPitScore());
                returnable.teamNumber = teams.get(i).teamNumber;
                currentHigh = (int) teams.get(i).getPitScore();
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