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
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainMenueInstrumentedTest {

    @Rule
    public ActivityTestRule<HobbiT_Main_Startscreen> mActivityTestRule = new ActivityTestRule<>(HobbiT_Main_Startscreen.class);

    @Test
    public  void mainMenueInstrumentedTest() throws InterruptedException {
        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /****************************************/

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.register), withText("Register"), isDisplayed()));
        appCompatButton.perform(click());

        Thread.sleep(3000);


        pressBack();

        ViewInteraction appCompatButton10 = onView(
                allOf(withId(R.id.register), withText("Register"), isDisplayed()));
        appCompatButton10.perform(click());

        Thread.sleep(1900);

        ViewInteraction appCompatButton33 = onView(
                allOf(withId(R.id.register), withText("Register"), isDisplayed()));
        appCompatButton33.perform(click());

        Thread.sleep(1900);

        onView(withText("OK")).perform(click());

        Thread.sleep(1500);

       /* ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.nickName), isDisplayed()));
        appCompatEditText.perform(click());*/

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.nickName), isDisplayed()));
        appCompatEditText2.perform(replaceText("Test1"), closeSoftKeyboard());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.firstName), isDisplayed()));
        appCompatEditText3.perform(replaceText("Test1"), closeSoftKeyboard());

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.lastName), isDisplayed()));
        appCompatEditText4.perform(replaceText("Test1"), closeSoftKeyboard());

        ViewInteraction appCompatEditText5 = onView(
                allOf(withId(R.id.email), isDisplayed()));
        appCompatEditText5.perform(replaceText("test1"), closeSoftKeyboard());

        ViewInteraction appCompatEditText6 = onView(
                allOf(withId(R.id.location), isDisplayed()));
        appCompatEditText6.perform(replaceText("Test"), closeSoftKeyboard());

        ViewInteraction appCompatEditText7 = onView(
                allOf(withId(R.id.password), isDisplayed()));
        appCompatEditText7.perform(replaceText("test1"), closeSoftKeyboard());

        ViewInteraction appCompatEditText8 = onView(
                allOf(withId(R.id.passwordCheck), isDisplayed()));
        appCompatEditText8.perform(replaceText("test1"), closeSoftKeyboard());

        Thread.sleep(500);

        //pressBack();

        ViewInteraction appCompatButton1 = onView(
                allOf(withId(R.id.register), withText("Register"), isDisplayed()));
        appCompatButton1.perform(click());

         Thread.sleep(2900);

        onView(withText("OK")).perform(click());

        Thread.sleep(1500);

        pressBack();

        /****************************************/


        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.username), isDisplayed()));
        appCompatEditText.perform(click());

        ViewInteraction appCompatEditText20 = onView(
                allOf(withId(R.id.username), isDisplayed()));
        appCompatEditText20.perform(replaceText("test"), closeSoftKeyboard());

        ViewInteraction appCompatEditText30 = onView(
                allOf(withId(R.id.password), isDisplayed()));
        appCompatEditText30.perform(replaceText("test"), closeSoftKeyboard());

        ViewInteraction appCompatButton0 = onView(
                allOf(withId(R.id.login), withText("Login"), isDisplayed()));
        appCompatButton0.perform(click());

        Thread.sleep(3500);

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

        ViewInteraction appCompatTextView = onView(
                allOf(withId(R.id.title), withText("Login"), isDisplayed()));
        appCompatTextView.perform(click());

        pressBack();

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

        ViewInteraction appCompatTextView2 = onView(
                allOf(withId(R.id.title), withText("Edit Profile"), isDisplayed()));
        appCompatTextView2.perform(click());

        pressBack();

        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.fab), isDisplayed()));
        floatingActionButton.perform(click());

        Thread.sleep(1500);

        ViewInteraction appCompatImageButton = onView(
                allOf(withContentDescription("Open navigation drawer"),
                        withParent(withId(R.id.toolbar)),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        pressBack();

        ViewInteraction appCompatImageButton2 = onView(
                allOf(withContentDescription("Open navigation drawer"),
                        withParent(withId(R.id.toolbar)),
                        isDisplayed()));
        appCompatImageButton2.perform(click());

        ViewInteraction appCompatCheckedTextView = onView(
                allOf(withId(R.id.design_menu_item_text), withText("Join Group"), isDisplayed()));
        appCompatCheckedTextView.perform(click());

        Thread.sleep(4000);
        closeSoftKeyboard();
        Thread.sleep(4000);
        pressBack();
        pressBack();
        Thread.sleep(4000);

        ViewInteraction appCompatImageButton3 = onView(
                allOf(withContentDescription("Open navigation drawer"),
                        withParent(withId(R.id.toolbar)),
                        isDisplayed()));
        appCompatImageButton3.perform(click());

        //appCompatImageButton2.perform(click());

        Thread.sleep(1500);

        ViewInteraction appCompatCheckedTextView2 = onView(
                allOf(withId(R.id.design_menu_item_text), withText("Group Overview"), isDisplayed()));
        appCompatCheckedTextView2.perform(click());

//        ViewInteraction appCompatImageButton4 = onView(
//                allOf(withContentDescription("Nach oben"),
//                        withParent(allOf(withId(R.id.action_bar),
//                                withParent(withId(R.id.action_bar_container)))),
//                        isDisplayed()));

     //   appCompatImageButton4.perform(click());

        Thread.sleep(2000);
        pressBack();
        Thread.sleep(2000);

        ViewInteraction appCompatImageButton4 = onView(
                allOf(withContentDescription("Open navigation drawer"),
                        withParent(withId(R.id.toolbar)),
                        isDisplayed()));
        appCompatImageButton4.perform(click());

      //  appCompatImageButton2.perform(click());

        ViewInteraction appCompatCheckedTextView3 = onView(
                allOf(withId(R.id.design_menu_item_text), withText("Group Creation"), isDisplayed()));
        appCompatCheckedTextView3.perform(click());

        pressBack();

    }

}
