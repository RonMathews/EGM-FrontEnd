package com.example.jeevan.splash;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by ksameersrk on 5/3/16.
 */
public class ListOfPlaces extends Activity
{
    /*
    There is bug if the list is greater then the height of the view
     */
    String[] places = {
            "Bekal Fort",
            "Bengaluru Palace",
            "Brindavan Gardens",
            "Ducati Bengaluru",
            "Eco Tourism Park",
            "Jawaharlal Nehru Planetarium",
            "Panambur Beach",
            "Bekal Fort",
            "Bengaluru Palace",
            "Brindavan Gardens",
            "Ducati Bengaluru",
            "Eco Tourism Park",
            "Jawaharlal Nehru Planetarium",
            "Panambur Beach",
            "Bekal Fort",
            "Bengaluru Palace",
            "Brindavan Gardens",
            "Ducati Bengaluru",
            "Eco Tourism Park",
            "Jawaharlal Nehru Planetarium",
            "Panambur Beach",
            "Tipu Sultan's Fort"
    };
    private ListView mainListView;
    private ArrayAdapter<String> listAdapter;
    private String my_sel_items;
    private ArrayList<String> selected_places;
    private  ArrayList<Integer> selectedIds;
    Integer topRow = new Integer(0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_of_places);

        //set header
        final TextView t = (TextView) findViewById(R.id.headerText);
        Bundle bundle = getIntent().getExtras();
        String tmp = bundle.getString("header");
        t.setText(tmp);

        //initialize
        my_sel_items = "";

        // Find the ListView resource.
        mainListView = (ListView) findViewById(R.id.mainListView);
        //create adapter
        listAdapter = new ArrayAdapter<String>(this,
                R.layout.simple_row,
                R.id.rowTextView,
                places
        );
        //set Adapter
        mainListView.setAdapter(listAdapter);
        mainListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        //set the listener for onclick
        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                view.setSelected(true);
                my_sel_items = "";
                SparseBooleanArray a = mainListView.getCheckedItemPositions();
                selected_places = new ArrayList<String>();
                selectedIds = new ArrayList<Integer>();
                for (int j = 0; j < a.size(); j++) {
                    Log.v("value size : ", a.size() + "");
                    if (a.valueAt(j)) {
                        my_sel_items = (String) mainListView.getAdapter().getItem(a.keyAt(j));
                        selected_places.add(my_sel_items);
                        selectedIds.add(new Integer(a.keyAt(j)));
                    }
                    else
                    {
                        int tmp = a.keyAt(j);
                        if(selectedIds.contains(tmp))
                        {
                            selectedIds.remove(new Integer(tmp));
                        }
                    }
                }
                topRow = new Integer(mainListView.getFirstVisiblePosition());
                Log.v("First : ", mainListView.getFirstVisiblePosition()+"");
                Log.v("Last  : ", mainListView.getLastVisiblePosition()+"");
                Log.v("list", selected_places.toString());
                Log.v("list", selectedIds.toString());
                for(int k = 0; k < adapterView.getChildCount(); k++)
                {

                    adapterView.getChildAt(k).setBackgroundColor(Color.TRANSPARENT);
                }
                for(int k = 0; k < a.size(); k++)
                {
                    Log.v("a.value ", a.keyAt(k)+"");
                    if(a.valueAt(k) && adapterView.getChildAt(a.keyAt(k)) != null)
                    {
                        adapterView.getChildAt(a.keyAt(k)).setBackgroundColor(Color.GREEN);
                        Log.v("if : value ", a.keyAt(k) + "");
                    }
                    else if(adapterView.getChildAt(a.keyAt(k)) != null)
                    {
                        adapterView.getChildAt(a.keyAt(k)).setBackgroundColor(Color.TRANSPARENT);
                        Log.v("else : value ", a.keyAt(k) + "");
                    }
                }
                //view.setBackgroundColor(Color.GREEN);
            }
        });
        }
    }
