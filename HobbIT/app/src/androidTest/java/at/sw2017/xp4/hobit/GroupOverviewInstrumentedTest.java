
package at.sw2017.xp4.hobit;

import android.database.sqlite.SQLiteOutOfMemoryException;
import android.provider.Settings;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

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
    public void testSpinners0() throws Exception {

        //get Data from DB
        Thread.sleep(3500);
        System.out.println("GroupOverviewInstrumentedTest.testSpinners0");
        onView(withId(R.id.spinnerHobbies)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Games"))).perform(click());
        onView(withId(R.id.spinnerHobbies)).check(matches(withSpinnerText(containsString("Games"))));

        Thread.sleep(500);

        onView(withId(R.id.spinnerHobbies)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Sports"))).perform(click());
        onView(withId(R.id.spinnerHobbies)).check(matches(withSpinnerText(containsString("Sports"))));

    }

        @Test
        public void testSpinners1() throws Exception {
            Thread.sleep(3500);
            System.out.println("GroupOverviewInstrumentedTest.testSpinners1");
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
        }

    @Test
    public void testSpinners2() throws Exception {
        Thread.sleep(3500);
        System.out.println("GroupOverviewInstrumentedTest.testSpinners2");

        onView(withId(R.id.spinnerGroup)).perform(click());

        onData(allOf(is(instanceOf(String.class)), is("Slacking Graz"))).perform(click());
        onView(withId(R.id.spinnerGroup)).check(matches(withSpinnerText(containsString("Slacking Graz"))));
        onView(withId(R.id.txtview_description_input)).check(matches(withText("Doing nothing @ TU Graz")));
        onView(withId(R.id.btn_join)).perform(click());
        Thread.sleep(2000);
        onView(withText("You are already member of this group :-)")).inRoot(isDialog()).check(matches(isDisplayed()));
        onView(withText("Ok")).inRoot(isDialog()).check(matches(isDisplayed())).perform(click());

        Thread.sleep(2000);
        onView(withId(R.id.spinnerGroup)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("TennisWien"))).perform(click());
        Thread.sleep(1000);
        onView(withId(R.id.spinnerGroup)).check(matches(withSpinnerText(containsString("TennisWien"))));
        onView(withId(R.id.txtview_description_input)).check(matches(withText("Playing tennis in vienna")));
        onView(withId(R.id.btn_join)).perform(click());
        Thread.sleep(2000);
        onView(withText("You are already member of this group :-)")).inRoot(isDialog()).check(matches(isDisplayed()));
        onView(withText("Ok")).inRoot(isDialog()).check(matches(isDisplayed())).perform(click());
        Thread.sleep(2000);
        onView(withId(R.id.spinnerGroup)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("CyclingSalzburg"))).perform(click());
        Thread.sleep(1000);
        onView(withId(R.id.spinnerGroup)).check(matches(withSpinnerText(containsString("CyclingSalzburg"))));
        onView(withId(R.id.txtview_description_input)).check(matches(withText("Cycling in Salzburg :)")));
        onView(withId(R.id.btn_join)).perform(click());
        Thread.sleep(2000);
        onView(withText("You are already member of this group :-)")).inRoot(isDialog()).check(matches(isDisplayed()));
        onView(withText("Ok")).inRoot(isDialog()).check(matches(isDisplayed())).perform(click());
        Thread.sleep(2000);
        onView(withId(R.id.spinnerGroup)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("FootballInnsbruck"))).perform(click());
        Thread.sleep(1000);
        onView(withId(R.id.spinnerGroup)).check(matches(withSpinnerText(containsString("FootballInnsbruck"))));
        onView(withId(R.id.txtview_description_input)).check(matches(withText("Playing footbal in Innsbruck")));
        onView(withId(R.id.btn_join)).perform(click());
        Thread.sleep(2000);
        onView(withText("You are already member of this group :-)")).inRoot(isDialog()).check(matches(isDisplayed()));
        onView(withText("Ok")).inRoot(isDialog()).check(matches(isDisplayed())).perform(click());
        Thread.sleep(1000);
    }

    @Test
    public void testHandlerEmpty() throws Exception {
        System.out.println("GroupOverviewInstrumentedTest.testHandlerEmpty");
        //get Data from DB
        Thread.sleep(4000);

        onView(withId(R.id.txtview_location_input)).perform(replaceText(" "));
        onView(withId(R.id.spinnerHobbies)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is(""))).perform(click());
        onView(withId(R.id.spinnerHobbies)).check(matches(withSpinnerText(containsString(""))));

        onView(withId(R.id.spinnerLocation)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is(""))).perform(click());
        onView(withId(R.id.spinnerLocation)).check(matches(withSpinnerText(containsString(""))));

        onView(withId(R.id.spinnerGroup)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is(""))).perform(click());
        onView(withId(R.id.spinnerGroup)).check(matches(withSpinnerText(containsString(""))));

        onView(withId(R.id.txtview_location_input)).perform(replaceText(""));
        Thread.sleep(2000);

        //TODO Check why " " is not valid
        onView(withId(R.id.txtGroupText)).perform(replaceText("  "));
        onView(withId(R.id.spinnerHobbies)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is(""))).perform(click());
        onView(withId(R.id.spinnerHobbies)).check(matches(withSpinnerText(containsString(""))));

        onView(withId(R.id.spinnerLocation)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is(""))).perform(click());
        onView(withId(R.id.spinnerLocation)).check(matches(withSpinnerText(containsString(""))));

        onView(withId(R.id.spinnerGroup)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is(""))).perform(click());
        onView(withId(R.id.spinnerGroup)).check(matches(withSpinnerText(containsString(""))));

    }

    @Test
    public void testHandlerInsertDeleteLocation() throws Exception {
        System.out.println("GroupOverviewInstrumentedTest.testHandlerInsertDeleteLocation");
        //get Data from DB
        Thread.sleep(3500);

        onView(withId(R.id.txtview_location_input)).perform(replaceText(" "));
        onView(withId(R.id.txtview_location_input)).perform(replaceText(""));
        Thread.sleep(500);
        onView(withId(R.id.spinnerHobbies)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Sports"))).perform(click());
        onView(withId(R.id.spinnerHobbies)).check(matches(withSpinnerText(containsString("Sports"))));
        Thread.sleep(500);
        onView(withId(R.id.spinnerLocation)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Graz"))).perform(click());
        onView(withId(R.id.spinnerLocation)).check(matches(withSpinnerText(containsString("Graz"))));
        Thread.sleep(500);
        onView(withId(R.id.spinnerGroup)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Slacking Graz"))).perform(click());
        onView(withId(R.id.spinnerGroup)).check(matches(withSpinnerText(containsString("Slacking Graz"))));
        Thread.sleep(500);

        onView(withId(R.id.txtGroupText)).perform(replaceText(" "));
        onView(withId(R.id.spinnerHobbies)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Games"))).perform(click());
        onView(withId(R.id.spinnerHobbies)).check(matches(withSpinnerText(containsString("Games"))));
        Thread.sleep(500);
        onView(withId(R.id.spinnerLocation)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Graz"))).perform(click());
        onView(withId(R.id.spinnerLocation)).check(matches(withSpinnerText(containsString("Graz"))));
        Thread.sleep(500);
        onView(withId(R.id.spinnerGroup)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Graz gaming"))).perform(click());
        onView(withId(R.id.spinnerGroup)).check(matches(withSpinnerText(containsString("Graz gaming"))));

    }

    @Test
    public void testEmptyLocation() throws Exception {

        System.out.println("GroupOverviewInstrumentedTest.testEmptyLocation");
        //get Data from DB
        Thread.sleep(3500);
        onView(withId(R.id.txtGroupText)).perform(replaceText(" "));
        onView(withId(R.id.txtview_location_input)).perform(replaceText(""));
        Thread.sleep(500);
        onView(withId(R.id.spinnerHobbies)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Sports"))).perform(click());
        onView(withId(R.id.spinnerHobbies)).check(matches(withSpinnerText(containsString("Sports"))));
        Thread.sleep(500);
        onView(withId(R.id.spinnerLocation)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Graz"))).perform(click());
        onView(withId(R.id.spinnerLocation)).check(matches(withSpinnerText(containsString("Graz"))));
        Thread.sleep(500);
        onView(withId(R.id.spinnerGroup)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Slacking Graz"))).perform(click());
        onView(withId(R.id.spinnerGroup)).check(matches(withSpinnerText(containsString("Slacking Graz"))));
    }


    @Test
    public void testHandlerInsertDeleteGroupFilter() throws Exception {
        System.out.println("GroupOverviewInstrumentedTest.testHandlerInsertDeleteGroupFilter");
        //get Data from DB
        Thread.sleep(3500);

        onView(withId(R.id.txtGroupText)).perform(replaceText(" "));
        Thread.sleep(500);
        onView(withId(R.id.spinnerHobbies)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Games"))).perform(click());
        onView(withId(R.id.spinnerHobbies)).check(matches(withSpinnerText(containsString("Games"))));
        Thread.sleep(500);
        onView(withId(R.id.spinnerLocation)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Graz"))).perform(click());
        onView(withId(R.id.spinnerLocation)).check(matches(withSpinnerText(containsString("Graz"))));
        Thread.sleep(500);
        onView(withId(R.id.spinnerGroup)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Graz gaming"))).perform(click());
        onView(withId(R.id.spinnerGroup)).check(matches(withSpinnerText(containsString("Graz gaming"))));


    }

        @Test
        public void testFilter() throws Exception {
            System.out.println("GroupOverviewInstrumentedTest.testFilter");
            Thread.sleep(3500);

        onView(withId(R.id.txtview_location_input)).perform(replaceText("shitshitshit"));
        onView(withId(R.id.spinnerGroup)).check(matches(withSpinnerText(containsString(""))));
        onView(withId(R.id.spinnerLocation)).check(matches(withSpinnerText("")));
        onView(withId(R.id.spinnerGroup)).check(matches(withSpinnerText(containsString(""))));
        onView(withId(R.id.txtview_location_input)).perform(replaceText(""));
       Thread.sleep(2000);
        onView(withId(R.id.txtGroupText)).perform(replaceText("shitshitshit"));
        onView(withId(R.id.spinnerHobbies)).check(matches(withSpinnerText(containsString(""))));
        onView(withId(R.id.spinnerLocation)).check(matches(withSpinnerText(containsString(""))));
        onView(withId(R.id.spinnerGroup)).check(matches(withSpinnerText(containsString(""))));

        //onView(withId(R.id.)).perform(replaceText("shitshitshit"));

    }

    @Test
    public void testEmptyValues() throws Exception {
        System.out.println("GroupOverviewInstrumentedTest.testEmptyValues");
        Thread.sleep(3500);

        onView(withId(R.id.txtview_location_input)).perform(replaceText("shitshitshit"));
        onView(withId(R.id.spinnerGroup)).check(matches(withSpinnerText(containsString(""))));
        onView(withId(R.id.spinnerLocation)).check(matches(withSpinnerText("")));
        onView(withId(R.id.spinnerGroup)).check(matches(withSpinnerText(containsString(""))));
        onView(withId(R.id.txtview_location_input)).perform(replaceText(""));
        Thread.sleep(2000);
        onView(withId(R.id.txtGroupText)).perform(replaceText("shitshitshit"));
        onView(withId(R.id.spinnerHobbies)).check(matches(withSpinnerText(containsString(""))));
        onView(withId(R.id.spinnerLocation)).check(matches(withSpinnerText(containsString(""))));
        onView(withId(R.id.spinnerGroup)).check(matches(withSpinnerText(containsString(""))));

        //onView(withId(R.id.)).perform(replaceText("shitshitshit"));

    }

    @Test
    public void testInputLocationChooseSpinners() throws Exception {
        System.out.println("GroupOverviewInstrumentedTest.testInputLocationChooseSpinners");
        Thread.sleep(3500);

        onView(withId(R.id.txtview_location_input)).perform(replaceText("Wien"));
        onView(withId(R.id.spinnerHobbies)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Sports"))).perform(click());
        onView(withId(R.id.spinnerHobbies)).check(matches(withSpinnerText(containsString("Sports"))));
        Thread.sleep(500);
        onView(withId(R.id.spinnerLocation)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Wien"))).perform(click());
        onView(withId(R.id.spinnerLocation)).check(matches(withSpinnerText(containsString("Wien"))));
        Thread.sleep(500);
        onView(withId(R.id.spinnerGroup)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Flowerpower"))).perform(click());
        onView(withId(R.id.spinnerGroup)).check(matches(withSpinnerText(containsString("Flowerpower"))));
        Thread.sleep(500);

    }

    @Test
    public void testManyInputChooseSpinners() throws Exception {
        System.out.println("GroupOverviewInstrumentedTest.testInputLocationChooseSpinners");
        Thread.sleep(3500);

        onView(withId(R.id.txtview_location_input)).perform(replaceText("Wien"));
        onView(withId(R.id.spinnerHobbies)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Sports"))).perform(click());
        onView(withId(R.id.spinnerHobbies)).check(matches(withSpinnerText(containsString("Sports"))));
        Thread.sleep(500);
        onView(withId(R.id.spinnerLocation)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Wien"))).perform(click());
        onView(withId(R.id.spinnerLocation)).check(matches(withSpinnerText(containsString("Wien"))));
        Thread.sleep(500);
        onView(withId(R.id.spinnerGroup)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Flowerpower"))).perform(click());
        onView(withId(R.id.spinnerGroup)).check(matches(withSpinnerText(containsString("Flowerpower"))));
        Thread.sleep(500);

        onView(withId(R.id.txtGroupText)).perform(replaceText("Trees"));
        Thread.sleep(2000);

        onView(withId(R.id.spinnerHobbies)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Collecting"))).perform(click());
        onView(withId(R.id.spinnerHobbies)).check(matches(withSpinnerText(containsString("Collecting"))));
        Thread.sleep(500);
        onView(withId(R.id.spinnerGroup)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Trees"))).perform(click());
        onView(withId(R.id.spinnerGroup)).check(matches(withSpinnerText(containsString("Trees"))));
        Thread.sleep(500);


    }




}
