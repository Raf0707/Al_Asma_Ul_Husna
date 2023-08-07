package ru.tabiin.alasmaulhusna.ui.counters.main;

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
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import ru.tabiin.alasmaulhusna.R;
import ru.tabiin.alasmaulhusna.adapters.counters.CounterAdapter;
import ru.tabiin.alasmaulhusna.databinding.FragmentMainSwipeBinding;
import ru.tabiin.alasmaulhusna.domain.dao.CounterDao;
import ru.tabiin.alasmaulhusna.domain.database.CounterDatabase;
import ru.tabiin.alasmaulhusna.domain.models.CounterItem;
import ru.tabiin.alasmaulhusna.domain.repository.CounterRepository;
import ru.tabiin.alasmaulhusna.ui.counters.swipe_counter.GestureCounterFragment;
import ru.tabiin.alasmaulhusna.ui.counters.view_model.CounterViewModel;

public class MainSwipeFragment extends Fragment implements CounterAdapter.HandleCounterClick {
    FragmentMainSwipeBinding binding;

    private CounterAdapter counterAdapter;
    private List<CounterItem> counterlist = new ArrayList<>();
    private CounterViewModel counterViewModel;
    private CounterItem counterForEdit;
    private GestureCounterFragment gestureCounterFragment;
    private String title;
    private int target;
    private int progress;
    private int id;
    private CounterItem counterItem;
    //CounterRepository counterRepository = new CounterRepository(
            //requireActivity().getApplication());

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMainSwipeBinding.inflate(getLayoutInflater());

        gestureCounterFragment = new GestureCounterFragment();

        counterAdapter = new CounterAdapter(getContext(), this);

        counterViewModel = new ViewModelProvider(this,
                (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory
                        .getInstance(requireActivity().getApplication()))
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

        binding.fabAddCounter.setOnClickListener(v -> {
            onMaterialAlert(false);
        });

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

        binding.recycleCounter.addOnScrollListener(
                new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                        if (dy < 0 && !binding.fabAddCounter.isShown())
                            binding.fabAddCounter.show();
                        else if (dy > 0 && binding.fabAddCounter.isShown())
                            binding.fabAddCounter.hide();
                    }
                }
        );

        binding.searchCounters.setOnClickListener(v -> {
            binding.searchCounters.clearFocus();
        });

        //if (counterRepository.getAllData() == null) init();
        initViewModel();
        initRecycleView();
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
                    if (counterItems.isEmpty()) {
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
                new MaterialAlertDialogBuilder(requireContext());

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

            if (counterTitle.getText().toString().length() == 0) {
                counterTitle.setText(getRandomString(12));
            }

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

    /*private void init() {
        //if (CounterDatabase.getInstance(getContext()) == null) {
            for (String i : namesOfAllah) {
                CounterItem counterItemL = new CounterItem(i, 10, 0);
                counterViewModel.insert(counterItemL);
            }

    }*/
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

    public static String getRandomString( int length) {
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(3);
            long result = 0;
            switch (number) {
                case 0:
                    result = Math.round(Math.random() * 25 + 65);
                    sb.append((char) result);
                    break;
                case 1:
                    result = Math.round(Math.random() * 25 + 97);
                    sb.append((char) result);
                    break;
                case 2:
                    sb.append(new Random().nextInt(10));
                    break;
            }


        }
        return sb.toString();
    }

}