package at.sw2017.xp4.hobit;

import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by alex on 05.04.17.
 */
@RunWith(AndroidJUnit4.class)
public class ListHobbyGroupsInstrumentedTest {


    @Rule
    public ActivityTestRule<ListHobbyGroups> mActivityRule = new ActivityTestRule<>(ListHobbyGroups.class);

    @Test
    public void SearchGroup() throws Exception {
        onView( withId(R.id.editText_search)).perform(click());
        onView( withId(R.id.editText_search)).perform(typeText("OhHappyDay"));
        onView( withId(R.id.editText_search)).check(matches(withText("OhHappyDay")));


        Espresso.closeSoftKeyboard();

        onView( withId(R.id.btn_search)).perform(click());
        onView( withId(R.id.btn_search)).perform(click());
    }
}
