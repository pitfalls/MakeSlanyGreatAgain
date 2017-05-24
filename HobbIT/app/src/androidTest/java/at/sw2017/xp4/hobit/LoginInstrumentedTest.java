package at.sw2017.xp4.hobit;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.Until;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

import com.facebook.login.LoginManager;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;

/**
 * Created by Gerd on 17.05.2017.
 */

public class LoginInstrumentedTest {

    @Rule
    public ActivityTestRule<FacebookLogin> mActivityRule =
            new ActivityTestRule<>(FacebookLogin.class);

    @Test
    public void changeProperties() throws Exception {
        onView(withId(R.id.username)).perform(replaceText("john.doe@testuser.com"));
        onView(withId(R.id.password)).perform(replaceText("JohnDoe"));

        onView(withId(R.id.login)).perform(click());

        Thread.sleep(10000);

        assertEquals("2", Globals.getInstance().getUserID());

        Globals.getInstance().setUserID("");
    }

    @Test
    public void emptyCredentials() throws Exception {
        onView(withId(R.id.username)).perform(replaceText(""));
        onView(withId(R.id.password)).perform(replaceText(""));

        onView(withId(R.id.login)).perform(click());

        Thread.sleep(10000);

        assertEquals("", Globals.getInstance().getUserID());

        Globals.getInstance().setUserID("");
    }

/*
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

        Thread.sleep(10000);

        assertEquals("fb103863606870788", Globals.getInstance().getUserID());
    }
*/

    @Test
    public void registerTest() throws Exception {
        onView(withId(R.id.register)).perform(click());
    }
}
