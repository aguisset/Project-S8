package ma.hatim.ahadri.repertoire;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import ma.hatim.ahadri.repertoire.login_database.Contact;
import ma.hatim.ahadri.repertoire.login_database.ContactDB;


public class ContactsListFragment extends Fragment {

    private RecyclerView contactsRecyclerView;
    private  static String id ;
    private Button supprimer  ;
    private Button modifier ;
    private static List<Contact> sContacts = new ArrayList<>();
    private static final int ADD_CONTACT  = 0 ;
    private static final int UPDATE_CONTACT  = 1 ;
    private static final String TAG = "ContactList";
    private final String MAJ_DIALOG = "Maj Dialog" ;
    private ContactAdapter mAdapter ;


    public static ContactsListFragment newInstance() {
        return new ContactsListFragment();
    }

    public static String getID() {
        return id;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true); // To resolve the landscape case , I know it's not the best solution fro that !
        setHasOptionsMenu(true); // To add an Options Menu
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crime_list, container, false);
        contactsRecyclerView = (RecyclerView) view.findViewById(R.id.contact_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        contactsRecyclerView.setLayoutManager(linearLayoutManager);
        updateUI();
        return view;
    }

    private void updateUI() {
        ContactDB contactdb = ContactDB.get(getActivity());
        List<Contact> contacts = contactdb.getContacts();
        mAdapter = new ContactAdapter(contacts);
        contactsRecyclerView.setAdapter(mAdapter);
    }

    /*
        Creating the Products Holder class
     */


    private class ContactHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // The RecyclerView Items :
        private TextView full_name;
        private TextView email ;
        private TextView number ;
        private Contact cont ;

        public ContactHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            full_name = (TextView) itemView.findViewById(R.id.full_name);
            email = (TextView) itemView.findViewById(R.id.email);
            number = (TextView) itemView.findViewById(R.id.number);
            supprimer = (Button) itemView.findViewById(R.id.supprimer);
            modifier = (Button) itemView.findViewById(R.id.update);

        }


        public void bindProduct(final Contact contact) {
            full_name.setText(contact.getFirst_name().concat(" " + contact.getSecond_name()));
            email.setText(contact.getEmail());
            number.setText(contact.getNumber());
            cont = contact ;
            supprimer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    ContactDB.get(getActivity()).deleteContact(cont);
                    updateUI();
                }
            });
            modifier.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Toast.makeText(getActivity() , "Modifier un Contact" , Toast.LENGTH_LONG).show();
                    FragmentManager manager = getFragmentManager();
                    Update_fragment majFragment = new Update_fragment();
                    majFragment.setTargetFragment(ContactsListFragment.this, UPDATE_CONTACT);
                     id = contact.getId();
                    majFragment.show(manager, MAJ_DIALOG);
                }
            });
        }

        @Override
        public void onClick(View view)
        {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + number.getText().toString()));
            startActivity(callIntent);
        }
    }

    /*
        Creating the Products Adapter class
    */


    private class ContactAdapter extends RecyclerView.Adapter<ContactHolder> {
        private List<Contact> contacts = new LinkedList<>();

        public ContactAdapter(List<Contact> contactItems) {
            contacts = contactItems;

        }


        @Override
        public ContactHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            Log.i("SIZE" , getItemCount()+"");
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View view = inflater.inflate(R.layout.contact_layout, viewGroup, false);
            return new ContactHolder(view);
        }


        @Override
        public void onBindViewHolder(ContactHolder contactHolder, int position) {
            Contact contact = contacts.get(position);
            contactHolder.bindProduct(contact);
        }

        @Override
        public int getItemCount() {
            return contacts == null ? 0 : contacts.size();
        }
    }





    @Override
    public void onCreateOptionsMenu(Menu menu , MenuInflater inflater)
    {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.ajouter :
                Toast.makeText(getActivity() , "Ajouter un Contact" , Toast.LENGTH_LONG).show();
                FragmentManager manager = getFragmentManager();
                Add_fragment majFragment = new Add_fragment();
                majFragment.setTargetFragment(ContactsListFragment.this, ADD_CONTACT);
                majFragment.show(manager, MAJ_DIALOG);
                break ;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode , int resultCode , Intent data )
    {
        if( resultCode != Activity.RESULT_OK)
        {
            return;
        }
        updateUI();
    }

}









