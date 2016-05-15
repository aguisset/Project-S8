package com.example.abdoul.highway2helpf;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;

public class Capteurs extends AppCompatActivity {

    @Bind(R.id.temp)
    TextView e1;

    @Bind(R.id.lumi)
    TextView e2;

    @Bind(R.id.BLogout)
    AppCompatButton _logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capteurs);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        _logoutButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Capteurs.this,MainActivity.class);
                startActivity(intent);

            }
        });
    }

}
