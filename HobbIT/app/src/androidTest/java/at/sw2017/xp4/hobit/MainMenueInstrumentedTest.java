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
import org.junit.experimental.theories.Theories;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.Random;

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
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainMenueInstrumentedTest {

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
    public  void registerTestAndRandomRegister() throws InterruptedException {
        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /******************************************************************************************/ // REGISTER TEST

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.register), withText("Register"), isDisplayed()));                 //Empty Strings
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

        //------------------------------------------------------------------------------------------ TestStrings
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

        //onView(withText("OK")).perform(click());
        pressBack();
        Thread.sleep(1500);

       // mActivityTestRule.getClass().update();

        //------------------------------------------------------------------------------------------ Random User Test

        appCompatEditText2 = onView(
                allOf(withId(R.id.nickName), isDisplayed()));
        appCompatEditText2.perform(replaceText(randomString(6)), closeSoftKeyboard());

        appCompatEditText3 = onView(
                allOf(withId(R.id.firstName), isDisplayed()));
        appCompatEditText3.perform(replaceText(randomString(6)), closeSoftKeyboard());

        appCompatEditText4 = onView(
                allOf(withId(R.id.lastName), isDisplayed()));
        appCompatEditText4.perform(replaceText(randomString(6)), closeSoftKeyboard());

        appCompatEditText5 = onView(
                allOf(withId(R.id.email), isDisplayed()));
        appCompatEditText5.perform(replaceText(randomString(6)), closeSoftKeyboard());

        appCompatEditText6 = onView(
                allOf(withId(R.id.location), isDisplayed()));
        appCompatEditText6.perform(replaceText(randomString(6)), closeSoftKeyboard());

        String pw = randomString(6);

        appCompatEditText7 = onView(
                allOf(withId(R.id.password), isDisplayed()));
        appCompatEditText7.perform(replaceText(pw), closeSoftKeyboard());

        appCompatEditText8 = onView(
                allOf(withId(R.id.passwordCheck), isDisplayed()));
        appCompatEditText8.perform(replaceText(pw), closeSoftKeyboard());

        Thread.sleep(500);

        appCompatButton1 = onView(
                allOf(withId(R.id.register), withText("Register"), isDisplayed()));
        appCompatButton1.perform(click());

        Thread.sleep(2900);

        //  onView(withText("OK")).perform(click());  MSG mit Ok kommt nur kurz dann geht weiter zum Starndardfenster.... lil Bug ;p

        //Thread.sleep(1500);

        //------------------------------------------------------------------------------------------ Ende

       // pressBack();
    }
        /******************************************************************************************/ //MAIN

