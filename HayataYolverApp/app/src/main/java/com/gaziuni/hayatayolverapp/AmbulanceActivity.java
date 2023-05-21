package com.gaziuni.hayatayolverapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AmbulanceActivity extends AppCompatActivity {
    EditText namesurnameEditText;
    Button buttonMapLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ambulance);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        namesurnameEditText = findViewById(R.id.namesurnameEditText);
        namesurnameEditText.setText(name);
        buttonMapLogin = findViewById(R.id.buttonMapGiris);
        buttonMapLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent anotherIntent = new Intent(AmbulanceActivity.this, AmbulancemapActivity.class);
                startActivity(anotherIntent);
            }
        });



    }
}