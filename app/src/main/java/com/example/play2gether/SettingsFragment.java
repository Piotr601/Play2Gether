package com.example.play2gether;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SettingsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        TextView settings_name = (TextView) view.findViewById(R.id.settings_name);

        if(MainActivity.FULLNAME != null)
            settings_name.setText(MainActivity.FULLNAME);
        else
            settings_name.setText(MainActivity.NAME);

        return view;
    }
}