package com.example.abdoul.highway2help;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements View.OnClickListener {

    // Attribut that the login activity have
    Button BLogout;
    EditText EtName,EtFirstName;
    UserLocalStore userLocalStore; // to have access to the userLocalStore

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialization

        BLogout = (Button) findViewById(R.id.BLogout);
        EtName = (EditText) findViewById(R.id.EtName);
        EtFirstName = (EditText) findViewById(R.id.EtFirstName);

        BLogout.setOnClickListener(this);

        userLocalStore = new UserLocalStore(this);

    }

    protected void onStart() {
        super.onStart();

        // User logged in
        if(authentificate() == true) {
            displayUserDetails();
        } else {
            startActivity(new Intent(MainActivity.this,Login.class));
        }
    }

    // Check if user is logged in or logged out
    private boolean authentificate(){
        return userLocalStore.getUserLoggedIn();
    }
    // display user details
    private void displayUserDetails(){
        User user = userLocalStore.getLoggedInUser();
        

        // Display what we want
        EtName.setText(user.Name);
        EtFirstName.setText(user.FirstName);

    }
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.BLogout:
                startActivity(new Intent(this,Login.class)); // Go to the login page

                userLocalStore.clearUserData();
                userLocalStore.setUserLoggedIn(false);
                break;


        }
    }


    /*@Override
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/
}
