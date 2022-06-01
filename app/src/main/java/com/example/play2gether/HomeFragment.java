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
import android.widget.Toast;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HomeFragment extends Fragment {

    LinearLayout l1, l2, l3;
    ImageView avatar, add_home;

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