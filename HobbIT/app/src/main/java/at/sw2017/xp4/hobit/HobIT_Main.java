package at.sw2017.xp4.hobit;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.ArrayAdapter;
import android.widget.Button;

import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;

import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//TODO: Change login to logout if logged in

public class HobIT_Main extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private NavigationView sideBarNavigationView;

    public void initGroupCreation()
    {
        Button ButtonClick = (Button) findViewById(R.id.button_creation);
        ButtonClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Globals.getInstance().getUserID().equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(HobIT_Main.this);
                    builder.setMessage("Please login first")
                            .setNegativeButton("Retry", null)
                            .create()
                            .show();
                    return;
                }
                Intent intent = new Intent(HobIT_Main.this, GroupCreation.class);
                startActivity(intent);
            }
        });
    }

    public void initListGroups()
    {
        Button ButtonClick = (Button) findViewById(R.id.button_list);
        ButtonClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Globals.getInstance().getUserID().equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(HobIT_Main.this);
                    builder.setMessage("Please login first")
                            .setNegativeButton("Retry", null)
                            .create()
                            .show();
                    return;
                }
                Intent intent = new Intent(HobIT_Main.this, ListHobbyGroups.class);
                startActivity(intent);
            }
        });
    }

    public void initListViewGroups()
    {
       ListView listView = (ListView) findViewById(R.id.ListViewGroups);

        ArrayList<String>dataList = new ArrayList<String>();

        dataList.add("Ich");
        dataList.add("bin");
        dataList.add("echt");
        dataList.add("super");

        /*ArrayAdapter<String> listAdapter =
                new ArrayAdapter<>(
                        this, // Die aktuelle Umgebung (diese Activity)
                        R.layout.content_hob_it__main, // ID der XML-Layout Datei
                        R.id.ListViewGroups, // ID des TextViews
                        dataList); // Beispieldaten in einer ArrayList
*/
        //listView.setAdapter(listAdapter);

    }




    public void initGroupOverview()
    {
        Button ButtonClick = (Button) findViewById(R.id.button_overview);
        ButtonClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Globals.getInstance().getUserID().equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(HobIT_Main.this);
                    builder.setMessage("Please login first")
                            .setNegativeButton("Retry", null)
                            .create()
                            .show();
                    return;
                }
                Intent intent = new Intent(HobIT_Main.this, GroupOverview.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(Globals.getInstance().getStartStatus() == 0){
            Intent startscreen = new Intent(this, HobbiT_Main_Startscreen.class);
            startActivity(startscreen);
            Globals.getInstance().setStartStatus(1);
            finish();
        }

        setContentView(R.layout.activity_hob_it__main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /**--------------------------INIT BUTTON------------------**/
        initGroupCreation();
        initListGroups();
        initGroupOverview();
        initListViewGroups();
        /**--------------------END INIT BUTTON--------------------**/



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your .... action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        sideBarNavigationView = (NavigationView) findViewById(R.id.nav_view);
        sideBarNavigationView.setNavigationItemSelectedListener(this);

        setTitle("HobbiT Homepage");

        SharedPreferences settings = getSharedPreferences("CurrentUser", 0);
        String currentUser = settings.getString("CurrentUser", "");
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.hob_it__main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //getSupportActionBar().

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.action_login) {
            Intent login = new Intent(this, FacebookLogin.class);
            startActivityForResult(login, 1);
            return true;
        }
        else  if (id == R.id.action_edit_profile) {
            Intent editProfileIntent = new Intent(this, EditProfileActivity.class);
            startActivity(editProfileIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void printDebugToast (CharSequence text )
    {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast save_toast = Toast.makeText(context, text, duration);
        save_toast.show();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        printDebugToast("nav item selected");

        if (id == R.id.join_group_sidebar_action) {
            // Handle the camera action


            Intent intent = new Intent(HobIT_Main.this, ListHobbyGroups.class);
            startActivity(intent);
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
