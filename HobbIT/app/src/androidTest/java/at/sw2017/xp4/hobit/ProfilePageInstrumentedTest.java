package at.sw2017.xp4.hobit;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.provider.Settings;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;
import android.util.Log;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.security.SecureRandom;

import static android.app.PendingIntent.getActivity;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.DrawerActions.open;
import static android.support.test.espresso.contrib.DrawerMatchers.isClosed;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

/**
 * Created by andy on 22.03.2017.
 */

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ProfilePageInstrumentedTest {


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
            setFlightMode(mActivityRule.getActivity(), 1);
            Thread.sleep(10000);
        }
        else {
            setFlightMode(mActivityRule.getActivity(), 0);
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
    public ActivityTestRule<EditProfileActivity> mActivityRule =
            new ActivityTestRule<>(EditProfileActivity.class);

    @Test
    public void changePropertiesProfilPageInstrTest() throws Exception {
        Globals.getInstance().setUserID("2");
        mActivityRule.getActivity().update();
        Thread.sleep(4000);

        onView(withId(R.id.editTextProfileNickname)).perform(replaceText("Il Dottore"));
        onView(withId(R.id.editTextProfileForename)).perform(replaceText("Valentino"));
        onView(withId(R.id.editTextProfileSurename)).perform(replaceText("Rossi"));
        onView(withId(R.id.editTextProfileLocation)).perform(replaceText("Italy"));

        onView(withId(R.id.ButtonSave)).perform(click());

        Thread.sleep(3000);


        //Failte sehr oft!! --> ersetzt mit dirty pressBack();
       // onView(withText("OK")).perform(click());
        pressBack();

        Globals.getInstance().setUserID("fb103863606870788");
        mActivityRule.getActivity().update();
        Thread.sleep(6500);

        onView(withId(R.id.editTextProfileNickname)).check(matches(withText("SlanyTest")));
        onView(withId(R.id.editTextProfileForename)).check(matches(withText("Slany")));
        onView(withId(R.id.editTextProfileSurename)).check(matches(withText("GreatAgain")));
        onView(withId(R.id.editTextProfileLocation)).check(matches(withText("Graz")));

        Globals.getInstance().setUserID("2");
        mActivityRule.getActivity().update();
        Thread.sleep(3000);

        onView(withId(R.id.editTextProfileNickname)).check(matches(withText("Il Dottore")));
        onView(withId(R.id.editTextProfileForename)).check(matches(withText("Valentino")));
        onView(withId(R.id.editTextProfileSurename)).check(matches(withText("Rossi")));
        onView(withId(R.id.editTextProfileLocation)).check(matches(withText("Italy")));

        Thread.sleep(3000);

        onView(withId(R.id.editTextProfileNickname)).perform(replaceText("Johnny"));
        onView(withId(R.id.editTextProfileForename)).perform(replaceText("John"));
        onView(withId(R.id.editTextProfileSurename)).perform(replaceText("Doe"));
        onView(withId(R.id.editTextProfileLocation)).perform(replaceText("Graz"));

        onView(withId(R.id.ButtonSave)).perform(click());
    }

    @Test
    public void failedDatabaseTest() throws Exception {
        Thread.sleep(4000);
        Globals.getInstance().setUserID("test00FAIL");
        mActivityRule.getActivity().update();

        Thread.sleep(5000);

       /* onView(withId(R.id.editTextProfileNickname)).check(matches(withText("")));
        onView(withId(R.id.editTextProfileForename)).check(matches(withText("")));
        onView(withId(R.id.editTextProfileSurename)).check(matches(withText("")));
        onView(withId(R.id.editTextProfileLocation)).check(matches(withText("")));*/

    }

    @Test
    public void wrongUserIdTest() throws Exception {
        Thread.sleep(4000);
        Globals.getInstance().setUserID("not_existing");
        mActivityRule.getActivity().update();

        Thread.sleep(3000);

        onView(withId(R.id.editTextProfileNickname)).perform(replaceText("Il Dottore"));
        onView(withId(R.id.editTextProfileForename)).perform(replaceText("Valentino"));
        onView(withId(R.id.editTextProfileSurename)).perform(replaceText("Rossi"));
        onView(withId(R.id.editTextProfileLocation)).perform(replaceText("Italy"));

        onView(withId(R.id.ButtonSave)).perform(click());
    }

}
