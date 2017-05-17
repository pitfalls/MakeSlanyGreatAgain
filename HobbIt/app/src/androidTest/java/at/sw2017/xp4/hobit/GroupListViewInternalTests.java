package at.sw2017.xp4.hobit;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.contrib.DrawerActions.open;
import static android.support.test.espresso.contrib.DrawerActions.openDrawer;
import static android.support.test.espresso.contrib.DrawerMatchers.isClosed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
//import static android.support.test.espresso.contrib.DrawerAction;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

@RunWith(AndroidJUnit4.class)
public class GroupListViewInternalTests {

    @Rule
    public ActivityTestRule<HobbyGroupListActivity> mActivityRule = new ActivityTestRule<>(HobbyGroupListActivity.class);

    @Test
    public void joinButtonTest() throws Exception {
        Thread.sleep(1000);

        onView(withText("Category_test")).perform(click());
        onView(withText("Group_X")).perform(click());

        onView(withId(R.id. btn_join)).perform(click());
        Thread.sleep(1000);
    }

    @Test
    public void backButtonTest() throws Exception {
        Thread.sleep(1000);

        onView(withText("Boobs")).perform(click());
        onView(withText("Pamela Anderson")).perform(click());

        onView(withId(R.id. btn_back)).perform(click());
        Thread.sleep(1000);
    }

    @Test
    public void navigationTest() throws Exception {
        Thread.sleep(1000);

        onView(withText("Boobs")).perform(click());
        onView(withText("Pamela Anderson")).perform(click());
        onView(withId(R.id. btn_back)).perform(click());

        Thread.sleep(1000);

        onView(withText("Category_test")).perform(click());
        onView(withText("Group_X")).perform(click());
        onView(withId(R.id. btn_join)).perform(click());
        onView(withId(R.id. btn_back)).perform(click());

        Thread.sleep(1000);

        onView(withText("Group_Y")).perform(click());
        onView(withId(R.id. btn_join)).perform(click());
        onView(withId(R.id. btn_back)).perform(click());

        Thread.sleep(1000);

        onView(withText("Megan Fox")).perform(click());
        onView(withId(R.id. btn_join)).perform(click());
        onView(withId(R.id. btn_back)).perform(click());

        Thread.sleep(1000);

        onView(withText("Megan Fox")).perform(click());
        onView(withId(R.id. btn_back)).perform(click());

        Thread.sleep(1000);
    }


    /*
    This test is for the case a Leave Button gets implemented

    @Test
    public void leaveButtonTest() throws Exception {
        Thread.sleep(1000);

        onView(withText("Category_test")).perform(click());
        onView(withText("Group_X")).perform(click());
        onView(withId(R.id. btn_join)).perform(click());
        onView(withId(R.id. btn_back)).perform(click());

        Thread.sleep(1000);

        onView(withText("Category_test")).perform(click());
        onView(withText("Group_X")).perform(click());
        onView(withId(R.id. btn_leave)).perform(click());
        onView(withId(R.id. btn_back)).perform(click());
        Thread.sleep(1000);
    }*/
}