//        @Test
//        public  void mainMenueTestFullVersion() throws InterruptedException {
//
//            try {
//                Thread.sleep(5000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//
//            ViewInteraction appCompatEditText = onView(
//                    allOf(withId(R.id.username), isDisplayed()));
//            appCompatEditText.perform(click());
//
//            ViewInteraction appCompatEditText2 = onView(
//                    allOf(withId(R.id.username), isDisplayed()));
//            appCompatEditText2.perform(replaceText("test"), closeSoftKeyboard());
//
//            ViewInteraction appCompatEditText3 = onView(
//                    allOf(withId(R.id.password), isDisplayed()));
//            appCompatEditText3.perform(replaceText("test"), closeSoftKeyboard());
//
//            ViewInteraction appCompatButton = onView(
//                    allOf(withId(R.id.login), withText("Login"), isDisplayed()));
//            appCompatButton.perform(click());
//
//            Thread.sleep(3500);
//
//            openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
//
//            ViewInteraction appCompatTextView = onView(
//                    allOf(withId(R.id.title), withText("Login"), isDisplayed()));
//            appCompatTextView.perform(click());
//
//            pressBack();
//
//            openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
//
//            ViewInteraction appCompatTextView2 = onView(
//                    allOf(withId(R.id.title), withText("Edit Profile"), isDisplayed()));
//            appCompatTextView2.perform(click());
//
//            pressBack();
//
//            ViewInteraction floatingActionButton = onView(
//                    allOf(withId(R.id.fab), isDisplayed()));
//            floatingActionButton.perform(click());
//
//            Thread.sleep(1500);
//
//            ViewInteraction appCompatImageButton = onView(
//                    allOf(withContentDescription("Open navigation drawer"),
//                            withParent(withId(R.id.toolbar)),
//                            isDisplayed()));
//            appCompatImageButton.perform(click());
//
//            Thread.sleep(500);
//            pressBack();
//
//            ViewInteraction appCompatImageButton2 = onView(
//                    allOf(withContentDescription("Open navigation drawer"),
//                            withParent(withId(R.id.toolbar)),
//                            isDisplayed()));
//            appCompatImageButton2.perform(click());
//            Thread.sleep(2500);
//
//            ViewInteraction appCompatCheckedTextView = onView(
//                    allOf(withId(R.id.design_menu_item_text), withText("Join Group"), isDisplayed()));
//            appCompatCheckedTextView.perform(click());
//
//            Thread.sleep(4000);
//            closeSoftKeyboard();
//            Thread.sleep(4000);
//            pressBack();
//            pressBack();
//            Thread.sleep(4000);
//
//            ViewInteraction appCompatImageButton3 = onView(
//                    allOf(withContentDescription("Open navigation drawer"),
//                            withParent(withId(R.id.toolbar)),
//                            isDisplayed()));
//            appCompatImageButton3.perform(click());
//
//            //appCompatImageButton2.perform(click());
//
//            Thread.sleep(1500);
//
//            ViewInteraction appCompatCheckedTextView2 = onView(
//                    allOf(withId(R.id.design_menu_item_text), withText("Group Overview"), isDisplayed()));
//            appCompatCheckedTextView2.perform(click());
//
////        ViewInteraction appCompatImageButton4 = onView(
////                allOf(withContentDescription("Nach oben"),
////                        withParent(allOf(withId(R.id.action_bar),
////                                withParent(withId(R.id.action_bar_container)))),
////                        isDisplayed()));
//
//            //   appCompatImageButton4.perform(click());
//
//            Thread.sleep(2000);
//            pressBack();
//            Thread.sleep(2000);
//
//            ViewInteraction appCompatImageButton4 = onView(
//                    allOf(withContentDescription("Open navigation drawer"),
//                            withParent(withId(R.id.toolbar)),
//                            isDisplayed()));
//            appCompatImageButton4.perform(click());
//            Thread.sleep(2500);
//            //  appCompatImageButton2.perform(click());
//
//            ViewInteraction appCompatCheckedTextView3 = onView(
//                    allOf(withId(R.id.design_menu_item_text), withText("Group Creation"), isDisplayed()));
//            appCompatCheckedTextView3.perform(click());
//
//            pressBack();
//
//        }


