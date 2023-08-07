package ru.tabiin.alasmaulhusna.ui.counter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import ru.tabiin.alasmaulhusna.R;
import ru.tabiin.alasmaulhusna.databinding.FragmentTutorialBinding;
import ru.tabiin.alasmaulhusna.ui.counters.main.MainSwipeFragment;
import ru.tabiin.alasmaulhusna.ui.counters.swipe_counter.GestureCounterFragment;
import ru.tabiin.alasmaulhusna.util.UtilFragment;
import ru.tabiin.alasmaulhusna.R;
import ru.tabiin.alasmaulhusna.databinding.FragmentTutorialBinding;
import ru.tabiin.alasmaulhusna.util.UtilFragment;

public class TutorialFragment extends Fragment {
    FragmentTutorialBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentTutorialBinding.inflate(getLayoutInflater());

        binding.backGestureCounter.setOnClickListener(v -> {
            UtilFragment.changeFragment(requireActivity(),
                    new MainSwipeFragment(),
                    R.id.containerFragment,
                    savedInstanceState
            );
        });

        return binding.getRoot();
    }
}