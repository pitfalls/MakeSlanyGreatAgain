package at.sw2017.xp4.hobit;

import android.content.ContextWrapper;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.widget.ExpandableListAdapter;

import java.security.AccessControlContext;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static java.security.AccessController.getContext;

/**
 * Created by andy on 26.04.2017.
 */

public class HobbyGroupsExpListAdapter extends BaseExpandableListAdapter {

    private Activity context;
    private Map<String, List<GroupData>> groups;
    private List<String> categories;

    private Context appContext;
    private Context packageContext;

    public HobbyGroupsExpListAdapter(Activity context, List<String> hobbyCategories,
                                        Map<String, List<GroupData>> hobbyGroups, Context appContext, Context packageContext) {
        this.context = context;
        this.groups = hobbyGroups;
        this.categories = hobbyCategories;

        this.appContext = appContext;
        this.packageContext = packageContext;
    }

    public Object getChild(int groupPosition, int childPosition) {
        return groups.get(categories.get(groupPosition)).get(childPosition);
    }

    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }


    public View getChildView(final int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final GroupData group = (GroupData) getChild(groupPosition, childPosition);
        LayoutInflater inflater = context.getLayoutInflater();

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.hobbygroup_child_item, null);
        }

        TextView item = (TextView) convertView.findViewById(R.id.hg_child_item);

        item.setText(group.getName());

        View.OnClickListener clickListen = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int duration = Toast.LENGTH_SHORT;
                String text = "test on click output Name:" + group.getName() + " ID:" + group.getId();
                Toast save_toast = Toast.makeText(appContext, text, duration);
                save_toast.show();

                Intent intent = new Intent(packageContext, GroupOverview.class);
                packageContext.startActivity(intent);
            }
        };
        item.setOnClickListener( clickListen );
        return convertView;
    }

    public int getChildrenCount(int groupPosition) {
        return groups.get(categories.get(groupPosition)).size();
    }

    public Object getGroup(int groupPosition) {
        return categories.get(groupPosition);
    }

    public int getGroupCount() {
        return categories.size();
    }

    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String category = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.hobbygroup_group_item,
                    null);
        }
        TextView item = (TextView) convertView.findViewById(R.id.hg_group_item);
        item.setTypeface(null, Typeface.BOLD);
        item.setText(category);
        return convertView;
    }

    public boolean hasStableIds() {
        return true;
    }

    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}
