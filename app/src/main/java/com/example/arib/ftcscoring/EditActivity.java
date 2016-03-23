package com.example.arib.ftcscoring;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EditActivity extends Activity {

    String teamName;
    String teamNumber;
    String teamNotes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Intent intent = getIntent();
        teamName = intent.getStringExtra(TeamListActivity.TEAMNAME);
        teamNumber = intent.getStringExtra(TeamListActivity.TEAMNUMBER);
        teamNotes = intent.getStringExtra(TeamListActivity.TEAMNOTES);
        EditText editingTeamName = (EditText) findViewById(R.id.editingTeamName);
        EditText editingTeamNumber = (EditText) findViewById(R.id.editingTeamNumber);
        EditText editingTeamNotes = (EditText) findViewById(R.id.editingTeamNotes);
        editingTeamName.setText(teamName);
        editingTeamNumber.setText(teamNumber);
        editingTeamNotes.setText(teamNotes);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit, menu);
        return true;
    }

    public void saveButtonPressed(View v) {
        int number;
        DatabaseHandler db = new DatabaseHandler(MainActivity.mainContext);
        try {
            number = Integer.parseInt(teamNumber);
        } catch (NumberFormatException e) {
            Context context = getApplicationContext();
            CharSequence text = "Error on input";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            number = 0;
        }
        Team t = MainActivity.getTeamByNumber(number);
        EditText editingTeamName = (EditText) findViewById(R.id.editingTeamName);
        EditText editingTeamNumber = (EditText) findViewById(R.id.editingTeamNumber);
        EditText editingTeamNotes = (EditText) findViewById(R.id.editingTeamNotes);
        boolean allGood = true;
        int newNumber;
        try {
            newNumber = Integer.parseInt(editingTeamNumber.getText() + "");
        } catch (NumberFormatException e) {
            Context context = getApplicationContext();
            CharSequence text = "Error on input";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            allGood = false;
            newNumber = 0;
        }
        if(allGood) {
            t.setTeamName(editingTeamName.getText() + "");
            t.setTeamNumber(newNumber);
            t.setInfo(editingTeamNotes.getText() + "");
            db.deleteTeam(t);
            db.addTeam(t);
            this.finish();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
