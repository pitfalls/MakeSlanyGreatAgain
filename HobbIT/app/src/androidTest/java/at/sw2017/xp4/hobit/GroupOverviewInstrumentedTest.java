package at.sw2017.xp4.hobit;


import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.provider.Settings;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.util.Log;
import android.widget.Spinner;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

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
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class GroupOverviewInstrumentedTest {

    // Von: https://stackoverflow.com/questions/25674655/how-to-turn-on-off-airplane-mode-even-on-new-android-versions-and-even-with-ro
    //****************************************************************************************************
    //****************************************************************************************************

    public boolean isNetworkAvailable(Context contextValue) {
        Context context = contextValue;
        ConnectivityManager connectivity = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }

        }
        return false;
    }

    private void executeCommandWithoutWait(Context context, String option, String command) {
        boolean success = false;
        String su = "su";
        for (int i = 0; i < 3; i++) {
            // "su" command executed successfully.
            if (success) {
                // Stop executing alternative su commands below.
                break;
            }
            if (i == 1) {
                su = "/system/xbin/su";
            } else if (i == 2) {
                su = "/system/bin/su";
            }
            try {
                // execute command
                Runtime.getRuntime().exec(new String[]{su, option, command});
            } catch (IOException e) {
                Log.e("", "su command has failed due to: " + e.fillInStackTrace());
            }
        }
    }

    @SuppressLint("NewApi")
    @SuppressWarnings("deprecation")
    private boolean isFlightModeEnabled(Context context) {
        boolean mode = false;
        // API 17 onwards
        mode = Settings.Global.getInt(context.getContentResolver(), Settings.Global.AIRPLANE_MODE_ON, 0) == 1;
        return mode;
    }

    private final String COMMAND_FLIGHT_MODE_1 = "settings put global airplane_mode_on";
    private final String COMMAND_FLIGHT_MODE_2 = "am broadcast -a android.intent.action.AIRPLANE_MODE --ez state";

    @SuppressLint("NewApi")
    @SuppressWarnings("deprecation")
    public void setFlightMode(Context context, int mode) {
        // API 17 and greater :)
        // NEEDS ROOT ACCESS!!!!!! SU Abfrage wird gemacht.. dann nochmals starten..
       // int enabled = isFlightModeEnabled(context) ? 0 : 1;
        // Set Airplane / Flight mode using su commands.
        String command = COMMAND_FLIGHT_MODE_1 + " " + mode;
        executeCommandWithoutWait(context, "-c", command);
        command = COMMAND_FLIGHT_MODE_2 + " " + mode;
        executeCommandWithoutWait(context, "-c", command);

        //--------------------------------------------------- flightmode set

      /*  ConnectivityManager mgr =  (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        mgr.setAirplaneMode(true);*/

    }


    static final boolean ON = true;
    static final boolean OFF = false;
    @SuppressLint("NewApi")
    public void setAirplaneMode(boolean mode) throws InterruptedException {
        Thread.sleep(1500);

        if(mode)
        {
            setFlightMode(mActivityTestRule.getActivity(), 1);
            Thread.sleep(10000);
        }
        else {
            setFlightMode(mActivityTestRule.getActivity(), 0);
            Thread.sleep(10000);
        }
    }


    //****************************************************************************************************
    //****************************************************************************************************
    //****************************************************************************************************
    //****************************************************************************************************
    //****************************************************************************************************
    //****************************************************************************************************

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
        Thread.sleep(5000);

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
        Thread.sleep(500);

        ViewInteraction appCompatCheckedTextView = onView(
                allOf(withId(R.id.design_menu_item_text), withText("Group Overview"), isDisplayed()));
        appCompatCheckedTextView.perform(click());

        //*********************

        ViewInteraction appCompatEditText66 = onView(
                allOf(withId(R.id.txtview_location_input), isDisplayed()));
        appCompatEditText66.perform(replaceText("FADHJ"), closeSoftKeyboard());
        Thread.sleep(1000);
        closeSoftKeyboard();
        onView(withId(R.id.txtview_location_input)).perform(replaceText(""));
        ViewInteraction appCompatEditText67 = onView(
                allOf(withId(R.id.txtGroupText), isDisplayed()));
        appCompatEditText67.perform(replaceText("FADHJ"), closeSoftKeyboard());
        Thread.sleep(1000);
        closeSoftKeyboard();
        onView(withId(R.id.txtGroupText)).perform(replaceText(""));

        //*********************


        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.btn_join), withText("Join"), isDisplayed()));
        appCompatButton2.perform(click());

        Thread.sleep(5000);

        //Failt hin und wieder
        ViewInteraction appCompatButton3 = onView(
                allOf(withId(android.R.id.button2), withText("Ok")));
        appCompatButton3.perform(scrollTo(), click());

        //onView(withText("OK")).perform(click());

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

        Thread.sleep(1000);

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

        Thread.sleep(5000);

        ViewInteraction appCompatButton5 = onView(
                allOf(withId(android.R.id.button2), withText("Ok")));
        appCompatButton5.perform(scrollTo(), click());

        onView(withId(R.id.txtview_location_input)).perform(replaceText("Salz7"));

        Thread.sleep(500);

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

        Thread.sleep(500);

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

        /***********************/

        onView(withId(R.id.txtview_location_input)).perform(replaceText(""));
        onView(withId(R.id.txtGroupText)).perform(replaceText(""));

        ViewInteraction appCompatEditText68 = onView(
                allOf(withId(R.id.txtview_location_input), isDisplayed()));
        appCompatEditText68.perform(replaceText("FADHJ"), closeSoftKeyboard());
        Thread.sleep(1000);
        closeSoftKeyboard();

        onView(withId(R.id.txtview_location_input)).perform(replaceText(""));

        pressBack();

       /* ViewInteraction appCompatEditText69 = onView(
                allOf(withId(R.id.txtGroupText), isDisplayed()));
        appCompatEditText69.perform(replaceText("Cy"), closeSoftKeyboard());
        Thread.sleep(1000);

           ViewInteraction appCompatSpinner18 = onView(
                allOf(withId(R.id.spinnerGroup), isDisplayed()));
        appCompatSpinner18.perform(click());*/

        //   onView(withId(R.id.txtGroupText)).perform(replaceText(""));

        /***********************/


    }

    @Test
    public void offlineResponderTests() throws InterruptedException {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

      //  assertEquals(true, isNetworkAvailable(mActivityTestRule.getActivity()));

        setAirplaneMode(ON);

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


        Thread.sleep(1500);

        /*ViewInteraction appCompatButton7 = onView(
                allOf(withId(android.R.id.button2), withText("Retry")));
        appCompatButton7.perform(scrollTo(), click());
*/
        setAirplaneMode(OFF);

        Thread.sleep(4000);

    }
}