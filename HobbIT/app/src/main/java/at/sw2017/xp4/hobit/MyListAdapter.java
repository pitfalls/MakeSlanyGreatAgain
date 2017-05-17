package at.sw2017.xp4.hobit;

/**
 * Created by Michaela on 10.05.2017.
 */

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class MyListAdapter  extends BaseExpandableListAdapter {

    private Context context;
    private ArrayList<HobbyData> hobbyList;
    private ArrayList<HobbyData> originalList;

    public MyListAdapter(Context context, ArrayList<HobbyData> hobbyList) {
        this.context = context;
        this.hobbyList = new ArrayList<HobbyData>();
        this.hobbyList.addAll(hobbyList);
        this.originalList = new ArrayList<HobbyData>();
        this.originalList.addAll(hobbyList);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        ArrayList<GroupData> groupList = hobbyList.get(groupPosition).getGroupList();
        return groupList.get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
                             View view, ViewGroup parent) {

        GroupData group = (GroupData) getChild(groupPosition, childPosition);
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.child_row, null);
        }

        TextView name = (TextView) view.findViewById(R.id.name);
        name.setText(group.getName().trim());

        return view;
    }

    @Override
    public int getChildrenCount(int groupPosition) {

        ArrayList<GroupData> groupList = hobbyList.get(groupPosition).getGroupList();
        return groupList.size();

    }

    @Override
    public Object getGroup(int groupPosition) {
        return hobbyList.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return hobbyList.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isLastChild, View view,
                             ViewGroup parent) {

        HobbyData hobby = (HobbyData) getGroup(groupPosition);
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.group_row, null);
        }

        TextView heading = (TextView) view.findViewById(R.id.heading);
        heading.setText(hobby.getName().trim());

        return view;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public void filterData(String query){

        query = query.toLowerCase();
        Log.v("MyListAdapter", String.valueOf(hobbyList.size()));
        hobbyList.clear();

        if(query.isEmpty()){
            hobbyList.addAll(originalList);
        }
        else {

            for(HobbyData hobby: originalList){

                ArrayList<GroupData> groupList = hobby.getGroupList();
                ArrayList<GroupData> newList = new ArrayList<GroupData>();
                for(GroupData group: groupList){
                    if(group.getName().toLowerCase().contains(query) ||
                            group.getName().toLowerCase().contains(query)){
                        newList.add(group);
                    }
                }
                if(newList.size() > 0){
                    HobbyData nHobby = new HobbyData(hobby.getName(),newList);
            hobbyList.add(nHobby);
        }
    }
        }

        Log.v("MyListAdapter", String.valueOf(hobbyList.size()));
        notifyDataSetChanged();

    }


}
