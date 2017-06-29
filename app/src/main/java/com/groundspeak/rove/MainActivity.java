package com.groundspeak.rove;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.compass_arrow) ImageView arrow;
    @BindView(R.id.distance) TextView distance;
    @BindView(R.id.status_message) TextView status;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
