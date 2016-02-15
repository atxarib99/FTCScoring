package com.example.arib.ftcscoring;

import android.app.Activity;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class HelpActivity extends Activity {

    int teamNumber = 0;
    int score = 0;
    boolean activated;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help_main);
        activated = false;
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
        if (id == R.id.action_lol) {
            Toast toast = new Toast(this);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setText("You Little Rebel.");
            toast.show();
            while(Toast.LENGTH_LONG > toast.getDuration()) {

            }
            SharedPreferences sharedPreferences = getSharedPreferences("special", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            if(activated) {
                editor.putBoolean("specialS", false);
                activated = false;
            } else {
                editor.putBoolean("specialS", true);
                activated = true;
            }
            editor.commit();
            toast.setText("Special Settings = " + sharedPreferences.getBoolean("specialS", false));
        }

        return super.onOptionsItemSelected(item);
    }

}