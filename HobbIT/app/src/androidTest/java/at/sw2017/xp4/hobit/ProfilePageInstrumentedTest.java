package at.sw2017.xp4.hobit;

import android.app.Activity;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;
import android.widget.TextView;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.app.PendingIntent.getActivity;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
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

    @Rule
    public ActivityTestRule< HobIT_Main > mActivityRule = new
            ActivityTestRule<>( HobIT_Main.class );

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("at.sw2017.xp4.hobit", appContext.getPackageName());
    }

    @Test
    public void testOpenEditProfileView() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        openActionBarOverflowOrOptionsMenu(appContext);

        onView( withText("Edit Profile")).perform(click());
    }

    @Test
    public void testClickTextfieldsAndElements() throws Exception {
        testOpenEditProfileView();

        onView( withId(R.id.editTextProfileSurename)).perform(click());
        Espresso.
        onView( withId(R.id.imageViewProfilePicture)).perform(click());

        onView( withId(R.id.editTextProfileForename)).perform(click());

        onView( withId(R.id.editTextProfileNickname)).perform(click());

        onView( withId(R.id.editTextProfileLocation)).perform(click());
        //onView(withContentDescription("Navigate back")).perform(click());

        Espresso.pressBack();
        onView( withId(R.id.editTextProfileDescription)).perform(click());

        Espresso.closeSoftKeyboard();
        onView( withId(R.id.buttonEditInterests)).perform(click());
    }

    @Test
    public void testEditTextFieldsWithDefaultValues() throws Exception {

        EditText et;
        testOpenEditProfileView();

        onView( withId(R.id.editTextProfileSurename)).perform(typeText("Doe"));
        onView( withId(R.id.editTextProfileSurename)).check(matches(withText("Doe")));

        onView( withId(R.id.editTextProfileForename)).perform(typeText("John"));
        onView( withId(R.id.editTextProfileForename)).check(matches(withText("John")));

        onView( withId(R.id.editTextProfileNickname)).perform(typeText("JD"));
        onView( withId(R.id.editTextProfileNickname)).check(matches(withText("JD")));

        onView( withId(R.id.editTextProfileLocation)).perform(typeText("Nowhere"));
        onView( withId(R.id.editTextProfileLocation)).check(matches(withText("Nowhere")));

        Espresso.pressBack();
        onView( withId(R.id.editTextProfileDescription)).perform(typeText("I'm the devil in disguise"));
        onView(withId(R.id.editTextProfileDescription)).check(matches(withText("I'm the devil in disguise")));
    }
}
