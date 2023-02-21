package ru.tabiin.alasmaulhusna;

import static android.graphics.Color.BLACK;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import ru.tabiin.alasmaulhusna.R;
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

    private boolean isEditing;

    private NavHostFragment navHostFragment;

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

            navHostFragment = (NavHostFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.navHostFragment);
            assert navHostFragment != null;
            NavController navController = navHostFragment.getNavController();
            //BottomNavigationView bottomNav = findViewById(R.id.bottomAppBar);
            NavigationUI.setupWithNavController(binding.bottomNavView, navController);
        }

        allahNamesFragment = new AllahNamesFragment();
        allahNamesInfoFragment = new AllahNamesInfoFragment();
        allahNamesCounterFragment = new AllahNamesCounterFragment();
        appAboutFragment = new AppAboutFragment();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerFragment, allahNamesFragment).commit();



        /*
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

         */

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        int id = navHostFragment.getNavController().getCurrentDestination().getId();
        //if (!(id == R.id.devicesFragment || id == R.id.deviceSettingsFragment)) {
            //actionBar.setDisplayHomeAsUpEnabled(false);
        //}
    }

    @Override
    public void recreate() {
        getWindow().setWindowAnimations(R.style.WindowAnimationFadeInOut);
        super.recreate();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}