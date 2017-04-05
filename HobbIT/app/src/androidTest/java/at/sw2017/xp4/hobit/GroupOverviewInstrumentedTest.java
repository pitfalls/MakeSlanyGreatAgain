package at.sw2017.xp4.hobit;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by alex on 05.04.17.
 */
@RunWith(AndroidJUnit4.class)
public class GroupOverviewInstrumentedTest {
        @Rule
        public ActivityTestRule<GroupOverview> mActivityRule = new ActivityTestRule<>(GroupOverview.class);



    @Test
    public void testActivityJoin() throws Exception {
        onView( withId(R.id.btn_join)).perform(click());
      }

    @Test
    public void testActivityCancel() throws Exception {
        onView( withId(R.id.btn_back)).perform(click());
    }


}
