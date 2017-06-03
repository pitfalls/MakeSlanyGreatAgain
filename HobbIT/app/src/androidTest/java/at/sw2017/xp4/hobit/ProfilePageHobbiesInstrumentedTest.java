package at.sw2017.xp4.hobit;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ProfilePageHobbiesInstrumentedTest {

    @Rule
    public ActivityTestRule<HobbiT_Main_Startscreen> mActivityTestRule = new ActivityTestRule<>(HobbiT_Main_Startscreen.class);

    @Test
    public void profilePageHobbiesInstrumentedTest() throws InterruptedException {
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

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

        ViewInteraction appCompatTextView = onView(
                allOf(withId(R.id.title), withText("Edit Profile"), isDisplayed()));
        appCompatTextView.perform(click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.buttonEditInterests), withText("Edit Interests"), isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction appCompatCheckBox = onView(
                allOf(withId(R.id.checkBox1), withText("Swimming"), isDisplayed()));
        appCompatCheckBox.perform(click());

        ViewInteraction appCompatCheckBox2 = onView(
                allOf(withId(R.id.checkBox2), withText("Hiking"), isDisplayed()));
        appCompatCheckBox2.perform(click());

        ViewInteraction appCompatCheckBox3 = onView(
                allOf(withId(R.id.checkBox3), withText("Fitness studio"), isDisplayed()));
        appCompatCheckBox3.perform(click());

        ViewInteraction appCompatCheckBox4 = onView(
                allOf(withId(R.id.checkBox4), withText("Playing cards"), isDisplayed()));
        appCompatCheckBox4.perform(click());

        ViewInteraction appCompatCheckBox5 = onView(
                allOf(withId(R.id.checkBox5), withText("Computer Games"), isDisplayed()));
        appCompatCheckBox5.perform(click());

        ViewInteraction appCompatCheckBox6 = onView(
                allOf(withId(R.id.checkBox6), withText("Solve quiz"), isDisplayed()));
        appCompatCheckBox6.perform(click());

        ViewInteraction appCompatCheckBox7 = onView(
                allOf(withId(R.id.checkBox7), withText("Ride a bicycle"), isDisplayed()));
        appCompatCheckBox7.perform(click());

        ViewInteraction appCompatCheckBox8 = onView(
                allOf(withId(R.id.checkBox8), withText("Puzzle"), isDisplayed()));
        appCompatCheckBox8.perform(click());

        ViewInteraction appCompatCheckBox9 = onView(
                allOf(withId(R.id.checkBox9), withText("Needle"), isDisplayed()));
        appCompatCheckBox9.perform(click());

        ViewInteraction appCompatCheckBox10 = onView(
                allOf(withId(R.id.checkBox10), withText("Model making"), isDisplayed()));
        appCompatCheckBox10.perform(click());

        ViewInteraction appCompatCheckBox11 = onView(
                allOf(withId(R.id.checkBox11), withText("Yoyo playing"), isDisplayed()));
        appCompatCheckBox11.perform(click());

        ViewInteraction appCompatCheckBox12 = onView(
                allOf(withId(R.id.checkBox12), withText("Dancing"), isDisplayed()));
        appCompatCheckBox12.perform(click());

        ViewInteraction appCompatCheckBox13 = onView(
                allOf(withId(R.id.checkBox13), withText("Climbing"), isDisplayed()));
        appCompatCheckBox13.perform(click());

        ViewInteraction appCompatCheckBox14 = onView(
                allOf(withId(R.id.checkBox14), withText("Astronomy"), isDisplayed()));
        appCompatCheckBox14.perform(click());

        ViewInteraction appCompatCheckBox15 = onView(
                allOf(withId(R.id.checkBox15), withText("Collecting"), isDisplayed()));
        appCompatCheckBox15.perform(click());

        ViewInteraction appCompatCheckBox16 = onView(
                allOf(withId(R.id.checkBox16), withText("Cooking"), isDisplayed()));
        appCompatCheckBox16.perform(click());

        ViewInteraction appCompatCheckBox17 = onView(
                allOf(withId(R.id.checkBox17), withText("Football"), isDisplayed()));
        appCompatCheckBox17.perform(click());

        ViewInteraction appCompatCheckBox18 = onView(
                allOf(withId(R.id.checkBox18), withText("Music"), isDisplayed()));
        appCompatCheckBox18.perform(click());

        ViewInteraction appCompatCheckBox19 = onView(
                allOf(withId(R.id.checkBox19), withText("others"), isDisplayed()));
        appCompatCheckBox19.perform(click());

        Thread.sleep(500);

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.cont_button), withText("Continue"), isDisplayed()));
        appCompatButton3.perform(click());

    }

}
