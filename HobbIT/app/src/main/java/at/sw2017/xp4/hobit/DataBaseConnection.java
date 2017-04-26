package at.sw2017.xp4.hobit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gerd on 05.04.2017.
 */

public class DataBaseConnection {
    private DataBaseHelper dbHelper;
    private SQLiteDatabase db;

    private static DataBaseConnection instance;

    /**
     * private Constructor because this class should be a singleton
     */
    private DataBaseConnection(Context context) {
        dbHelper = new DataBaseHelper(context);

        try {
            dbHelper.createDataBase();
        } catch (IOException ioe) {
            throw new Error("Unable to create database");
        }

        try {
            dbHelper.openDataBase();
        } catch (SQLException sqle){
            throw sqle;
        }
        dbHelper.close();

        db = dbHelper.getWritableDatabase();
    }

    public static DataBaseConnection getInstance(Context context) {
        if (DataBaseConnection.instance == null) {
            instance = new DataBaseConnection(context);
        }

        return instance;
    }

    public User getCurrentUser() {
        String[] projection = {
                "_id",
                DataBaseContract.CurrentUserEntry.COLUMN_NAME_USERID,
        };


        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                DataBaseContract.CurrentUserEntry.COLUMN_NAME_USERID + " DESC";

        Cursor cursor = db.query(
                DataBaseContract.CurrentUserEntry.TABLE_NAME,                     // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );

        String id = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseContract.CurrentUserEntry.COLUMN_NAME_USERID));

        return getUser(id);
    }

    public boolean setCurrentUser(User user) {
        db.delete(DataBaseContract.CurrentUserEntry.TABLE_NAME, null, null);

        ContentValues values = new ContentValues();
        values.put("_id", user.getId());
        values.put(DataBaseContract.CurrentUserEntry.COLUMN_NAME_USERID,  user.getId());

        // Insert the new row, returning the primary key value of the new row
        if (db.insert(DataBaseContract.UsersEntry.TABLE_NAME, null, values) != -1) {
            return true;
        }
        return false;
    }

    public User getUser(String id) {
        String[] projection = {
                "_id",
                DataBaseContract.UsersEntry.COLUMN_NAME_NICKNAME,
                DataBaseContract.UsersEntry.COLUMN_NAME_FIRSTNAME,
                DataBaseContract.UsersEntry.COLUMN_NAME_SURNAME,
                DataBaseContract.UsersEntry.COLUMN_NAME_LOCATION
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = "_id = ?";
        String[] selectionArgs = { id };

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                DataBaseContract.UsersEntry.COLUMN_NAME_NICKNAME + " DESC";

        Cursor cursor = db.query(
                DataBaseContract.UsersEntry.TABLE_NAME,                     // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );

        ArrayList<User> users = new ArrayList<User>();
        while(cursor.moveToNext()) {
            users.add(new User(id,
                    cursor.getString(cursor.getColumnIndexOrThrow(DataBaseContract.UsersEntry.COLUMN_NAME_NICKNAME)),
                    cursor.getString(cursor.getColumnIndexOrThrow(DataBaseContract.UsersEntry.COLUMN_NAME_FIRSTNAME)),
                    cursor.getString(cursor.getColumnIndexOrThrow(DataBaseContract.UsersEntry.COLUMN_NAME_SURNAME)),
                    cursor.getString(cursor.getColumnIndexOrThrow(DataBaseContract.UsersEntry.COLUMN_NAME_LOCATION))
                    ));
        }
        cursor.close();

        if (users.isEmpty()) {
            return null;
        }

        return users.get(0);
    }

    public boolean createUser(User user) {
        ContentValues values = new ContentValues();
        values.put("_id", user.getId());
        values.put(DataBaseContract.UsersEntry.COLUMN_NAME_NICKNAME,  user.getNickName());
        values.put(DataBaseContract.UsersEntry.COLUMN_NAME_FIRSTNAME, user.getFirstName());
        values.put(DataBaseContract.UsersEntry.COLUMN_NAME_SURNAME,   user.getSurName());
        values.put(DataBaseContract.UsersEntry.COLUMN_NAME_LOCATION,  user.getLocation());

        // Insert the new row, returning the primary key value of the new row
        if (db.insert(DataBaseContract.UsersEntry.TABLE_NAME, null, values) != -1) {
            return true;
        }

        return false;
    }

    public boolean updateUser(User user) {
        ContentValues values = new ContentValues();
        values.put(DataBaseContract.UsersEntry.COLUMN_NAME_NICKNAME,  user.getNickName());
        values.put(DataBaseContract.UsersEntry.COLUMN_NAME_FIRSTNAME, user.getFirstName());
        values.put(DataBaseContract.UsersEntry.COLUMN_NAME_SURNAME,   user.getSurName());
        values.put(DataBaseContract.UsersEntry.COLUMN_NAME_LOCATION,  user.getLocation());

        String selection = "_id LIKE ?";
        String[] selectionArgs = { user.getId() };

        int count = db.update(DataBaseContract.UsersEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs);

        if (count > 0) {
            return true;
        }

        return false;
    }

    public boolean deleteUser(User user) {
        return delUser(user.getId());
    }

    public boolean deleteUser(String id) {
        return delUser(id);
    }

    private boolean delUser(String id) {
        String selection = "_id LIKE ?";

        String[] selectionArgs = { id };

        int count = db.delete(DataBaseContract.UsersEntry.TABLE_NAME, selection, selectionArgs);

        if (count > 0) {
            return true;
        }
        return false;
    }
}
