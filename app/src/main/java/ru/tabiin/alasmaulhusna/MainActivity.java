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
    private int counteInit = 0;
    @Override
    public void onStart() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerFragment, new AllahNamesFragment()).commit();
        super.onStart();
    }
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

        binding.bottomNavView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.allahNames:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.containerFragment, new AllahNamesFragment()).commit();
                    return true;

                case R.id.allahNamesInfo:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.containerFragment, new AllahNamesInfoFragment()).commit();
                    return true;

                case R.id.counterAllahNames:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.containerFragment, new AllahNamesCounterFragment()).commit();
                    return true;

                case R.id.about_app:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.containerFragment, new AppAboutFragment()).commit();
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

    @Override
    public void onDestroy() {
        counteInit = 0;
        super.onDestroy();
    }
}