package com.example.abdoul.highway2help;

/**
 * Created by Abdoul on 17/03/2016.
 *
 * Inform the Activity which perform the server request when the server (background) request is complete
 */
public interface GetUserCallback {

    // interface can only have abstract
    public abstract void done(User returnedUser);


}
