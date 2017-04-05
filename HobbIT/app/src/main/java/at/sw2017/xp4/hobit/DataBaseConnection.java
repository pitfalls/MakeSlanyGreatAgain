package at.sw2017.xp4.hobit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.io.IOException;

/**
 * Created by Gerd on 05.04.2017.
 */

public class DataBaseConnection {
    private DataBaseHelper dbHelper;
    private SQLiteDatabase db;

    private DataBaseConnection connection = null;

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

    public DataBaseConnection getConnection(Context context) {
        if (connection.equals(null)) {
            connection = new DataBaseConnection(context);
        }

        return connection;
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
