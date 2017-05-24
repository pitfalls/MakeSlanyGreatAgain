
package at.sw2017.xp4.hobit;

import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.security.acl.Group;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
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
public class GroupOverviewInstrumentedTest {

    @Rule
    public ActivityTestRule<GroupOverview> mActivityRule = new ActivityTestRule<>(GroupOverview.class);



    @Test
    public void testSpinners() throws Exception {

        //get Data from DB
        Thread.sleep(3000);

        onView(withId(R.id.spinnerHobbies)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Football"))).perform(click());
        onView(withId(R.id.spinnerHobbies)).check(matches(withSpinnerText(containsString("Football"))));

        onView(withId(R.id.spinnerHobbies)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Tennis"))).perform(click());
        onView(withId(R.id.spinnerHobbies)).check(matches(withSpinnerText(containsString("Tennis"))));

        onView(withId(R.id.spinnerHobbies)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Cycling"))).perform(click());
        onView(withId(R.id.spinnerHobbies)).check(matches(withSpinnerText(containsString("Cycling"))));

        Thread.sleep(1500);

        onView(withId(R.id.spinnerLocation)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Graz"))).perform(click());
        onView(withId(R.id.spinnerLocation)).check(matches(withSpinnerText(containsString("Graz"))));

        onView(withId(R.id.spinnerLocation)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Wien"))).perform(click());
        onView(withId(R.id.spinnerLocation)).check(matches(withSpinnerText(containsString("Wien"))));

        onView(withId(R.id.spinnerLocation)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Salzburg"))).perform(click());
        onView(withId(R.id.spinnerLocation)).check(matches(withSpinnerText(containsString("Salzburg"))));


        onView(withId(R.id.spinnerLocation)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Innsbruck"))).perform(click());
        onView(withId(R.id.spinnerLocation)).check(matches(withSpinnerText(containsString("Innsbruck"))));

        Thread.sleep(1500);

        onView(withId(R.id.spinnerGroup)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("TestGroup1"))).perform(click());
        onView(withId(R.id.spinnerGroup)).check(matches(withSpinnerText(containsString("TestGroup1"))));
        onView(withId(R.id.txtview_description_input)).check(matches(withText("Test test test test")));
        onView(withId(R.id.btn_join)).perform(click());
        Thread.sleep(1500);
        onView(withText("You are already member of this group :-)")).inRoot(isDialog()).check(matches(isDisplayed()));
        onView(withText("Ok")).inRoot(isDialog()).check(matches(isDisplayed())).perform(click());

        onView(withId(R.id.spinnerGroup)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("TennisWien"))).perform(click());
        onView(withId(R.id.spinnerGroup)).check(matches(withSpinnerText(containsString("TennisWien"))));
        onView(withId(R.id.txtview_description_input)).check(matches(withText("Playing tennis in vienna")));
        onView(withId(R.id.btn_join)).perform(click());
        Thread.sleep(1500);
        onView(withText("You are already member of this group :-)")).inRoot(isDialog()).check(matches(isDisplayed()));
        onView(withText("Ok")).inRoot(isDialog()).check(matches(isDisplayed())).perform(click());

        onView(withId(R.id.spinnerGroup)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("CyclingSalzburg"))).perform(click());
        onView(withId(R.id.spinnerGroup)).check(matches(withSpinnerText(containsString("CyclingSalzburg"))));
        onView(withId(R.id.txtview_description_input)).check(matches(withText("Cycling in Salzburg :)")));
        onView(withId(R.id.btn_join)).perform(click());
        Thread.sleep(1500);
        onView(withText("You are already member of this group :-)")).inRoot(isDialog()).check(matches(isDisplayed()));
        onView(withText("Ok")).inRoot(isDialog()).check(matches(isDisplayed())).perform(click());

        onView(withId(R.id.spinnerGroup)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("FootballInnsbruck"))).perform(click());
        onView(withId(R.id.spinnerGroup)).check(matches(withSpinnerText(containsString("FootballInnsbruck"))));
        onView(withId(R.id.txtview_description_input)).check(matches(withText("Playing footbal in Innsbruck")));
        onView(withId(R.id.btn_join)).perform(click());
        Thread.sleep(1500);
        onView(withText("You are already member of this group :-)")).inRoot(isDialog()).check(matches(isDisplayed()));
        onView(withText("Ok")).inRoot(isDialog()).check(matches(isDisplayed())).perform(click());

    }
      /*  @Test
        public void testFilter() throws Exception {

            Thread.sleep(7500);
         /*
        onView(withId(R.id.txtview_location_input)).perform(replaceText("shitshitshit"));
        onView(withId(R.id.spinnerGroup)).check(matches(withSpinnerText("")));
        onView(withId(R.id.spinnerLocation)).check(matches(withSpinnerText("")));
        onView(withId(R.id.spinnerGroup)).check(matches(withSpinnerText("")));
        onView(withId(R.id.txtview_location_input)).perform(replaceText(""));
               Thread.sleep(2000);
        onView(withId(R.id.txtGroupText)).perform(replaceText("shitshitshit"));
        onView(withId(R.id.spinnerHobbies)).check(matches(withSpinnerText("")));
        onView(withId(R.id.spinnerLocation)).check(matches(withSpinnerText("")));
        onView(withId(R.id.spinnerGroup)).check(matches(withSpinnerText("")));

        //onView(withId(R.id.)).perform(replaceText("shitshitshit"));



    }   */
}
