package com.example.abdoul.highway2helpf;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

import com.example.abdoul.highway2helpf.ID3.DecisionTree;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HomePatient extends AppCompatActivity {
    private static final int REQUEST_START = 0;

    @Bind(R.id.BLogout)
    AppCompatButton _logoutButton;

    @Bind(R.id.BPlanning)
    AppCompatButton _planningButton;

    @Bind(R.id.IBlogo_button)
    ImageButton _returnToMenuButton;

    /* This function can open another application which is already installed */
    public static boolean openApp(Context context, String packageName) {
        PackageManager manager = context.getPackageManager();
        try {
            Intent i = manager.getLaunchIntentForPackage(packageName);
            if (i == null) {
                return false;
                //throw new PackageManager.NameNotFoundException();
            }
            i.addCategory(Intent.CATEGORY_LAUNCHER);
            context.startActivity(i);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_patient);
        ButterKnife.bind(this);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        _logoutButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePatient.this,MainActivity.class);
                startActivity(intent);

            }
        });

        _returnToMenuButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivityForResult(intent, REQUEST_START);
            }
        });

        _planningButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openApp(HomePatient.this, "ws.xsoh.etar");
            }
        });


    }

}
