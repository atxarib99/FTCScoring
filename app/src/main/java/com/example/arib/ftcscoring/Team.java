package com.example.arib.ftcscoring;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by dhuka_844963 on 1/13/2016.
 */
public class Team {
    double MMR;
    //int[] matchScores;
    ArrayList<Integer> matchScores;
    double pitScore;
    int teamNumber;
    int id;
    String notes;

    public Team() {
        id = MainActivity.getNextAvailId();
        MMR = 0;
//        matchScores = new int[5];
        matchScores = new ArrayList<>();

        pitScore = 0.0;
        teamNumber = 0;
        notes = "";
    }
    public Team(int score, int teamNumber) {
//        matchScores = new int[5];
//        matchScores[0] = score;
        matchScores = new ArrayList<>();
        matchScores.add(score);
        this.teamNumber = teamNumber;
        id = MainActivity.getNextAvailId();
        MMR = 0;
        notes = "";
    }
    public Team(double score, int teamNumber, String notes) {
//        matchScores = new int[5];
        matchScores = new ArrayList<>();
        pitScore = score;
        this.teamNumber = teamNumber;
        MMR = 0;
        id = MainActivity.getNextAvailId();
        this.notes = notes;
    }

    public void calculateMMR() {
        int average = 0;
        boolean okSoFar = true;
        int consistency = 0;
        final int MAXIMUM_VALUE = 30;
        if(matchScores.size() > 0) {
            for(int i = 0; i < matchScores.size(); i++) {
                average += matchScores.get(i);
            }
            average = average / matchScores.size();
        } else {
            okSoFar = false;
            Context context = MainActivity.mainContext;
            CharSequence text = "Error Occured";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        int minimumScore = Integer.MAX_VALUE;
        int maximumScore = Integer.MIN_VALUE;
        if(okSoFar && matchScores.size() > 1) {
            for(int i = 0; i < matchScores.size(); i++) {
                if(matchScores.get(i) < minimumScore) {
                    minimumScore = matchScores.get(i);
                }
                if(matchScores.get(i) > maximumScore) {
                    maximumScore = matchScores.get(i);
                }
            }
            consistency = maximumScore - minimumScore;
            consistency = consistency / 10;
            consistency = Math.abs((maximumScore / 2) - consistency);

        }
        else {
            consistency = 0;
            maximumScore = matchScores.get(0);

        }
        if(okSoFar) {
            double scoreToAdd = 0;
            scoreToAdd += average;
            scoreToAdd += maximumScore / 2;
            scoreToAdd += consistency;
            MMR = scoreToAdd;
        }

    }

    public boolean addMatchScore(int score) {
        int spot = 0;
        boolean found = false;
        for(int i = 0; i < matchScores.size(); i++) {
            if(matchScores.get(i) == 0 && !found) {
                spot = i;
                found = true;
            }
        }
        if(spot + 1 == 5) {
            return false;
        }
        else {
            matchScores.add(score);
            return true;
        }
    }

    private int getUsedSize() {
        int spot = 0;
        boolean found = false;
        for(int i = 0; i < matchScores.size(); i++) {
            if(matchScores.get(i) == 0 && !found) {
                spot = i;
                found = true;
            }
        }
        return spot + 1;
    }

    public int getTeamNumber() {
        return teamNumber;
    }

    public double getMMR() {
        return MMR;
    }

    public void setMMR(double MMR) {
        this.MMR = MMR;
    }

    public ArrayList<Integer> getMatchScores() {
        return matchScores;
    }

    public void setMatchScores(ArrayList<Integer> matchScores) {
        this.matchScores = matchScores;
    }

    public double getPitScore() {
        return pitScore;
    }

    public void setPitScore(double pitScore) {
        this.pitScore = pitScore;
    }

    public void setTeamNumber(int teamNumber) {
        this.teamNumber = teamNumber;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setInfo(String message) {
        notes = message;
    }

    public String getInfo() {
        return notes;
    }
}