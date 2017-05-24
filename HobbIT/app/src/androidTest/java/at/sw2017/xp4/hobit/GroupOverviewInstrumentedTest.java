
package at.sw2017.xp4.hobit;

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
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
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
    public void testContentExistence() throws Exception {

        //onView(withText("JOIN")).perform(click());


       /* ViewInteraction customTextView = onView(allOf (withId(R.id.tv_spinner_desc), withText("hello"), isDisplayed()));
        customTextView.perform(click());
*/
       // Spinner mySpinner = (Spinner) findViewById(R.id.my_spinner);
      //  List<MyValue> list = Arrays.asList(

        onView(withId(R.id.spinnerHobbies)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is(selectionText))).perform(click());
        onView(withId(spinnerId)).check(matches(withSpinnerText(containsString(selectionText))));

        onView(withId(R.id.btn_join)).perform(click());
        onView(withId(R.id.btn_join)).perform(click());
        onView(withId(R.id.btn_join)).perform(click());



    }
}
