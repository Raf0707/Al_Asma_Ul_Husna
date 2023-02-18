package ru.tabiin.alasmaulhusna;

import static android.graphics.Color.BLACK;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import ru.tabiin.alasmaulhusna.R;
import ru.tabiin.alasmaulhusna.databinding.ActivityMainBinding;
import ru.tabiin.alasmaulhusna.ui.about_app.AppAboutFragment;
import ru.tabiin.alasmaulhusna.ui.counter.AllahNamesCounterFragment;
import ru.tabiin.alasmaulhusna.ui.names.AllahNamesFragment;
import ru.tabiin.alasmaulhusna.ui.names.AllahNamesInfoFragment;

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