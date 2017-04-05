package at.sw2017.xp4.hobit;

/**
 * Created by Gerd on 05.04.2017.
 */

import org.junit.Test;

public class HobbyTest {
    // Create Hobby with Constructor
    @Test
    public void hobbyCreate1Test() throws Exception {
        User testHobby =
                new User("AFancyHobby", "where you do fancy stuff", "8010 Graz");


        assertEquals(testHobby.name, "AFancyHobby");
        assertEquals(testHobby.description, "where you do fancy stuff");
        assertEquals(testHobby.location, "8010 Graz");
    }

    // Create User with empty constructor an set properties
    @Test
    public void hobbyCreate2Test() throws Exception {
        User testHobby = new User();

        testHobby.setNickName("AFancyHobby");
        testHobby.setFirstName("where you do fancy stuff");
        testHobby.setLocation("8010 Graz");

        assertEquals(testHobby.nickName, "Franzl");
        assertEquals(testHobby.firstkName, "Gandalf");
        assertEquals(testHobby.location, "8010 Graz");
    }
}
