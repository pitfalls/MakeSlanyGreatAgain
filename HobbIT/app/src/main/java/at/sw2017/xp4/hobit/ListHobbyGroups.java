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

        ExpandableListView lv = (ExpandableListView) findViewById(R.id.expandableListViewHobbyGroups);

        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        addCategory("Category_test");
        addCategory("Boobs");
        List<String> child = new ArrayList<String>();
        child.add("Group_X");
        child.add("Group_Y");
        child.add("Group_Z");
        listDataChild.put(listDataHeader.get(0),child);
        List<String> boobs = new ArrayList<String>();
        boobs.add("Pamela Anderson");
        boobs.add("Megan Fox");
        listDataChild.put(listDataHeader.get(1),boobs);

        ExpandableListAdapter listAdapter =
            //    new HobbyGroupsExpListAdapter(
                new HobbyGroupsExpListAdapter(
                        this, listDataHeader, listDataChild);

        lv.setAdapter(listAdapter);
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
        if ( categoryExists(category)) {
            listDataHeader.add(category);
            List<String> categoryGroupList = new ArrayList<String>();
            listDataChild.put(category, categoryGroupList);
        }
    }

    List<String> getGroupList(String category)
    {
        List<String> groupList = null;
        groupList = listDataChild.get(category);
        return groupList;
    }


    /** adds a new group to the category group datastrucutre
     * @param category
     */
    void addGroup(String category, String group)
    {
        if ( categoryExists(category) ) {
            listDataHeader.add(category);
        }
    }
}
