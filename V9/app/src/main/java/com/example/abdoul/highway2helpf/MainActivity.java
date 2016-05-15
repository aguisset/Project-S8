package com.example.abdoul.highway2helpf;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton BAidant;
    ImageButton BPatient;

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
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Initialization

        BAidant = (ImageButton) findViewById(R.id.BAidant);
        BPatient = (ImageButton) findViewById(R.id.BPatient);

        BAidant.setOnClickListener(this);
        BPatient.setOnClickListener(this);

    }


    public void onClick(View v) {
        switch(v.getId()){
            case R.id.BAidant:
             startActivity(new Intent(this,LoginAidant.class)); // Go to the login page
                //openApp(this, "ws.xsoh.etar");

             break;

            case R.id.BPatient:
              startActivity(new Intent(this,LoginPatient.class));
                break;

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id) {
            case R.id.action_settings:
                return true;
            case R.id.nav_home:
                Intent launchNewIntent = new Intent(this,MainActivity.class);
                startActivityForResult(launchNewIntent, 0);
                return true;
            //noinspection SimplifiableIfStatement
        }

        return super.onOptionsItemSelected(item);
    }
}
