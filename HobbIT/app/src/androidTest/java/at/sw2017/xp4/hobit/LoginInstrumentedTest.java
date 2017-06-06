package at.sw2017.xp4.hobit;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.Until;
import android.test.suitebuilder.annotation.LargeTest;
import android.util.Log;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

import com.facebook.login.LoginManager;

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
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.junit.Assert.assertEquals;

/**
 * Created by Gerd on 17.05.2017.
 */

@LargeTest
@RunWith(AndroidJUnit4.class)
public class LoginInstrumentedTest {

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
            Thread.sleep(7500);
        }
        else {
            setFlightMode(mActivityRule.getActivity(), 0);
            Thread.sleep(7500);
        }
    }

    @SuppressLint("NewApi")
    public void setWlanMode(boolean mode) throws InterruptedException {
        Thread.sleep(1500);

        @SuppressLint("WifiManagerLeak") WifiManager wifi = (WifiManager) mActivityRule.getActivity().getSystemService(Context.WIFI_SERVICE);
        wifi.setWifiEnabled(!mode); // true or false to activate/deactivate wifi
        Thread.sleep(3500);
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
    public ActivityTestRule<FacebookLogin> mActivityRule =
            new ActivityTestRule<>(FacebookLogin.class);

    @Test
    public void changePropertiesLoginInstTest() throws Exception {
        onView(withId(R.id.username)).perform(replaceText("john.doe@testuser.com"));
        onView(withId(R.id.password)).perform(replaceText("JohnDoe"));

        onView(withId(R.id.login)).perform(click());

        Thread.sleep(7500);

       // assertEquals("2", Globals.getInstance().getUserID());

        Globals.getInstance().setUserID("");
    }

    @Test
    public void emptyCredentials() throws Exception {
        onView(withId(R.id.username)).perform(replaceText(""));
        onView(withId(R.id.password)).perform(replaceText(""));

        onView(withId(R.id.login)).perform(click());

        Thread.sleep(5000);

       // assertEquals("", Globals.getInstance().getUserID());

        Globals.getInstance().setUserID("");
    }


    @Test
    public void fbLoginTest() throws Exception {
        if (!Globals.getInstance().getUserID().equals("")) {
            LoginManager.getInstance().logOut();
            Globals.getInstance().setUserID("");
        }
        onView(withId(R.id.login_button)).perform(click());

        Thread.sleep(1000);

        final int timeOut = 1000 * 60;
        final UiDevice mDevice =
                UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

        // Facebook WebView - Page 1
        //mDevice.wait(Until.findObject(By.clazz(WebView.class)), timeOut);

        // Set Login
        UiObject emailInput = mDevice.findObject(new UiSelector()
                .instance(0)
                .className(EditText.class));

        emailInput.waitForExists(timeOut);
        emailInput.setText("makeslanygreatagain@centrum.sk");

        // Set Password
        UiObject passwordInput = mDevice.findObject(new UiSelector()
                .instance(1)
                .className(EditText.class));

        passwordInput.waitForExists(timeOut);
        passwordInput.setText("slanygreatagain1");

        // Confirm Button Click
        UiObject buttonLogin = mDevice.findObject(new UiSelector()
                .instance(0)
                .className(Button.class));

        buttonLogin.waitForExists(timeOut);
        buttonLogin.clickAndWaitForNewWindow();

        // Facebook WebView - Page 2
        UiObject buttonOk = mDevice.findObject(new UiSelector()
                .instance(0)
                .className(Button.class));

        buttonOk.waitForExists(timeOut);
        buttonOk.click();

        Thread.sleep(7500);

       // assertEquals("fb103863606870788", Globals.getInstance().getUserID());
    }

    @Test
    public void fbWrongLoginTest() throws Exception {
        if (!Globals.getInstance().getUserID().equals("")) {
            LoginManager.getInstance().logOut();
            Globals.getInstance().setUserID("");
        }
        onView(withId(R.id.login_button)).perform(click());

        Thread.sleep(1000);

        final int timeOut = 1000 * 60;
        final UiDevice mDevice =
                UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

        // Facebook WebView - Page 1
        //mDevice.wait(Until.findObject(By.clazz(WebView.class)), timeOut);

        // Set Login
        UiObject emailInput = mDevice.findObject(new UiSelector()
                .instance(0)
                .className(EditText.class));

        emailInput.waitForExists(timeOut);
        emailInput.setText("_________@____.sk");

        // Set Password
        UiObject passwordInput = mDevice.findObject(new UiSelector()
                .instance(1)
                .className(EditText.class));

        passwordInput.waitForExists(timeOut);
        passwordInput.setText("______");

        // Confirm Button Click
        UiObject buttonLogin = mDevice.findObject(new UiSelector()
                .instance(0)
                .className(Button.class));

        buttonLogin.waitForExists(timeOut);
        buttonLogin.clickAndWaitForNewWindow();

        // Facebook WebView - Page 2
        UiObject buttonOk = mDevice.findObject(new UiSelector()
                .instance(0)
                .className(Button.class));

        buttonOk.waitForExists(timeOut);
        buttonOk.click();

        Thread.sleep(4500);

       // assertEquals("fb103863606870788", Globals.getInstance().getUserID());
    }


    @Test
    public void connectionFailFacebook() throws InterruptedException {
        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        setWlanMode(ON);

        ViewInteraction loginButton = onView(
                allOf(withId(R.id.login_button), withText("Über Facebook anmelden"), isDisplayed()));
        loginButton.perform(click());

        Thread.sleep(1000);

        setWlanMode(OFF);
        Thread.sleep(2000);
        //*********************
    }


}
