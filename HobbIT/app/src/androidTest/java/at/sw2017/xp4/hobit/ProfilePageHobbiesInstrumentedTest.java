package at.sw2017.xp4.hobit;


import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.security.SecureRandom;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertEquals;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ProfilePageHobbiesInstrumentedTest {

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
            Thread.sleep(7500);
        }
        else {
            setFlightMode(mActivityTestRule.getActivity(), 0);
            Thread.sleep(7500);
        }
    }

    @SuppressLint("NewApi")
    public void setWlanMode(boolean mode) throws InterruptedException {
        Thread.sleep(1500);

        @SuppressLint("WifiManagerLeak") WifiManager wifi = (WifiManager) mActivityTestRule.getActivity().getSystemService(Context.WIFI_SERVICE);
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
                allOf(withId(R.id.login), withText(endsWith("Login")), isDisplayed()));
        appCompatButton.perform(click());

        Thread.sleep(3500);

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

        ViewInteraction appCompatTextView = onView(
                allOf(withId(R.id.title), withText("Edit Profile"), isDisplayed()));
        appCompatTextView.perform(click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.buttonEditInterests), withText(endsWith("Edit Interests")), isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction appCompatCheckBox = onView(
                allOf(withId(R.id.checkBox1), withText(endsWith("Swimming")), isDisplayed()));
        appCompatCheckBox.perform(click());

        ViewInteraction appCompatCheckBox2 = onView(
                allOf(withId(R.id.checkBox2), withText(endsWith("Hiking")), isDisplayed()));
        appCompatCheckBox2.perform(click());

        ViewInteraction appCompatCheckBox3 = onView(
                allOf(withId(R.id.checkBox3), withText(endsWith("Fitness studio")), isDisplayed()));
        appCompatCheckBox3.perform(click());

        ViewInteraction appCompatCheckBox4 = onView(
                allOf(withId(R.id.checkBox4), withText(endsWith("Playing cards")), isDisplayed()));
        appCompatCheckBox4.perform(click());

        ViewInteraction appCompatCheckBox5 = onView(
                allOf(withId(R.id.checkBox5), withText(endsWith("Computer Games")), isDisplayed()));
        appCompatCheckBox5.perform(click());

        ViewInteraction appCompatCheckBox6 = onView(
                allOf(withId(R.id.checkBox6), withText(endsWith("Solve quiz")), isDisplayed()));
        appCompatCheckBox6.perform(click());

        ViewInteraction appCompatCheckBox7 = onView(
                allOf(withId(R.id.checkBox7), withText(endsWith("Ride a bicycle")), isDisplayed()));
        appCompatCheckBox7.perform(click());

        ViewInteraction appCompatCheckBox8 = onView(
                allOf(withId(R.id.checkBox8), withText(endsWith("Puzzle")), isDisplayed()));
        appCompatCheckBox8.perform(click());

        ViewInteraction appCompatCheckBox9 = onView(
                allOf(withId(R.id.checkBox9), withText(endsWith("Needle")), isDisplayed()));
        appCompatCheckBox9.perform(click());

        ViewInteraction appCompatCheckBox10 = onView(
                allOf(withId(R.id.checkBox10), withText(endsWith("Model making")), isDisplayed()));
        appCompatCheckBox10.perform(click());

        ViewInteraction appCompatCheckBox11 = onView(
                allOf(withId(R.id.checkBox11), withText(endsWith("Yoyo playing")), isDisplayed()));
        appCompatCheckBox11.perform(click());

        ViewInteraction appCompatCheckBox12 = onView(
                allOf(withId(R.id.checkBox12), withText(endsWith("Dancing")), isDisplayed()));
        appCompatCheckBox12.perform(click());

        ViewInteraction appCompatCheckBox13 = onView(
                allOf(withId(R.id.checkBox13), withText(endsWith("Climbing")), isDisplayed()));
        appCompatCheckBox13.perform(click());

        ViewInteraction appCompatCheckBox14 = onView(
                allOf(withId(R.id.checkBox14), withText(endsWith("Astronomy")), isDisplayed()));
        appCompatCheckBox14.perform(click());

        ViewInteraction appCompatCheckBox15 = onView(
                allOf(withId(R.id.checkBox15), withText(endsWith("Collecting")), isDisplayed()));
        appCompatCheckBox15.perform(click());

        ViewInteraction appCompatCheckBox16 = onView(
                allOf(withId(R.id.checkBox16), withText(endsWith("Cooking")), isDisplayed()));
        appCompatCheckBox16.perform(click());

        ViewInteraction appCompatCheckBox17 = onView(
                allOf(withId(R.id.checkBox17), withText(endsWith("Football")), isDisplayed()));
        appCompatCheckBox17.perform(click());

        ViewInteraction appCompatCheckBox18 = onView(
                allOf(withId(R.id.checkBox18), withText(endsWith("Music")), isDisplayed()));
        appCompatCheckBox18.perform(click());

        ViewInteraction appCompatCheckBox19 = onView(
                allOf(withId(R.id.checkBox19), withText(endsWith("others")), isDisplayed()));
        appCompatCheckBox19.perform(click());

        Thread.sleep(500);

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.cont_button), withText(endsWith("Continue")), isDisplayed()));
        appCompatButton3.perform(click());


    }

    @Test
    public void profilePageHobbiesInstrumentedTestOfflineNotGoodEnough() throws InterruptedException {
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
                allOf(withId(R.id.login), withText(endsWith("Login")), isDisplayed()));
        appCompatButton.perform(click());

        Thread.sleep(1000);

        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

        ViewInteraction appCompatTextView = onView(
                allOf(withId(R.id.title), withText("Edit Profile"), isDisplayed()));
        appCompatTextView.perform(click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.buttonEditInterests), withText(endsWith("Edit Interests")), isDisplayed()));
        appCompatButton2.perform(click());

        Thread.sleep(500);

        setWlanMode(ON);

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.cont_button), withText(endsWith("Continue")), isDisplayed()));
        appCompatButton3.perform(click());

        setWlanMode(OFF);

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }

    @Test
    public void offlineResponderTestsRegister() throws InterruptedException {

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //  assertEquals(true, isNetworkAvailable(mActivityTestRule.getActivity()));

        setWlanMode(ON);
        assertEquals(false, isNetworkAvailable(mActivityTestRule.getActivity()));

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

        ViewInteraction appCompatButton7 = onView(
                allOf(withId(android.R.id.button2), withText("Retry")));
        appCompatButton7.perform(scrollTo(), click());

        setWlanMode(OFF);

        Thread.sleep(4000);

    }


    @Test
    public void joinGroupAndMainScreenInstrumentedTest() throws InterruptedException {
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

        Thread.sleep(4000);

        pressBack();

        Thread.sleep(2000);

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.login), withText("Login"), isDisplayed()));
        appCompatButton2.perform(click());

        Thread.sleep(4000);

        ViewInteraction appCompatTextView = onView(
                allOf(withId(android.R.id.text1), withText("House & garden"),
                        childAtPosition(
                                allOf(withId(R.id.ListViewGroups),
                                        withParent(withId(R.id.main_view))),
                                0),
                        isDisplayed()));
        appCompatTextView.perform(click());

        pressBack();

        Thread.sleep(3000);

        ViewInteraction appCompatImageButton = onView(
                allOf(withContentDescription("Open navigation drawer"),
                        withParent(withId(R.id.toolbar)),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        Thread.sleep(3000);

        ViewInteraction appCompatCheckedTextView = onView(
                allOf(withId(R.id.design_menu_item_text), withText("Join Group"), isDisplayed()));
        appCompatCheckedTextView.perform(click());

        Thread.sleep(3000);

        ViewInteraction searchAutoComplete = onView(
                allOf(withClassName(is("android.widget.SearchView$SearchAutoComplete")),
                        withParent(allOf(withClassName(is("android.widget.LinearLayout")),
                                withParent(withClassName(is("android.widget.LinearLayout"))))),
                        isDisplayed()));

        Thread.sleep(2000);
        searchAutoComplete.perform(replaceText("fl"), closeSoftKeyboard());
        Thread.sleep(4000);

        ViewInteraction textView = onView(
                allOf(withId(R.id.name), withText("Flowerpower"),
                        withParent(childAtPosition(
                                withId(R.id.expandableList),
                                1)),
                        isDisplayed()));
        textView.perform(click());

        Thread.sleep(3000);

        pressBack();

        Thread.sleep(1500);

        ViewInteraction searchAutoComplete2 = onView(
                allOf(withClassName(is("android.widget.SearchView$SearchAutoComplete")), withText("fl"),
                        withParent(allOf(withClassName(is("android.widget.LinearLayout")),
                                withParent(withClassName(is("android.widget.LinearLayout"))))),
                        isDisplayed()));

        Thread.sleep(2000);
        searchAutoComplete.perform(replaceText("bfl"), closeSoftKeyboard());
        searchAutoComplete.perform(replaceText("b"), closeSoftKeyboard());

        Thread.sleep(4000);

        ViewInteraction imageView2 = onView(
                allOf(withClassName(is("android.widget.ImageView")), withContentDescription("Abfrage löschen"),
                        withParent(allOf(withClassName(is("android.widget.LinearLayout")),
                                withParent(withClassName(is("android.widget.LinearLayout"))))),
                        isDisplayed()));
        imageView2.perform(click());

        Thread.sleep(1500);

        Thread.sleep(1500);

    }


    @Test
    public void offlineUpdateEditProfile() throws InterruptedException {
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

        setWlanMode(ON);

        ViewInteraction appCompatTextView2 = onView(
                allOf(withId(R.id.title), withText("Edit Profile"), isDisplayed()));
        appCompatTextView2.perform(click());

        pressBack();

        setWlanMode(OFF);
        pressBack();

    }


}
