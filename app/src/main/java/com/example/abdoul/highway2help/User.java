package com.example.abdoul.highway2help;

/**
 * Created by Abdoul on 25/02/2016.
 *
 * On enregistre les informations du User dans les préférences du systèmes comme sa même si on
 * ferme l'application, elle gardera les informations en mémoire.
 */
public class User {
    // Attributes
    String Nom,Prenom, Mdp,Email;
    int Numero;

    // Constructor if we know all the attributes
    public User( String Nom, String Prenom, String Mdp, int Numero, String Email) {
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.Mdp = Mdp;
        this.Numero = Numero;
        this.Email = Email;
    }

    // Constructor if we know email and mdp
    public User (String Email,String Mdp) {
        this.Email = Email;
        this.Mdp = Mdp;

        // We give some value to the other attributes we don't know
        this.Nom = "";
        this.Mdp = "";
        this.Numero=-1;
        this.Prenom = "";
    }

}
