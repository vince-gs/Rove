package com.groundspeak.rove;

import android.Manifest;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.location.LocationServices;

public class PrimerActivity extends AppCompatActivity {

    //TODO Request location permission at runtime on API >= 21

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primer);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }
}
