package com.example.expendedlistviewdemo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    MyExpandableListAdapter listAdapter;
    ExpandableListView expandableListView;
    List<String> listHeader;
    HashMap<String, List<String>> listChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        expandableListView = findViewById(R.id.lvExp);

        prepareListData();
        listAdapter = new MyExpandableListAdapter(this,listHeader,listChild);

        expandableListView.setAdapter(listAdapter);

        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                Toast.makeText(getApplicationContext(),"Group Clicked : " +
                        listHeader.get(groupPosition),Toast.LENGTH_SHORT).show();
                showmsg("Group Clicked :",listHeader.get(groupPosition));
                return false;
            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(getApplicationContext(),listHeader.get(groupPosition)+" : " +
                        listChild.get(listHeader.get(groupPosition)).
                                get(childPosition),Toast.LENGTH_SHORT).show();
                showmsg(" ", listChild.get(listHeader.get(groupPosition)).get(childPosition));
                return false;
            }
        });

        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(), listHeader.get(groupPosition)+" Expanded"
                       ,Toast.LENGTH_SHORT).show();
            }
        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(), listHeader.get(groupPosition)+" Collapsed"
                        ,Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void showmsg(String selected_item, String item)
    {
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(selected_item);
        builder.setMessage(item);
        builder.show();
    }

    private void prepareListData() {
        listChild = new HashMap<>();
        listHeader = new ArrayList<>();

        listHeader.add("Topics");
        listHeader.add("Topics Covered");
        listHeader.add("Topics Not Covered");
        listHeader.add("Switch to StartActivity For Result");

        List<String> top250 = new ArrayList<String>();
        top250.add("Android");
        top250.add("Android Architecture");
        top250.add("Android SDK");
        top250.add("Android UI");
        top250.add("Android Notification");
        top250.add("Android Maps");
        top250.add("Android Bluetooth");
        top250.add("Android Wi-Fi");


        List<String> nowShowing = new ArrayList<String>();
        nowShowing.add("Android");
        nowShowing.add("Android Architecture");
        nowShowing.add("Android SDK");
        nowShowing.add("Android UI");
        nowShowing.add("Android Notification");


        List<String> comingSoon = new ArrayList<String>();
        comingSoon.add("Android Maps");
        comingSoon.add("Android Blutooth");
        comingSoon.add("Android Wi-Fi");


        listChild.put(listHeader.get(0), top250);
        listChild.put(listHeader.get(1), nowShowing);
        listChild.put(listHeader.get(2), comingSoon);

    }
}
