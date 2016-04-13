package ma.hatim.ahadri.repertoire.login_database;

import android.database.Cursor;
import android.database.CursorWrapper;
import java.util.LinkedList;
import java.util.List;


public class ContactCursorWrapper extends CursorWrapper
{

    public ContactCursorWrapper(Cursor cursor)
    {
        super(cursor);
    }

    public List<Contact> getContacts() {
        List<Contact> contacts = new LinkedList<>();
        if (this.getCount() == 0) // If the cursor is empty
        {
            return null;
        } else {
            Contact contact = new Contact();
            try {
                this.moveToFirst();
                while (!this.isAfterLast())
                {
                    String first_name = getString(getColumnIndex(ContactDBSchema.ContactTable.Columuns.FIRST_NAME));
                    String second_name = getString(getColumnIndex(ContactDBSchema.ContactTable.Columuns.SECOND_NAME));
                    String email = getString(getColumnIndex(ContactDBSchema.ContactTable.Columuns.EMAIL));
                    String number = getString(getColumnIndex(ContactDBSchema.ContactTable.Columuns.NUMBER));
                    String id = getString(getColumnIndex(ContactDBSchema.ContactTable.Columuns.ID));
                    contact.setFirst_name(first_name);
                    contact.setId(id);
                    contact.setSecond_name(second_name);
                    contact.setEmail(email);
                    contact.setNumber(number);
                    contacts.add(contact);
                    contact = new Contact();
                    this.moveToNext();
                }
                return contacts;
            } finally {
                this.close();
            }

        }

    }}
