package at.sw2017.xp4.hobit;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by andy on 05.04.2017.
 */

public class HobbyGroupsExpListAdapter implements ExpandableListAdapter {
    ArrayList<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;


    public HobbyGroupsExpListAdapter(Context context, ArrayList<String> headerList, HashMap<String, List<String>> childList)
    {
        listDataChild = childList;
        listDataHeader = headerList;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getGroupCount() {
        return listDataHeader.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        Object obj = listDataHeader.get(groupPosition);
        return listDataChild.get(obj).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        Object obj = listDataHeader.get(groupPosition);
        return listDataChild.get(obj);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        Object obj = listDataHeader.get(groupPosition);
        return listDataChild.get(obj).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void onGroupExpanded(int groupPosition) {

    }

    @Override
    public void onGroupCollapsed(int groupPosition) {

    }

    @Override
    public long getCombinedChildId(long groupId, long childId) {
        return 0;
    }

    @Override
    public long getCombinedGroupId(long groupId) {
        return 0;
    }
}


