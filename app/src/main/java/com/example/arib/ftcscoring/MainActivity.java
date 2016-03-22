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

    //defining static variables to use for intents
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

    //static log tag variable for useful logging
    public final static String LOG_TAG = MainActivity.class.getSimpleName();

    //array list of teams
    static ArrayList<Team> teams = new ArrayList<>();

    //contect of the activity
    protected static Context mainContext;


    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */


    //What the activity should do when it is started
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set the screen to the main view
        setContentView(R.layout.activity_main);
        //set the context
        mainContext = this;
        //create the tabview
        TabHost tabHost = (TabHost) findViewById(R.id.tabHost);
        tabHost.setup();

        //Adding a tab
        TabHost.TabSpec tabSpec = tabHost.newTabSpec("PitScouting");
        tabHost.addTab(tabHost.newTabSpec("PitScouting").setIndicator("Pit").setContent(R.id.PitScouting));

        //Adding a tab
        tabSpec = tabHost.newTabSpec("MatchScouting");
        tabHost.addTab(tabHost.newTabSpec("MatchScouting").setIndicator("Match").setContent(R.id.MatchScouting));

        //creating the database
        DatabaseHandler db = new DatabaseHandler(this);
        //filling the array list with teams from the database
        teams = (ArrayList) db.getAllTeams();


    }

    //restores the action bar if it disappears
    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
    }

    //creates the option menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //handles what to do when a item is selected
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        // retrieves the id
        int id = item.getItemId();

        //when the get best pit button is pressed
        if (id == R.id.action_bestpit) {
            //create the intent to start screens
            Intent intent = new Intent(this, RanksActivity.class);
            //gets the top 7 teams by pit
            ArrayList<Team> best = findBestTeamByPit();

            //creates variables to store the scores
            String sending;
            String sending2;
            String sending3;
            String sending4;
            String sending5;
            String sending6;
            String sending7;

            //checks to see if the team exists and puts their info with the intent
            if(0 <= best.size() - 1) {
                sending = ((int) best.get(0).getPitScore()) + "";
                intent.putExtra(TEAM1, best.get(0).getTeamNumber() + "");
                intent.putExtra(SCORE1, sending);
            }
            //checks next team and adds their info
            if(1 <= best.size() - 1) {
                sending2 = ((int) best.get(1).getPitScore()) + "";
                intent.putExtra(TEAM2, best.get(1).getTeamNumber() + "");
                intent.putExtra(SCORE2, sending2);
            }
            //checks next team and adds their info
            if(2 <= best.size() - 1) {
                sending3 = ((int) best.get(2).getPitScore()) + "";
                intent.putExtra(TEAM3, best.get(2).getTeamNumber() + "");
                intent.putExtra(SCORE3, sending3);
            }
            //checks next team and adds their info
            if(3 <= best.size() - 1) {
                sending4 = ((int) best.get(3).getPitScore()) + "";
                intent.putExtra(TEAM4, best.get(3).getTeamNumber() + "");
                intent.putExtra(SCORE4, sending4);
            }
            //checks next team and adds their info
            if(4 <= best.size() - 1) {
                sending5 = ((int) best.get(4).getPitScore()) + "";
                intent.putExtra(TEAM5, best.get(4).getTeamNumber() + "");
                intent.putExtra(SCORE5, sending5);
            }
            //checks next team and adds their info
            if(5 <= best.size() - 1) {
                sending6 = ((int) best.get(5).getPitScore()) + "";
                intent.putExtra(TEAM6, best.get(5).getTeamNumber() + "");
                intent.putExtra(SCORE6, sending6);
            }
            //checks next team and adds their info
            if(6 <= best.size() - 1) {
                sending7 = ((int) best.get(6).getPitScore()) + "";
                intent.putExtra(TEAM7, best.get(6).getTeamNumber() + "");
                intent.putExtra(SCORE7, sending7);
            }

            //starts the activity
            startActivity(intent);
            return true;
        }

        //if the help button is pressed
        if(id == R.id.action_help) {
            //create the intent to navigate screens
            Intent intent = new Intent(this, HelpActivity.class);
            //start the activity
            startActivity(intent);
            return true;
        }
        //if best match is pressed
        if(id == R.id.action_bestmatch) {
            //create the intent to navigate screens
            Intent intent = new Intent(this, RanksActivity.class);

            //create the array list with the best 7 teams by match
            ArrayList<Team> best = findBestTeamsByMatch();

            //create the variables to hold the scores
            String sending;
            String sending2;
            String sending3;
            String sending4;
            String sending5;
            String sending6;
            String sending7;
            //checks to see if the team exists and puts their info with the intent
            if(0 <= best.size() - 1) {
                sending = ((int) best.get(0).getMMR()) + "";
                intent.putExtra(TEAM1, best.get(0).getTeamNumber() + "");
                intent.putExtra(SCORE1, sending);
            }

            //checks to see if the team exists and puts their info with the intent
            if(1 <= best.size() - 1) {
                sending2 = ((int) best.get(1).getMMR()) + "";
                intent.putExtra(TEAM2, best.get(1).getTeamNumber() + "");
                intent.putExtra(SCORE2, sending2);
            }

            //checks to see if the team exists and puts their info with the intent
            if(2 <= best.size() - 1) {
                sending3 = ((int) best.get(2).getMMR()) + "";
                intent.putExtra(TEAM3, best.get(2).getTeamNumber() + "");
                intent.putExtra(SCORE3, sending3);
            }

            //checks to see if the team exists and puts their info with the intent
            if(3 <= best.size() - 1) {
                sending4 = ((int) best.get(3).getMMR()) + "";
                intent.putExtra(TEAM4, best.get(3).getTeamNumber() + "");
                intent.putExtra(SCORE4, sending4);
            }

            //checks to see if the team exists and puts their info with the intent
            if(4 <= best.size() - 1) {
                sending5 = ((int) best.get(4).getMMR()) + "";
                intent.putExtra(TEAM5, best.get(4).getTeamNumber() + "");
                intent.putExtra(SCORE5, sending5);
            }

            //checks to see if the team exists and puts their info with the intent
            if(5 <= best.size() - 1) {
                sending6 = ((int) best.get(5).getMMR()) + "";
                intent.putExtra(TEAM6, best.get(5).getTeamNumber() + "");
                intent.putExtra(SCORE6, sending6);
            }

            //checks to see if the team exists and puts their info with the intent
            if(6 <= best.size() - 1) {
                sending7 = ((int) best.get(6).getMMR()) + "";
                intent.putExtra(TEAM7, best.get(6).getTeamNumber() + "");
                intent.putExtra(SCORE7, sending7);
            }

            //start the activity
            startActivity(intent);
            return true;
        }

        //if the reset button is pressed
        if(id == R.id.action_reset) {
            reset();
        }

        //if the view teams button is pressed.
        if(id == R.id.action_view) {
            //navigate to the list screen
            startActivity(new Intent(this, TeamListActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

    //Clears all the fields
    public void clearFields(View view) {
        //create all the edit texts that can be edited
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

        //set all the texts to blank
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

    //Adds a pit score to a team
    public void addPitScore(View view) {
        //variables to hold team values
        double autonomousScore;
        double autonomousConsistency;
        double teleOpScore;
        double teleOpConsistency;
        double endGameScore;
        double endGameConsistency;
        int teamNumber;
        String teamName;
        int structure;
        double score = 0.0;

        //creates the database handler to add the team to the database
        DatabaseHandler db = new DatabaseHandler(this);
        boolean allGood = true;

        //creates the spinner and fills it
        Spinner structure1 = (Spinner) findViewById(R.id.structure);
        String structureSeleceted = structure1.getSelectedItem().toString();

        //if statement to handle what to do with the selected spinner value
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


        //try to ge the scores from the edit text fields and catch any errors
        try {
            autonomousScore = Integer.parseInt(((EditText) findViewById(R.id.autoScore)).getText() + "");
            autonomousConsistency = Integer.parseInt(((EditText) findViewById(R.id.autoConsist)).getText() + "");
            teleOpScore = Integer.parseInt(((EditText) findViewById(R.id.teleOpScore)).getText() + "");
            teleOpConsistency = Integer.parseInt(((EditText) findViewById(R.id.teleopConsist)).getText() + "");
            endGameScore = Integer.parseInt(((EditText) findViewById(R.id.endGameScore)).getText() + "");
            endGameConsistency = Integer.parseInt(((EditText) findViewById(R.id.endgameConsist)).getText() + "");
            teamNumber = Integer.parseInt(((EditText) findViewById(R.id.teamNumber)).getText() + "");
        } catch (NumberFormatException e){
            //Display that an error has occured
            Context context = getApplicationContext();
            CharSequence text = "Error: Incorrect Input";
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

            //set boolean to false to not proceed
            allGood = false;

            //set all the values to 0
            autonomousConsistency = 0;
            autonomousScore = 0;
            teleOpScore = 0;
            teleOpConsistency = 0;
            endGameScore = 0;
            endGameConsistency = 0;
            teamNumber = 0;

        }

        //get the values for notes and the team name these dont have to be exception handled because
        //they are strings
        String notes = ((EditText) findViewById(R.id.notes)).getText() + "";
        teamName = ((EditText) findViewById(R.id.teamName)).getText() + "";

        //log the teamName for debugging purposes
        Log.e(LOG_TAG, teamName);

        //if the team name is left blank display and error and do not proceed
        if((teamName.equals(""))) {
            allGood = false;
            Context context = getApplicationContext();
            CharSequence text = "Error: input team name";
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

        //if values were accepted begin calculation
        if(allGood) {

            //Pit score calculation system Score impacted by percentage of accuracy
            double score1 = autonomousScore + (autonomousConsistency / 10);
            double score2 = teleOpScore + (teleOpConsistency / 10);
            double score3 = endGameScore + (endGameConsistency / 10);
            double score4 = structure;
            score = score1 + score2 + score3 + score4;

            //create the formatter to have a nice looking value
            DecimalFormat df = new DecimalFormat("###.###");
            double finalScore;

            //boolean to check if the value was calculated correctly
            boolean finished = true;

            //try to parse format the number as a string then parse it as a double
            try {
                finalScore = Double.parseDouble(df.format(score));
            } catch (NumberFormatException e) {
                //Display the error
                Context context = getApplicationContext();
                CharSequence text = "Error incorrect format";
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

                //set score to 0
                finalScore = 0;

                //set finished to false to stop proceeding
                finished = false;
            }

            //if value was processed correctly, advance
            if(finished) {

                //create the team
                Team t;

                //if the team (checked by number) already exists just edit its values
                if (findTeamByNumber(teamNumber)) {
                    t = getTeamByNumber(teamNumber);
                    t.setPitScore(finalScore);
                    t.setInfo(notes);
                    t.setTeamName(teamName);
                    teams.add(t);
                } else {    //if the team does not already exist create it as a new team
                    t = new Team(finalScore, teamNumber, notes, teamName);
                    teams.add(t);
                }

                //Display the Pitscore Briefly
                Context context = getApplicationContext();
                CharSequence text = t.getTeamNumber() + ", " + t.getPitScore();
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

                //add the team to the database
                db.addTeam(t);
            }

            //clear the fields
            clearFields(view);
        }

    }

    //add a match score
    public void addMatchScore(View view) {
        DatabaseHandler db = new DatabaseHandler(this);
        //create values to use as calculations
        int redAlliance1;
        int redAlliance2;
        int blueAlliance1;
        int blueAlliance2;
        int redAllianceScore;
        int blueAllianceScore;

        //create boolean variable to check if everything is going correctly
        boolean allGood = true;

        //try to set all the values and catch an exception if one is not correct
        try {
            redAlliance1 = Integer.parseInt(((EditText) findViewById(R.id.redteam1)).getText() + "");
            redAlliance2 = Integer.parseInt(((EditText) findViewById(R.id.redTeam2)).getText() + "");
            blueAlliance1 = Integer.parseInt(((EditText) findViewById(R.id.blueTeam1)).getText() + "");
            blueAlliance2 = Integer.parseInt(((EditText) findViewById(R.id.blueTeam2)).getText() + "");
            redAllianceScore = Integer.parseInt(((EditText) findViewById(R.id.redAllianceScore)).getText() + "");
            blueAllianceScore = Integer.parseInt(((EditText) findViewById(R.id.blueAllianceScore)).getText() + "");
        } catch (NumberFormatException e) {

            //set all the values to 0 and do not proceed
            allGood = false;
            redAlliance1 = 0;
            blueAlliance1 = 0;
            redAlliance2 = 0;
            blueAlliance2 = 0;
            blueAllianceScore = 0;
            redAllianceScore = 0;

            //Display that an error has occured
            Context context = getApplicationContext();
            CharSequence text = "Incorrect Input";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

        }

        //if the values were accepted proceed
        if(allGood) {

            //if the team already exists add the match score to its personal array list
            if(findTeamByNumber(redAlliance1)) {
                Team team = getTeamByNumber(redAlliance1);
                //if the score was added properly (error occurs when team has already played 5 matches)
                boolean good = team.addMatchScore(redAllianceScore);
                if(!good) {

                    //if team has already played its matches display an error
                    Context context = getApplicationContext();
                    CharSequence text = "Team has already played 5 matches";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }

                //calculate the teams MMR
                team.calculateMMR();

                //delete the current team from the database
                db.deleteTeam(team);

                //add the new updated team to the database
                db.addTeam(team);
            } else {
                //if the team does not already exist create it as a new team
                Team team = new Team(redAllianceScore, redAlliance1);

                //add the team to the array list
                teams.add(team);

                //add the team to the database
                db.addTeam(team);
            }

            //repeat the above steps for the next red team
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

            //repeat the previous steps for the blue team
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

            //repeat the previous steps for the other blue team
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

            //clear the fields once everything has been added
            clearFields(view);
        }
    }

    //handles an alert dialog to prevent accidental deleting
    private AlertDialog AskOption() {
        //creating a database to edit
        final DatabaseHandler db = new DatabaseHandler(this);

        //creating the dialog
        AlertDialog restart = new AlertDialog.Builder(this)
                .setTitle("Restart")                                                    //setting the title
                .setMessage("Are you sure you want to wipe current competition data?")  //setting the message
                .setPositiveButton("Restart", new DialogInterface.OnClickListener() {   //adding the yes button
                    @Override
                    public void onClick(DialogInterface dialog, int which) {            //what to do when yes is clicked
                        Context context = getApplicationContext();
                        CharSequence text = "Resetting... Please Wait";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();

                        //goes throught the database first then the array list and deletes the teams 1 by 1
                        while (teams.size() > 0) {
                            db.deleteTeam(teams.get(0));
                            teams.remove(0);
                        }
                        //removes the dialog
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {    //if no is clicked do nothing
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //removes the dialog
                        dialog.dismiss();
                    }
                })
                .create();

        return restart;

    }

    //asks if you want to reset the database
    public void reset() {
        //creates the dialog
        AlertDialog ask = AskOption();
        //show the dialog
        ask.show();
    }


    //finds the best teams by pitscore
    public ArrayList<Team> findBestTeamByPit() {
        //creating the variables to hold the highest scores
        double highest = 0;
        double highest2 = 0;
        double highest3 = 0;
        double highest4 = 0;
        double highest5 = 0;
        double highest6 = 0;
        double highest7 = 0;

        //creating the seven teams that will be returned
        Team returnable = new Team();
        Team returnable2 = new Team();
        Team returnable3 = new Team();
        Team returnable4 = new Team();
        Team returnable5 = new Team();
        Team returnable6 = new Team();
        Team returnable7 = new Team();

        //creats the array list that will be returned
        ArrayList<Team> toReturn = new ArrayList<>();

        //iterates through the ArrayList to find the highest score and sets the first team
        for(int i = 0; i < teams.size(); i++) {
            if(teams.get(i).getPitScore() > highest) {
                returnable.setPitScore(teams.get(i).getPitScore());
                returnable.setTeamNumber(teams.get(i).getTeamNumber());
                highest = teams.get(i).getPitScore();
            }
        }
        //iterates through the ArrayList to find the next highest score
        for(int i = 0; i < teams.size(); i++) {
            if(teams.get(i).getPitScore() > highest2 && teams.get(i).getPitScore() < highest) {
                returnable2.setPitScore(teams.get(i).getPitScore());
                returnable2.setTeamNumber(teams.get(i).getTeamNumber());
                highest2 = teams.get(i).getPitScore();
            }
        }
        //iterates through the ArrayList to find the next highest score
        for(int i = 0; i < teams.size(); i++) {
            if(teams.get(i).getPitScore() > highest3 && teams.get(i).getPitScore() < highest2) {
                returnable3.setPitScore(teams.get(i).getPitScore());
                returnable3.setTeamNumber(teams.get(i).getTeamNumber());
                highest3 = teams.get(i).getPitScore();
            }
        }
        //iterates through the ArrayList to find the next highest score
        for(int i = 0; i < teams.size(); i++) {
            if(teams.get(i).getPitScore() > highest4 && teams.get(i).getPitScore() < highest3) {
                returnable4.setPitScore(teams.get(i).getPitScore());
                returnable4.setTeamNumber(teams.get(i).getTeamNumber());
                highest4 = teams.get(i).getPitScore();
            }
        }
        if(teams.size() > 6) {

            //iterates through the ArrayList to find the next highest score
            for (int i = 0; i < teams.size(); i++) {
                if (teams.get(i).getPitScore() > highest5 && teams.get(i).getPitScore() < highest4) {
                    returnable5.setPitScore(teams.get(i).getPitScore());
                    returnable5.setTeamNumber(teams.get(i).getTeamNumber());
                    highest5 = teams.get(i).getPitScore();
                }
            }
            //iterates through the ArrayList to find the next highest score
            for (int i = 0; i < teams.size(); i++) {
                if (teams.get(i).getPitScore() > highest6 && teams.get(i).getPitScore() < highest5) {
                    returnable6.setPitScore(teams.get(i).getPitScore());
                    returnable6.setTeamNumber(teams.get(i).getTeamNumber());
                    highest6 = teams.get(i).getPitScore();
                }
            }
            //iterates through the ArrayList to find the next highest score
            for (int i = 0; i < teams.size(); i++) {
                if (teams.get(i).getPitScore() > highest7 && teams.get(i).getPitScore() < highest6) {
                    returnable7.setPitScore(teams.get(i).getPitScore());
                    returnable7.setTeamNumber(teams.get(i).getTeamNumber());
                    highest7 = teams.get(i).getPitScore();
                }
            }
        }

        //adds all the teams to the ArrayList
        toReturn.add(returnable);
        toReturn.add(returnable2);
        toReturn.add(returnable3);
        toReturn.add(returnable4);
        toReturn.add(returnable5);
        toReturn.add(returnable6);
        toReturn.add(returnable7);

        //returns the ArrayList
        return toReturn;
    }

    //finds the best team by the MMR system
    private ArrayList<Team> findBestTeamsByMatch() {
        //sets the variables to hold the highest scores
        double highest = 0;
        double highest2 = 0;
        double highest3 = 0;
        double highest4 = 0;
        double highest5 = 0;
        double highest6 = 0;
        double highest7 = 0;

        //creates the seven teams
        Team returnable = new Team();
        Team returnable2 = new Team();
        Team returnable3 = new Team();
        Team returnable4 = new Team();
        Team returnable5 = new Team();
        Team returnable6 = new Team();
        Team returnable7 = new Team();

        //creates the ArrayList that will be returned
        ArrayList<Team> toReturn = new ArrayList<>();

        //finds the highest score and sets the first team
        for(int i = 0; i < teams.size(); i++) {
            if(teams.get(i).getMMR() > highest) {
                returnable.setMMR(teams.get(i).getMMR());
                returnable.setTeamNumber(teams.get(i).getTeamNumber());
                highest = teams.get(i).getMMR();
            }
        }
        //iterates through the ArrayList to find the next highest score
        for(int i = 0; i < teams.size(); i++) {
            if(teams.get(i).getMMR() > highest2 && teams.get(i).getMMR() < highest) {
                returnable2.setMMR(teams.get(i).getMMR());
                returnable2.setTeamNumber(teams.get(i).getTeamNumber());
                highest2 = teams.get(i).getMMR();
            }
        }
        //iterates through the ArrayList to find the next highest score
        for(int i = 0; i < teams.size(); i++) {
            if(teams.get(i).getMMR() > highest3 && teams.get(i).getMMR() < highest2) {
                returnable3.setMMR(teams.get(i).getMMR());
                returnable3.setTeamNumber(teams.get(i).getTeamNumber());
                highest3 = teams.get(i).getMMR();
            }
        }
        //iterates through the ArrayList to find the next highest score
        for(int i = 0; i < teams.size(); i++) {
            if(teams.get(i).getMMR() > highest4 && teams.get(i).getMMR() < highest3) {
                returnable4.setMMR(teams.get(i).getMMR());
                returnable4.setTeamNumber(teams.get(i).getTeamNumber());
                highest4 = teams.get(i).getMMR();
            }
        }
        //iterates through the ArrayList to find the next highest score
        if(teams.size() > 6) {
            for (int i = 0; i < teams.size(); i++) {
                if (teams.get(i).getMMR() > highest5 && teams.get(i).getMMR() < highest4) {
                    returnable5.setMMR(teams.get(i).getMMR());
                    returnable5.setTeamNumber(teams.get(i).getTeamNumber());
                    highest5 = teams.get(i).getMMR();
                }
            }
            //iterates through the ArrayList to find the next highest score
            for (int i = 0; i < teams.size(); i++) {
                if (teams.get(i).getMMR() > highest6 && teams.get(i).getMMR() < highest5) {
                    returnable6.setMMR(teams.get(i).getMMR());
                    returnable6.setTeamNumber(teams.get(i).getTeamNumber());
                    highest6 = teams.get(i).getMMR();
                }
            }
            //iterates through the ArrayList to find the next highest score
            for (int i = 0; i < teams.size(); i++) {
                if (teams.get(i).getMMR() > highest7 && teams.get(i).getMMR() < highest6) {
                    returnable7.setMMR(teams.get(i).getMMR());
                    returnable7.setTeamNumber(teams.get(i).getTeamNumber());
                    highest7 = teams.get(i).getMMR();
                }
            }
        }
        //adds the teams to the ArrayList
        toReturn.add(returnable);
        toReturn.add(returnable2);
        toReturn.add(returnable3);
        toReturn.add(returnable4);
        toReturn.add(returnable5);
        toReturn.add(returnable6);
        toReturn.add(returnable7);

        //returns the ArrayList
        return toReturn;
    }

    //checks to see if the team with the given number exists
    public boolean findTeamByNumber(int teamNumber) {
        //iterates through the ArrayList
        for (Team t : teams) {
            //checks to see if the current team has the given team number return true if yes
            if (t.getTeamNumber() == teamNumber) {
                return true;
            }
        }
        //returns false if the team was not found
        return false;
    }

    //returns the Team with the given team number
    public static Team getTeamByNumber(int teamNumber) {
        //iterate through the ArrayList of teams
        for (Team t : teams) {
            //finds the team with the given team number and returns it
            if (t.getTeamNumber() == teamNumber) {
                return t;
            }
        }
        //returns an empty team if the team was not found (this should never occur)
        return new Team(0, 0);
    }

    //static method to get the next available id to be assigned
    public static int getNextAvailId() {
        //the lowest id that can be given
        int id = 0;
        //iterates through the array list to see if a team already has the id
        for(int i = 0; i < teams.size(); i++) {
            if(teams.get(i).getId() == id) {
                id++;
                i = 0;
            }
        }
        //returns the lowest unused ID
        return id;
    }
}