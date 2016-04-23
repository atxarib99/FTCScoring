package com.example.arib.ftcscoring;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EditActivity extends Activity {

    String teamName;
    String teamNumber;
    String teamNotes;
    String teamMMR;
    String teamPit;
    public Uri fileUri;

    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;

    /** Create a file Uri for saving an image or video */
    private static Uri getOutputMediaFileUri(int type){
        return Uri.fromFile(getOutputMediaFile(type));
    }

    /** Create a File for saving an image or video */
    private static File getOutputMediaFile(int type){
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.

        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "FTCScoring");
        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                Log.d("FTCScoring", "failed to create directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE){
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "IMG_"+ timeStamp + ".jpg");
        } else if(type == MEDIA_TYPE_VIDEO) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "VID_"+ timeStamp + ".mp4");
        } else {
            return null;
        }

        return mediaFile;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Intent intent = getIntent();
        teamName = intent.getStringExtra(TeamListActivity.TEAMNAME);
        teamNumber = intent.getStringExtra(TeamListActivity.TEAMNUMBER);
        teamNotes = intent.getStringExtra(TeamListActivity.TEAMNOTES);
        teamMMR = intent.getStringExtra(TeamListActivity.TEAMMMR);
        teamPit = intent.getStringExtra(TeamListActivity.TEAMPIT);
        int intTeamNumber = Integer.parseInt(teamNumber);
        Uri imageUri = null;
        ArrayList<TeamImage> tempImageHolding = MainActivity.teamPics;
        for(TeamImage i : tempImageHolding) {
            if(i.getTeamNumber() == intTeamNumber) {
                imageUri = i.getImageUri();
            }
        }
        EditText editingTeamName = (EditText) findViewById(R.id.editingTeamName);
        EditText editingTeamNumber = (EditText) findViewById(R.id.editingTeamNumber);
        EditText editingTeamNotes = (EditText) findViewById(R.id.editingTeamNotes);
        TextView editingTeamMMR = (TextView) findViewById(R.id.editingTeamMMR);
        TextView editingTeamPit = (TextView) findViewById(R.id.editingTeamPit);
        ImageView imageView = (ImageView) findViewById(R.id.viewingImage);
        if(imageUri == null) {
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.blank));
        } else {
            imageView.setImageURI(imageUri);
        }
        editingTeamName.setText(teamName);
        editingTeamNumber.setText(teamNumber);
        editingTeamNotes.setText(teamNotes);
        editingTeamMMR.setText(teamMMR);
        editingTeamPit.setText(teamPit);

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
    public void addPicture(View v) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

        startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);

        ArrayList<TeamImage> tempImageHolding = MainActivity.teamPics;
        int intTeamNumber = Integer.parseInt(teamNumber);
        TeamImage toAdd = new TeamImage(fileUri, intTeamNumber);
        tempImageHolding.add(toAdd);
        ImageView imageView = (ImageView) findViewById(R.id.viewingImage);
        imageView.setImageURI(fileUri);
    }
}
