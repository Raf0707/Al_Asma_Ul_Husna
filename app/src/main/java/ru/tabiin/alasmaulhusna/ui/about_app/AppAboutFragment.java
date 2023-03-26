package ru.tabiin.alasmaulhusna.ui.about_app;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.snackbar.Snackbar;

import ru.tabiin.alasmaulhusna.BuildConfig;
import ru.tabiin.alasmaulhusna.R;
import ru.tabiin.alasmaulhusna.databinding.FragmentAppAboutBinding;
import ru.tabiin.alasmaulhusna.ui.settings.SettingsFragment;
import ru.tabiin.alasmaulhusna.util.BugReportHelper;
import ru.tabiin.alasmaulhusna.util.CustomTabUtil;
import ru.tabiin.alasmaulhusna.BuildConfig;
import ru.tabiin.alasmaulhusna.R;
import ru.tabiin.alasmaulhusna.databinding.FragmentAppAboutBinding;
import ru.tabiin.alasmaulhusna.ui.settings.SettingsFragment;
import ru.tabiin.alasmaulhusna.util.BugReportHelper;
import ru.tabiin.alasmaulhusna.util.CustomTabUtil;


public class AppAboutFragment extends Fragment {

    private FragmentAppAboutBinding binding;
    private int iconId;
    public static String selectTheme = "system";

