package at.sw2017.xp4.hobit;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.Swipe;
import android.support.test.espresso.contrib.DrawerActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
//import static android.support.test.espresso.contrib.DrawerAction;
import static org.junit.Assert.assertEquals;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class HobbyListViewInstrumentedTest {

    @Rule
    public ActivityTestRule<HobIT_Main> mActivityRule = new ActivityTestRule<>(HobIT_Main.class);

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("at.sw2017.xp4.hobit", appContext.getPackageName());
    }

    @Test
    public void openHobbyListViewViaSidebar() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        //Espresso.pressBack();
        onView(withId(R.id.main_view)).perform(swipeRight());
        //onView(withId(R.id.main_view)).perform(DrawerActions.open());
    }
}
