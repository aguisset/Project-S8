package com.example.abdoul.highway2help;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class StartActivity extends AppCompatActivity implements View.OnClickListener{


    Button BAidant, BPatient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        // Initialization

        BAidant = (Button) findViewById(R.id.BAidant);
        BPatient = (Button) findViewById(R.id.BPatient);

        BAidant.setOnClickListener(this);
        BPatient.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.BAidant:
                startActivity(new Intent(this,Login.class)); // Go to the login page
                break;

            case R.id.BPatient:
                startActivity(new Intent(this,Login.class));
                break;
        }
    }
}
