package com.example.arib.ftcscoring;

import android.app.Activity;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TabHost;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    public final static String TEAM1 = "com.example.dhuka_844963.team1";
    public final static String SCORE1 = "com.example.dhuka_844963.score1";
    public final static String TEAM2 = "com.example.arib.team2";
    public final static String SCORE2 = "com.example.arib.score2";
    public final static String TEAM3 = "com.example.arib.team3";
    public final static String SCORE3 = "com.example.arib.score3";
    public final static String TEAM4 = "com.example.arib.team4";
    public final static String SCORE4 = "com.example.arib.score4";
    public final static String TEAM5 = "com.example.arib.team5";
    public final static String SCORE5 = "com.example.arib.score5";
    public final static String TEAM6 = "com.example.arib.team6";
    public final static String SCORE6 = "com.example.arib.score6";
    public final static String TEAM7 = "com.example.arib.team7";
    public final static String SCORE7 = "com.example.arib.score7";
    public final static String TEAM8 = "com.example.arib.team8";
    public final static String SCORE8 = "com.example.arib.score8";
    public final static String LOG_TAG = MainActivity.class.getSimpleName();
    static ArrayList<Team> teams = new ArrayList<>();
    protected static Context mainContext;
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
        mainContext = this;
        TabHost tabHost = (TabHost) findViewById(R.id.tabHost);
        tabHost.setup();

        TabHost.TabSpec tabSpec = tabHost.newTabSpec("PitScouting");
        tabHost.addTab(tabHost.newTabSpec("PitScouting").setIndicator("Pit").setContent(R.id.PitScouting));

        tabSpec = tabHost.newTabSpec("MatchScouting");
        tabHost.addTab(tabHost.newTabSpec("MatchScouting").setIndicator("Match").setContent(R.id.MatchScouting));
        DatabaseHandler db = new DatabaseHandler(this);
        teams = (ArrayList) db.getAllTeams();


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
            ArrayList<Team> best = findBestTeamByPit();
            String sending = ((int) best.get(0).getPitScore()) + "";
            String sending2 = ((int) best.get(1).getPitScore()) + "";
            String sending3 = ((int) best.get(2).getPitScore()) + "";
            String sending4 = ((int) best.get(3).getPitScore()) + "";
            String sending5 = ((int) best.get(4).getPitScore()) + "";
            String sending6 = ((int) best.get(5).getPitScore()) + "";
            String sending7 = ((int) best.get(6).getPitScore()) + "";
            intent.putExtra(TEAM1, best.get(0).getTeamNumber() + "");
            intent.putExtra(SCORE1, sending);
            intent.putExtra(TEAM2, best.get(1).getTeamNumber() + "");
            intent.putExtra(SCORE2, sending2);
            intent.putExtra(TEAM3, best.get(2).getTeamNumber() + "");
            intent.putExtra(SCORE3, sending3);
            intent.putExtra(TEAM4, best.get(3).getTeamNumber() + "");
            intent.putExtra(SCORE4, sending4);
            intent.putExtra(TEAM5, best.get(4).getTeamNumber() + "");
            intent.putExtra(SCORE5, sending5);
            intent.putExtra(TEAM6, best.get(5).getTeamNumber() + "");
            intent.putExtra(SCORE6, sending6);
            intent.putExtra(TEAM7, best.get(6).getTeamNumber() + "");
            intent.putExtra(SCORE7, sending7);


            startActivity(intent);
            return true;
        }
        if(id == R.id.action_help) {
            Intent intent = new Intent(this, HelpActivity.class);
            startActivity(intent);
            return true;
        }
        if(id == R.id.action_bestmatch) {
            Intent intent = new Intent(this, RanksActivity.class);
            ArrayList<Team> best = findBestTeamsByMatch();
            DecimalFormat df = new DecimalFormat("###.###");
            String sending = df.format(best.get(0).getMMR());
            String sending2 = df.format(best.get(1).getMMR());
            String sending3 = df.format(best.get(2).getMMR());
            String sending4 = df.format(best.get(3).getMMR());
            String sending5 = df.format(best.get(4).getMMR());
            String sending6 = df.format(best.get(5).getMMR());
            String sending7 = df.format(best.get(6).getMMR());
            intent.putExtra(TEAM1, best.get(0).getTeamNumber() + "");
            intent.putExtra(SCORE1, sending);
            intent.putExtra(TEAM2, best.get(1).getTeamNumber() + "");
            intent.putExtra(SCORE2, sending2);
            intent.putExtra(TEAM3, best.get(2).getTeamNumber() + "");
            intent.putExtra(SCORE3, sending3);
            intent.putExtra(TEAM4, best.get(3).getTeamNumber() + "");
            intent.putExtra(SCORE4, sending4);
            intent.putExtra(TEAM5, best.get(4).getTeamNumber() + "");
            intent.putExtra(SCORE5, sending5);
            intent.putExtra(TEAM6, best.get(5).getTeamNumber() + "");
            intent.putExtra(SCORE6, sending6);
            intent.putExtra(TEAM7, best.get(6).getTeamNumber() + "");
            intent.putExtra(SCORE7, sending7);
            startActivity(intent);
            return true;
        }
        if(id == R.id.action_reset) {
            reset();
        }

        if(id == R.id.action_view) {
            startActivity(new Intent(this, TeamListActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

    public void clearFields(View view) {
        EditText autonomousScore = (EditText) findViewById(R.id.autoScore);
        EditText autonomousConsistency = (EditText) findViewById(R.id.autoConsist);
        EditText teleOpScore = (EditText) findViewById(R.id.teleOpScore);
        EditText teleOpConsistency = (EditText) findViewById(R.id.teleopConsist);
        EditText endGameScore = (EditText) findViewById(R.id.endGameScore);
        EditText endGameConsistency = (EditText) findViewById(R.id.endgameConsist);
        EditText redTeam1 = (EditText) findViewById(R.id.redteam1);
        EditText redTeam2 = (EditText) findViewById(R.id.redTeam2);
        EditText redScore = (EditText) findViewById(R.id.redAllianceScore);
        EditText blueTeam1 = (EditText) findViewById(R.id.blueTeam1);
        EditText blueTeam2 = (EditText) findViewById(R.id.blueTeam2);
        EditText blueScore = (EditText) findViewById(R.id.blueAllianceScore);
        EditText teamNumber = (EditText) findViewById(R.id.teamNumber);
        EditText teamName = (EditText) findViewById(R.id.teamName);
        EditText notes = (EditText) findViewById(R.id.notes);
        autonomousScore.setText("");
        teamName.setText("");
        notes.setText("");
        autonomousConsistency.setText("");
        teleOpScore.setText("");
        teleOpConsistency.setText("");
        endGameScore.setText("");
        endGameConsistency.setText("");
        teamNumber.setText("");
        redTeam1.setText("");
        redTeam2.setText("");
        redScore.setText("");
        blueTeam1.setText("");
        blueTeam2.setText("");
        blueScore.setText("");


    }

    public void addPitScore(View view) {
        double autonomousScore;
        double autonomousConsistency;
        double teleOpScore;
        double teleOpConsistency;
        double endGameScore;
        double endGameConsistency;
        int teamNumber;
        String teamName;
        DatabaseHandler db = new DatabaseHandler(this);
        boolean allGood = true;
        Spinner structure1 = (Spinner) findViewById(R.id.structure);
        String structureSeleceted = structure1.getSelectedItem().toString();
        int structure;
        if(structureSeleceted.equals("Weak(Needs constant repair)")) {
            structure = 3;
        }
        else if(structureSeleceted.equals("Sturdy(Few repairs needed)")) {
            structure = 7;
        }
        else if(structureSeleceted.equals("Strong(Very rarely needs repairs)")) {
            structure = 9;
        }
        else {
            structure = 0;
        }



        try {
            autonomousScore = Integer.parseInt(((EditText) findViewById(R.id.autoScore)).getText() + "");
            autonomousConsistency = Integer.parseInt(((EditText) findViewById(R.id.autoConsist)).getText() + "");
            teleOpScore = Integer.parseInt(((EditText) findViewById(R.id.teleOpScore)).getText() + "");
            teleOpConsistency = Integer.parseInt(((EditText) findViewById(R.id.teleopConsist)).getText() + "");
            endGameScore = Integer.parseInt(((EditText) findViewById(R.id.endGameScore)).getText() + "");
            endGameConsistency = Integer.parseInt(((EditText) findViewById(R.id.endgameConsist)).getText() + "");
            teamNumber = Integer.parseInt(((EditText) findViewById(R.id.teamNumber)).getText() + "");
        } catch (NumberFormatException e){
            Context context = getApplicationContext();
            CharSequence text = "Error: Incorrect Input";
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            allGood = false;
            autonomousConsistency = 0;
            autonomousScore = 0;
            teleOpScore = 0;
            teleOpConsistency = 0;
            endGameScore = 0;
            endGameConsistency = 0;
            teamNumber = 0;

        }
        double score = 0.0;
        String notes = ((EditText) findViewById(R.id.notes)).getText() + "";
        teamName = ((EditText) findViewById(R.id.teamName)).getText() + "";
        Log.e(LOG_TAG, teamName);
        if((teamName.equals(""))) {
            allGood = false;
            Context context = getApplicationContext();
            CharSequence text = "Error: input team name";
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        if(allGood) {
            double score1 = autonomousScore + (autonomousConsistency / 10);
            double score2 = teleOpScore + (teleOpConsistency / 10);
            double score3 = endGameScore + (endGameConsistency / 10);
            double score4 = structure;
            score = score1 + score2 + score3 + score4;
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
                    t.setInfo(notes);
                    t.setTeamName(teamName);
                    teams.add(t);
                } else {
                    t = new Team(finalScore, teamNumber, notes, teamName);
                    teams.add(t);
                }
                Context context = getApplicationContext();
                CharSequence text = t.getTeamNumber() + ", " + t.getPitScore();
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

                db.addTeam(t);
            }
            clearFields(view);
        }

    }

    public void addMatchScore(View view) {
        DatabaseHandler db = new DatabaseHandler(this);
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
            Context context = getApplicationContext();
            CharSequence text = "Incorrect Input";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

        }
        if(allGood) {
            if(findTeamByNumber(redAlliance1)) {
                Team team = getTeamByNumber(redAlliance1);
                boolean good = team.addMatchScore(redAllianceScore);
                if(!good) {
                    Context context = getApplicationContext();
                    CharSequence text = "Team has already played 5 matches";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
                team.calculateMMR();
                db.deleteTeam(team);
                db.addTeam(team);
            } else {
                Team team = new Team(redAllianceScore, redAlliance1);
                teams.add(team);
                db.addTeam(team);
            }

            if(findTeamByNumber(redAlliance2)) {
                Team team = getTeamByNumber(redAlliance2);
                boolean good = team.addMatchScore(redAllianceScore);
                if(!good) {
                    Context context = getApplicationContext();
                    CharSequence text = "Team has already played 5 matches";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
                team.calculateMMR();
                db.deleteTeam(team);
                db.addTeam(team);
            } else {
                Team team = new Team(redAllianceScore, redAlliance2);
                teams.add(team);
                team.calculateMMR();
                db.addTeam(team);
            }

            if(findTeamByNumber(blueAlliance1)) {
                Team team = getTeamByNumber(blueAlliance1);
                boolean good = team.addMatchScore(blueAllianceScore);
                if(!good) {
                    Context context = getApplicationContext();
                    CharSequence text = "Team has already played 5 matches";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
                team.calculateMMR();
                db.deleteTeam(team);
                db.addTeam(team);
            } else {
                Team team = new Team(blueAllianceScore, blueAlliance1);
                teams.add(team);
                team.calculateMMR();
                db.addTeam(team);
            }

            if(findTeamByNumber(blueAlliance2)) {
                Team team = getTeamByNumber(blueAlliance2);
                boolean good = team.addMatchScore(blueAllianceScore);
                if(!good) {
                    Context context = getApplicationContext();
                    CharSequence text = "Team has already played 5 matches";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
                team.calculateMMR();
                db.deleteTeam(team);
                db.addTeam(team);
            } else {
                Team team = new Team(blueAllianceScore, blueAlliance2);
                teams.add(team);
                db.addTeam(team);
            }

            clearFields(view);
        }
    }

    private AlertDialog AskOption() {
        final DatabaseHandler db = new DatabaseHandler(this);
        AlertDialog restart = new AlertDialog.Builder(this)
                .setTitle("Restart")
                .setMessage("Are you sure you want to wipe current competition data?")
                .setPositiveButton("Restart", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Context context = getApplicationContext();
                        CharSequence text = "Resetting... Please Wait";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                        while (teams.size() > 0) {
                            db.deleteTeam(teams.get(0));
                            teams.remove(0);
                        }
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

    public void reset() {

        AlertDialog ask = AskOption();
        ask.show();
    }


    public ArrayList<Team> findBestTeamByPit() {
        double highest = 0;
        double highest2 = 0;
        double highest3 = 0;
        double highest4 = 0;
        double highest5 = 0;
        double highest6 = 0;
        double highest7 = 0;
        Team returnable = new Team();
        Team returnable2 = new Team();
        Team returnable3 = new Team();
        Team returnable4 = new Team();
        Team returnable5 = new Team();
        Team returnable6 = new Team();
        Team returnable7 = new Team();
        ArrayList<Team> toReturn = new ArrayList<>();

        for(int i = 0; i < teams.size(); i++) {
            if(teams.get(i).getPitScore() > highest) {
                returnable.setPitScore(teams.get(i).getPitScore());
                returnable.setTeamNumber(teams.get(i).getTeamNumber());
                highest = teams.get(i).getPitScore();
            }
        }
        for(int i = 0; i < teams.size(); i++) {
            if(teams.get(i).getPitScore() > highest2 && teams.get(i).getPitScore() < highest) {
                returnable2.setPitScore(teams.get(i).getPitScore());
                returnable2.setTeamNumber(teams.get(i).getTeamNumber());
                highest2 = teams.get(i).getPitScore();
            }
        }
        for(int i = 0; i < teams.size(); i++) {
            if(teams.get(i).getPitScore() > highest3 && teams.get(i).getPitScore() < highest2) {
                returnable3.setPitScore(teams.get(i).getPitScore());
                returnable3.setTeamNumber(teams.get(i).getTeamNumber());
                highest3 = teams.get(i).getPitScore();
            }
        }
        for(int i = 0; i < teams.size(); i++) {
            if(teams.get(i).getPitScore() > highest4 && teams.get(i).getPitScore() < highest3) {
                returnable4.setPitScore(teams.get(i).getPitScore());
                returnable4.setTeamNumber(teams.get(i).getTeamNumber());
                highest4 = teams.get(i).getPitScore();
            }
        }
        if(teams.size() > 6) {
            for (int i = 0; i < teams.size(); i++) {
                if (teams.get(i).getPitScore() > highest5 && teams.get(i).getPitScore() < highest4) {
                    returnable5.setPitScore(teams.get(i).getPitScore());
                    returnable5.setTeamNumber(teams.get(i).getTeamNumber());
                    highest5 = teams.get(i).getPitScore();
                }
            }
            for (int i = 0; i < teams.size(); i++) {
                if (teams.get(i).getPitScore() > highest6 && teams.get(i).getPitScore() < highest5) {
                    returnable6.setPitScore(teams.get(i).getPitScore());
                    returnable6.setTeamNumber(teams.get(i).getTeamNumber());
                    highest6 = teams.get(i).getPitScore();
                }
            }
            for (int i = 0; i < teams.size(); i++) {
                if (teams.get(i).getPitScore() > highest7 && teams.get(i).getPitScore() < highest6) {
                    returnable7.setPitScore(teams.get(i).getPitScore());
                    returnable7.setTeamNumber(teams.get(i).getTeamNumber());
                    highest7 = teams.get(i).getPitScore();
                }
            }
        }
        toReturn.add(returnable);
        toReturn.add(returnable2);
        toReturn.add(returnable3);
        toReturn.add(returnable4);
        toReturn.add(returnable5);
        toReturn.add(returnable6);
        toReturn.add(returnable7);


        return toReturn;
    }

    private ArrayList<Team> findBestTeamsByMatch() {
        double highest = 0;
        double highest2 = 0;
        double highest3 = 0;
        double highest4 = 0;
        double highest5 = 0;
        double highest6 = 0;
        double highest7 = 0;
        Team returnable = new Team();
        Team returnable2 = new Team();
        Team returnable3 = new Team();
        Team returnable4 = new Team();
        Team returnable5 = new Team();
        Team returnable6 = new Team();
        Team returnable7 = new Team();
        ArrayList<Team> toReturn = new ArrayList<>();

        for(int i = 0; i < teams.size(); i++) {
            if(teams.get(i).getMMR() > highest) {
                returnable.setMMR(teams.get(i).getMMR());
                returnable.setTeamNumber(teams.get(i).getTeamNumber());
                highest = teams.get(i).getMMR();
            }
        }
        for(int i = 0; i < teams.size(); i++) {
            if(teams.get(i).getMMR() > highest2 && teams.get(i).getMMR() < highest) {
                returnable2.setMMR(teams.get(i).getMMR());
                returnable2.setTeamNumber(teams.get(i).getTeamNumber());
                highest2 = teams.get(i).getMMR();
            }
        }
        for(int i = 0; i < teams.size(); i++) {
            if(teams.get(i).getMMR() > highest3 && teams.get(i).getMMR() < highest2) {
                returnable3.setMMR(teams.get(i).getMMR());
                returnable3.setTeamNumber(teams.get(i).getTeamNumber());
                highest3 = teams.get(i).getMMR();
            }
        }
        for(int i = 0; i < teams.size(); i++) {
            if(teams.get(i).getMMR() > highest4 && teams.get(i).getMMR() < highest3) {
                returnable4.setMMR(teams.get(i).getMMR());
                returnable4.setTeamNumber(teams.get(i).getTeamNumber());
                highest4 = teams.get(i).getMMR();
            }
        }
        if(teams.size() > 6) {
            for (int i = 0; i < teams.size(); i++) {
                if (teams.get(i).getMMR() > highest5 && teams.get(i).getMMR() < highest4) {
                    returnable5.setMMR(teams.get(i).getMMR());
                    returnable5.setTeamNumber(teams.get(i).getTeamNumber());
                    highest5 = teams.get(i).getMMR();
                }
            }
            for (int i = 0; i < teams.size(); i++) {
                if (teams.get(i).getMMR() > highest6 && teams.get(i).getMMR() < highest5) {
                    returnable6.setMMR(teams.get(i).getMMR());
                    returnable6.setTeamNumber(teams.get(i).getTeamNumber());
                    highest6 = teams.get(i).getMMR();
                }
            }
            for (int i = 0; i < teams.size(); i++) {
                if (teams.get(i).getMMR() > highest7 && teams.get(i).getMMR() < highest6) {
                    returnable7.setMMR(teams.get(i).getMMR());
                    returnable7.setTeamNumber(teams.get(i).getTeamNumber());
                    highest7 = teams.get(i).getMMR();
                }
            }
        }
        toReturn.add(returnable);
        toReturn.add(returnable2);
        toReturn.add(returnable3);
        toReturn.add(returnable4);
        toReturn.add(returnable5);
        toReturn.add(returnable6);
        toReturn.add(returnable7);


        return toReturn;
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

    public static int getNextAvailId() {
        int id = 0;
        for(int i = 0; i < teams.size(); i++) {
            if(teams.get(i).getId() == id) {
                id++;
                i = 0;
            }
        }
        return id;
    }
}