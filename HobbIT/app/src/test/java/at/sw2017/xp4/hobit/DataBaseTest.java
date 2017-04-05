package at.sw2017.xp4.hobit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static java.sql.Types.NULL;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;

/**
 * Created by feldh on 05.04.2017.
 */


public class DataBaseTest {
    /*
    *
    * User anlegen, löschen, updaten, hobbies und group, join group
    *
    * */

   /* @Before
    public void setUp() throws Exception {
        getTargetContext().deleteDatabase(DatabaseHelper.DB_NAME);
        database = new DatabaseHelper(getTargetContext());
    }*/

   DataBaseConnection dbConnection;


    @Before
    public void setupDataBase() throws Exception{
        dbConnection = DataBaseConnection.getInstance();
    }

   public void setUpNewUser(String id, String nick, String forname, String surename, String location)
   {
       //User currentUser = new User("currentUserId", "Nick", "Nicolai", "Schefe", "8010 Graz");
       User currentUser = new User(id, nick, forname, surename, location);
       dbConnection.createUser(currentUser);
   }

    public int updateUser(String id, String nick, String forname, String surename, String location)
    {
        try {
            User newUser = dbConnection.getUser(id);
            newUser.setNickName(nick);
            newUser.setFirstName(forname);
            newUser.setSurName(surename);
            newUser.setLocation(location);

            dbConnection.updateUser(newUser);

            return 0;
        }
        catch (NullPointerException nullex)
        {
            return -1;
        }
    }

    public int deleteUser(String id)
    {
        try {
            User newUser = dbConnection.getUser(id);

            dbConnection.deleteUser(id);
            return 0;
        }
        catch (NullPointerException nullex)
        {
            return -1;
        }
    }

    @Test
    public void databaseConnectionCreateUserTest() throws Exception
    {
        User newUser = dbConnection.getUser("currentUserId");

        assertNotNull(newUser);

        assertEquals(newUser.getId(), "currentUserId");
        assertEquals(newUser.getNickName(), "Nick");
        assertEquals(newUser.getFirstName(), "Nicolai");
        assertEquals(newUser.getSurName(), "Schefe");
        assertEquals(newUser.getLocation(), "8010 Graz");
    }

    @Test
    public void databaseConnectionUpdateUser() throws Exception
    {
        setUpNewUser("currentUserId", "Nick", "Nicolai", "Schefe", "8010 Graz");

        User newUser = dbConnection.getUser("currentUserId");

        assertNotNull(newUser);
        assertEquals(newUser.getId(), "currentUserId");
        assertEquals(newUser.getNickName(), "Nick");
        assertEquals(newUser.getFirstName(), "Nicolai");
        assertEquals(newUser.getSurName(), "Schefe");
        assertEquals(newUser.getLocation(), "8010 Graz");

        updateUser(newUser.getId(), "Alli", "Alan", "Walker", "DJ Gasse 3000");

        newUser = dbConnection.getUser("currentUserId");

        assertNotNull(newUser);

        assertEquals(newUser.getId(), "currentUserId");
        assertEquals(newUser.getNickName(), "Alli");
        assertEquals(newUser.getFirstName(), "Alan");
        assertEquals(newUser.getSurName(), "Walker");
        assertEquals(newUser.getLocation(), "DJ Gasse 3000");
    }

    @Test
    public void databaseDeleteUser() throws Exception {

        setUpNewUser("currentUserId", "Nick", "Nicolai", "Schefe", "8010 Graz");

        User newUser = dbConnection.getUser("currentUserId");

        assertNotNull(newUser);

        assertEquals(deleteUser("currentUserId") , true);

        newUser = dbConnection.getUser("currentUserId");

        assertNull(newUser);
    }
}


