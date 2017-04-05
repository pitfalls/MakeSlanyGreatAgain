package at.sw2017.xp4.hobit;

/**
 * Created by Gerd on 05.04.2017.
 */

import org.junit.Test;

public class HobbyTest {
    // Create Hobby with Constructor
    @Test
    public void hobbyCreate1Test() throws Exception {
        Hobby testHobby =
                new Hobby("AFancyHobby", "where you do fancy stuff", "8010 Graz");

        assertEquals(testHobby.getName(),        "AFancyHobby");
        assertEquals(testHobby.getDescription(), "where you do fancy stuff");
        assertEquals(testHobby.getLocation(),    "8010 Graz");
    }

    // Create User with empty constructor an set properties
    @Test
    public void hobbyCreate2Test() throws Exception {
        Hobby testHobby = new Hobby();

        testHobby.setName("AFancyHobby");
        testHobby.setDescription("where you do fancy stuff");
        testHobby.setLocation("8010 Graz");

        assertEquals(testHobby.getName(),        "AFancyHobby");
        assertEquals(testHobby.getDescription(), "where you do fancy stuff");
        assertEquals(testHobby.getLocation(),    "8010 Graz");
    }
}
