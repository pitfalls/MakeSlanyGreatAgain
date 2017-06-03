package at.sw2017.xp4.hobit;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

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
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class GroupCreationInstrumentedTest {

    @Rule
    public ActivityTestRule<HobbiT_Main_Startscreen> mActivityTestRule = new ActivityTestRule<>(HobbiT_Main_Startscreen.class);

    @Test
    public void testAll() throws InterruptedException {
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
        closeSoftKeyboard();
        Thread.sleep(3500);

        ViewInteraction appCompatImageButton = onView(
                allOf(withContentDescription("Open navigation drawer"),
                        withParent(withId(R.id.toolbar)),
                        isDisplayed()));

        appCompatImageButton.perform(click());

        ViewInteraction appCompatCheckedTextView = onView(
                allOf(withId(R.id.design_menu_item_text), withText("Group Creation"), isDisplayed()));
        appCompatCheckedTextView.perform(click());

        Thread.sleep(3000);

        ViewInteraction appCompatSpinner = onView(
                allOf(withId(R.id.spinner_hobby_groupCreation), isDisplayed()));
        appCompatSpinner.perform(click());

        ViewInteraction appCompatCheckedTextView2 = onView(
                allOf(withId(android.R.id.text1), withText("Sports"), isDisplayed()));
        appCompatCheckedTextView2.perform(click());

        ViewInteraction appCompatSpinner2 = onView(
                allOf(withId(R.id.spinner_location_GroupCreation), isDisplayed()));
        appCompatSpinner2.perform(click());

        ViewInteraction appCompatCheckedTextView3 = onView(
                allOf(withId(android.R.id.text1), withText("Graz"), isDisplayed()));
        appCompatCheckedTextView3.perform(click());

        // pressBack();

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.btn_save), withText("Save"), isDisplayed()));
        appCompatButton2.perform(click());

        Thread.sleep(3000);

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(android.R.id.button2), withText("Retry")));
        appCompatButton3.perform(scrollTo(), click());

        //***

        ViewInteraction appCompatSpinner3 = onView(
                allOf(withId(R.id.spinner_hobby_groupCreation), isDisplayed()));
        appCompatSpinner3.perform(click());

        pressBack();

        ViewInteraction appCompatSpinner4 = onView(
                allOf(withId(R.id.spinner_location_GroupCreation), isDisplayed()));
        appCompatSpinner4.perform(click());

        pressBack();

        ViewInteraction appCompatEditText6 = onView(
                allOf(withId(R.id.editText_Description), isDisplayed()));
        appCompatEditText6.perform(click());

        pressBack();

        ViewInteraction appCompatButton5 = onView(
                allOf(withId(R.id.btn_cancel), withText("Cancel"), isDisplayed()));
        appCompatButton5.perform(click());

    }

}
