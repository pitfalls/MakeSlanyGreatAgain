package at.sw2017.xp4.hobit;


import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.util.Log;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.security.SecureRandom;

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
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class GroupCreationInstrumentedTest {


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



    //From: https://stackoverflow.com/questions/41107/how-to-generate-a-random-alpha-numeric-string
    static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    static SecureRandom rnd = new SecureRandom();

    String randomString( int len ){
        StringBuilder sb = new StringBuilder( len );
        for( int i = 0; i < len; i++ )
            sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
        return sb.toString();
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
    public void groupCreationTestStandard() throws InterruptedException {
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

        Thread.sleep(500);

        appCompatImageButton.perform(click());

        Thread.sleep(500);

        ViewInteraction appCompatCheckedTextView = onView(
                allOf(withId(R.id.design_menu_item_text), withText("Group Creation"), isDisplayed()));
        appCompatCheckedTextView.perform(click());



        //**///////*********////////////*************/////////////////////////******************** cancel button

        ViewInteraction appCompatButton5 = onView(
                allOf(withId(R.id.btn_cancel), withText("Cancel"), isDisplayed()));
        appCompatButton5.perform(click());

        Thread.sleep(3500);

         appCompatImageButton = onView(
                allOf(withContentDescription("Open navigation drawer"),
                        withParent(withId(R.id.toolbar)),
                        isDisplayed()));

        Thread.sleep(500);

        appCompatImageButton.perform(click());
        Thread.sleep(500);

         appCompatCheckedTextView = onView(
                allOf(withId(R.id.design_menu_item_text), withText("Group Creation"), isDisplayed()));
        appCompatCheckedTextView.perform(click());


        //**///////*********////////////*************/////////////////////////********************

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

        //****************************************************************************************** create random User
        appCompatSpinner.perform(click());
        appCompatCheckedTextView2.perform(click());

         appCompatSpinner2 = onView(
                allOf(withId(R.id.spinner_location_GroupCreation), isDisplayed()));
        appCompatSpinner2.perform(click());

         appCompatCheckedTextView3 = onView(
                allOf(withId(android.R.id.text1), withText("Wien"), isDisplayed()));
        appCompatCheckedTextView3.perform(click());


        ViewInteraction appCompatSpinner5 = onView(
                allOf(withId(R.id.editText_Groupname), isDisplayed()));
        appCompatSpinner5.perform(click());

        ViewInteraction appCompatEditText22 = onView(
                allOf(withId(R.id.editText_Groupname), isDisplayed()));
        appCompatEditText22.perform(replaceText(randomString(6)), closeSoftKeyboard());

        ViewInteraction appCompatSpinner55 = onView(
                allOf(withId(R.id.editText_Description), isDisplayed()));
        appCompatSpinner55.perform(click());

        ViewInteraction appCompatEditText23 = onView(
                allOf(withId(R.id.editText_Description), isDisplayed()));
        appCompatEditText23.perform(replaceText(randomString(120)), closeSoftKeyboard());

        ViewInteraction appCompatButton23 = onView(
                allOf(withId(R.id.btn_save), withText("Save"), isDisplayed()));
        appCompatButton23.perform(click());

    }

    @Test
    public void connectionFailTest() throws InterruptedException {
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

        Thread.sleep(500);

        appCompatImageButton.perform(click());

        Thread.sleep(500);


        //-----------------------
        setAirplaneMode(ON);
        ViewInteraction appCompatCheckedTextView = onView(
                allOf(withId(R.id.design_menu_item_text), withText("Group Creation"), isDisplayed()));
        appCompatCheckedTextView.perform(click());

        setAirplaneMode(OFF);

        Thread.sleep(4000);

    }

    @Test
    public void connectionFailTestSendNewGroup() throws InterruptedException {
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

        Thread.sleep(500);

        appCompatImageButton.perform(click());

        Thread.sleep(500);

        ViewInteraction appCompatCheckedTextView = onView(
                allOf(withId(R.id.design_menu_item_text), withText("Group Creation"), isDisplayed()));
        appCompatCheckedTextView.perform(click());

        //-----------------------
        Thread.sleep(1500);

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
                allOf(withId(android.R.id.text1), withText("Wien"), isDisplayed()));
        appCompatCheckedTextView3.perform(click());


        ViewInteraction appCompatSpinner5 = onView(
                allOf(withId(R.id.editText_Groupname), isDisplayed()));
        appCompatSpinner5.perform(click());

        ViewInteraction appCompatEditText22 = onView(
                allOf(withId(R.id.editText_Groupname), isDisplayed()));
        appCompatEditText22.perform(replaceText(randomString(200)), closeSoftKeyboard());

        ViewInteraction appCompatSpinner55 = onView(
                allOf(withId(R.id.editText_Description), isDisplayed()));
        appCompatSpinner55.perform(click());

        ViewInteraction appCompatEditText23 = onView(
                allOf(withId(R.id.editText_Description), isDisplayed()));
        appCompatEditText23.perform(replaceText(randomString(301)), closeSoftKeyboard());


//
//        setFlightMode(mActivityTestRule.getActivity(), 1);
//        //assertEquals(false, isNetworkAvailable(mActivityTestRule.getActivity()));

        ViewInteraction appCompatButton23 = onView(
                allOf(withId(R.id.btn_save), withText("Save"), isDisplayed()));
        appCompatButton23.perform(click());

      //  setAirplaneMode(OFF);

        Thread.sleep(4000);

    }


    @Test
    public void connectionFailTestValidDescriptionAndName() throws InterruptedException {
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

        Thread.sleep(500);

        appCompatImageButton.perform(click());

        Thread.sleep(500);

        ViewInteraction appCompatCheckedTextView = onView(
                allOf(withId(R.id.design_menu_item_text), withText("Group Creation"), isDisplayed()));
        appCompatCheckedTextView.perform(click());

        //-----------------------
        Thread.sleep(1500);

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
                allOf(withId(android.R.id.text1), withText("Wien"), isDisplayed()));
        appCompatCheckedTextView3.perform(click());


        ViewInteraction appCompatSpinner5 = onView(
                allOf(withId(R.id.editText_Groupname), isDisplayed()));
        appCompatSpinner5.perform(click());

        ViewInteraction appCompatEditText22 = onView(
                allOf(withId(R.id.editText_Groupname), isDisplayed()));
        appCompatEditText22.perform(replaceText(randomString(200)), closeSoftKeyboard());

        ViewInteraction appCompatSpinner55 = onView(
                allOf(withId(R.id.editText_Description), isDisplayed()));
        appCompatSpinner55.perform(click());

        ViewInteraction appCompatEditText23 = onView(
                allOf(withId(R.id.editText_Description), isDisplayed()));
        appCompatEditText23.perform(replaceText(randomString(301)), closeSoftKeyboard());


//
//        setFlightMode(mActivityTestRule.getActivity(), 1);
//        //assertEquals(false, isNetworkAvailable(mActivityTestRule.getActivity()));

        setAirplaneMode(ON);

        ViewInteraction appCompatButton23 = onView(
                allOf(withId(R.id.btn_save), withText("Save"), isDisplayed()));
        appCompatButton23.perform(click());

          setAirplaneMode(OFF);

        Thread.sleep(4000);

    }


}
