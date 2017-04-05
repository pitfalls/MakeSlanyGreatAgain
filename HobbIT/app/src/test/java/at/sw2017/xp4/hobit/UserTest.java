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


        assertEquals(testUser.id, "testUserID");
        assertEquals(testUser.nickName, "Franzl");
        assertEquals(testUser.firstkName, "Gandalf");
        assertEquals(testUser.surName, "Franzbrand");
        assertEquals(testUser.location, "8010 Graz");
    }

    // Create User with empty constructor an set properties
    @Test
    public void userCreate2Test() throws Exception {
        User testUser = new User();

        testUser.setId("testUserID");
        testUser.setNickName("Franzl");
        testUser.setFirstName("Gandalf");
        testUser.setSurName("Franzbrand");
        testUser.setLocation("8010 Graz");

        assertEquals(testUser.id, "testUserID");
        assertEquals(testUser.nickName, "Franzl");
        assertEquals(testUser.firstkName, "Gandalf");
        assertEquals(testUser.surName, "Franzbrand");
        assertEquals(testUser.location, "8010 Graz");
    }
}
