package ru.tabiin.alasmaulhusna;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.color.DynamicColors;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import ru.tabiin.alasmaulhusna.databinding.ActivityMainBinding;
import ru.tabiin.alasmaulhusna.util.SharedPreferencesUtils;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private NavHostFragment navHostFragment;
    private ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SplashScreen splashScreen = SplashScreen.installSplashScreen(this);

        if (SharedPreferencesUtils.getBoolean(this, "useDynamicColors"))
            DynamicColors.applyToActivityIfAvailable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        actionBar = getSupportActionBar();

        if (!SharedPreferencesUtils.getBoolean(this, "isFirstOpen")) {
            MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(new ContextThemeWrapper(this, R.style.Theme_AlAsmaUlHusna));
            builder.setIcon(R.drawable.icon_tabiin_light);
            builder.setTitle(R.string.welcome);
            builder.setMessage(R.string.welcome_message);
            builder.setPositiveButton("OK", (dialog, which) -> SharedPreferencesUtils.saveBoolean(this, "isFirstOpen", true));
            builder.show();

        }

        try {
            navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_content_main);
            assert navHostFragment != null;
            NavController navController = navHostFragment.getNavController();
            BottomNavigationView bottomNav = findViewById(R.id.bottomAppBar);
            NavigationUI.setupWithNavController(bottomNav, navController);

        } catch (Exception e) {
            new MaterialAlertDialogBuilder(this)
                    .setTitle("НА, ХАВАЙ")
                    .setMessage("Ошибка в этом:" + "\n" + e.getMessage())
                    .setPositiveButton("OK", null)
                    .show();
        }
    }


    /*
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        int id = navHostFragment.getNavController().getCurrentDestination().getId();
        if (!(id == R.id.counterAllahNames)) {
            actionBar.setDisplayHomeAsUpEnabled(false);
        }
    }

     */

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
        super.onDestroy();
    }
}