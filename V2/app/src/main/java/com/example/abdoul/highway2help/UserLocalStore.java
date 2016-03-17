package com.example.abdoul.highway2help;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Abdoul on 09/03/2016.
 */
public class UserLocalStore {

    public static final String SP_NAME = "userDetails";

    SharedPreferences userLocalDatabase;

    // Constructor
    public UserLocalStore(Context context) {
        userLocalDatabase = context.getSharedPreferences(SP_NAME, 0); // Instanciation of a SharedPreferences which is not an activity
    }

    // Get user data from local database and set the user data
    public void storeUserData(User user) {
        SharedPreferences.Editor userLocalDatabaseEditor = userLocalDatabase.edit(); // Allows to edit what is contain in the SharedPreferences

        // Attributes that we want to store in the local database
        userLocalDatabaseEditor.putString("name", user.Name);
        userLocalDatabaseEditor.putString("username", user.FirstName);
        userLocalDatabaseEditor.putString("password", user.Password);
        userLocalDatabaseEditor.putString("phone number", user.PhoneNumber);
        userLocalDatabaseEditor.putString("email",user.Email);

        // Commit
        userLocalDatabaseEditor.commit();
    }

    // Depends on how logged if true, false than
    public void setUserLoggedIn(boolean loggedIn) {
        SharedPreferences.Editor userLocalDatabaseEditor = userLocalDatabase.edit();
        userLocalDatabaseEditor.putBoolean("loggedIn", loggedIn);
        userLocalDatabaseEditor.commit();
    }

    // Clear user data when the user logout
    public void clearUserData() {
        SharedPreferences.Editor userLocalDatabaseEditor = userLocalDatabase.edit();
        userLocalDatabaseEditor.clear();
        userLocalDatabaseEditor.commit();
    }

    // User logged in or loggout
    public boolean getUserLoggedIn() {

        if(userLocalDatabase.getBoolean("LoggedIn",false))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    // A user will be return with the attributes of the current user
    public User getLoggedInUser() {
        if (userLocalDatabase.getBoolean("loggedIn", false) == false) {
            return null;
        }

        String name = userLocalDatabase.getString("name", "");

        String password = userLocalDatabase.getString("password", "");
        String email = userLocalDatabase.getString("email","");
        String phone_number = userLocalDatabase.getString("phone number", "");


        User user = new User(name,password, password, phone_number, email);
        return user;
    }
}