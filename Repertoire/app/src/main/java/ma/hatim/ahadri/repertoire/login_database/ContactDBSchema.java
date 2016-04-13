package ma.hatim.ahadri.repertoire.login_database;

/**
 * Created by hatim on 31/03/16.
 */
public class ContactDBSchema
{

    public static final class ContactTable
    {
        public static final String NAME = "contact" ;

        public static final class Columuns
        {
            public static final String ID = "id" ;
            public static final String  FIRST_NAME = "first_name" ;
            public static final String  SECOND_NAME = "second_name" ;
            public static final String EMAIL = "email" ;
            public static final String NUMBER = "number" ;
        }
    }
}
