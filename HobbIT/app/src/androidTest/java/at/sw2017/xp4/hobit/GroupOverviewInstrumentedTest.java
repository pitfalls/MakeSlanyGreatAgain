package at.sw2017.xp4.hobit;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.widget.Spinner;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class GroupOverviewInstrumentedTest {

    @Rule
    public ActivityTestRule<HobbiT_Main_Startscreen> mActivityTestRule = new ActivityTestRule<>(HobbiT_Main_Startscreen.class);

    @Test
    public void groupOverviewInstrumentedTest() throws InterruptedException {
        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.username), isDisplayed()));
        appCompatEditText.perform(click());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.username), isDisplayed()));
        appCompatEditText2.perform(replaceText("test"), closeSoftKeyboard());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.password), isDisplayed()));
        appCompatEditText3.perform(replaceText("test"), closeSoftKeyboard());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.login), withText("Login"), isDisplayed()));
        appCompatButton.perform(click());

        Thread.sleep(3500);

        ViewInteraction appCompatImageButton = onView(
                allOf(withContentDescription("Open navigation drawer"),
                        withParent(withId(R.id.toolbar)),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction appCompatCheckedTextView = onView(
                allOf(withId(R.id.design_menu_item_text), withText("Group Overview"), isDisplayed()));
        appCompatCheckedTextView.perform(click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.btn_join), withText("Join"), isDisplayed()));
        appCompatButton2.perform(click());

        Thread.sleep(3500);

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(android.R.id.button2), withText("Ok")));
        appCompatButton3.perform(scrollTo(), click());

        ViewInteraction appCompatSpinner = onView(
                allOf(withId(R.id.spinnerHobbies), isDisplayed()));
        appCompatSpinner.perform(click());

        ViewInteraction appCompatCheckedTextView2 = onView(
                allOf(withId(android.R.id.text1), withText("Fashion"), isDisplayed()));
        appCompatCheckedTextView2.perform(click());

        Thread.sleep(500);

        ViewInteraction appCompatSpinner2 = onView(
                allOf(withId(R.id.spinnerLocation), isDisplayed()));
        appCompatSpinner2.perform(click());

        ViewInteraction appCompatCheckedTextView3 = onView(
                allOf(withId(android.R.id.text1), withText("Stainach-Irdning"), isDisplayed()));
        appCompatCheckedTextView3.perform(click());

        ViewInteraction appCompatSpinner3 = onView(
                allOf(withId(R.id.spinnerGroup), isDisplayed()));
        appCompatSpinner3.perform(click());

        Thread.sleep(500);

        ViewInteraction appCompatCheckedTextView4 = onView(
                allOf(withId(android.R.id.text1), withText("HobbIt-programming"), isDisplayed()));
        appCompatCheckedTextView4.perform(click());

        ViewInteraction appCompatEditText5 = onView(
                allOf(withId(R.id.txtview_location_input), isDisplayed()));
        appCompatEditText5.perform(click());

        ViewInteraction appCompatEditText6 = onView(
                allOf(withId(R.id.txtview_location_input), isDisplayed()));
        appCompatEditText6.perform(replaceText("FADHJ"), closeSoftKeyboard());

        Thread.sleep(500);

       /* ViewInteraction appCompatSpinner4 = onView(
                allOf(withId(R.id.spinnerHobbies), isDisplayed()));
        appCompatSpinner4.perform(click());
*/
        // Thread.sleep(2500);

      /*  ViewInteraction appCompatSpinner5 = onView(
                allOf(withId(R.id.spinnerHobbies), isDisplayed()));
        appCompatSpinner5.perform(click());

        Thread.sleep(2500);*/
