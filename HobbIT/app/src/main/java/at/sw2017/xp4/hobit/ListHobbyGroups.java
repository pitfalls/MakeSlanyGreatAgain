package at.sw2017.xp4.hobit;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.support.v4.content.ContextCompat.startActivity;

public class ListHobbyGroups extends AppCompatActivity {

    private ArrayList<String> categoryList;
    private HashMap<String, List<GroupData>> categoryMapGroups;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_hobby_groups);

        categoryList = new ArrayList<String>();
        categoryMapGroups = new HashMap<String, List<GroupData>>();

        setDefaultData();

        setCurrentExpandableListAdapter();
    }

    void setDefaultData()
    {
        String firstCat = "Category_test";
        GroupData group;

        group = new GroupData("Group_X", 1);
        addGroup(firstCat, group);
        group = new GroupData("Group_Y", 2);
        addGroup(firstCat, group);
        group = new GroupData("Group_Z", 3);
        addGroup(firstCat, group);

        String catBoobs = "Boobs";
        group = new GroupData("Pamela Anderson", 4);
        addGroup(catBoobs, group);
        group = new GroupData("Megan Fox", 5);
        addGroup(catBoobs, group);
    }

    void setCurrentExpandableListAdapter()
    {
        ExpandableListAdapter listAdapter =
                //    new HobbyGroupsExpListAdapter(
                new HobbyGroupsExpListAdapter(
                        this, categoryList, categoryMapGroups, getApplicationContext(), ListHobbyGroups.this);
/*
        for ( int categoryIdx = 0; categoryIdx < categoryList.size(); categoryIdx++ )
        {
            List<String> group = categoryMapGroups.get(categoryList.get(categoryIdx));

            for ( int groupIdx = 0; groupIdx < group.size(); groupIdx++ ) {
                boolean lastChild = false;
                if ( (group.size() - 1) == groupIdx )
                    lastChild = true;

                listAdapter.getChildView(categoryIdx, groupIdx, lastChild);
            }
        }
        */

        ExpandableListView lv = (ExpandableListView) findViewById(R.id.expandableListViewHobbyGroups);
        lv.setAdapter(listAdapter);
       // return listAdapter;
    }

    boolean categoryExists (String category)
    {
        boolean retVal = false;
        int categoryIndex = categoryList.indexOf(category);
        if ( categoryIndex != -1 ) {
            retVal = true;
        }
        return retVal;
    }
    /** adds a new category to the datastrucutre
     * @param category
     */
    void addCategory(String category)
    {
        if ( !categoryExists(category)) {
            categoryList.add(category);
            List<GroupData> categoryGroupList = new ArrayList<GroupData>();
            categoryMapGroups.put(category, categoryGroupList);
        }
    }

    /**
     * @param category
     * @return
     */
    List<GroupData> getGroupList(String category)
    {
        List<GroupData> groupList = null;
        groupList = categoryMapGroups.get(category);
        return groupList;
    }

    /**
     *
     * @return
     */
    List<String> getCategoryList()
    {
        return categoryList;
    }


    /** adds a new group to the category group datastrucutre
     * @param category
     */
    void addGroup(String category, GroupData group)
    {
        if ( !categoryExists(category) ) {
            addCategory(category);
        }

        List<GroupData> groupList = categoryMapGroups.get(category);
        groupList.add(group);
    }
}
