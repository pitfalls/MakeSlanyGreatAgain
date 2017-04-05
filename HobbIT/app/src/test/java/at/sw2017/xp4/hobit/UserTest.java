package at.sw2017.xp4.hobit;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Gerd on 05.04.2017.
 */

public class UserTest {

    // Create User with constructor
    @Test
    public void userCreate1Test() throws Exception {
        User testUser =
                new User("testUserID", "Franzl", "Gandalf", "Franzbrand", "8010 Graz");

        assertEquals(testUser.getId(),        "testUserID");
        assertEquals(testUser.getNickName(),  "Franzl");
        assertEquals(testUser.getFirstName(), "Gandalf");
        assertEquals(testUser.getSurName(),   "Franzbrand");
        assertEquals(testUser.getLocation(),  "8010 Graz");
    }

    // Create User with empty constructor an set properties
    @Test
    public void userCreate2Test() throws Exception {
        User testUser = new User("testUserID");

        testUser.setNickName("Franzl");
        testUser.setFirstName("Gandalf");
        testUser.setSurName("Franzbrand");
        testUser.setLocation("8010 Graz");

        assertEquals(testUser.getId(),        "testUserID");
        assertEquals(testUser.getNickName(),  "Franzl");
        assertEquals(testUser.getFirstName(), "Gandalf");
        assertEquals(testUser.getSurName(),   "Franzbrand");
        assertEquals(testUser.getLocation(),  "8010 Graz");
    }
}
