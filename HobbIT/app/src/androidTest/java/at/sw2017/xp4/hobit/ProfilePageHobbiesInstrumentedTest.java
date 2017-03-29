
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

        onView(withText("Swimming")).perform(click());
        onView(withText("Hiking")).perform(click());
        onView(withText("Fitness studio")).perform(click());
        onView(withText("Playing cards")).perform(click());
        onView(withText("Computer Games")).perform(click());
        onView(withText("Solve quiz")).perform(click());
        onView(withText("Ride a bicycle")).perform(click());
        onView(withText("Puzzle")).perform(click());
        onView(withText("Needle")).perform(click());
        onView(withText("Model making")).perform(click());
        onView(withText("Yoyo playing")).perform(click());
        onView(withText("Dancing")).perform(click());
        onView(withText("Climbing")).perform(click());
        onView(withText("Astronomy")).perform(click());
        onView(withText("Collecting")).perform(click());
        onView(withText("Cooking")).perform(click());
        onView(withText("Football")).perform(click());
        onView(withText("Music")).perform(click());
        onView(withText("others")).perform(click());
        onView(withText("Continue")).perform(click());
    }
}
