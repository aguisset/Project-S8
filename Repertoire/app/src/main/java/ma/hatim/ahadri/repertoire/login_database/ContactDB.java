package ma.hatim.ahadri.repertoire.login_database;

import android.app.Application;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.List;


/**
 * Created by hatim on 31/03/16.
 */
public class ContactDB  extends Application// A Singleton class
{
    private static ContactDB sContactDB;
    private Context context ;
    private SQLiteDatabase db ;
    private final String Tag = "DataBase_CRUD" ;


    public static ContactDB get(Context context)
    {
        if(sContactDB == null)
        {
            sContactDB = new ContactDB(context);
        }
        return sContactDB;
    }

    private ContactDB(Context pcontext)
    {
        this.context = pcontext.getApplicationContext();
        db =new ContactDBHelper(context).getWritableDatabase();
    }


    public List<Contact> getContacts()
    {
        //   Verification using the given login if exists !
        Cursor cursor = db.query(
                ContactDBSchema.ContactTable.NAME ,
                null , // null to select all columns
                null,
                null,
                null ,
                null ,
                null
        );


        return new ContactCursorWrapper(cursor).getContacts() ;
    }

    public void addContact(Contact contact)
    {
        // Add the given login to the database !
        ContentValues values = getContentValues(contact);
        db.insert(ContactDBSchema.ContactTable.NAME, null, values);
    }

    public void updateContact(Contact contact)
    {
        ContentValues values = getContentValues(contact);
        db.update(ContactDBSchema.ContactTable.NAME, values, ContactDBSchema.ContactTable.Columuns.ID + " = ?", new String[]{contact.getId()});
        Log.i(Tag, "Acount Updated !");
    }
    public void deleteContact(Contact contact)
    {
        ContentValues values = getContentValues(contact);
        db.delete(ContactDBSchema.ContactTable.NAME , ContactDBSchema.ContactTable.Columuns.ID +" =  ? ",new String[]{contact.getId()});
        Log.i(Tag, "Acount Removed  !");
    }
    private static ContentValues getContentValues(Contact contact)
    {
        ContentValues values = new ContentValues();
        values.put(ContactDBSchema.ContactTable.Columuns.FIRST_NAME , contact.getFirst_name());
        values.put(ContactDBSchema.ContactTable.Columuns.SECOND_NAME , contact.getSecond_name());
        values.put(ContactDBSchema.ContactTable.Columuns.EMAIL , contact.getEmail());
        values.put(ContactDBSchema.ContactTable.Columuns.NUMBER , contact.getNumber());

        return values ;
    }
}