//
//        ViewInteraction appCompatSpinner6 = onView(
//                allOf(withId(R.id.spinnerGroup), isDisplayed()));
//        appCompatSpinner6.perform(click());
//
//        ViewInteraction appCompatSpinner7 = onView(
//                allOf(withId(R.id.spinnerLocation), isDisplayed()));
//        appCompatSpinner7.perform(click());

     /*   ViewInteraction appCompatEditText5_ = onView(
                allOf(withId(R.id.txtview_location_input), isDisplayed()));
        appCompatEditText5_.perform(click());

        ViewInteraction appCompatEditText7 = onView(
                allOf(withId(R.id.txtview_location_input), withText("FADHJ"), isDisplayed()));
        appCompatEditText7.perform(click());
*/
        Thread.sleep(500);

        closeSoftKeyboard();

        onView(withId(R.id.txtview_location_input)).perform(replaceText("Salz"));

    /*    ViewInteraction appCompatEditText8 = onView(
                allOf(withId(R.id.txtview_location_input), withText("FADHJ"), isDisplayed()));
        appCompatEditText8.perform(replaceText("Salz"), closeSoftKeyboard());*/

        Thread.sleep(2500);

        ViewInteraction appCompatSpinner8 = onView(
                allOf(withId(R.id.spinnerLocation), isDisplayed()));
        appCompatSpinner8.perform(click());

        Thread.sleep(2500);
        ViewInteraction appCompatTextView = onView(
                allOf(withId(android.R.id.text1), withText("Salzburg"), isDisplayed()));
        appCompatTextView.perform(click());

        Thread.sleep(500);

        ViewInteraction appCompatSpinner9 = onView(
                allOf(withId(R.id.spinnerHobbies), isDisplayed()));
        appCompatSpinner9.perform(click());

        ViewInteraction appCompatTextView2 = onView(
                allOf(withId(android.R.id.text1), withText("Sports"), isDisplayed()));
        appCompatTextView2.perform(click());

        ViewInteraction appCompatSpinner10 = onView(
                allOf(withId(R.id.spinnerGroup), isDisplayed()));
        appCompatSpinner10.perform(click());

        Thread.sleep(500);

        ViewInteraction appCompatTextView3 = onView(
                allOf(withId(android.R.id.text1), withText("CyclingSalzburg"), isDisplayed()));
        appCompatTextView3.perform(click());

        ViewInteraction appCompatEditText9 = onView(
                allOf(withId(R.id.txtGroupText), isDisplayed()));
        appCompatEditText9.perform(replaceText("CY"), closeSoftKeyboard());

    /*    ViewInteraction appCompatSpinner11 = onView(
                allOf(withId(R.id.spinnerGroup), isDisplayed()));
        appCompatSpinner11.perform(click());*/

        Thread.sleep(2500);

        onView(withId(R.id.txtGroupText)).perform(replaceText("Cyc"));

        ViewInteraction appCompatSpinner14 = onView(
                allOf(withId(R.id.spinnerGroup), isDisplayed()));
        appCompatSpinner14.perform(click());

        ViewInteraction appCompatTextView4 = onView(
                allOf(withId(android.R.id.text1), withText("CyclingSalzburg"), isDisplayed()));
        appCompatTextView4.perform(click());

        Thread.sleep(500);

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(R.id.btn_join), withText("Join"), isDisplayed()));
        appCompatButton4.perform(click());

        Thread.sleep(2500);

        ViewInteraction appCompatButton5 = onView(
                allOf(withId(android.R.id.button2), withText("Ok")));
        appCompatButton5.perform(scrollTo(), click());

        /*ViewInteraction appCompatEditText12 = onView(
                allOf(withId(R.id.txtview_location_input), withText("Salz"), isDisplayed()));
        appCompatEditText12.perform(replaceText("Salz7"), closeSoftKeyboard());*/

        onView(withId(R.id.txtview_location_input)).perform(replaceText("Salz7"));

       /* ViewInteraction appCompatSpinner15 = onView(
                allOf(withId(R.id.spinnerHobbies), isDisplayed()));
        appCompatSpinner15.perform(click());*/

        Thread.sleep(500);

   /*     ViewInteraction appCompatSpinner16 = onView(
                allOf(withId(R.id.spinnerLocation), isDisplayed()));
        appCompatSpinner16.perform(click());

        ViewInteraction appCompatSpinner17 = onView(
                allOf(withId(R.id.spinnerGroup), isDisplayed()));
        appCompatSpinner17.perform(click());*/

        ViewInteraction appCompatButton6 = onView(
                allOf(withId(R.id.btn_join), withText("Join"), isDisplayed()));
        appCompatButton6.perform(click());

        Thread.sleep(500);

        ViewInteraction appCompatButton7 = onView(
                allOf(withId(android.R.id.button2), withText("Retry")));
        appCompatButton7.perform(scrollTo(), click());

        ViewInteraction appCompatEditText13 = onView(
                allOf(withId(R.id.txtview_location_input), withText("Salz7"), isDisplayed()));
        appCompatEditText13.perform(click());

        onView(withId(R.id.txtview_location_input)).perform(replaceText("Salz"));

   /*     ViewInteraction appCompatEditText14 = onView(
                allOf(withId(R.id.txtview_location_input), withText("Salz7"), isDisplayed()));
        appCompatEditText14.perform(replaceText("Salz"), closeSoftKeyboard());
*/
        Thread.sleep(500);

   /*     ViewInteraction appCompatEditText15 = onView(
                allOf(withId(R.id.txtGroupText), withText("Cyc"), isDisplayed()));
        appCompatEditText15.perform(replaceText("Cyc6"), closeSoftKeyboard());*/

        onView(withId(R.id.txtGroupText)).perform(replaceText("Cyc6"));

        pressBack();
        Thread.sleep(500);

        ViewInteraction appCompatButton8 = onView(
                allOf(withId(R.id.btn_join), withText("Join"), isDisplayed()));
        appCompatButton8.perform(click());

        ViewInteraction appCompatButton9 = onView(
                allOf(withId(android.R.id.button2), withText("Retry")));
        appCompatButton9.perform(scrollTo(), click());

        Thread.sleep(500);

        ViewInteraction appCompatSpinner18 = onView(
                allOf(withId(R.id.spinnerGroup), isDisplayed()));
        appCompatSpinner18.perform(click());


    }

}
