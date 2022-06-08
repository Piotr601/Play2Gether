package com.example.play2gether;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;


public class CreatingFragment extends Fragment {

    EditText editTextDate,editTextDate2,editTextTextPersonName5,editTextTextPersonName2,
             editTextTextPersonName3,editTextTextPersonName4,editTextTextPersonName8;
    EditText editTextNumber;
    Button buttonact;
    ImageView buttonbackact3;

    public String nowa_data, nowa_godzina, nowa_nazwa, nowa_kategoria, nowa_obiekt, nowa_adres, nowa_zal;
    public Integer nowa_ilosc_os;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_creating, container, false);

        Date date = new Date();
        SimpleDateFormat formatterdate = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat formattertime = new SimpleDateFormat("HH:mm");
        String date_msg = formatterdate.format(date);
        String time_msg = formattertime.format(date);

        editTextDate = (EditText) view.findViewById(R.id.editTextDate);
        editTextDate2 = (EditText) view.findViewById(R.id.editTextDate2);
        editTextNumber = (EditText) view.findViewById(R.id.editTextNumber);
        editTextTextPersonName2 = (EditText) view.findViewById(R.id.editTextTextPersonName2);
        editTextTextPersonName3 = (EditText) view.findViewById(R.id.editTextTextPersonName3);
        editTextTextPersonName4 = (EditText) view.findViewById(R.id.editTextTextPersonName4);
        editTextTextPersonName5 = (EditText) view.findViewById(R.id.editTextTextPersonName5);
        editTextTextPersonName8 = (EditText) view.findViewById(R.id.editTextTextPersonName8);

        if(MainActivity.FULLNAME != null)
            editTextTextPersonName5.setText(MainActivity.FULLNAME);
        else
            editTextTextPersonName5.setText(MainActivity.NAME);
        editTextDate.setText(date_msg);
        editTextDate2.setText(time_msg);

        buttonbackact3 = (ImageView) view.findViewById(R.id.buttonbackact3);
        buttonbackact3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();;
                fr.replace(R.id.fragment_container, new HomeFragment());
                fr.commit();
            }
        });

        buttonact = (Button) view.findViewById(R.id.buttonact);
        buttonact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checking()){
                    data();
                    FragmentTransaction fr = getFragmentManager().beginTransaction();;
                    fr.replace(R.id.fragment_container, new HomeFragment());
                    fr.commit();
                    Toast.makeText(getActivity(), "Pomyślnie utworzono aktywność", Toast.LENGTH_SHORT).show();
                } else
                {
                    Toast.makeText(getActivity(), "Uzupelnij wszystkie pola", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }

    void data(){
        nowa_data       = editTextDate.getText().toString();
        nowa_godzina    = editTextDate2.getText().toString();
        nowa_nazwa      = editTextTextPersonName2.getText().toString();
        nowa_kategoria  = editTextTextPersonName3.getText().toString();
        nowa_obiekt     = editTextTextPersonName4.getText().toString();
        nowa_adres      = editTextTextPersonName8.getText().toString();
        nowa_zal        = editTextTextPersonName5.getText().toString();
        nowa_ilosc_os   = Integer.valueOf(editTextNumber.getText().toString());
    }

    boolean checking(){
        if(editTextDate.getText().toString().equals("") ||
                editTextDate2.getText().toString().equals("") ||
                editTextNumber.getText().toString().equals("") ||
                editTextTextPersonName2.getText().toString().equals("") ||
                editTextTextPersonName3.getText().toString().equals("") ||
                editTextTextPersonName4.getText().toString().equals("") ||
                editTextTextPersonName5.getText().toString().equals("") ||
                editTextTextPersonName8.getText().toString().equals(""))
            return false;
        else
            return true;
    }
}