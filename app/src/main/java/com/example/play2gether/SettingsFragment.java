package com.example.play2gether;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class SettingsFragment extends Fragment {


    ImageView set_a1,set_a2,set_a3,set_a4,set_a5,set_a6,set_a7;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        TextView settings_name = (TextView) view.findViewById(R.id.settings_name);
        if(MainActivity.FULLNAME != null)
            settings_name.setText(MainActivity.FULLNAME);
        else
            settings_name.setText(MainActivity.NAME);

        set_a1 = (ImageView) view.findViewById(R.id.set_a1);
        set_a2 = (ImageView) view.findViewById(R.id.set_a2);
        set_a3 = (ImageView) view.findViewById(R.id.set_a3);

        set_a1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                String Body = "Download this App\nhttp://play.google.com/play2gether";
                intent.putExtra(Intent.EXTRA_TEXT, Body);
                startActivity(Intent.createChooser(intent, "Share using"));
            }
        });
        // LANGUAGE
        set_a2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();;
                fr.replace(R.id.fragment_container, new LanguageFragment());
                fr.commit();
            }
        });
        set_a3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_donate = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/Piotr601")); ///Play2Gether/blob/master/README.md
                startActivity(intent_donate);
            }
        });

        // LINKS
        set_a4 = (ImageView) view.findViewById(R.id.set_a4);
        set_a5 = (ImageView) view.findViewById(R.id.set_a5);
        set_a6 = (ImageView) view.findViewById(R.id.set_a6);

        set_a4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMail();
            }
        });
        set_a5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_donate = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.buycoffee.to/play2gether"));
                startActivity(intent_donate);
            }
        });
        set_a6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_donate = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/Piotr601")); ///Play2Gether/blob/master/README.md
                startActivity(intent_donate);
            }
        });

        // WYLOGUJ
        set_a7 = (ImageView) view.findViewById(R.id.set_a7);
        set_a7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getActivity(), MainActivity.class);
                startActivity(in);
            }
        });


        return view;
    }

    private void sendMail() {
        String recipientList = "appplay2gether@gmail.com";
        String[] recipient = recipientList.split(",");

        String subject = "Kontakt";

        Intent intent_contact = new Intent(Intent.ACTION_SEND);
        intent_contact.putExtra(Intent.EXTRA_EMAIL, recipient);
        intent_contact.putExtra(Intent.EXTRA_SUBJECT, subject);

        intent_contact.setType("message/rfc882");
        startActivity(Intent.createChooser(intent_contact, "Choose an email client"));
    }
}