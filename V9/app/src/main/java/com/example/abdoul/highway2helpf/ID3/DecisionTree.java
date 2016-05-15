package com.example.abdoul.highway2helpf.ID3;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abdoul.highway2helpf.HomeAidantActivity;
import com.example.abdoul.highway2helpf.LoginAidant;
import com.example.abdoul.highway2helpf.R;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DecisionTree extends AppCompatActivity {


    private static final String DECISION_URL = "http://highway2help.eirb.fr/php/dec_tree3.php";

    @Bind(R.id.IBlogo_button)
    ImageButton _homeButton;


    @Bind(R.id.BDecision)
    AppCompatButton _decisionButton;

    @Bind(R.id.rate)
    TextView e;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decision_tree);
        ButterKnife.bind(this);


        _decisionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });


        _homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DecisionTree.this, HomeAidantActivity.class);
                startActivity(intent);
            }
        });


    }

    private void registerUser() {


        getJSON();
    }


    private void getJSON() {
        class getData extends AsyncTask<String, Void, String> {
            HttpURLConnection urlConnection;

            @Override
            protected String doInBackground(String... args) {

                StringBuilder result = new StringBuilder();

                try {
                    URL url = new URL(DECISION_URL);
                    urlConnection = (HttpURLConnection) url.openConnection();
                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());

                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                    String line;
                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    urlConnection.disconnect();
                }


                return result.toString();
            }

            @Override
            protected void onPostExecute(String result) {

                //Do something with the JSON string
                e.setText("D'après les données objectifs : Le patient est susceptible d'être dépressif ...");

                //System.out.println(result);

                // Parse


            }

        }

        getData ru = new getData();
        ru.execute();


    }
}

