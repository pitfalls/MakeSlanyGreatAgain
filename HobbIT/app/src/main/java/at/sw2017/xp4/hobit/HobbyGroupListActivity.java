package at.sw2017.xp4.hobit;

/**
 * Created by Michaela on 10.05.2017.
 */

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.widget.ExpandableListView;
import android.widget.SearchView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import at.sw2017.xp4.hobit.requests.GetHobbiesDestinationRequest;

public class HobbyGroupListActivity extends Activity implements SearchView.OnQueryTextListener, SearchView.OnCloseListener {

    private SearchView search;
    private HobbyGroupListAdapter listAdapter;
    private ExpandableListView myList;
    private ArrayList<HobbyData> hobbyList = new ArrayList<HobbyData>();

    List<String> allUserHobbies = new ArrayList<String>();
    String[][] groupDataList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        search = (SearchView) findViewById(R.id.search);
        search.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        search.setIconifiedByDefault(false);
        search.setOnQueryTextListener(this);
        search.setOnCloseListener(this);

        //display the list
        //displayList();
        //expand all Groups
        //expandAll();
        setupDatabaseConnection();
    }


    public void setupDatabaseConnection()
    {
        Response.Listener<String> listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");

                    if (success) {

                        final JSONArray allHobbies = jsonResponse.getJSONArray("hobby");
                        final JSONArray allNames = jsonResponse.getJSONArray("group");
                        final JSONArray allIds = jsonResponse.getJSONArray("id");



                        groupDataList = new String[3][allIds.length()];

                        for (int i = 0; i < groupDataList[0].length; i++)
                        {

                            String currentHobby = allHobbies.getString(i);
                            if(!allUserHobbies.contains(currentHobby))
                            {
                                allUserHobbies.add(currentHobby);
                            }

                            groupDataList[0][i] = allIds.getString(i);
                            groupDataList[1][i] = allNames.getString(i);
                            groupDataList[2][i] = currentHobby;


                        }

                        //display the list
                        displayList();
                        //expand all Groups
                        expandAll();

                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(HobbyGroupListActivity.this);
                        builder.setMessage("Something went wrong!")
                                .setNegativeButton("OK", null)
                                .create()
                                .show();
                    }



                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                AlertDialog.Builder builder = new AlertDialog.Builder(HobbyGroupListActivity.this);
                builder.setMessage("Connection failed HGLA")
                        .setNegativeButton("Retry", null)
                        .create()
                        .show();
            }
        };

        GetHobbiesDestinationRequest getUserGroupsRequest = new GetHobbiesDestinationRequest(listener, errorListener);
        final RequestQueue queue = Volley.newRequestQueue(HobbyGroupListActivity.this);
        queue.add(getUserGroupsRequest);

    }

    //@Override
   /*public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }*/

    //method to expand all groups
    private void expandAll() {
        int count = listAdapter.getGroupCount();
        for (int i = 0; i < count; i++){
            myList.expandGroup(i);
        }
    }

    //method to expand all groups
    private void displayList() {

        //display the list
        loadDataFromDatabase();

        //get reference to the ExpandableListView
        myList = (ExpandableListView) findViewById(R.id.expandableList);
        //create the adapter by passing your ArrayList data
        listAdapter = new HobbyGroupListAdapter(HobbyGroupListActivity.this, hobbyList );
        //attach the adapter to the list
        myList.setAdapter(listAdapter);

    }

    private void loadDataFromDatabase()
    {
        ArrayList<GroupData> groupList;
        GroupData group;
        HobbyData hobby;

        for(String currentHobby : allUserHobbies){
            groupList = new ArrayList<>();

            for(int iter = 0; iter < groupDataList[0].length; iter++){

                if(groupDataList[2][iter].equals(currentHobby)){

                    group = new GroupData(groupDataList[1][iter],Integer.parseInt( groupDataList[0][iter]));
                    groupList.add(group);
                }
            }
            hobby = new HobbyData(currentHobby, groupList);
            hobbyList.add(hobby);

        }
    }

    @Override
    public boolean onClose() {
       /* listAdapter.filterData("");
        expandAll();*/
        return false;
    }

    @Override
    public boolean onQueryTextChange(String query) {
       if(query != null && !query.isEmpty() && listAdapter != null)
       {
           listAdapter.filterData(query);
           expandAll();
       }

        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        /*if(query != null && !query.isEmpty() && listAdapter != null)
        {
            listAdapter.filterData(query);
            expandAll();
        }*/
        return false;
    }

}
