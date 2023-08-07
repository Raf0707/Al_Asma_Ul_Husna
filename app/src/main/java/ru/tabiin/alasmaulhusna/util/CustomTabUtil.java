package ru.tabiin.alasmaulhusna.util;

import android.content.Context;
import android.net.Uri;

import androidx.browser.customtabs.CustomTabColorSchemeParams;
import androidx.browser.customtabs.CustomTabsIntent;

import ru.tabiin.alasmaulhusna.R;

public class CustomTabUtil {

    public void openCustomTab(Context context, String URL, int colorInt) {
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(context, Uri.parse(URL));

        CustomTabColorSchemeParams defaultColors = new CustomTabColorSchemeParams.Builder()
                .setToolbarColor(colorInt)
                .build();
        builder.setDefaultColorSchemeParams(defaultColors);
    }
}