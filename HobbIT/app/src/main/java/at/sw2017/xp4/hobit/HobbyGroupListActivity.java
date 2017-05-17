package at.sw2017.xp4.hobit;

/**
 * Created by Michaela on 10.05.2017.
 */

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.widget.ExpandableListView;
import android.widget.SearchView;

public class HobbyGroupListActivity extends Activity implements SearchView.OnQueryTextListener, SearchView.OnCloseListener {

    private SearchView search;
    private HobbyGroupListAdapter listAdapter;
    private ExpandableListView myList;
    private ArrayList<HobbyData> hobbyList = new ArrayList<HobbyData>();

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
        displayList();
        //expand all Groups
        expandAll();

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
        loadSomeData();

        //get reference to the ExpandableListView
        myList = (ExpandableListView) findViewById(R.id.expandableList);
        //create the adapter by passing your ArrayList data
        listAdapter = new HobbyGroupListAdapter(HobbyGroupListActivity.this, hobbyList );
        //attach the adapter to the list
        myList.setAdapter(listAdapter);

    }

    private void loadSomeData() {

        ArrayList<GroupData> groupList = new ArrayList<GroupData>();
        GroupData group = new GroupData("TEST",10000000);
        groupList.add(group);
        group = new GroupData("Canada",20000000);
        groupList.add(group);
        group = new GroupData("United States",50000000);
        groupList.add(group);

        HobbyData hobby = new HobbyData("North America",groupList);
        hobbyList.add(hobby);

        groupList = new ArrayList<GroupData>();
        group = new GroupData("China",10000100);
        groupList.add(group);
        group = new GroupData("Japan",20000200);
        groupList.add(group);
        group = new GroupData("Thailand",50000500);
        groupList.add(group);

        hobby = new HobbyData("Asia",groupList);
        hobbyList.add(hobby);

    }

    @Override
    public boolean onClose() {
        listAdapter.filterData("");
        expandAll();
        return false;
    }

    @Override
    public boolean onQueryTextChange(String query) {
        listAdapter.filterData(query);
        expandAll();
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        listAdapter.filterData(query);
        expandAll();
        return false;
    }

}
