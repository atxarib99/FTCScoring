package com.example.arib.ftcscoring;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.annotation.Target;

import static android.Manifest.permission.MANAGE_DOCUMENTS;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;


public class EditActivity extends Activity {

    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;
    private final int REQUEST_CODE_ASK_EXTERNAL = 124;
    private final int REQUEST_CODE_ASK_EXTERNAL_WRITE = 125;
    String teamName;
    String teamNumber;
    String teamNotes;
    String teamMMR;
    String teamPit;
    public Uri fileUri;
    ImageView imageView;


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
        fileUri = null;
        imageView = (ImageView) findViewById(R.id.viewingImage);

        int intTeamNumber = Integer.parseInt(teamNumber);
        Team t = MainActivity.getTeamByNumber(intTeamNumber);
        Uri imageUri = t.getTeamImage();

        EditText editingTeamName = (EditText) findViewById(R.id.editingTeamName);
        EditText editingTeamNumber = (EditText) findViewById(R.id.editingTeamNumber);
        EditText editingTeamNotes = (EditText) findViewById(R.id.editingTeamNotes);
        TextView editingTeamMMR = (TextView) findViewById(R.id.editingTeamMMR);
        TextView editingTeamPit = (TextView) findViewById(R.id.editingTeamPit);
        editingTeamNumber.setText(teamNumber);
        editingTeamNotes.setText(teamNotes);
        editingTeamMMR.setText(teamMMR);
        editingTeamPit.setText(teamPit);
        if(imageUri == null) {
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.blank));
        } else {

            boolean should = shouldRequestManage();
            boolean shouldExternal = shouldRequestExternal();
            boolean shouldExternalWrite = shouldRequestExternalWrite();
            if(shouldExternal) {
                showMessageOKCancelExternal("NEED TO READ EXTERNAL STORAGE");
            }
            if(shouldExternalWrite) {
                showMessageOKCancelExternalWrite("NEED TO WRITE EXTERNAL STORAGE");
            }
            if(should) {
                showMessageOKCancel(getString(R.string.permission_rationale));
            }
            if(checkSelfPermission(MANAGE_DOCUMENTS) == PackageManager.PERMISSION_GRANTED &&
                    checkSelfPermission(READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                    checkSelfPermission(WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                imageView.setImageURI(imageUri);
            }
        }
        editingTeamName.setText(teamName);


    }

    private void showMessageOKCancel(String message) {
        AlertDialog toShow = new AlertDialog.Builder(EditActivity.this)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        requestPermissions(new String[] {MANAGE_DOCUMENTS}, REQUEST_CODE_ASK_PERMISSIONS);
                    }
                })
                .setNegativeButton("Cancel", null)
                .create();
        toShow.show();
    }

    private void showMessageOKCancelExternal(String message) {
        AlertDialog toShow = new AlertDialog.Builder(EditActivity.this)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        requestPermissions(new String[] {READ_EXTERNAL_STORAGE}, REQUEST_CODE_ASK_EXTERNAL);
                    }
                })
                .setNegativeButton("Cancel", null)
                .create();
        toShow.show();
    }
    private void showMessageOKCancelExternalWrite(String message) {
        AlertDialog toShow = new AlertDialog.Builder(EditActivity.this)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        requestPermissions(new String[] {WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_ASK_EXTERNAL_WRITE);
                    }
                })
                .setNegativeButton("Cancel", null)
                .create();
        toShow.show();
    }

    private boolean shouldRequestExternal() {
        return checkSelfPermission(READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED;
    }

    private boolean shouldRequestExternalWrite() {
        return checkSelfPermission(WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED;
    }

    private boolean shouldRequestManage() {
        return checkSelfPermission(MANAGE_DOCUMENTS) != PackageManager.PERMISSION_GRANTED;
        //        Log.e("SHOULDSHOW", shouldShowRequestPermissionRationale(MANAGE_DOCUMENTS) + "");
//        if(shouldShowRequestPermissionRationale(MANAGE_DOCUMENTS)) {
////            Snackbar.make(imageView, R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE)
////                    .setAction(android.R.string.ok, new View.OnClickListener() {
////                        @Override
////                        @TargetApi(Build.VERSION_CODES.M)
////                        public void onClick(View v) {
////                            requestPermissions(new String[] {MANAGE_DOCUMENTS}, REQUEST_CODE_ASK_PERMISSIONS);
////                        }
////                    });
//            Log.e("STEP 1", "got here");
//            requestPermissions(new String[]{MANAGE_DOCUMENTS}, REQUEST_CODE_ASK_PERMISSIONS);
//        } else {
//            return false;
//        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if(requestCode == REQUEST_CODE_ASK_PERMISSIONS) {
            Log.e("grantResults", grantResults.length + "---" + grantResults[0] + "---" + PackageManager.PERMISSION_GRANTED);
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission Granted
                ImageView imageView = (ImageView) findViewById(R.id.viewingImage);
                int intTeamNumber = Integer.parseInt(teamNumber);
                Team t = MainActivity.getTeamByNumber(intTeamNumber);
                Uri imageUri = t.getTeamImage();
                imageView.setImageURI(imageUri);
            } else {
                // Permission Denied
                Context context = getApplicationContext();
                CharSequence text = "Manage Permission Denied";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        }
        if(requestCode == REQUEST_CODE_ASK_EXTERNAL) {
            if(grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Context context = getApplicationContext();
                CharSequence text = "External Permission Accepted";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
            else {
                Context context = getApplicationContext();
                CharSequence text = "External Permission Denied";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        }
        if(requestCode == REQUEST_CODE_ASK_EXTERNAL_WRITE) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Context context = getApplicationContext();
                CharSequence text = "External Write Permission Accepted";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            } else {
                Context context = getApplicationContext();
                CharSequence text = "External Write Permission Denied";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        }
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
            if(fileUri != null) {
                t.setTeamImage(fileUri);
            }
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
            Intent intent = new Intent();
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Team or Robot Image"),1);
    }


    public void onActivityResult(int reqCode, int resCode, Intent data)
    {
        if(resCode==RESULT_OK)
        {
            if(reqCode==1) {
                fileUri = data.getData();
                int number;
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
                t.setTeamImage(fileUri);
                ImageView imageView = (ImageView) findViewById(R.id.viewingImage);
                imageView.setImageURI(fileUri);

            }
        }
    }
}
