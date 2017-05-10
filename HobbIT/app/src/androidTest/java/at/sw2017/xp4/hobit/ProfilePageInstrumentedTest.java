package at.sw2017.xp4.hobit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase;
import android.test.ActivityInstrumentationTestCase2;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.app.PendingIntent.getActivity;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.DrawerActions.open;
import static android.support.test.espresso.contrib.DrawerMatchers.isClosed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

/**
 * Created by andy on 22.03.2017.
 */

@RunWith(AndroidJUnit4.class)
public class ProfilePageInstrumentedTest {

    Intent intent;

    @Rule
    public ActivityTestRule<EditProfileActivity> mActivityRule = new ActivityTestRule<>(
            EditProfileActivity.class,
            true,
            false); // Activity is not launched immediately


    @Before
    public void setUp() {
        Globals.getInstance().setUserID("test0000");
    }

    @Test
    public void readProperties() throws Exception {
        mActivityRule.launchActivity(intent);

        onView(withId(R.id.editTextProfileNickname)).check(matches(withText("Johnny")));
        onView(withId(R.id.editTextProfileForename)).check(matches(withText("John")));
        onView(withId(R.id.editTextProfileSurename)).check(matches(withText("Doe")));
        onView(withId(R.id.editTextProfileLocation)).check(matches(withText("Graz")));

        Thread.sleep(3000);
    }

    @Test
    public void changeProperties() throws Exception {
        mActivityRule.launchActivity(intent);

        onView(withId(R.id.editTextProfileNickname)).perform(replaceText("Il Dottore"));
        onView(withId(R.id.editTextProfileForename)).perform(replaceText("Valentino"));
        onView(withId(R.id.editTextProfileSurename)).perform(replaceText("Rossi"));
        onView(withId(R.id.editTextProfileLocation)).perform(replaceText("Italy"));

        onView(withId(R.id.ButtonSave)).perform(click());

        onView(withId(R.id.editTextProfileNickname)).check(matches(withText("Il Dottore")));
        onView(withId(R.id.editTextProfileForename)).check(matches(withText("Valentino")));
        onView(withId(R.id.editTextProfileSurename)).check(matches(withText("Rossi")));
        onView(withId(R.id.editTextProfileLocation)).check(matches(withText("Italy")));

        Thread.sleep(3000);
    }

    @Test
    public void readPropertiesAfterChange() throws Exception {
        mActivityRule.launchActivity(intent);

        onView(withId(R.id.editTextProfileNickname)).check(matches(withText("Il Dottore")));
        onView(withId(R.id.editTextProfileForename)).check(matches(withText("Valentino")));
        onView(withId(R.id.editTextProfileSurename)).check(matches(withText("Rossi")));
        onView(withId(R.id.editTextProfileLocation)).check(matches(withText("Italy")));

        Thread.sleep(3000);
    }
}
