package com.example.arib.ftcscoring;

/**
 * Created by Arib on 2/18/2016.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Arib on 2/9/2016.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION   = 1;
    private static final String DATABASE_NAME   = "teamManager";
    private static final String TABLE_TEAMS  = "teams";

    // Contacts Table column headers

    private static final String KEY_ID          = "id";
    private static final String KEY_NUMBER      = "number";
    private static final String KEY_MMR         = "mmr";
    private static final String KEY_PITSCORE    = "pitscore";
    private static final String KEY_INFO        = "info";

    //default constructor
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_TEAMS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NUMBER + " TEXT,"
                + KEY_MMR + " TEXT," + KEY_PITSCORE + "TEXT," +
                KEY_INFO + "TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TEAMS);

        // Create Tables again
        onCreate(db);
    }

    public void addTeam(Team team) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_ID, team.getId());
        values.put(KEY_NUMBER, team.getTeamNumber());
        values.put(KEY_MMR, team.getMMR());
        values.put(KEY_PITSCORE, team.getPitScore());
        values.put(KEY_INFO, team.getInfo());

        db.insert(TABLE_TEAMS, null, values);
        db.close();
    }

    public Team getContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_TEAMS, new String[] { KEY_ID,
                KEY_NUMBER, KEY_MMR, KEY_INFO}, KEY_ID + "=?", new String[] { String.valueOf(id) }, null, null, null, null);

        if(cursor != null) {
            cursor.moveToFirst();
        }
        Team team = new Team();



        return team;
    }

    public List<Team> getAllTeams() {
        List<Team> teamList = new ArrayList<Team>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_TEAMS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Team team = new Team();
                team.setId(Integer.parseInt(cursor.getString(0)));
                team.setTeamNumber(Integer.parseInt(cursor.getString(1)));
                team.setMMR(Double.parseDouble(cursor.getString(2)));
                team.setPitScore(Integer.parseInt(cursor.getString(3)));
                team.setInfo(cursor.getString(4));
                teamList.add(team);
            } while (cursor.moveToNext());
        }

        // return contact list
        return teamList;

    }

    public int getTeamsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_TEAMS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

    public int updateTeam(Team team) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NUMBER, team.getTeamNumber());
        values.put(KEY_MMR, team.getMMR());

        // updating row
        return db.update(TABLE_TEAMS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(team.getId()) });
    }

    public void deleteTeam(Team team) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TEAMS, KEY_ID + " = ?",
                new String[] { String.valueOf(team.getId()) });
        db.close();
    }
}
