package com.example.arib.ftcscoring;

/**
 * Created by dhuka_844963 on 1/13/2016.
 */
public class Team {
    double score;
    int teamNumber;
    public Team(double score, int teamNumber) {
        this.score = score;
        this.teamNumber = teamNumber;
    }
    public void addNewScore(double newScore) {
        double temp = newScore;
        score = (score + temp) / 2;
    }
    public int getTeamNumber() {
        return teamNumber;
    }
}
