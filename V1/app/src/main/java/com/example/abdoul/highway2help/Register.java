package com.example.abdoul.highway2help;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Register extends AppCompatActivity implements View.OnClickListener {

    // Declaration
    Button BRegister;
    EditText EtName,EtFirstName,EtPhoneNumber,EtEmail, EtPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Initialization
        BRegister = (Button) findViewById(R.id.BRegister);
        EtName = (EditText) findViewById(R.id.EtName);
        EtFirstName = (EditText) findViewById(R.id.EtFirstName);
        EtPhoneNumber = (EditText) findViewById(R.id.EtPhoneNumber);
        EtEmail = (EditText) findViewById(R.id.EtEmail);
        EtPassword = (EditText) findViewById(R.id.EtPassword);

        BRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.BRegister:
                break;
        }
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);
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
