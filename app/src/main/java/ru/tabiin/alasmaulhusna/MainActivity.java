package ru.tabiin.alasmaulhusna;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;
import android.view.ContextThemeWrapper;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import ru.tabiin.alasmaulhusna.databinding.ActivityMainBinding;
import ru.tabiin.alasmaulhusna.ui.about_app.AppAboutFragment;
import ru.tabiin.alasmaulhusna.ui.counter.AllahNamesCounterFragment;
import ru.tabiin.alasmaulhusna.ui.names.AllahNamesFragment;
import ru.tabiin.alasmaulhusna.ui.names.AllahNamesInfoFragment;
import ru.tabiin.alasmaulhusna.util.SharedPreferencesUtils;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    private AllahNamesFragment allahNamesFragment;
    private AllahNamesInfoFragment allahNamesInfoFragment;
    private AllahNamesCounterFragment allahNamesCounterFragment;
    private AppAboutFragment appAboutFragment;
    private Fragment activeFragment;
    private Fragment lastFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SplashScreen splashScreen = SplashScreen.installSplashScreen(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (!SharedPreferencesUtils.getBoolean(this, "isFirstOpen")) {
            MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(new ContextThemeWrapper(this, R.style.Theme_AlAsmaUlHusna));
            builder.setIcon(R.drawable.icon_tabiin_light);
            builder.setTitle(R.string.welcome);
            builder.setMessage(R.string.welcome_message);
            builder.setPositiveButton("OK", (dialog, which) -> SharedPreferencesUtils.saveBoolean(this, "isFirstOpen", true));
            builder.show();

        }


        allahNamesFragment = new AllahNamesFragment();
        allahNamesInfoFragment = new AllahNamesInfoFragment();
        allahNamesCounterFragment = new AllahNamesCounterFragment();
        appAboutFragment = new AppAboutFragment();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.containerFragment, new AllahNamesFragment())
                .add(R.id.containerFragment, new AllahNamesInfoFragment())
                .add(R.id.containerFragment, new AllahNamesCounterFragment())
                .add(R.id.containerFragment, new AppAboutFragment());

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerFragment, new AllahNamesFragment()).commit();
        activeFragment = allahNamesFragment;


        binding.bottomNavView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.allahNames:
                    getSupportFragmentManager().beginTransaction()
                            .hide(activeFragment)
                            .show(new AllahNamesFragment())
                            .replace(R.id.containerFragment, new AllahNamesFragment()).commit();
                    activeFragment = allahNamesFragment;
                    return true;

                case R.id.allahNamesInfo:
                    getSupportFragmentManager().beginTransaction()
                            .hide(activeFragment)
                            .show(new AllahNamesInfoFragment())
                            .replace(R.id.containerFragment, new AllahNamesInfoFragment()).commit();
                    activeFragment = allahNamesInfoFragment;
                    return true;

                case R.id.counterAllahNames:
                    getSupportFragmentManager().beginTransaction()
                            .hide(activeFragment)
                            .show(new AllahNamesCounterFragment())
                            .replace(R.id.containerFragment, new AllahNamesCounterFragment()).commit();
                    activeFragment = allahNamesCounterFragment;
                    return true;

                case R.id.about_app:
                    getSupportFragmentManager().beginTransaction()
                            .hide(activeFragment)
                            .show(new AppAboutFragment())
                            .replace(R.id.containerFragment, new AppAboutFragment()).commit();
                    activeFragment = appAboutFragment;
                    return true;
            }
            return false;
        });

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }
}