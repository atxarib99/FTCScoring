package com.example.arib.ftcscoring;

import android.net.Uri;

/**
 * Created by Arib on 4/20/2016.
 */
public class TeamImage {
    private Uri imageUri;
    private int teamNumber;
    public TeamImage() {
        imageUri = null;
        teamNumber = 0;
    }
    public TeamImage(Uri imageUri, int teamNumber) {
        this.imageUri = imageUri;
        this.teamNumber = teamNumber;
    }

    public Uri getImageUri() {
        return imageUri;
    }

    public void setImageUri(Uri imageUri) {
        this.imageUri = imageUri;
    }

    public int getTeamNumber() {
        return teamNumber;
    }

    public void setTeamNumber(int teamNumber) {
        this.teamNumber = teamNumber;
    }
}
