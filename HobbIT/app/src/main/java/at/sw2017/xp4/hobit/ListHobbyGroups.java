package at.sw2017.xp4.hobit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ListHobbyGroups extends AppCompatActivity {

    private ArrayList<String> listDataHeader;
    private HashMap<String, List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_hobby_groups);

        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

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
        ExpandableListAdapter listAdapter =
                //    new HobbyGroupsExpListAdapter(
                new HobbyGroupsExpListAdapter(
                        this, listDataHeader, listDataChild);

        ExpandableListView lv = (ExpandableListView) findViewById(R.id.expandableListViewHobbyGroups);
        lv.setAdapter(listAdapter);
       // return listAdapter;
    }

    boolean categoryExists (String category)
    {
        boolean retVal = false;
        int categoryIndex = listDataHeader.indexOf(category);
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
            listDataHeader.add(category);
            List<String> categoryGroupList = new ArrayList<String>();
            listDataChild.put(category, categoryGroupList);
        }
    }

    /**
     * @param category
     * @return
     */
    List<String> getGroupList(String category)
    {
        List<String> groupList = null;
        groupList = listDataChild.get(category);
        return groupList;
    }

    /**
     *
     * @return
     */
    List<String> getCategoryList()
    {
        return listDataHeader;
    }


    /** adds a new group to the category group datastrucutre
     * @param category
     */
    void addGroup(String category, String group)
    {
        if ( !categoryExists(category) ) {
            addCategory(category);
        }

        List<String> groupList = listDataChild.get(category);
        groupList.add(group);
    }
}
