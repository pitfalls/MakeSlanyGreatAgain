
package at.sw2017.xp4.hobit;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.Gravity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.DrawerActions.open;
import static android.support.test.espresso.contrib.DrawerMatchers.isClosed;
import static android.support.test.espresso.matcher.RootMatchers.isDialog;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */


@RunWith(AndroidJUnit4.class)
public class MainInstumentedTests {

    @Rule
    public ActivityTestRule<HobIT_Main> mActivityRule = new ActivityTestRule<>(HobIT_Main.class);


    @Test
    public void testGroupOverview() throws Exception {

        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.LEFT))) // Left Drawer should be closed.
                .perform(open()); // Open Drawer
        onView(withText("Group Overview")).perform(click());
    }

    @Test
    public void testJoinGroup() throws Exception {

        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.LEFT))) // Left Drawer should be closed.
                .perform(open()); // Open Drawer
        onView(withText("Join Group")).perform(click());
    }

    @Test
    public void testGroupCreation() throws Exception {

        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.LEFT))) // Left Drawer should be closed.
                .perform(open()); // Open Drawer
        onView(withText("Group Creation")).perform(click());
    }

    @Test
    public void test() throws Exception {

        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.LEFT))) // Left Drawer should be closed.
                .perform(open()); // Open Drawer
        onView(withText("Group Creation")).perform(click());
    }

        /* onView(withId(R.id.spinnerHobbies)).check(matches(withSpinnerText(containsString("Football"))));

        onView(withId(R.id.spinnerHobbies)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Tennis"))).perform(click());
        onView(withId(R.id.spinnerHobbies)).check(matches(withSpinnerText(containsString("Tennis"))));

        onView(withId(R.id.spinnerHobbies)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Cycling"))).perform(click());
        onView(withId(R.id.spinnerHobbies)).check(matches(withSpinnerText(containsString("Cycling"))));

        Thread.sleep(1500);

        onView(withId(R.id.spinnerLocation)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Graz"))).perform(click());
        onView(withId(R.id.spinnerLocation)).check(matches(withSpinnerText(containsString("Graz"))));

        onView(withId(R.id.spinnerLocation)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Wien"))).perform(click());
        onView(withId(R.id.spinnerLocation)).check(matches(withSpinnerText(containsString("Wien"))));

        onView(withId(R.id.spinnerLocation)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Salzburg"))).perform(click());
        onView(withId(R.id.spinnerLocation)).check(matches(withSpinnerText(containsString("Salzburg"))));*/
    }
}