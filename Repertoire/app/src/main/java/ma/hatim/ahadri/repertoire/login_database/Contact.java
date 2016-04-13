package ma.hatim.ahadri.repertoire.login_database;

import java.util.UUID;

/**
 * Created by hatim on 31/03/16.
 */
public class Contact
{
    private String id ;
    private String first_name ;
    private String second_name ;
    private String email ;
    private String number ;


    public Contact() {}

    public Contact(String first_name, String second_name, String email, String number)
    {
        this.first_name = first_name;
        this.second_name = second_name;
        this.email = email;
        this.number = number;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getSecond_name() {
        return second_name;
    }

    public void setSecond_name(String second_name) {
        this.second_name = second_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

}
