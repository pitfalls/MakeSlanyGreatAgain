package at.sw2017.xp4.hobit;

/**
 * Created by Michaela on 10.05.2017.
 */

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import android.content.Context;
import android.content.Intent;
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

 //   private Context packageContext;

    public MyListAdapter(Context context,
                         ArrayList<HobbyData> hobbyList )
    //                     Context packageContext)
    {
        this.context = context;
        this.hobbyList = new ArrayList<HobbyData>();
        this.hobbyList.addAll(hobbyList);
        this.originalList = new ArrayList<HobbyData>();
        this.originalList.addAll(hobbyList);

 //       this.packageContext = packageContext;
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

        final GroupData group = (GroupData) getChild(groupPosition, childPosition);
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.child_row, null);
        }

        TextView item = (TextView) view.findViewById(R.id.name);
        item.setText(group.getName().trim());

        View.OnClickListener clickListen = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*int duration = Toast.LENGTH_SHORT;
                String text = "test on click output Name:" + group.getName() + " ID:" + group.getId();
                Toast save_toast = Toast.makeText(appContext, text, duration);
                save_toast.show();*/

                Intent intent = new Intent(context, GroupOverview.class);
                // pass group data details to next activity
                de.greenrobot.event.EventBus.getDefault().postSticky(group);

                //context.startActivityForResult(intent,0);

                context.startActivity(intent);
            }
        };
        item.setOnClickListener( clickListen );

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
