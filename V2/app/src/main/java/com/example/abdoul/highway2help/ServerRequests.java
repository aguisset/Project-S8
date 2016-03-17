package com.example.abdoul.highway2help;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by Abdoul on 17/03/2016.
 */
public class ServerRequests {

    ProgressDialog progressDialog;
    // Time in millisecond the connection should persist
    public static final int CONNECTION_TIMEOUT = 1000 * 15;

    public static final String SERVER_ADDRESS = "http://10.0.2.2:8080/Highway%202%20Help/";

    // Constructor, context is used when it does not extend class
    public ServerRequests(Context context) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false); // User won't be able to cancel the progress dialogue
        progressDialog.setTitle("Processing");
        progressDialog.setMessage("Please wait ...");
    }

    public void storeUserDataInBackground(User user, GetUserCallback userCallback) {
        progressDialog.show();
        new StoreUserDataAsyncTask(user, userCallback).execute(); // send user extend and user callback
    }

    // fetching the user data in the background
    public void fetchUserDataInBackground(User user, GetUserCallback callback) throws JSONException {
        progressDialog.show();
        new fetchUserDataAsyncTask(user,callback).execute();
    }

    private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for(Map.Entry<String, String> entry : params.entrySet()){
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        return result.toString();
    }

    // Async Task is a background task in Android
    public class StoreUserDataAsyncTask extends AsyncTask<Void, Void, Void> {
        User user;
        GetUserCallback userCallback;

        public StoreUserDataAsyncTask(User user, GetUserCallback userCallback) {
            this.user = user;
            this.userCallback = userCallback;
        }

        @Override
        protected Void doInBackground(Void... params) {

            Map<String, String> dataToSend = new HashMap<>();

            dataToSend.put("name", user.Name);
            dataToSend.put("first name", user.FirstName);
            dataToSend.put("password", user.Password);
            dataToSend.put("email", user.Email);
            dataToSend.put("phone number", user.PhoneNumber);


            //HttpParams httpRequestParams = new BasicHttpParams();

            // We will have to encode string by our custom method


            // Will be used if we want to read some data from server
            String response = "";

            try {
                URL url = null;
                url = new URL(SERVER_ADDRESS + "register.php");
                HttpURLConnection conn = null;
                conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(CONNECTION_TIMEOUT);
                conn.setConnectTimeout(CONNECTION_TIMEOUT);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                DataOutputStream os = new DataOutputStream(conn.getOutputStream());

                Uri.Builder builder = new Uri.Builder().appendQueryParameter("name", user.Name)
                        .appendQueryParameter("first name", user.FirstName)
                        .appendQueryParameter("phone number", user.PhoneNumber)
                        .appendQueryParameter("email", user.Email)
                        .appendQueryParameter("password", user.Password);


                String query = builder.build().getEncodedQuery();

                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));

                writer.write(query);
                writer.flush();
                writer.close();
                os.close();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //Same return null, but if you want to return the read string (stored in line)
            //then change the parameters of AsyncTask and return that type, by converting
            //the string - to say JSON or user in your case
            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            progressDialog.dismiss();
            userCallback.done(null); // background task is finish
            super.onPostExecute(aVoid);
        }
    }

    public class fetchUserDataAsyncTask extends AsyncTask<Void, Void, User> {
        User user;
        GetUserCallback userCallback;

        public fetchUserDataAsyncTask(User user, GetUserCallback userCallback) throws JSONException {
            this.user = user;
            this.userCallback = userCallback;
        }

        @Override
        protected User doInBackground(Void... params) {
            Map<String, String> dataToSend = new HashMap<>();


            dataToSend.put("password", user.Password);
            dataToSend.put("email", user.Email);



            // Will be used if we want to read some data from server
            BufferedReader reader = null;

            User returnedUser = null;
            try {
                URL url = null;
                url = new URL(SERVER_ADDRESS +"FetchUserData.php");
                HttpURLConnection conn = null;
                conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(CONNECTION_TIMEOUT);
                conn.setConnectTimeout(CONNECTION_TIMEOUT);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                DataOutputStream os = new DataOutputStream(conn.getOutputStream());

                Uri.Builder builder = new Uri.Builder().appendQueryParameter("name", user.Name)
                        .appendQueryParameter("first name", user.FirstName)
                        .appendQueryParameter("phone number", user.PhoneNumber)
                        .appendQueryParameter("email", user.Email)
                        .appendQueryParameter("password", user.Password);

                String query = builder.build().getEncodedQuery();

                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));

                writer.write(query);
                writer.flush();
                writer.close();
                os.close();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }




        @Override
        protected void onPostExecute(User returnedUser) {
            progressDialog.dismiss();
            userCallback.done(returnedUser); // background task is finish
            super.onPostExecute(returnedUser);
        }
    }

}
