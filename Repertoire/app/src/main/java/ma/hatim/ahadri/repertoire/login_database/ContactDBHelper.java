package ma.hatim.ahadri.repertoire.login_database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by hatim on 31/03/16.
 */
public class ContactDBHelper extends SQLiteOpenHelper
{
    private static final int VERSION = 1 ;
    private static final String TAG = "DataBase_Helper" ; // For debugging purpose
    private static final String DATABASE_NAME = "contactDB" ;
    private static final String CREATE_DB = "create table " + ContactDBSchema.ContactTable.NAME +
            "( " +
            ContactDBSchema.ContactTable.Columuns.ID + " integer primary key autoincrement , " +
            ContactDBSchema.ContactTable.Columuns.FIRST_NAME + "  varchar not null , " +
            ContactDBSchema.ContactTable.Columuns.SECOND_NAME + "  varchar not null ," +
            ContactDBSchema.ContactTable.Columuns.EMAIL + "  varchar not null ," +
            ContactDBSchema.ContactTable.Columuns.NUMBER + "  varchar not null " +
            ") ;" ;


    public ContactDBHelper(Context context)
    {
        super(context , DATABASE_NAME , null , VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db)
    {
        Log.i(TAG , "Creating the dataBase !");
        db.execSQL(CREATE_DB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db , int oldVersion , int newVersion)
    {
        db.execSQL("drop table" + ContactDBSchema.ContactTable.NAME);
        onCreate(db);
    }
}
