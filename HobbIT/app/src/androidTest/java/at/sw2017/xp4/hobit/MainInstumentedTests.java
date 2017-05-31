
package at.sw2017.xp4.hobit;

import android.support.test.espresso.contrib.NavigationViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.Gravity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeUp;
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

  /*  @Test
    public void testArrayListHouseandGarden() throws Exception
    {
        onView(withText("House & garden")).perform(click());
    }

    @Test
    public void testArrayListFashion() throws Exception
    {
        onView(withText("Fashion")).perform(click());
    }

    @Test
    public void testArrayListLiterature() throws Exception
    {
        Globals.getInstance().setUserID("1");
        Thread.sleep(10000);
        onView(withText("Literature")).perform(click());
    }*/

    @Test
    public void testLogout() throws Exception
    {
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        Thread.sleep(2500);
        onView(withText("Login")).perform(click());
    }

    @Test
    public void testFab() throws Exception
    {
        onView(withId(R.id.fab)).perform(click());
    }

    @Test
    public void testSettingsMenue() throws Exception {
        // Click menu
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        Thread.sleep(2500);
        // Choose item "Settings"
        onView(withText("Settings")).perform(click());
        //SETTINGS TUT NOCH NIX?
    }

    @Test
    public void testEditProfile() throws Exception
    {
        // Click menu
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        Thread.sleep(2500);
        onView(withText("Edit Profile")).perform(click());
    }

    @Test
    public void testthreedot() throws Exception
    {
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        Thread.sleep(2500);
    }

    @Test
    public void testeverything() throws Exception
    {
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        Thread.sleep(2500);
        //onView(withText("Edit Profile")).perform(click());
    }
}