//    @Test
//    public void joinGroupAndMainScreenInstrumentedTest() throws InterruptedException {
//        // Added a sleep statement to match the app's execution delay.
//        // The recommended way to handle such scenarios is to use Espresso idling resources:
//        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        ViewInteraction appCompatEditText = onView(
//                allOf(withId(R.id.username), isDisplayed()));
//        appCompatEditText.perform(click());
//
//        ViewInteraction appCompatEditText2 = onView(
//                allOf(withId(R.id.username), isDisplayed()));
//        appCompatEditText2.perform(replaceText("test"), closeSoftKeyboard());
//
//        ViewInteraction appCompatEditText3 = onView(
//                allOf(withId(R.id.password), isDisplayed()));
//        appCompatEditText3.perform(replaceText("test"), closeSoftKeyboard());
//
//        ViewInteraction appCompatButton = onView(
//                allOf(withId(R.id.login), withText("Login"), isDisplayed()));
//        appCompatButton.perform(click());
//
//        Thread.sleep(4000);
//
//        pressBack();
//
//        Thread.sleep(2000);
//
//        ViewInteraction appCompatButton2 = onView(
//                allOf(withId(R.id.login), withText("Login"), isDisplayed()));
//        appCompatButton2.perform(click());
//
//        Thread.sleep(4000);
//
//        ViewInteraction appCompatTextView = onView(
//                allOf(withId(android.R.id.text1), withText("House & garden"),
//                        childAtPosition(
//                                allOf(withId(R.id.ListViewGroups),
//                                        withParent(withId(R.id.main_view))),
//                                0),
//                        isDisplayed()));
//        appCompatTextView.perform(click());
//
//        pressBack();
//
//        Thread.sleep(3000);
//
//        ViewInteraction appCompatImageButton = onView(
//                allOf(withContentDescription("Open navigation drawer"),
//                        withParent(withId(R.id.toolbar)),
//                        isDisplayed()));
//        appCompatImageButton.perform(click());
//
//        Thread.sleep(3000);
//
//        ViewInteraction appCompatCheckedTextView = onView(
//                allOf(withId(R.id.design_menu_item_text), withText("Join Group"), isDisplayed()));
//        appCompatCheckedTextView.perform(click());
//
//        Thread.sleep(3000);
//
//        ViewInteraction searchAutoComplete = onView(
//                allOf(withClassName(is("android.widget.SearchView$SearchAutoComplete")),
//                        withParent(allOf(withClassName(is("android.widget.LinearLayout")),
//                                withParent(withClassName(is("android.widget.LinearLayout"))))),
//                        isDisplayed()));
//
//        Thread.sleep(2000);
//        searchAutoComplete.perform(replaceText("fl"), closeSoftKeyboard());
//        Thread.sleep(4000);
//
//        ViewInteraction textView = onView(
//                allOf(withId(R.id.name), withText("Flowerpower"),
//                        withParent(childAtPosition(
//                                withId(R.id.expandableList),
//                                1)),
//                        isDisplayed()));
//        textView.perform(click());
//
//        Thread.sleep(3000);
//
//        pressBack();
//
//        Thread.sleep(1500);
//
//        ViewInteraction searchAutoComplete2 = onView(
//                allOf(withClassName(is("android.widget.SearchView$SearchAutoComplete")), withText("fl"),
//                        withParent(allOf(withClassName(is("android.widget.LinearLayout")),
//                                withParent(withClassName(is("android.widget.LinearLayout"))))),
//                        isDisplayed()));
//
//        Thread.sleep(2000);
//        searchAutoComplete.perform(replaceText("bfl"), closeSoftKeyboard());
//        searchAutoComplete.perform(replaceText("b"), closeSoftKeyboard());
//
//        Thread.sleep(4000);
//
//        ViewInteraction imageView2 = onView(
//                allOf(withClassName(is("android.widget.ImageView")), withContentDescription("Abfrage löschen"),
//                        withParent(allOf(withClassName(is("android.widget.LinearLayout")),
//                                withParent(withClassName(is("android.widget.LinearLayout"))))),
//                        isDisplayed()));
//        imageView2.perform(click());
//
//        Thread.sleep(1500);
//
//        // textView.perform(click());
//
//        // Thread.sleep(1500);
//
////        ViewInteraction appCompatButton3 = onView(
////                allOf(withId(R.id.btn_join), withText("Join"), isDisplayed()));
////        appCompatButton3.perform(click());
//
//        Thread.sleep(1500);
//
//    }

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
    public void offlineRegistration() throws InterruptedException {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.register), withText("Register"), isDisplayed()));
        appCompatButton.perform(click());

        setWlanMode(ON);

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

        ViewInteraction appCompatButton10 = onView(
                allOf(withId(R.id.register), withText("Register"), isDisplayed()));
        appCompatButton10.perform(click());

        setWlanMode(OFF);

    }


    @Test
    public void offlineTestJoinGroup() throws InterruptedException {
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
        setWlanMode(ON);
        assertEquals(false, isNetworkAvailable(mActivityTestRule.getActivity()));
        Thread.sleep(500);

        ViewInteraction appCompatImageButton = onView(
                allOf(withContentDescription("Open navigation drawer"),
                        withParent(withId(R.id.toolbar)),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        Thread.sleep(1500);

        ViewInteraction appCompatCheckedTextView = onView(
                allOf(withId(R.id.design_menu_item_text), withText("Join Group"), isDisplayed()));
        appCompatCheckedTextView.perform(click());

        Thread.sleep(1000);
        pressBack();
        Thread.sleep(1000);

        ViewInteraction searchAutoComplete = onView(
                allOf(withClassName(is("android.widget.SearchView$SearchAutoComplete")),
                        withParent(allOf(withClassName(is("android.widget.LinearLayout")),
                                withParent(withClassName(is("android.widget.LinearLayout"))))),
                        isDisplayed()));

        Thread.sleep(2500);
        pressBack();
        Thread.sleep(2000);
        searchAutoComplete.perform(replaceText("fl"), closeSoftKeyboard());
        Thread.sleep(4000);
//
//        Thread.sleep(1500);
//
//        ViewInteraction appCompatButton3 = onView(
//                allOf(withId(R.id.btn_join), withText("Join"), isDisplayed()));
//        appCompatButton3.perform(click());


        pressBack();

        setWlanMode(OFF);
        pressBack();

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

}
