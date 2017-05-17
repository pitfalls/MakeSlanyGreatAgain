package at.sw2017.xp4.hobit;

import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by Gerd on 17.05.2017.
 */

public class RegistrationInstrumentedTest {

    @Rule
    public ActivityTestRule<RegisterUser> mActivityRule =
            new ActivityTestRule<>(RegisterUser.class);

    @Test
    public void registerTest() throws Exception {
        onView(withId(R.id.nickName)).perform(replaceText("test"));
        onView(withId(R.id.firstName)).perform(replaceText("test"));
        onView(withId(R.id.lastName)).perform(replaceText("test"));
        onView(withId(R.id.email)).perform(replaceText("test"));
        onView(withId(R.id.location)).perform(replaceText("test"));
        onView(withId(R.id.password)).perform(replaceText("test"));
        onView(withId(R.id.passwordCheck)).perform(replaceText("test"));

        onView(withId(R.id.register)).perform(click());
    }

    @Test
    public void emptyRegisterTest() throws Exception {
        onView(withId(R.id.nickName)).perform(replaceText(""));
        onView(withId(R.id.firstName)).perform(replaceText("test"));
        onView(withId(R.id.lastName)).perform(replaceText("test"));
        onView(withId(R.id.email)).perform(replaceText("test"));
        onView(withId(R.id.location)).perform(replaceText("test"));
        onView(withId(R.id.password)).perform(replaceText("test"));
        onView(withId(R.id.passwordCheck)).perform(replaceText("test"));

        onView(withId(R.id.register)).perform(click());
        onView(withText("OK")).perform(click());

        onView(withId(R.id.nickName)).perform(replaceText("test"));
        onView(withId(R.id.firstName)).perform(replaceText(""));
        onView(withId(R.id.lastName)).perform(replaceText("test"));
        onView(withId(R.id.email)).perform(replaceText("test"));
        onView(withId(R.id.location)).perform(replaceText("test"));
        onView(withId(R.id.password)).perform(replaceText("test"));
        onView(withId(R.id.passwordCheck)).perform(replaceText("test"));

        onView(withId(R.id.register)).perform(click());
        onView(withText("OK")).perform(click());


        onView(withId(R.id.nickName)).perform(replaceText("test"));
        onView(withId(R.id.firstName)).perform(replaceText("test"));
        onView(withId(R.id.lastName)).perform(replaceText(""));
        onView(withId(R.id.email)).perform(replaceText("test"));
        onView(withId(R.id.location)).perform(replaceText("test"));
        onView(withId(R.id.password)).perform(replaceText("test"));
        onView(withId(R.id.passwordCheck)).perform(replaceText("test"));

        onView(withId(R.id.register)).perform(click());
        onView(withText("OK")).perform(click());


        onView(withId(R.id.nickName)).perform(replaceText("test"));
        onView(withId(R.id.firstName)).perform(replaceText("test"));
        onView(withId(R.id.lastName)).perform(replaceText("test"));
        onView(withId(R.id.email)).perform(replaceText(""));
        onView(withId(R.id.location)).perform(replaceText("test"));
        onView(withId(R.id.password)).perform(replaceText("test"));
        onView(withId(R.id.passwordCheck)).perform(replaceText("test"));

        onView(withId(R.id.register)).perform(click());
        onView(withText("OK")).perform(click());


        onView(withId(R.id.nickName)).perform(replaceText("test"));
        onView(withId(R.id.firstName)).perform(replaceText("test"));
        onView(withId(R.id.lastName)).perform(replaceText("test"));
        onView(withId(R.id.email)).perform(replaceText("test"));
        onView(withId(R.id.location)).perform(replaceText(""));
        onView(withId(R.id.password)).perform(replaceText("test"));
        onView(withId(R.id.passwordCheck)).perform(replaceText("test"));

        onView(withId(R.id.register)).perform(click());
        onView(withText("OK")).perform(click());


        onView(withId(R.id.nickName)).perform(replaceText("test"));
        onView(withId(R.id.firstName)).perform(replaceText("test"));
        onView(withId(R.id.lastName)).perform(replaceText("test"));
        onView(withId(R.id.email)).perform(replaceText("test"));
        onView(withId(R.id.location)).perform(replaceText("test"));
        onView(withId(R.id.password)).perform(replaceText(""));
        onView(withId(R.id.passwordCheck)).perform(replaceText("test"));

        onView(withId(R.id.register)).perform(click());
        onView(withText("OK")).perform(click());


        onView(withId(R.id.nickName)).perform(replaceText("test"));
        onView(withId(R.id.firstName)).perform(replaceText("test"));
        onView(withId(R.id.lastName)).perform(replaceText("test"));
        onView(withId(R.id.email)).perform(replaceText("test"));
        onView(withId(R.id.location)).perform(replaceText("test"));
        onView(withId(R.id.password)).perform(replaceText("test"));
        onView(withId(R.id.passwordCheck)).perform(replaceText(""));

        onView(withId(R.id.register)).perform(click());
        onView(withText("OK")).perform(click());


        onView(withId(R.id.nickName)).perform(replaceText("test"));
        onView(withId(R.id.firstName)).perform(replaceText("test"));
        onView(withId(R.id.lastName)).perform(replaceText("test"));
        onView(withId(R.id.email)).perform(replaceText("test"));
        onView(withId(R.id.location)).perform(replaceText("test"));
        onView(withId(R.id.password)).perform(replaceText("test"));
        onView(withId(R.id.passwordCheck)).perform(replaceText("test2"));

        onView(withId(R.id.register)).perform(click());
        onView(withText("OK")).perform(click());
    }
}
