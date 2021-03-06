package at.sw2017.xp4.hobit;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
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

import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import at.sw2017.xp4.hobit.requests.GetUserGroupsRequest;

//TODO: Change login to logout if logged in

public class HobIT_Main extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private NavigationView sideBarNavigationView;

    public ArrayList<String> User_Hobbys;

    public void toArrayList(JSONArray array) {
        if (array == null)
            return;

        if (User_Hobbys.size() == 0) {
            for (int i = 0; i < array.length(); i++) {
                User_Hobbys.add(array.optString(i));
            }
        }
    }

    public void initListViewGroups() {
        final Response.Listener<String> GroupResponseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    final JSONArray allHobbies = jsonResponse.getJSONArray("Names");
                    toArrayList(allHobbies);
                    Globals.getInstance().setGlobal_array(User_Hobbys);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        final Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                AlertDialog.Builder builder = new AlertDialog.Builder(HobIT_Main.this);
                builder.setMessage("you are not good enough to join!! ;-)")
                        .setNegativeButton("Retry", null)
                        .create()
                        .show();
            }
        };


        GetUserGroupsRequest getUserGroupsRequest = new GetUserGroupsRequest(GroupResponseListener, errorListener);
        final RequestQueue queue = Volley.newRequestQueue(HobIT_Main.this);
        queue.add(getUserGroupsRequest);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, User_Hobbys);

        final ListView ListViews = (ListView) findViewById(R.id.ListViewGroups);
        ListViews.setAdapter(adapter);

        ListViews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                String itemValue = (String) ListViews.getItemAtPosition(position);

                Bundle bundle = new Bundle();
                bundle.putString("Group", itemValue);
                Intent newIntent = new Intent(HobIT_Main.this, GroupOverview.class);
                newIntent.putExtras(bundle);
                startActivity(newIntent);
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        User_Hobbys = Globals.getInstance().getGlobal_array();
        setContentView(R.layout.activity_hob_it__main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /**--------------------------INIT BUTTON------------------**/
        initListViewGroups();
        /**--------------------END INIT BUTTON--------------------**/

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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_login) {
            Intent login = new Intent(this, FacebookLogin.class);
            startActivityForResult(login, 1);
            finish();
            return true;
        } else if (id == R.id.action_edit_profile) {
            Intent editProfileIntent = new Intent(this, EditProfileActivity.class);
            startActivity(editProfileIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.join_group_sidebar_action) {
            // Handle the camera action

            Intent intent = new Intent(HobIT_Main.this, HobbyGroupListActivity.class);
            startActivity(intent);
        } else if (id == R.id.group_overview) {
            Intent intent = new Intent(HobIT_Main.this, GroupOverview.class);
            startActivity(intent);

        } else if (id == R.id.group_creation) {
            Intent intent = new Intent(HobIT_Main.this, GroupCreation.class);
            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
