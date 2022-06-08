package com.example.play2gether;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toolbar;

import com.google.android.gms.location.LocationServices;

import java.util.ArrayList;
import java.util.Collections;

public class PersonFragment extends Fragment {

    ListView listView;
    GestureDetector gestureDetector;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.calendar_view, container, false);

        listView=(ListView) view.findViewById(R.id.calendar_list_view);

        ArrayList<String>arrayList = new ArrayList<>();
        arrayList.add("fdsadas");
        arrayList.add("fdsaasdsa");

//        REST.getUserEvents();

        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, REST.getUserEvents());
        listView.setAdapter(arrayAdapter);

//        Log.d("REST val",REST.getUserEventString);

        return view;
    }
}