    private SharedPreferences sPref;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            selectTheme = savedInstanceState.getString("theme");
            //iconId = savedInstanceState.getInt("iconTheme");
            loadTheme(selectTheme);
            //binding.themesBtn.setIcon(getResources().getDrawable(iconId));
            Log.d("onCreate", "load " + selectTheme);
        }
    }

    @SuppressLint("IntentReset")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentAppAboutBinding
                .inflate(getLayoutInflater());

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.appVersionBtn.setText(new StringBuilder()
                .append(getString(R.string.version))
                .append(getString(R.string.str_dv))
                .append(BuildConfig.VERSION_NAME)
                .append(getString(R.string.val_str_sk_right))
                .append(BuildConfig.VERSION_CODE)
                .append(getString(R.string.val_str_sk_left))
                .toString());

        binding.appVersionBtn.setOnLongClickListener(v -> {
            addOnClick(v, getString(R.string.version_copied),
                    ClipData.newPlainText(
                            getString(R.string.getContext),
                            new StringBuilder()
                                    .append(getString(R.string.Tabiin_str_Version))
                                    .append(getString(R.string.version))
                                    .append(getString(R.string.str_dv))
                                    .append(BuildConfig.VERSION_NAME)
                                    .append(getString(R.string.val_str_sk_right))
                                    .append(BuildConfig.VERSION_CODE)
                                    .append(getString(R.string.val_str_sk_left))
                                    .toString()));
            return true;
        });

        binding.sourceCodeBtn.setOnLongClickListener(v -> {
            addOnClick(v, getString(R.string.link_to_source_copied),
                    ClipData.newPlainText(getString(R.string.getContext),
                            getString(R.string.source_code_url)));
            return true;
        });

        binding.rafailBtn.setOnLongClickListener(v -> {
            addOnClick(v, getString(R.string.raf_git_copylink),
                    ClipData.newPlainText(getString(R.string.getContext),
                            getString(R.string.rafail_url)));
            return true;
        });

        binding.mailRafBtn.setOnLongClickListener(v -> {
            addOnClick(v, getString(R.string.my_email_copylink),
                    ClipData.newPlainText(getString(R.string.getContext),
                            getString(R.string.mail_raf)));
            return true;
        });

        binding.rateBtn.setOnLongClickListener(v -> {
            addOnClick(v, getString(R.string.vk_tabiin_coyplink),
                    ClipData.newPlainText(getString(R.string.rateApp),
                            getString(R.string.tabiin)));
            return true;
        });

        binding.vkGroupBtn.setOnLongClickListener(v -> {
            addOnClick(v, getString(R.string.vk_tabiin_coyplink),
                    ClipData.newPlainText(getString(R.string.getContext),
                            getString(R.string.tabiin)));
            return true;
        });

        binding.sourceCodeBtn.setOnClickListener(v -> new CustomTabUtil()
                .openCustomTab(getActivity(),
                        getString(R.string.source_code_url),
                        R.color.purple_300));

        binding.rafailBtn.setOnClickListener(v -> new CustomTabUtil()
                .openCustomTab(getActivity(),
                        getString(R.string.rafail_url),
                        R.color.purple_300));


        binding.mailRafBtn.setOnClickListener(v -> {
            final Intent emailIntent = new Intent(Intent.ACTION_SEND);
            emailIntent.setData(Uri.parse(getString(R.string.mailto)))
                    .setType(getString(R.string.text_plain))
                    .putExtra(Intent.EXTRA_EMAIL,
                            new String[]{getString(R.string.mail_raf)})
                    .putExtra(Intent.EXTRA_SUBJECT, R.string.app_name)
                    .putExtra(Intent.EXTRA_TEXT,
                            new StringBuilder()
                                    .append(getString(R.string.app_name))
                                    .append(getString(R.string.semicolon))
                                    .append(getString(R.string.version))
                                    .append(getString(R.string.str_dv))
                                    .append(BuildConfig.VERSION_NAME)
                                    .append(getString(R.string.val_str_sk_right))
                                    .append(BuildConfig.VERSION_CODE)
                                    .append(getString(R.string.val_str_sk_left))
                                    .toString());

            emailIntent.setType(getString(R.string.text_plain));
            // setType("message/rfc822")

            try {
                startActivity(Intent.createChooser(emailIntent,
                        getString(R.string.email_client)));

            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(getActivity(),
                        R.string.no_email_client, Toast.LENGTH_SHORT).show();
            }
        });



        binding.rateBtn.setOnClickListener(v -> new CustomTabUtil()
            .openCustomTab(getActivity(),
                    getString(R.string.rateApp),
                    R.color.purple_300));


        binding.vkGroupBtn.setOnClickListener(v -> new CustomTabUtil()
                .openCustomTab(getActivity(),
                        getString(R.string.tabiin),
                        R.color.purple_300));

        binding.otherAppsBtn.setOnClickListener(v -> new CustomTabUtil()
            .openCustomTab(getActivity(),
                    getString(R.string.tabiin_android_dev),
                    R.color.purple_300));

        binding.settingsBtn.setOnClickListener(v -> {
            getFragmentManager().beginTransaction()
                    .replace(R.id.containerFragment, new SettingsFragment())
                    .commit();
        });

        //binding.bugReport.setOnClickListener(v -> BugReportHelper.sendEmail(getActivity()));

        binding.donateBtn.setOnClickListener(v -> new CustomTabUtil().openCustomTab(getActivity(),
                "https://www.donationalerts.com/r/raf0707", R.color.md_theme_light_onSecondary));

        binding.tgGroupBtn.setOnClickListener(v -> new CustomTabUtil()
                .openCustomTab(getActivity(), "https://t.me/+Lkw3ON0EsjZlNDIy",
                        R.color.md_theme_light_onSecondary));

        binding.downloadUmmaLifeGooglePlay.setOnClickListener(v -> {
            try {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://play.google.com/store/apps/details?id=com.ummalife.android")));
            } catch (android.content.ActivityNotFoundException anfe) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.GOOGLE_PLAY))));
            }
        });

        binding.vkUmmaLife.setOnClickListener(v -> new CustomTabUtil()
                .openCustomTab(getActivity(),
                        "https://vk.com/ummalife_com?ysclid=lfot4irl1443773327",
                        R.color.purple_300));

        binding.tgUmmaLife.setOnClickListener(v -> new CustomTabUtil()
                .openCustomTab(getActivity(),
                        "https://t.me/man_umma",
                        R.color.purple_300));

        binding.webUmmaLife.setOnClickListener(v -> new CustomTabUtil()
                .openCustomTab(getActivity(),
                        "https://ummalife.com",
                        R.color.purple_300));

        binding.weAreInUmmaLife.setOnClickListener(v -> new CustomTabUtil()
                .openCustomTab(getActivity(),
                        "https://ummalife.com/tabiin",
                        R.color.purple_300));

    }

    public void addOnClick(View view, String text, ClipData clipData) {
        ClipboardManager clipboardManager = (ClipboardManager)
                requireContext().getSystemService(Context.CLIPBOARD_SERVICE);

        clipboardManager.setPrimaryClip(clipData);
        Snackbar.make(requireView(), text, Snackbar.LENGTH_LONG).show();
    }

    public void saveTheme(String selectTheme) {
        Bundle tranBundle = new Bundle();
        FragmentManager fragmentManager  = getFragmentManager();
        AppAboutFragment appAboutFragment = new AppAboutFragment();
        tranBundle.putString("thm", selectTheme);
        appAboutFragment.setArguments(tranBundle);
    }
    public void loadTheme(String selectTheme) {
        Bundle bundle = getArguments();
        if (bundle != null) {
            String selectThm = bundle.getString("thm");
            selectTheme = selectThm;
            if (selectTheme.equals("system")) {
                AppCompatDelegate.setDefaultNightMode(
                        AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);

                }

                //saveTheme(selectTheme);
                requireActivity().recreate();

            } else if (selectTheme.equals("dark")) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

                requireActivity().recreate();

            } else if (selectTheme.equals("light")) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

                requireActivity().recreate();

            }
        }




    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString("theme", selectTheme);
        Log.d("onSaveInstanceState", "save " + selectTheme);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        Log.d("onViewStateRestored", "restore " + selectTheme);
        super.onViewStateRestored(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        saveTheme(selectTheme);
        Log.d("onDestroy", "save " + selectTheme);
        super.onDestroy();
    }


}