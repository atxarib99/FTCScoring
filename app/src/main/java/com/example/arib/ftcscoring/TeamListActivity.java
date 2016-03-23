package com.example.arib.ftcscoring;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class TeamListActivity extends Activity {

    //create a list and an adapter
    ArrayList<Team> teamList;
    ArrayAdapter<Team> adapter;
    public final static String TEAMNAME = "com.example.arib.teamName";
    public final static String TEAMNUMBER = "com.example.arib.teamNumber";
    public final static String TEAMNOTES = "com.example.arib.teamNotes";

    //set the arraylist and set the adapter to the listview
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_list);
        teamList = MainActivity.teams;      //set the arraylist
        adapter = new MyListAdapter();      //set the adapter
        final ListView list = (ListView) findViewById(R.id.teamListView);
        list.setAdapter(adapter);           //set the adapter to the listview
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {

                Object o = list.getItemAtPosition(position);
                Team team = teamList.get(position);
                String givingName = team.getTeamName();
                String givingNumber = team.getTeamNumber() + "";
                String givingNotes = team.getInfo();
                launchEditor(givingName, givingNumber, givingNotes);
                //create method to launch activity

            }
        });
    }

    //create the options
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_team_list, menu);
        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        updateThis();
    }

    private void launchEditor(String teamName, String teamNumber, String teamNotes) {
        Intent intent = new Intent(this, EditActivity.class);
        intent.putExtra(TEAMNAME, teamName);
        intent.putExtra(TEAMNUMBER, teamNumber);
        intent.putExtra(TEAMNOTES, teamNotes);
        startActivity(intent);
    }

    private void updateThis() {
        setContentView(R.layout.activity_team_list);
        teamList = MainActivity.teams;      //set the arraylist
        adapter = new MyListAdapter();      //set the adapter
        final ListView list = (ListView) findViewById(R.id.teamListView);
        list.setAdapter(adapter);           //set the adapter to the listview
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {

                Object o = list.getItemAtPosition(position);
                Team team = teamList.get(position);
                String givingName = team.getTeamName();
                String givingNumber = team.getTeamNumber() + "";
                String givingNotes = team.getInfo();
                launchEditor(givingName, givingNumber, givingNotes);
                //create method to launch activity

            }
        });
    }

    //set the options to do nothing
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_refresh) {
            updateThis();
        }

        return super.onOptionsItemSelected(item);
    }

    private class MyListAdapter extends ArrayAdapter<Team> {
        public MyListAdapter() {
            super(TeamListActivity.this, R.layout.listview_item, teamList);
        }

        //get the view of the item
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            //get the item view
            View itemView = convertView;
            if(itemView == null) {
                itemView = getLayoutInflater().inflate(R.layout.listview_item, parent, false);
            }

            //get the values to set
            Team currentTeam = teamList.get(position);
            String teamName = currentTeam.getTeamName() + "";
            String teamNumber = currentTeam.getTeamNumber() + "";
            String pitScore = currentTeam.getPitScore() + "";
            String MMR = currentTeam.getMMR() + "";
            String teamNotes = currentTeam.getInfo();

            //get the textview
            Log.e("teamName is: ", teamName);
            TextView teamNameView = (TextView) itemView.findViewById(R.id.viewingTeamName);
            TextView teamNumberView = (TextView) itemView.findViewById(R.id.viewingTeamNumber);
            TextView pitScoreView = (TextView) itemView.findViewById(R.id.viewingPitScore);
            TextView MMRScoreView = (TextView) itemView.findViewById(R.id.viewingMMR);
            TextView teamNotesView = (TextView) itemView.findViewById(R.id.viewingTeamNotes);

            //set the textviews to the values
            teamNameView.setText(teamName);
            teamNumberView.setText(teamNumber);
            pitScoreView.setText(pitScore);
            MMRScoreView.setText(MMR);
            teamNotesView.setText(teamNotes);

            //return the itemView as requested
            return itemView;

        }

    }
}
