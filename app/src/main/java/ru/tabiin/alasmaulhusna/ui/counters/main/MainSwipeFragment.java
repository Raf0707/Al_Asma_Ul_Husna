package ru.tabiin.alasmaulhusna.ui.counters.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ru.tabiin.alasmaulhusna.R;
import ru.tabiin.alasmaulhusna.adapters.counters.CounterAdapter;
import ru.tabiin.alasmaulhusna.databinding.FragmentMainSwipeBinding;
import ru.tabiin.alasmaulhusna.domain.database.CounterDatabase;
import ru.tabiin.alasmaulhusna.domain.models.CounterItem;
import ru.tabiin.alasmaulhusna.ui.counters.swipe_counter.GestureCounterFragment;
import ru.tabiin.alasmaulhusna.ui.counters.view_model.CounterViewModel;

public class MainSwipeFragment extends Fragment implements CounterAdapter.HandleCounterClick {
    FragmentMainSwipeBinding binding;

    private CounterAdapter counterAdapter;
    private List<CounterItem> counterlist = new ArrayList<>();
    private CounterViewModel counterViewModel;
    private CounterItem counterForEdit;
    //private CounterMainFragment counterMainFragment;
    //private CounterBetaFragment counterBetaFragment;
    private GestureCounterFragment gestureCounterFragment;
    private CounterDatabase counterDatabase;
    private CounterAdapter.MyViewHolder holder;
    private boolean edit;
    private String title;
    private int target;
    private int progress;
    private int id;
    private CounterItem counterItem;

    private String[] namesOfAllah = new String[] {

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
            "البَصِيرُﷻ ",
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
            "ذُو الجَلَالِ \nوَ الإِكْرَامِﷻ ",
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
            "الصَّبُورُﷻ ",
    };

    private List<CounterItem> counterItemList = new ArrayList<>();
    private int initCounter = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMainSwipeBinding.inflate(getLayoutInflater());

        gestureCounterFragment = new GestureCounterFragment();

        counterAdapter = new CounterAdapter(getContext(), this);

        counterViewModel = new ViewModelProvider(this,
                (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory
                        .getInstance(getActivity().getApplication()))
                .get(CounterViewModel.class);

        Bundle bundle = getArguments();
        if (bundle != null) {
            title = bundle.getString("title", counterItem.title);
            target = bundle.getInt("target", counterItem.target);
            progress = bundle.getInt("progress", counterItem.progress);
            id = bundle.getInt("id");

            counterItem = new CounterItem(id, title, target, progress);
            counterViewModel.update(counterItem);
        }

        binding.searchCounters.clearFocus();
        binding.searchCounters.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                //filterList(s);
                counterViewModel.findByNames(s);
                return true;
            }
        });

        binding.searchCounters.setOnClickListener(v -> {
            binding.searchCounters.clearFocus();
        });

        initRecycleView();
        initViewModel();
        if (initCounter == 0) {
            init();
            initCounter += 1;
        }
        counterViewModel.getAllCounterList();

        return binding.getRoot();
    }

    public void initRecycleView() {
        binding.recycleCounter.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recycleCounter.setHasFixedSize(true);
        binding.recycleCounter.setAdapter(counterAdapter);
    }

    public void initViewModel() {
        counterViewModel = new ViewModelProvider(this)
                .get(CounterViewModel.class);
        counterViewModel.getCounterlistObserver()
                .observe(requireActivity(), counterItems -> {
                    if (counterItems == null) {
                        binding.noRes.setVisibility(View.VISIBLE);
                        binding.recycleCounter.setVisibility(View.GONE);
                    } else {
                        counterAdapter.setCounterList(counterItems);
                        binding.recycleCounter.setVisibility(View.VISIBLE);
                        binding.noRes.setVisibility(View.GONE);
                    }
                });
    }

    public void onMaterialAlert(boolean isForEdit) {
        MaterialAlertDialogBuilder alert =
                new MaterialAlertDialogBuilder(getContext());

        View dialogView = getLayoutInflater()
                .inflate(R.layout.create_counter_dialog, null);

        alert.setTitle("Новый счетчик");
        alert.setMessage("введите название и цель");
        alert.setCancelable(true);

        TextView counterTitle = dialogView.findViewById(R.id.counterTitle);
        EditText counterTarget = dialogView.findViewById(R.id.counterTarget);
        TextView counterProgress = dialogView.findViewById(R.id.counterProgress);

        if (isForEdit) {
            alert.setTitle("Изменить счетчик");
            counterTitle.setText(counterForEdit.title);
            counterTarget.setText(counterForEdit.target + "");
        }

        alert.setNegativeButton("Отмена", (dialogInterface, i) -> {

        });


        alert.setPositiveButton("ОК", (dialogInterface, i) -> {

            if (counterTarget.getText().toString().length() == 0) {
                counterTarget.setText("10");
            }

            if (counterProgress.getText().toString().length() == 0) {
                counterProgress.setText("0");
            }

            if (isForEdit) {
                counterForEdit.title = counterTitle.getText().toString();
                counterForEdit.target = Integer.parseInt(counterTarget
                        .getText().toString());
                counterViewModel.update(counterForEdit);
            } else {
                counterViewModel.insert(counterTitle.getText().toString(),
                        Integer.parseInt(counterTarget.getText().toString()));
            }
        });

        alert.setView(dialogView);
        alert.show();
    }

    private void init() {
        for (String i : namesOfAllah) {
            if (counterItemList.size() < 100) {
                CounterItem counterItemL = new CounterItem(i, 10, 0);
                counterItemList.add(counterItemL);
                counterViewModel.insert(counterItemL);
            }
        }
    }

    private void initModel() {
        for (CounterItem c : counterItemList) {
            counterViewModel.insert(c);
        }
    }

    private void filterList(String text) {
        List<CounterItem> filteredList = new ArrayList<>();
        for (CounterItem ci : counterlist) {
            if (ci.getTitle().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(ci);
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
            counterAdapter.setFilteredList(filteredList);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Проверяем по коду нужный результат
        if(requestCode == 120) {
            if(resultCode == Activity.RESULT_OK) {
                //Действия при возврате результата
                String updateTitle = data.getStringExtra("updateTitle");
                int updateTarget = data.getIntExtra("updateTarget", 10);
                int updateProgress = data.getIntExtra("updateProgress", progress);

                CounterItem counterItem = new CounterItem(updateTitle, updateTarget,
                        updateProgress);

                title =  updateTitle;
                target = updateTarget;
                progress = updateProgress;

                counterViewModel.update(counterItem);
            }
        }
    }


    @Override
    public void itemClick(CounterItem counterItem) {

        Bundle bundle = new Bundle();
        FragmentManager fragmentManager = getFragmentManager();
        bundle.putString("title", counterItem.title);
        bundle.putInt("target", counterItem.target);
        bundle.putInt("progress", counterItem.progress);
        bundle.putInt("id", counterItem.id);
        gestureCounterFragment.setArguments(bundle);

        getParentFragmentManager().beginTransaction()
                .replace(R.id.containerFragment, gestureCounterFragment).commit();

    }

    @Override
    public void deleteItem(CounterItem counterItem) {
        counterViewModel.delete(counterItem);
    }

    @Override
    public void editItem(CounterItem counterItem) {
        this.counterForEdit = counterItem;
        onMaterialAlert(true);
    }

}