package com.example.arib.ftcscoring;

import java.util.ArrayList;

/**
 * Created by dhuka_844963 on 1/13/2016.
 */
public class Team {
    double MMR;
    int[] matchScores;
    double pitScore;
    int teamNumber;
    int id;

    public Team() {
        id = 0;
        MMR = 0;
        matchScores = new int[5];
        pitScore = 0.0;
        teamNumber = 0;
    }
    public Team(int score, int teamNumber) {
        matchScores = new int[5];
        matchScores[0] = score;
        this.teamNumber = teamNumber;
    }
    public Team(double score, int teamNumber) {
        matchScores = new int[5];
        pitScore = score;
        this.teamNumber = teamNumber;
    }

    public double calculateMMR() {
        int average = 0;
        boolean okSoFar = true;
        if(matchScores.length > 0) {
            for (int i : matchScores) {
                average += matchScores[i];
            }
            average = average / matchScores.length;
        } else {
            okSoFar = false;
        }
        int minimumScore = Integer.MAX_VALUE;
        int maximumScore = Integer.MIN_VALUE;
        if(okSoFar && matchScores.length > 2) {
            for(int i : matchScores) {
                if(matchScores[i] < minimumScore) {
                    minimumScore = matchScores[i];
                }
                if(matchScores[i] > maximumScore) {
                    maximumScore = matchScores[i];
                }
            }
        }

        return 0.0; //TODO: CHANGE THIS LATER

    } //TODO: STILL NEEDS COMPLETION

    public boolean addMatchScore(int score) {
        int spot = matchScores.length;
        if(spot == 5) {
            return false;
        }
        matchScores[spot] = score;
        return true;
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

    public int[] getMatchScores() {
        return matchScores;
    }

    public void setMatchScores(int[] matchScores) {
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
}