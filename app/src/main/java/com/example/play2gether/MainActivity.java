package com.example.play2gether;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.material.chip.Chip;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements ChipNavigationBar.OnItemSelectedListener {

    ChipNavigationBar navBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        loadFragment(new StartFragment());

        navBar=findViewById(R.id.bottom_nav_bar);
        navBar.setOnItemSelectedListener(this);
    }

    private void loadFragment(Fragment fragment) {

        if(fragment != null){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container,fragment)
                    .commit();
        }
        else
        {
            Toast.makeText(this, "Fragment error", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.nav_menu,menu);
        return true;
    }

    @Override
    public void onItemSelected(int i) {

        Fragment fragment = null;

        switch(i){
            case R.id.nav_home:
                fragment = new HomeFragment();
                break;
            case R.id.nav_person:
                fragment = new MapFragment();
                break;
            case R.id.nav_home1:
                // fragment= new Fragment();
                break;
            case R.id.nav_settings:
                fragment= new SettingsFragment();
                break;
        }

        loadFragment(fragment);
    }
}