package com.example.alasmaulhusna;

import static com.example.alasmaulhusna.util.UtilFragment.changeFragment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.alasmaulhusna.databinding.ActivityMainBinding;
import com.example.alasmaulhusna.ui.about_app.AppAboutFragment;
import com.example.alasmaulhusna.ui.counter.AllahNamesCounterFragment;
import com.example.alasmaulhusna.ui.names.AllahNamesFragment;
import com.example.alasmaulhusna.ui.names.AllahNamesInfoFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    AllahNamesFragment allahNamesFragment;
    AllahNamesInfoFragment allahNamesInfoFragment;
    AllahNamesCounterFragment allahNamesCounterFragment;
    AppAboutFragment appAboutFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        allahNamesFragment = new AllahNamesFragment();
        allahNamesInfoFragment = new AllahNamesInfoFragment();
        allahNamesCounterFragment = new AllahNamesCounterFragment();
        appAboutFragment = new AppAboutFragment();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerFragment, allahNamesFragment).commit();

        binding.bottomNavView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.listAllahNames:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.containerFragment, allahNamesFragment).commit();
                    return true;

                case R.id.infoAllahNames:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.containerFragment, allahNamesInfoFragment).commit();
                    return true;

                case R.id.counterAllahNames:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.containerFragment, allahNamesCounterFragment).commit();
                    return true;

                case R.id.about_app:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.containerFragment, appAboutFragment).commit();
                    return true;
            }
            return false;
        });
    }
}