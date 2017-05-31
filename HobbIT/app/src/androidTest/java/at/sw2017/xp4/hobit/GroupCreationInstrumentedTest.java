package at.sw2017.xp4.hobit;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;

/**
 * Created by Gerd on 31.05.2017.
 */

@RunWith(AndroidJUnit4.class)
public class GroupCreationInstrumentedTest {

    @Rule
    public ActivityTestRule<GroupCreation> mActivityRule =
            new ActivityTestRule<>(GroupCreation.class);

    @Test
    public void testLocationSpinner() throws Exception {
        onView(withId(R.id.spinner_location)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Graz"))).perform(click());
        onView(withId(R.id.spinner_location)).check(matches(withSpinnerText(containsString("Graz"))));
    }

    @Test
    public void testHobbySpinner() throws Exception {
        onView(withId(R.id.spinner_hobby)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Football"))).perform(click());
        onView(withId(R.id.spinner_hobby)).check(matches(withSpinnerText(containsString("Football"))));
    }

    @Test
    public void createGroup() throws Exception {
        onView(withId(R.id.spinner_location)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Graz"))).perform(click());

        onView(withId(R.id.spinner_hobby)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Sports"))).perform(click());

        onView(withId(R.id.editText_Groupname)).perform(replaceText("TEST"));
        onView(withId(R.id.editText_Description)).perform(replaceText("The best of the best of the best!"));

        onView(withId(R.id.btn_save)).perform(click());
    }
}
