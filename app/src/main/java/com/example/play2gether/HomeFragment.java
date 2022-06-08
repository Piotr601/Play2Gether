package com.example.play2gether;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
import android.widget.TextView;
import android.widget.Toast;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HomeFragment extends Fragment {

    LinearLayout l1,l2,l3,d1,d2,d3,d4,d5;
    ImageView l1_join,l2_join,l3_join;

    ImageView avatar, add_home;
    ImageView all_activities;
    ImageView bot1, bot2, bot3;

    TextView home_imie_id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        home_imie_id = (TextView) view.findViewById(R.id.home_imie_id);
        home_imie_id.setText(MainActivity.NAME);

        // TOP SECTION
        add_home = (ImageView) view.findViewById(R.id.add_home);
        add_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();;
                fr.replace(R.id.fragment_container, new CreatingFragment());
                fr.commit();
            }
        });

        // cardview slide
        l1 = (LinearLayout) view.findViewById(R.id.l1);
        l2 = (LinearLayout) view.findViewById(R.id.l2);
        l3 = (LinearLayout) view.findViewById(R.id.l3);
        l1_join = (ImageView) view.findViewById(R.id.l1_join);
        l2_join = (ImageView) view.findViewById(R.id.l2_join);
        l3_join = (ImageView) view.findViewById(R.id.l3_join);

        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "L1", Toast.LENGTH_SHORT).show();
            }
        });
        l2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "L2", Toast.LENGTH_SHORT).show();
            }
        });
        l3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "L3", Toast.LENGTH_SHORT).show();

            }
        });
        l1_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "L1_join", Toast.LENGTH_SHORT).show();
            }
        });
        l2_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "L2_join", Toast.LENGTH_SHORT).show();
            }
        });
        l3_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "L3_join", Toast.LENGTH_SHORT).show();
            }
        });

        d1 = (LinearLayout) view.findViewById(R.id.d1);
        d2 = (LinearLayout) view.findViewById(R.id.d2);
        d3 = (LinearLayout) view.findViewById(R.id.d3);
        d4 = (LinearLayout) view.findViewById(R.id.d4);
        d5 = (LinearLayout) view.findViewById(R.id.d5);

        d1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "D1", Toast.LENGTH_SHORT).show();
            }
        });
        d2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "D2", Toast.LENGTH_SHORT).show();
            }
        });
        d3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "D3", Toast.LENGTH_SHORT).show();
            }
        });
        d4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "D4", Toast.LENGTH_SHORT).show();
            }
        });
        d5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "D5", Toast.LENGTH_SHORT).show();
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

        all_activities = (ImageView) view.findViewById(R.id.all_activities);
        all_activities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();;
                fr.replace(R.id.fragment_container, new AllActivities());
                fr.commit();
            }
        });

        // BOTTOM SECTION
        bot1 = (ImageView) view.findViewById(R.id.wesprzyj);
        bot2 = (ImageView) view.findViewById(R.id.kontakt);
        bot3 = (ImageView) view.findViewById(R.id.regulamin);

        // DONATE
        bot1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_donate = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.buycoffee.to/play2gether"));
                startActivity(intent_donate);
            }
        });

        // CONTACT
        bot2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMail();
            }
        });

        // POLICY
        bot3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_policy = new Intent(Intent.ACTION_VIEW, Uri.parse("https://drive.google.com/file/d/13mBNJzbyTEa6BXeZHZLf6Zla0f0qhyex/view?usp=sharing"));
                startActivity(intent_policy);
            }
        });

        return view;
    }


    // SEND EMAIL FUNCTION
    private void sendMail() {
        Date date = new Date();
        SimpleDateFormat formatterdate = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat formattertime = new SimpleDateFormat("HH:mm:ss");

        String recipientList = "appplay2gether@gmail.com";
        String[] recipient = recipientList.split(",");

        String subject = "Bug report / Problem";
        String message = "Date: " + formatterdate.format(date) + "\nTime: "+ formattertime.format(date) + "\n\nThis is a bug report, please specify what is not working.\n\n";

        Intent intent_contact = new Intent(Intent.ACTION_SEND);
        intent_contact.putExtra(Intent.EXTRA_EMAIL, recipient);
        intent_contact.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent_contact.putExtra(Intent.EXTRA_TEXT, message);

        intent_contact.setType("message/rfc882");
        startActivity(Intent.createChooser(intent_contact, "Choose an email client"));
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