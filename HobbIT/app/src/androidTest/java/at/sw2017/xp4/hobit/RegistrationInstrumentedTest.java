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
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class RegistrationInstrumentedTest {

    @Rule
    public ActivityTestRule<HobbiT_Main_Startscreen> mActivityTestRule = new ActivityTestRule<>(HobbiT_Main_Startscreen.class);

    @Test
    public void registrationInstrumentedTest() throws InterruptedException {
        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread.sleep(5500);

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.register), withText("Register"), isDisplayed()));
        appCompatButton.perform(click());

        Thread.sleep(3000);

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.nickName), isDisplayed()));
        appCompatEditText.perform(click());

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

        pressBack();

        ViewInteraction appCompatButton1 = onView(
                allOf(withId(R.id.register), withText("Register"), isDisplayed()));
        appCompatButton1.perform(click());

        Thread.sleep(1900);

        //onView(withText("OK")).perform(click());

        Thread.sleep(1500);

        //##########################################################################################
//
//        ViewInteraction appCompatButton = onView(
//                allOf(withId(R.id.register), withText("Register"), isDisplayed()));
//        appCompatButton.perform(click());
//
//        Thread.sleep(3000);

         appCompatEditText = onView(
                allOf(withId(R.id.nickName), isDisplayed()));
        appCompatEditText.perform(click());

         appCompatEditText2 = onView(
                allOf(withId(R.id.nickName), isDisplayed()));
        appCompatEditText2.perform(replaceText(""), closeSoftKeyboard());

         appCompatEditText3 = onView(
                allOf(withId(R.id.firstName), isDisplayed()));
        appCompatEditText3.perform(replaceText("test1"), closeSoftKeyboard());

         appCompatEditText4 = onView(
                allOf(withId(R.id.lastName), isDisplayed()));
        appCompatEditText4.perform(replaceText("test1"), closeSoftKeyboard());

         appCompatEditText5 = onView(
                allOf(withId(R.id.email), isDisplayed()));
        appCompatEditText5.perform(replaceText("test1"), closeSoftKeyboard());

         appCompatEditText6 = onView(
                allOf(withId(R.id.location), isDisplayed()));
        appCompatEditText6.perform(replaceText("test1"), closeSoftKeyboard());

         appCompatEditText7 = onView(
                allOf(withId(R.id.password), isDisplayed()));
        appCompatEditText7.perform(replaceText("test1"), closeSoftKeyboard());

         appCompatEditText8 = onView(
                allOf(withId(R.id.passwordCheck), isDisplayed()));
        appCompatEditText8.perform(replaceText("test1"), closeSoftKeyboard());

        Thread.sleep(500);

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.register), withText("Register"), isDisplayed()));
        appCompatButton2.perform(click());

        Thread.sleep(1900);

        onView(withText("OK")).perform(click());

        Thread.sleep(1500);

        //*******************************************************************
        appCompatEditText2 = onView(
                allOf(withId(R.id.nickName), isDisplayed()));
        appCompatEditText2.perform(replaceText("test1"), closeSoftKeyboard());

        appCompatEditText3 = onView(
                allOf(withId(R.id.firstName), isDisplayed()));
        appCompatEditText3.perform(replaceText(""), closeSoftKeyboard());

        appCompatEditText4 = onView(
                allOf(withId(R.id.lastName), isDisplayed()));
        appCompatEditText4.perform(replaceText("test1"), closeSoftKeyboard());

        appCompatEditText5 = onView(
                allOf(withId(R.id.email), isDisplayed()));
        appCompatEditText5.perform(replaceText("test1"), closeSoftKeyboard());

        appCompatEditText6 = onView(
                allOf(withId(R.id.location), isDisplayed()));
        appCompatEditText6.perform(replaceText("test1"), closeSoftKeyboard());

        appCompatEditText7 = onView(
                allOf(withId(R.id.password), isDisplayed()));
        appCompatEditText7.perform(replaceText("test1"), closeSoftKeyboard());

        appCompatEditText8 = onView(
                allOf(withId(R.id.passwordCheck), isDisplayed()));
        appCompatEditText8.perform(replaceText("test1"), closeSoftKeyboard());

        Thread.sleep(500);

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.register), withText("Register"), isDisplayed()));
        appCompatButton3.perform(click());

        Thread.sleep(1900);

        onView(withText("OK")).perform(click());

        Thread.sleep(1500);

        //*******************************************************************

        appCompatEditText2 = onView(
                allOf(withId(R.id.nickName), isDisplayed()));
        appCompatEditText2.perform(replaceText("test1"), closeSoftKeyboard());

        appCompatEditText3 = onView(
                allOf(withId(R.id.firstName), isDisplayed()));
        appCompatEditText3.perform(replaceText("test1"), closeSoftKeyboard());

        appCompatEditText4 = onView(
                allOf(withId(R.id.lastName), isDisplayed()));
        appCompatEditText4.perform(replaceText(""), closeSoftKeyboard());

        appCompatEditText5 = onView(
                allOf(withId(R.id.email), isDisplayed()));
        appCompatEditText5.perform(replaceText("test1"), closeSoftKeyboard());

        appCompatEditText6 = onView(
                allOf(withId(R.id.location), isDisplayed()));
        appCompatEditText6.perform(replaceText("test1"), closeSoftKeyboard());

        appCompatEditText7 = onView(
                allOf(withId(R.id.password), isDisplayed()));
        appCompatEditText7.perform(replaceText("test1"), closeSoftKeyboard());

        appCompatEditText8 = onView(
                allOf(withId(R.id.passwordCheck), isDisplayed()));
        appCompatEditText8.perform(replaceText("test1"), closeSoftKeyboard());

        Thread.sleep(500);

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(R.id.register), withText("Register"), isDisplayed()));
        appCompatButton4.perform(click());

        Thread.sleep(1900);

        onView(withText("OK")).perform(click());

        Thread.sleep(1500);

        //*******************************************************************
        appCompatEditText2 = onView(
                allOf(withId(R.id.nickName), isDisplayed()));
        appCompatEditText2.perform(replaceText("test1"), closeSoftKeyboard());

        appCompatEditText3 = onView(
                allOf(withId(R.id.firstName), isDisplayed()));
        appCompatEditText3.perform(replaceText("test1"), closeSoftKeyboard());

        appCompatEditText4 = onView(
                allOf(withId(R.id.lastName), isDisplayed()));
        appCompatEditText4.perform(replaceText("test1"), closeSoftKeyboard());

        appCompatEditText5 = onView(
                allOf(withId(R.id.email), isDisplayed()));
        appCompatEditText5.perform(replaceText(""), closeSoftKeyboard());

        appCompatEditText6 = onView(
                allOf(withId(R.id.location), isDisplayed()));
        appCompatEditText6.perform(replaceText("test1"), closeSoftKeyboard());

        appCompatEditText7 = onView(
                allOf(withId(R.id.password), isDisplayed()));
        appCompatEditText7.perform(replaceText("test1"), closeSoftKeyboard());

        appCompatEditText8 = onView(
                allOf(withId(R.id.passwordCheck), isDisplayed()));
        appCompatEditText8.perform(replaceText("test1"), closeSoftKeyboard());

        Thread.sleep(500);

        ViewInteraction appCompatButton5 = onView(
                allOf(withId(R.id.register), withText("Register"), isDisplayed()));
        appCompatButton5.perform(click());

        Thread.sleep(1900);

        onView(withText("OK")).perform(click());

        Thread.sleep(1500);
        //*******************************************************************
        appCompatEditText2 = onView(
                allOf(withId(R.id.nickName), isDisplayed()));
        appCompatEditText2.perform(replaceText("test1"), closeSoftKeyboard());

        appCompatEditText3 = onView(
                allOf(withId(R.id.firstName), isDisplayed()));
        appCompatEditText3.perform(replaceText("test1"), closeSoftKeyboard());

        appCompatEditText4 = onView(
                allOf(withId(R.id.lastName), isDisplayed()));
        appCompatEditText4.perform(replaceText("test1"), closeSoftKeyboard());

        appCompatEditText5 = onView(
                allOf(withId(R.id.email), isDisplayed()));
        appCompatEditText5.perform(replaceText("test1"), closeSoftKeyboard());

        appCompatEditText6 = onView(
                allOf(withId(R.id.location), isDisplayed()));
        appCompatEditText6.perform(replaceText(""), closeSoftKeyboard());

        appCompatEditText7 = onView(
                allOf(withId(R.id.password), isDisplayed()));
        appCompatEditText7.perform(replaceText("test1"), closeSoftKeyboard());

        appCompatEditText8 = onView(
                allOf(withId(R.id.passwordCheck), isDisplayed()));
        appCompatEditText8.perform(replaceText("test1"), closeSoftKeyboard());

        Thread.sleep(500);

        ViewInteraction appCompatButton6 = onView(
                allOf(withId(R.id.register), withText("Register"), isDisplayed()));
        appCompatButton6.perform(click());

        Thread.sleep(1900);

        onView(withText("OK")).perform(click());

        Thread.sleep(1500);
        //*******************************************************************
        appCompatEditText2 = onView(
                allOf(withId(R.id.nickName), isDisplayed()));
        appCompatEditText2.perform(replaceText("test1"), closeSoftKeyboard());

        appCompatEditText3 = onView(
                allOf(withId(R.id.firstName), isDisplayed()));
        appCompatEditText3.perform(replaceText("test1"), closeSoftKeyboard());

        appCompatEditText4 = onView(
                allOf(withId(R.id.lastName), isDisplayed()));
        appCompatEditText4.perform(replaceText("test1"), closeSoftKeyboard());

        appCompatEditText5 = onView(
                allOf(withId(R.id.email), isDisplayed()));
        appCompatEditText5.perform(replaceText("test1"), closeSoftKeyboard());

        appCompatEditText6 = onView(
                allOf(withId(R.id.location), isDisplayed()));
        appCompatEditText6.perform(replaceText("test1"), closeSoftKeyboard());

        appCompatEditText7 = onView(
                allOf(withId(R.id.password), isDisplayed()));
        appCompatEditText7.perform(replaceText(""), closeSoftKeyboard());

        appCompatEditText8 = onView(
                allOf(withId(R.id.passwordCheck), isDisplayed()));
        appCompatEditText8.perform(replaceText("test1"), closeSoftKeyboard());

        Thread.sleep(500);

        ViewInteraction appCompatButton7 = onView(
                allOf(withId(R.id.register), withText("Register"), isDisplayed()));
        appCompatButton7.perform(click());

        Thread.sleep(1900);

        onView(withText("OK")).perform(click());

        Thread.sleep(1500);
        //*******************************************************************
        appCompatEditText2 = onView(
                allOf(withId(R.id.nickName), isDisplayed()));
        appCompatEditText2.perform(replaceText("test1"), closeSoftKeyboard());

        appCompatEditText3 = onView(
                allOf(withId(R.id.firstName), isDisplayed()));
        appCompatEditText3.perform(replaceText("test1"), closeSoftKeyboard());

        appCompatEditText4 = onView(
                allOf(withId(R.id.lastName), isDisplayed()));
        appCompatEditText4.perform(replaceText("test1"), closeSoftKeyboard());

        appCompatEditText5 = onView(
                allOf(withId(R.id.email), isDisplayed()));
        appCompatEditText5.perform(replaceText("test1"), closeSoftKeyboard());

        appCompatEditText6 = onView(
                allOf(withId(R.id.location), isDisplayed()));
        appCompatEditText6.perform(replaceText("test1"), closeSoftKeyboard());

        appCompatEditText7 = onView(
                allOf(withId(R.id.password), isDisplayed()));
        appCompatEditText7.perform(replaceText("test1"), closeSoftKeyboard());

        appCompatEditText8 = onView(
                allOf(withId(R.id.passwordCheck), isDisplayed()));
        appCompatEditText8.perform(replaceText(""), closeSoftKeyboard());

        Thread.sleep(500);

        ViewInteraction appCompatButton8 = onView(
                allOf(withId(R.id.register), withText("Register"), isDisplayed()));
        appCompatButton8.perform(click());

        Thread.sleep(1900);

        onView(withText("OK")).perform(click());

        Thread.sleep(1500);
        //*******************************************************************
        appCompatEditText2 = onView(
                allOf(withId(R.id.nickName), isDisplayed()));
        appCompatEditText2.perform(replaceText("test1"), closeSoftKeyboard());

        appCompatEditText3 = onView(
                allOf(withId(R.id.firstName), isDisplayed()));
        appCompatEditText3.perform(replaceText("test1"), closeSoftKeyboard());

        appCompatEditText4 = onView(
                allOf(withId(R.id.lastName), isDisplayed()));
        appCompatEditText4.perform(replaceText("test1"), closeSoftKeyboard());

        appCompatEditText5 = onView(
                allOf(withId(R.id.email), isDisplayed()));
        appCompatEditText5.perform(replaceText("test1"), closeSoftKeyboard());

        appCompatEditText6 = onView(
                allOf(withId(R.id.location), isDisplayed()));
        appCompatEditText6.perform(replaceText("test1"), closeSoftKeyboard());

        appCompatEditText7 = onView(
                allOf(withId(R.id.password), isDisplayed()));
        appCompatEditText7.perform(replaceText("test1"), closeSoftKeyboard());

        appCompatEditText8 = onView(
                allOf(withId(R.id.passwordCheck), isDisplayed()));
        appCompatEditText8.perform(replaceText("test2"), closeSoftKeyboard());

        Thread.sleep(500);

        ViewInteraction appCompatButton9 = onView(
                allOf(withId(R.id.register), withText("Register"), isDisplayed()));
        appCompatButton9.perform(click());

        Thread.sleep(1900);

        onView(withText("OK")).perform(click());

        Thread.sleep(1500);
        //*******************************************************************

    }

    @Test
    public void emptyRegisterTest() throws Exception {


    }
}
