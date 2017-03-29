package at.sw2017.xp4.hobit;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.app.PendingIntent.getActivity;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
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
    public void testEditProfileEntryInMenu() throws Exception {
       // onView( withContentDescription(R.string.))
       // System.out.print(getActivity().toString());
        Context appContext = InstrumentationRegistry.getTargetContext();
        openActionBarOverflowOrOptionsMenu(appContext);

        onView( withText("Edit Profile")).perform(click());
        //onView( withId(R.id.action_bar)).perform(click());
        // Context of the app under test.
        //onView( withId(R.id.action_edit_profile)).perform(click());
    }
}
