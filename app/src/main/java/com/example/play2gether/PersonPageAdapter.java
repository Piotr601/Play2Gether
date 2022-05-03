package com.example.play2gether;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import android.os.Bundle;

public class PersonPageAdapter extends FragmentPagerAdapter {

    public PersonPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        PersonFragment personFragment = new PersonFragment();
        position = position + 1;
        Bundle bundle = new Bundle();
        bundle.putString("message","Fragment :" + position);
        personFragment.setArguments(bundle);

        return personFragment;
    }

    @Override
    public int getCount() {
        return 3;
    }
}