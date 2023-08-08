package ru.tabiin.alasmaulhusna.ui.names;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.fragment.app.Fragment;
import androidx.transition.Transition;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.transition.MaterialFadeThrough;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import ru.tabiin.alasmaulhusna.adapters.names.NamesAdapter;
import ru.tabiin.alasmaulhusna.databinding.FragmentAllahNamesBinding;
import ru.tabiin.alasmaulhusna.objects.names.names.Name;


public class AllahNamesFragment extends Fragment {
    private String[] namesAllaha = new String[] {

            "للهُﷻ ",
            "الرَّحْمَانُﷻ ",
            "الرَّحِيمُﷻ ",
            "المَلِكُﷻ ",
            "القُدُّوسُﷻ ",
            "السَّلَامُﷻ ",
            "المُؤمِنُﷻ ",
            "المُهَيْمِنُﷻ ",
            "العَزِيزُﷻ ",
            "الجَبَّارُﷻ ",
            "المُتَكَبِّرُﷻ ",
            "الخَالِقُﷻ ",
            "البَارِئُﷻ ",
            "المُصَوِّرُﷻ ",
            "الغَفَّارُﷻ ",
            "القَهَّارُﷻ ",
            "الوَهَّابُﷻ ",
            "الرَّزَّاقُﷻ ",
            "الفَتَّاحُﷻ ",
            "العَلِيمُﷻ ",
            "القَابِضُﷻ ",
            "البَاسِطُﷻ ",
            "الخَافِضُﷻ ",
            "الرَّافِعُﷻ ",
            "المُعِزُّﷻ ",
            "المُذِلُّﷻ ",
            "السَّمِيعُﷻ ",
            "البَصِيرُﷻ " ,
            "الحَكَمُﷻُ ",
            "العَدْلُﷻُ ",
            "اللَّطِيفُﷻُ ",
            "الخَبِيرُﷻ ",
            "الحَلِيمُﷻ ",
            "العَظِيمُﷻ ",
            "الغَفُورُﷻ ",
            "الشَّكُورُﷻ ",
            "العَلِيُّﷻ ",
            "الكَبِيرُﷻ ",
            "الحَفِيظُﷻ ",
            "المُقِيتُﷻ ",
            "الحَسِيبُﷻ ",
            "الجَلِيلُﷻ ",
            "الكَرِيمُﷻ ",
            "الرَّقِيبُﷻ ",
            "المُجِيبُﷻ ",
            "الوَاسِعُﷻ ",
            "الحَكِيمُﷻ ",
            "الوَدُودُﷻ ",
            "المَجِيدُﷻ ",
            "البَاعِثُﷻ ",
            "الشَّهِيدُﷻ ",
            "الحَقُّﷻ ",
            "الوَكِيلُﷻ ",
            "القَوِيُّﷻ ",
            "المَتِينُﷻ ",
            "الوَلِيُّﷻ ",
            "الحَمِيدُﷻ ",
            "المُحْصِيﷻ ",
            "المُبْدِئُﷻ ",
            "المُعِيدُﷻ ",
            "المُحْيِيﷻ ",
            "المُمِيتُﷻ ",
            "الحَيُّﷻ ",
            "القَيُّومُﷻ ",
            "الوَاجِدُﷻ ",
            "المَاجِدُﷻ ",
            "الوَاحِدُﷻ ",
            "الصَّمَدُﷻ ",
            "القَادِرُﷻ ",
            "المُقْتَدِرُﷻ ",
            "المُقَدِّمُﷻ ",
            "المُؤَخِّرُﷻ ",
            "الأَوَّلُﷻ ",
            "الآخِرُﷻ ",
            "الظَّاهِرُﷻ ",
            "البَاطِنُﷻ ",
            "الوَالِيﷻ ",
            "المُتَعَالِيﷻ ",
            "البَرُّﷻ ",
            "التَّوَّابُﷻ ",
            "المُنْتَقِمُﷻ ",
            "العَفُوُّﷻ ",
            "الرَّءُؤفُﷻ ",
            "مَالِكُ المُلْكِﷻ ",
            "ذُو الجَلَالِ وَ الإِكْرَامِﷻ ",
            "المُقْسِطُﷻ ",
            "الجَامِعُﷻ ",
            "الغَنِيُّﷻ ",
            "المُغْنِيﷻ ",
            "المَانِعُﷻ ",
            "الضَّارُّﷻ ",
            "النَّافِعُﷻ ",
            "النُّورُﷻ ",
            "الهَادِيﷻ ",
            "البَدِيعُﷻ ",
            "البَاقِيﷻ ",
            "الوَارِثﷻ ",
            "الرَّشِيدُﷻ ",
            "الصَّبُورُﷻ "

    };

    public static WeakReference<AllahNamesFragment> ctx = null;
    private List<Name> names = new ArrayList<>();
    private ru.tabiin.alasmaulhusna.adapters.names.NamesAdapter namesAdapter;

    private FragmentAllahNamesBinding binding;
    MaterialFadeThrough materialFadeThrough = new MaterialFadeThrough();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentAllahNamesBinding.inflate(getLayoutInflater());
        ctx = new WeakReference<>(this);

        binding.searchName.clearFocus();
        binding.searchName.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                filterList(s);
                return true;
            }
        });

         binding.searchName.setOnClickListener(v -> {
             binding.searchName.clearFocus();
         });


        init();
        namesAdapter = new ru.tabiin.alasmaulhusna.adapters.names.NamesAdapter(requireActivity(), names);
        binding.drawerListItem.setAdapter(namesAdapter);
        binding.drawerListItem.setHasFixedSize(false);

        return binding.getRoot();

    }

    private void filterList(String text) {
        List<Name> filteredList = new ArrayList<>();
        for (Name name : names) {
            if (name.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(name);
            }

            /*
            if (Integer.parseInt(text) >= 1 && Integer.parseInt(text) <= 99) {
                filteredList.add(names.get(Integer.parseInt(text) - 1));
            }

             */
        }

        if (filteredList.isEmpty()) {
            Snackbar.make(binding.getRoot(), "Не найдено", BaseTransientBottomBar.LENGTH_SHORT);
        } else {
            namesAdapter.setFilteredList(filteredList);
        }
    }

    public void init() {
        for(String n : namesAllaha){
            names.add(new Name(n));
        }
    }
    @Override
    public void onResume() {
        binding.searchName.clearFocus();
        super.onResume();
    }
}
