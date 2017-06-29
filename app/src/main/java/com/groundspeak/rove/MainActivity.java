package com.groundspeak.rove;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.groundspeak.rove.util.LatLng;
import com.groundspeak.rove.util.SphericalUtil;
import com.groundspeak.rove.util.Util;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_TARGET_LAT = "MainActivity.TARGET_LATITUDE";
    public static final String EXTRA_TARGET_LNG = "MainActivity.TARGET_LONGITUDE";

    public static Intent createIntent(Context context, LatLng target){
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(EXTRA_TARGET_LAT, target.latitude);
        intent.putExtra(EXTRA_TARGET_LNG, target.longitude);

        return intent;
    }

    @BindView(R.id.compass_arrow) ImageView arrow;
    @BindView(R.id.distance) TextView distance;
    @BindView(R.id.status_message) TextView status;

    private FusedLocationProviderClient locationClient;
    private LocationRequest locationRequest;

    private LatLng targetLatLng;

    private LocationCallback locationCallback = new LocationCallback() {

        @Override
        public void onLocationAvailability(LocationAvailability locationAvailability) {
            if(!locationAvailability.isLocationAvailable()){
                onLocationError();
            }
        }

        @Override
        public void onLocationResult(LocationResult locationResult) {
            onLocationReceived(locationResult.getLastLocation());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        double lat = getIntent().getDoubleExtra(EXTRA_TARGET_LAT, 0);
        double lng = getIntent().getDoubleExtra(EXTRA_TARGET_LNG, 0);

        targetLatLng = new LatLng(lat, lng);

        locationClient = LocationServices.getFusedLocationProviderClient(this);

        //for now, assuming phone is set to optimal gps settings

        locationRequest = new LocationRequest()
                .setInterval(2000) //2 seconds
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    private void onLocationError(){
        status.setText(R.string.location_error);
    }

    private void onLocationReceived(Location location){
        status.setText(null);

        LatLng userLatLng = new LatLng(location.getLatitude(), location.getLongitude());

        double distance = SphericalUtil.computeDistanceBetween(userLatLng, targetLatLng);

        this.distance.setText(Util.getDistanceString(distance));
    }

    @Override
    protected void onResume() {
        super.onResume();
        requestLocationUpdates();
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopLocationUpdates();
    }

    private void requestLocationUpdates() {
        status.setText(R.string.location_pending);
        locationClient.requestLocationUpdates(locationRequest, locationCallback, null);
    }

    private void stopLocationUpdates() {
        locationClient.removeLocationUpdates(locationCallback);
    }

}
