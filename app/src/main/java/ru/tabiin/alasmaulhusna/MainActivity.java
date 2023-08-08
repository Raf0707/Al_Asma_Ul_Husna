package ru.tabiin.alasmaulhusna;

import android.content.Intent;
import android.os.Bundle;
import androidx.transition.TransitionManager;
import android.view.ViewGroup;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.splashscreen.SplashScreen;
import androidx.transition.Transition;
import androidx.transition.TransitionInflater;

import com.google.android.material.color.DynamicColors;
import com.google.android.material.transition.MaterialFadeThrough;

import ru.tabiin.alasmaulhusna.databinding.ActivityMainBinding;
import ru.tabiin.alasmaulhusna.ui.about_app.AppAboutFragment;
import ru.tabiin.alasmaulhusna.ui.counter.AllahNamesCounterFragment;
import ru.tabiin.alasmaulhusna.ui.counters.main.MainSwipeFragment;
import ru.tabiin.alasmaulhusna.ui.names.AllahNamesFragment;
import ru.tabiin.alasmaulhusna.util.SharedPreferencesUtils;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    AppAboutFragment appAboutFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        App.instance.setNightMode();
        SplashScreen splashScreen = SplashScreen.installSplashScreen(this);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.containerFragment, new AllahNamesFragment())
                    .commit();
        }

        if (SharedPreferencesUtils.getBoolean(this, "useDynamicColors"))
            DynamicColors.applyToActivityIfAvailable(this);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        appAboutFragment = new AppAboutFragment();

        Transition transition = TransitionInflater.from(this).inflateTransition(android.R.transition.fade);
        Transition transition1 = TransitionInflater.from(this).inflateTransition(android.R.transition.explode);
        Transition transition2 = TransitionInflater.from(this).inflateTransition(android.R.transition.move);
        Transition transition3 = TransitionInflater.from(this).inflateTransition(android.R.transition.slide_bottom);
        Transition transition4 = TransitionInflater.from(this).inflateTransition(android.R.transition.slide_left);
        Transition transition5 = TransitionInflater.from(this).inflateTransition(android.R.transition.slide_right);
        ViewGroup fragmentContainer = findViewById(R.id.containerFragment);
        //Transition transition = MaterialFadeThrough.create(getApplicationContext());

        binding.bottomNavView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.names_list:
                    transition.setDuration(1000); // Установите желаемую продолжительность анимации
                    transition1.setDuration(1000);
                    transition2.setDuration(1000);
                    TransitionManager.beginDelayedTransition(fragmentContainer, transition2);

                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.containerFragment, new AllahNamesFragment())
                            .commit();

                    return true;

                case R.id.names_info:
                    transition.setDuration(1000); // Установите желаемую продолжительность анимации
                    transition1.setDuration(1000);
                    transition2.setDuration(1000);
                    TransitionManager.beginDelayedTransition(fragmentContainer, transition2);

                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.containerFragment, new ru.tabiin.alasmaulhusna.ui.names.AllahNamesInfoFragment())
                            .commit();

                    return true;

                case R.id.names_counter:
                    transition.setDuration(1000); // Установите желаемую продолжительность анимации
                    transition1.setDuration(1000);
                    transition2.setDuration(1000);
                    TransitionManager.beginDelayedTransition(fragmentContainer, transition2);

                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.containerFragment, new MainSwipeFragment())
                            .commit();

                    return true;

                case R.id.app_about:
                    transition.setDuration(1000); // Установите желаемую продолжительность анимации
                    transition1.setDuration(1000);
                    transition2.setDuration(1000);
                    TransitionManager.beginDelayedTransition(fragmentContainer, transition2);

                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.containerFragment, new AppAboutFragment())
                            .commit();
                    return true;
            }
            return false;
        });
    }
}