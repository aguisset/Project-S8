package com.example.abdoul.highway2help;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;


public class Login extends Activity implements View.OnClickListener {

    // Attribut that the login activity have
    Button BLogin;
    EditText EtEmail,EtPassword;
    TextView TvRegisterLink;

    UserLocalStore userLocalStore; // to have access to the userLocalStore

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialization
        EtEmail = (EditText) findViewById(R.id.EtEmail);
        EtPassword = (EditText) findViewById(R.id.EtPassword);
        BLogin = (Button) findViewById(R.id.BLogin);
        TvRegisterLink = (TextView) findViewById(R.id.TvRegisterLink);

        userLocalStore = new UserLocalStore(this);

        // Action on case you click "this" button = BLogin

        BLogin.setOnClickListener(this);
        TvRegisterLink.setOnClickListener(this);


    }

    // This function will be called when the login button is clicked on

    @Override
    public void onClick(View v) {
        // Here we define the actions to take in case the login button is clicked on
        switch(v.getId()){
            case R.id.BLogin:
                String email = EtEmail.getText().toString();
                String password = EtPassword.getText().toString();

                User user = new User(email, password);

                userLocalStore.storeUserData(user);
                try {
                    authenticate(user);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;

            // If we click on the register link
            case R.id.TvRegisterLink:

                startActivity(new Intent(Login.this,Register.class));
                break;
        }
    }

    private void authenticate(User user) throws JSONException {
        ServerRequests serverRequests = new ServerRequests(this);
        serverRequests.fetchUserDataInBackground(user, new GetUserCallback() {
            @Override
            public void done(User returnedUser) {

                // No user
                if (returnedUser == null) {
                    showErrorMessage();
                } else {
                    logUserIn(returnedUser);
                }
            }
        });
    }

    private void showErrorMessage() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(Login.this);
        dialogBuilder.setMessage("Informations incorrecte ");
        dialogBuilder.setPositiveButton("Ok",null);
        dialogBuilder.show();

    }



    private void logUserIn(User returnedUser) {
        userLocalStore.storeUserData(returnedUser);
        userLocalStore.setUserLoggedIn(true);

        startActivity(new Intent(this,MainActivity.class));
    }
    // We will use this latter
    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_authentification, menu);
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
    }
    */
}
