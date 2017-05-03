package at.sw2017.xp4.hobit;

import android.app.Activity;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.Swipe;
import android.support.test.espresso.contrib.DrawerActions;
import android.support.test.espresso.contrib.NavigationViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.Gravity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.DrawerActions.open;
import static android.support.test.espresso.contrib.DrawerActions.openDrawer;
import static android.support.test.espresso.contrib.DrawerMatchers.isClosed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
//import static android.support.test.espresso.contrib.DrawerAction;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static at.sw2017.xp4.hobit.R.id.expandableListViewHobbyGroups;
import static org.junit.Assert.assertEquals;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

@RunWith(AndroidJUnit4.class)
public class GroupListViewInternalTests {

    @Rule
    public ActivityTestRule<ListHobbyGroups> mActivityRule = new ActivityTestRule<>(ListHobbyGroups.class);

    @Test
    public void openHobbyListViewViaSidebar() throws Exception {
        Thread.sleep(1000);
        // Context of the app under test.
        onView(withText("Category_test")).perform(click());
        onView(withText("Group_X")).perform(click());

        onView(withId(R.id. btn_join)).perform(click());


        /*onView(withId(R.id.activity_group_overview))
                .check(matches(isClosed(Gravity.LEFT))) // Left Drawer should be closed.
                .perform(open()); // Open Drawer
*/
        //onData().inAdapterView(withId(R.id.laptop)).perform(click());


        // Check that you Activity was opened.
/*        String expectedNoStatisticsText = InstrumentationRegistry.getTargetContext()
                .getString(R.string.no_item_available);
        onView(withId(R.id.no_statistics)).check(matches(withText(expectedNoStatisticsText)));
*/
        // onView(withId(R.id.acti))

//        Context appContext = InstrumentationRegistry.getTargetContext();
//        String s = appContext.getApplicationContext().getApplicationInfo().toString();
//        System.out.println(s);



        //intended(hasComponent(ExpectedActivity.class.getName()));
        Thread.sleep(1000);
    }



}
