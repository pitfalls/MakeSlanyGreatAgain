package at.sw2017.xp4.hobit;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertEquals;
import static org.hamcrest.Matchers.anything;

/**
 * Created by alex on 05.04.17.
 */


@RunWith(AndroidJUnit4.class)
public class GroupCreationInstrumentedTest {



    @Rule
    public ActivityTestRule<GroupCreation> mActivityRule = new ActivityTestRule<>(GroupCreation.class);


    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("at.sw2017.xp4.hobit", appContext.getPackageName());
    }


    @Test
    public void SaveGroup() throws Exception {

        onView( withId(R.id.editText_groupname)).perform(click());
        onView( withId(R.id.editText_groupname)).perform(typeText("OhHappyDay"));
        onView( withId(R.id.editText_groupname)).check(matches(withText("OhHappyDay")));


        Espresso.closeSoftKeyboard();

        onView( withId(R.id.spinner_hobby)).perform(click());
        onData(anything()).atPosition(2).perform(click());

        onView( withId(R.id.spinner_location)).perform(click());
        onData(anything()).atPosition(1).perform(click());


        onView( withId(R.id.editText_description)).perform(click());
        onView( withId(R.id.editText_description)).perform(typeText("Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet."));
        onView( withId(R.id.editText_description)).check(matches(withText("Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.")));

        Espresso.closeSoftKeyboard();

        onView( withId(R.id.btn_save)).perform(click());
    }



    @Test
    public void CancelGroup() throws Exception {

        onView( withId(R.id.editText_groupname)).perform(click());
        onView( withId(R.id.editText_groupname)).perform(typeText("OhHappyDay"));
        onView( withId(R.id.editText_groupname)).check(matches(withText("OhHappyDay")));

        Espresso.closeSoftKeyboard();

        onView( withId(R.id.spinner_hobby)).perform(click());
        onData(anything()).atPosition(2).perform(click());

        onView( withId(R.id.spinner_location)).perform(click());
        onData(anything()).atPosition(1).perform(click());


        onView( withId(R.id.editText_description)).perform(click());
        onView( withId(R.id.editText_description)).perform(typeText("Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet."));
        onView( withId(R.id.editText_description)).check(matches(withText("Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.")));

        Espresso.closeSoftKeyboard();

        onView( withId(R.id.btn_cancel)).perform(click());
    }




}
