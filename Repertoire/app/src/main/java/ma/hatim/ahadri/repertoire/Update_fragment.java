package ma.hatim.ahadri.repertoire;


import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import ma.hatim.ahadri.repertoire.login_database.Contact;
import ma.hatim.ahadri.repertoire.login_database.ContactDB;


public class Update_fragment extends DialogFragment
{
    private EditText first_name ;
    private EditText second_name ;
    private EditText email ;
    private EditText number ;
    private String id ;
    private static  final String UPDTAE = "update_contact" ;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        id = ContactsListFragment.getID();
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.modifier_layout, null);
        first_name = (EditText) view.findViewById(R.id.first_name_txt);
        second_name = (EditText) view.findViewById(R.id.second_name_txt);
        email = (EditText) view.findViewById(R.id.email_txt);
        number = (EditText) view.findViewById(R.id.number_txt);
        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.maj)
                .setView(view)
                .setPositiveButton(android.R.string.ok,
                        new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {

                                    sendResult(Activity.RESULT_OK);
                            }
                        })
                .create();
    }



    private void sendResult(int resultCode)
    {
        if(getTargetFragment() == null)
        {
            return;
        }
        if(!first_name.getText().toString().isEmpty() && !second_name.getText().toString().isEmpty() && !email.getText().toString().isEmpty() && !number.getText().toString().isEmpty()) {
            Contact contact = new Contact(first_name.getText().toString(), second_name.getText().toString(), email.getText().toString(), number.getText().toString());
            contact.setId(id);
            ContactDB.get(getActivity()).updateContact(contact);
            Toast.makeText(getActivity() ,"Contact met a jour" , Toast.LENGTH_LONG).show();
        }
        Intent intent = new Intent();
        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);
    }

}
