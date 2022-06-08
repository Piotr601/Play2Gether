package com.example.play2gether;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;


public class AllActivities extends Fragment {

    ImageView buttonbackact;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_activities, container, false);

        buttonbackact = (ImageView) view.findViewById(R.id.buttonbackact);
        buttonbackact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();;
                fr.replace(R.id.fragment_container, new HomeFragment());
                fr.commit();
            }
        });

        return view;
    }
}