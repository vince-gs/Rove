package com.groundspeak.rove;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class PrimerActivity extends AppCompatActivity {

    //TODO Request location permission at runtime on API >= 21
    //TODO show magic location settings dialog (if necessary) per
    // https://developer.android.com/training/location/change-location-settings.html
    // to ensure high-accuracy setting

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primer);
    }
}
