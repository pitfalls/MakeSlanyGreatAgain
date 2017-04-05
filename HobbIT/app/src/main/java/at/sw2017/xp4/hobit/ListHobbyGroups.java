package at.sw2017.xp4.hobit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ListHobbyGroups extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_hobby_groups);
        //setTitle("Individual");

        ExpandableListView lv = (ExpandableListView) findViewById(R.id.expHobbyList);

        //ExpandableListAdapter data = lv.getExpandableListAdapter();

        ArrayList<String>listDataHeader = new ArrayList<String>();
        HashMap<String, List<String>> listDataChild = new HashMap<String, List<String>>();

        listDataHeader.add("testGroup");
        List<String> child = new ArrayList<String>();
        child.add("testChild");
        listDataChild.put(listDataHeader.get(0),child);

        ExpandableListAdapter listAdapter =
                new HobbyGroupsExpListAdapter(
                        this, listDataHeader, listDataChild);

    }
}
