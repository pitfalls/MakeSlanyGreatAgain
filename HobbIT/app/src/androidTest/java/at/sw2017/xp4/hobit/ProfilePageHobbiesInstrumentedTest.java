
package at.sw2017.xp4.hobit;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */


@RunWith(AndroidJUnit4.class)
public class ProfilePageHobbiesInstrumentedTest {

    @Rule
    public ActivityTestRule<EditProfileHobbiesActivity> mActivityRule = new ActivityTestRule<>(EditProfileHobbiesActivity.class);



    @Test
    public void testContentExistence() throws Exception {

        onView(withText("House and garden")).perform(click());
        onView(withText("Fashion")).perform(click());
        onView(withText("Literature")).perform(click());
        onView(withText("Games")).perform(click());
        onView(withText("Sports")).perform(click());
        onView(withText("Crafting")).perform(click());
        onView(withText("Computer and consoles")).perform(click());
        onView(withText("Music and instruments")).perform(click());
        onView(withText("Dancing")).perform(click());
        onView(withText("Art")).perform(click());
        onView(withText("Astronomy and universe")).perform(click());
        onView(withText("Collecting")).perform(click());
        onView(withText("Science")).perform(click());
        onView(withText("Racing")).perform(click());
        onView(withText("TV and cinema")).perform(click());
        onView(withText("Traveling")).perform(click());
        onView(withText("Extreme sports")).perform(click());
        onView(withText("Health and fitness")).perform(click());
        onView(withText("Other")).perform(click());
        onView(withText("Continue")).perform(click());
    }
}
