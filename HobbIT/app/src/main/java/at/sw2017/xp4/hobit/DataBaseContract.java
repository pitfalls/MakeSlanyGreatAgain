package at.sw2017.xp4.hobit;

import android.provider.BaseColumns;

/**
 * Created by Gerd on 05.04.2017.
 */

public final class DataBaseContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private DataBaseContract() {}

    /* Inner class that defines the table contents */
    public static class GroupEntry implements BaseColumns {
        public static final String TABLE_NAME = "Groups";
        public static final String COLUMN_NAME_NAME = "Name";
        public static final String COLUMN_NAME_DESCRIPTION = "Description";
        public static final String COLUMN_NAME_HOBBY = "Hobby";
    }

    public static class HobbiesEntry implements BaseColumns {
        public static final String TABLE_NAME = "Hobbies";
        public static final String COLUMN_NAME_NAME = "Name";
        public static final String COLUMN_NAME_DESCRIPTION = "Description";
        public static final String COLUMN_NAME_LOCATION = "Location";
    }

    public static class UsersEntry implements BaseColumns {
        public static final String TABLE_NAME = "Users";
        public static final String COLUMN_NAME_NICKNAME = "NickName";
        public static final String COLUMN_NAME_FIRSTNAME = "FirstName";
        public static final String COLUMN_NAME_SURNAME = "SurName";
        public static final String COLUMN_NAME_LOCATION = "Location";
        public static final String COLUMN_NAME_PICTURE = "Picture";
    }
}