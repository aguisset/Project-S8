package com.example.abdoul.highway2help;

/**
 * Created by Abdoul on 25/02/2016.
 *
 * On enregistre les informations du User dans les préférences du systèmes comme sa même si on
 * ferme l'application, elle gardera les informations en mémoire.
 */
public class User {
    // Attributes
    String Name,FirstName, Password,Email;
    String PhoneNumber;

    // Constructor if we know all the attributes
    public User( String Nom, String Prenom, String Mdp, String Numero, String Email) {
        this.Name = Nom;
        this.FirstName = Prenom;
        this.Password = Mdp;
        this.PhoneNumber = Numero;
        this.Email = Email;
    }

    // Constructor if we know email and mdp
    public User (String Email,String Mdp) {
        this.Email = Email;
        this.Password = Mdp;

        // We give some value to the other attributes we don't know
        this.Name = "";
        this.PhoneNumber="";
        this.FirstName = "";
    }

}
