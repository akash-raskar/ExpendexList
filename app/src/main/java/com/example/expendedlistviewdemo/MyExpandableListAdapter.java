package com.example.expendedlistviewdemo;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

class MyExpandableListAdapter extends BaseExpandableListAdapter {

    Context context;
    List<String> listH;
    HashMap<String, List<String>> listC;

    public MyExpandableListAdapter(Context context, List<String> listH, HashMap<String, List<String>> listC) {
        this.context = context;
        this.listH = listH;
        this.listC = listC;
    }

    @Override
    public int getGroupCount() {
        return this.listH.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.listC.get(this.listH.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.listH.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {

        return this.listC.get(this.listH.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        String headerfile = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(MainActivity.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_group, null);
        }

        TextView lb1ListHearder = convertView.findViewById(R.id.llblListHeader);
        lb1ListHearder.setTypeface(null, Typeface.BOLD);
        lb1ListHearder.setText(headerfile);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        final String cheaderfile = (String) getChild(groupPosition,childPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(MainActivity.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_group_c, null);
        }

        TextView lb1ListHearder = convertView.findViewById(R.id.llblListHeaderc);
        lb1ListHearder.setTypeface(null, Typeface.BOLD);
        lb1ListHearder.setText(cheaderfile);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
