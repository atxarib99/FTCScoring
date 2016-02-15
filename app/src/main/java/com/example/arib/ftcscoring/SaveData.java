package com.example.arib.ftcscoring;

import java.util.ArrayList;

/**
 * Created by Arib on 1/28/2016.
 */
public class SaveData {
    Team[] vals;
    public SaveData(Team[] v) {
        vals = v;
    }

    public SaveData() {

        vals = new Team[3];
        vals[0] = new Team(0, 0);
        vals[1] = new Team(0, 1);
        vals[2] = new Team(0, 2);

    }
    public ArrayList<Team> getArrayListTeams() {
        ArrayList<Team> teams = new ArrayList<>();
        for(int i = 0; i < vals.length; i++) {
            teams.add(vals[i]);
        }
        return teams;
    }
}
