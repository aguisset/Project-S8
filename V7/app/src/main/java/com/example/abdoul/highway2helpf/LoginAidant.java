package com.example.abdoul.highway2helpf;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

import butterknife.Bind;
import butterknife.ButterKnife;


public class LoginAidant extends AppCompatActivity  {

    private static final String TAG = "LoginActivity";
    private static final int REQUEST_LOGIN = 0;
    private static final int REQUEST_START = 0;

    private static final String LOGIN_URL = "http://highway2help.16mb.com/test/Login.php";


    @Bind(R.id.input_email)
    EditText _emailText;
    @Bind(R.id.input_password) EditText _passwordText;
    @Bind(R.id.btn_login)
    Button _loginButton;


    @Bind(R.id.link_signup)
    TextView _signupLink;

    @Bind(R.id.IBlogo_button)
    ImageButton _returnToMenuButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_aidant);
        ButterKnife.bind(this);


        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });

        _returnToMenuButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivityForResult(intent, REQUEST_START);
            }
        });

        _signupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterAidantActivity.class);
                startActivityForResult(intent, REQUEST_LOGIN);

            }
        });
    }

    private void LoginUser() {
        String password = _passwordText.getText().toString().trim().toLowerCase();
        String email = _emailText.getText().toString().trim().toLowerCase();

        login(email,password);
    }

    private void login(String email, String password) {
        class LoginUser extends AsyncTask<String, Void, String> {
            ProgressDialog loading;
            // RegisterUserClass ruc = new RegisterUserClass();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(LoginAidant.this, "Please Wait",null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();

                String result = "Login success...";
                if (s.equals(result) ) {
                    Intent intent = new Intent(LoginAidant.this,HomeAidantActivity.class);
                    startActivity(intent);
                }



            }

            @Override
            protected String doInBackground(String... params) {
                URL url;
                HttpURLConnection conn;

                try{
                    //if you are using https, make sure to import java.net.HttpsURLConnection


                    //you need to encode ONLY the values of the parameters
                    String param="email=" + URLEncoder.encode(params[0], "UTF-8")+
                            "&password="+URLEncoder.encode(params[1],"UTF-8");

                    url=new URL(LOGIN_URL+"?"+param);


                    conn=(HttpURLConnection)url.openConnection();

                    //set the output to true, indicating you are outputting(uploading) GET data
                    conn.setDoOutput(true);

                    //Set the type of request method
                    conn.setRequestMethod("GET");

                    //Android documentation suggested that you set the length of the data you are sending to the server, BUT

                    conn.setFixedLengthStreamingMode(param.getBytes().length);
                    conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    //send the GET out
                    PrintWriter out = new PrintWriter(conn.getOutputStream());
                    out.print(param);
                    out.close();



                    //start listening to the stream
                    Scanner inStream = new Scanner(conn.getInputStream());

                    conn.disconnect();

                    return "Login success...";
                }
                //catch some error
                catch(MalformedURLException ex){
                    Toast.makeText(LoginAidant.this, ex.toString(), 1 ).show();

                }
                // and some more
                catch(IOException ex){

                    Toast.makeText(LoginAidant.this, ex.toString(), 1 ).show();
                }


                return null;
            }
        }

        LoginUser ru = new LoginUser();
        ru.execute(email, password);
    }

    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        _loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginAidant.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        // TODO: Implement your own authentication logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        onLoginSuccess();
                        // onLoginFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_LOGIN) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        // Disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {
        _loginButton.setEnabled(true);
        finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("Entrer une adresse email valide !");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("Entre 4 et 10 caractères alphanumériques");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }



}


