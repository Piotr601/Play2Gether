package com.example.play2gether;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class HomeFragment extends Fragment {

    LinearLayout l1, l2, l3;
    ImageView avatar;

    ImageView bot1, bot2, bot3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // cardview slide
        l1 = (LinearLayout) view.findViewById(R.id.l1);
        l2 = (LinearLayout) view.findViewById(R.id.l2);
        l3 = (LinearLayout) view.findViewById(R.id.l3);

        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getActivity(), testActivity.class);
                in.putExtra("some","some data");
                startActivity(in);
            }
        });


        // clicking on avatar
        avatar = (ImageView) view.findViewById(R.id.home_avatar);
        avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // loadFragment(view);
            }
        });


        // BOTTOM SECTION
        bot1 = (ImageView) view.findViewById(R.id.wesprzyj);
        bot2 = (ImageView) view.findViewById(R.id.kontakt);
        bot3 = (ImageView) view.findViewById(R.id.regulamin);

        bot1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getActivity(), testActivity.class);
                in.putExtra("some","some data");
                startActivity(in);
            }
        });

        bot2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getActivity(), testActivity.class);
                in.putExtra("some","some data");
                startActivity(in);
            }
        });

        bot3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getActivity(), testActivity.class);
                in.putExtra("some","some data");
                startActivity(in);
            }
        });

        return view;
    }


    // OTHER FUNCTIONS
    private void loadFragment(View view) {
        if (view != null) {
            FragmentTransaction fragmentTransaction = getActivity()
                    .getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, new SettingsFragment());
            fragmentTransaction.commit();
        } else {
            Toast.makeText(getActivity(), "Avatar Click Error", Toast.LENGTH_SHORT).show();
        }
    }
}