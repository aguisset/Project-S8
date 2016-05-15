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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RegisterAidantActivity extends AppCompatActivity {

    private static final String TAG = "SignupActivity";
    private static final int REQUEST_START = 0;

    private static final String REGISTER_URL = "http://highway2help.eirb.fr/php/register.php";

    @Bind(R.id.input_name)
    EditText _nameText;

    @Bind(R.id.input_first_name)
    EditText _firstText;

    @Bind(R.id.input_phone_number) EditText _phoneText;

    @Bind(R.id.input_email) EditText _emailText;
    @Bind(R.id.input_password) EditText _passwordText;
    @Bind(R.id.btn_signup)
    Button _signupButton;
    @Bind(R.id.link_login)
    TextView _loginLink;

    @Bind(R.id.IBlogo_button)
    ImageButton _returnToMenuButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_aidant);
        ButterKnife.bind(this);

        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
                //signup();
            }
        });

        _returnToMenuButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivityForResult(intent, REQUEST_START);
            }
        });
        _loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                finish();
            }
        });


    }

    private void registerUser() {
        String name = _nameText.getText().toString().trim().toLowerCase();
        String first_name = _firstText.getText().toString().trim().toLowerCase();
        String phone_number = _phoneText.getText().toString().trim().toLowerCase();
        String password = _passwordText.getText().toString().trim().toLowerCase();
        String email = _emailText.getText().toString().trim().toLowerCase();


        register(name, first_name, phone_number, email, password);
    }

    private void register(String name, String first_name, String phone_number, String email,String password) {
        class RegisterUser extends AsyncTask<String, Void, String> {
            ProgressDialog loading;
           // RegisterUserClass ruc = new RegisterUserClass();


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(RegisterAidantActivity.this, "Please Wait",null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();

                String result = "Registration success...";
                if (s.equals(result) ) {
                    Intent intent = new Intent(RegisterAidantActivity.this,LoginAidant.class);
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
                    String param="name=" + URLEncoder.encode(params[0],"UTF-8")+
                            "&first_name="+URLEncoder.encode(params[1],"UTF-8")+
                            "&phone_number="+URLEncoder.encode(params[2],"UTF-8") + "&email="+URLEncoder.encode(params[3],"UTF-8")
                            +"&password=" + URLEncoder.encode(params[4],"UTF-8") ;

                    url=new URL(REGISTER_URL+"?"+param);


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

                    return "Registration success...";
                }
                //catch some error
                catch(MalformedURLException ex){
                    Toast.makeText(RegisterAidantActivity.this, ex.toString(), 1 ).show();

                }
                // and some more
                catch(IOException ex){

                    Toast.makeText(RegisterAidantActivity.this, ex.toString(), 1 ).show();
                }


                return null;
            }
        }

        RegisterUser ru = new RegisterUser();
        ru.execute(name, first_name, phone_number, email, password);
    }





    public boolean validate() {
        boolean valid = true;

        String name = _nameText.getText().toString();
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            _nameText.setError("Minimum 3 caractères ! ");
            valid = false;
        } else {
            _nameText.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("Entrer un email valide !");
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


