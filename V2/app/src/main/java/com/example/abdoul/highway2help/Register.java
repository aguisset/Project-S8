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

public class Register extends Activity implements View.OnClickListener {

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
                String name = EtName.getText().toString();
                String first_name = EtFirstName.getText().toString();
                String password = EtPassword.getText().toString();
                String email = EtEmail.getText().toString();
                String phone_number = EtPhoneNumber.getText().toString();

                User user = new User( name,first_name, password, phone_number, email);

                registerUser(user);
                break;
        }
    }

    private void registerUser(User User) {
        ServerRequests serverRequests = new ServerRequests(this);
        serverRequests.storeUserDataInBackground(User, new GetUserCallback() {
            @Override
            public void done(User returnedUser) {
                startActivity(new Intent(Register.this, Login.class));
            }
        });


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
