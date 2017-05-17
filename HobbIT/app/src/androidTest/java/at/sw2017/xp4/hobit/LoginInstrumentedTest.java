package at.sw2017.xp4.hobit;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;

/**
 * Created by Gerd on 17.05.2017.
 */

public class LoginInstrumentedTest {

    @Rule
    public ActivityTestRule<FacebookLogin> mActivityRule =
            new ActivityTestRule<>(FacebookLogin.class);

    @Test
    public void changeProperties() throws Exception {
        onView(withId(R.id.username)).perform(replaceText("john.doe@testuser.com"));
        onView(withId(R.id.password)).perform(replaceText("JohnDoe"));

        onView(withId(R.id.login)).perform(click());

        Thread.sleep(2000);

        assertEquals("test0000", Globals.getInstance().getUserID());
    }

    /*
    @Test
    public void fbLoginTest() throws Exception {
        if (!Globals.getInstance().getUserID().equals("")) {
            onView(withId(R.id.login_button)).perform(click());
            Globals.getInstance().setUserID("");
        }
        onView(withId(R.id.login_button)).perform(click());

        Thread.sleep(2000);

        assertEquals("fb1296393277116865", Globals.getInstance().getUserID());
    }
    */

    @Test
    public void registerTest() throws Exception {
        onView(withId(R.id.register)).perform(click());
    }
}
