package com.example.arib.ftcscoring;

/**
 * Created by dhuka_844963 on 1/13/2016.
 */
public class Score {
    int driverAbility;
    int robotStructure;
    int durability;
    int autonomousScore;
    int autonomousConsistency;
    int teleOpScore;
    int teleOpConsistency;
    int endGameScore;
    int endGameConsistency;
    boolean pushBot;
    int alliancePartnerScore;
    int teamNumber;

    public Score(int driverAbility,
            int robotStructure,
            int durability,
            int autonomousScore,
            int autonomousConsistency,
            int teleOpScore,
            int teleOpConsistency,
            int endGameScore,
            int endGameConsistency,
            boolean pushBot,
            int alliancePartnerScore,
            int teamNumber) {
        this.driverAbility = driverAbility;
        this.robotStructure = robotStructure;
        this.durability = durability;
        this.autonomousScore = autonomousScore;
        this.autonomousConsistency = autonomousConsistency;
        this.teleOpScore = teleOpScore;
        this.teleOpConsistency = teleOpConsistency;
        this.endGameScore = endGameScore;
        this.endGameConsistency = endGameConsistency;
        this.pushBot = pushBot;
        this.alliancePartnerScore = alliancePartnerScore;
        this.teamNumber = teamNumber;
    }

    public int createMMR() {
        int finalScore = (autonomousScore * (autonomousConsistency / 10)) + (teleOpScore *
                (teleOpConsistency / 10)) + (endGameScore * (endGameConsistency)) * (driverAbility / 10)
                * ((durability * robotStructure) / 10);
        if(pushBot) {
            finalScore /= 10;
        }

        if(alliancePartnerScore >= (finalScore * 1.5)) {
            finalScore /= 10;
        }
        return  finalScore;
    }

}
