package com.example.play2gether;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class LanguageFragment extends Fragment {


    LinearLayout language_polish;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_language, container, false);

        // LANGUAGE
        language_polish = (LinearLayout) view.findViewById(R.id.language_polish);
        language_polish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Polski", Toast.LENGTH_SHORT).show();
                FragmentTransaction fr = getFragmentManager().beginTransaction();;
                fr.replace(R.id.fragment_container, new SettingsFragment());
                fr.commit();
            }
        });

        return view;
    }
}