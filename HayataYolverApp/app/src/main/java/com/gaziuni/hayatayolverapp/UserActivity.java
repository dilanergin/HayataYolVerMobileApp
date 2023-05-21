package com.gaziuni.hayatayolverapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class UserActivity extends AppCompatActivity {

    FusedLocationProviderClient fusedLocationProviderClient;

    EditText phoneEdittext,nameEditText;
    Button helpbutton;
   LocationManager locationManager;
    Geocoder geocoder;
    public static final int  REQUEST_CODR=100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String phone =  intent.getStringExtra("phone");
        helpbutton = findViewById(R.id.buttonHelp);
        phoneEdittext = findViewById(R.id.phoneEditText);
        nameEditText = findViewById(R.id.nameEditText);
        phoneEdittext.setText(phone);
        nameEditText.setText(name);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        helpbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentLocation();
            }
        });
    }

    public void currentLocation() {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        {
            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if(location != null)
                    {
                        Geocoder geocoder = new Geocoder(UserActivity.this, Locale.getDefault());
                        List<Address> addressList = null;
                        try {
                            addressList = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                            Toast.makeText(UserActivity.this, "Latitude:"+addressList.get(0).getLatitude()+
                                    "Longitude:"+addressList.get(0).getLongitude()+
                                    "City:"+addressList.get(0).getLongitude()+
                                    "Country:"+addressList.get(0).getCountryName()+
                                    "Address:"+addressList.get(0).getAddressLine(0), Toast.LENGTH_SHORT).show();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                    }
                }
            });
        }else {
            AskPerm();

        }
    }

    private void AskPerm() {
        ActivityCompat.requestPermissions(UserActivity.this,new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION
        },REQUEST_CODR);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODR)
        {
            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                currentLocation();
            }else {
                Toast.makeText(this, "İzin gerekli!", Toast.LENGTH_SHORT).show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}