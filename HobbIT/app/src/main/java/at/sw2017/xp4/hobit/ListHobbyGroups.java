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
    private HashMap<String, List<String>> categoryMapGroups;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_hobby_groups);

        categoryList = new ArrayList<String>();
        categoryMapGroups = new HashMap<String, List<String>>();

        setDefaultData();

        setCurrentExpandableListAdapter();
    }

    void setDefaultData()
    {
        String firstCat = "Category_test";
        addGroup(firstCat, "Group_X");
        addGroup(firstCat, "Group_Y");
        addGroup(firstCat, "Group_Z");

        String catBoobs = "Boobs";
        addGroup(catBoobs, "Pamela Anderson");
        addGroup(catBoobs, "Megan Fox");
    }

    void setCurrentExpandableListAdapter()
    {
        View.OnClickListener clickListen = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int duration = Toast.LENGTH_SHORT;
                String text = "test on click output ";
                Toast save_toast = Toast.makeText(getApplicationContext(), text, duration);
                save_toast.show();

                Intent intent = new Intent(ListHobbyGroups.this, GroupOverview.class);
                startActivity(intent);
            }
        };

        ExpandableListAdapter listAdapter =
                //    new HobbyGroupsExpListAdapter(
                new HobbyGroupsExpListAdapter(
                        this, categoryList, categoryMapGroups, clickListen); //getApplicationContext());
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
            List<String> categoryGroupList = new ArrayList<String>();
            categoryMapGroups.put(category, categoryGroupList);
        }
    }

    /**
     * @param category
     * @return
     */
    List<String> getGroupList(String category)
    {
        List<String> groupList = null;
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
    void addGroup(String category, String group)
    {
        if ( !categoryExists(category) ) {
            addCategory(category);
        }

        List<String> groupList = categoryMapGroups.get(category);
        groupList.add(group);
    }
}
