package at.sw2017.xp4.hobit;

import org.junit.Test;

/**
 * Created by Gerd on 05.04.2017.
 */

public class GroupTest {

    // Create Group with constructor
    @Test
    public void groupCreate1Test() throws Exception {
        Group testGroup =
                new Group("MyTestGroup", "just for testing");

        assertEquals(testGroup.name, "MyTestGroup");
        assertEquals(testGroup.description, "just for testing");
    }

    // Create Group with empty constructor an set properties
    @Test
    public void groupCreate2Test() throws Exception {
        Group testGroup = new Group();

        testGroup.setName("MyTestGroup");
        testGroup.setDescription("just for testing");

        assertEquals(testGroup.name, "MyTestGroup");
        assertEquals(testGroup.description, "just for testing");
    }
}
