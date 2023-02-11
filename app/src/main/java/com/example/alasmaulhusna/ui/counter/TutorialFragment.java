package com.example.alasmaulhusna.ui.counter;

import static com.example.alasmaulhusna.util.UtilFragment.changeFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.alasmaulhusna.R;
import com.example.alasmaulhusna.databinding.FragmentTutorialBinding;
import com.google.android.material.snackbar.Snackbar;

public class TutorialFragment extends Fragment {
    FragmentTutorialBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentTutorialBinding.inflate(getLayoutInflater());



        binding.backGestureCounter.setOnClickListener(v -> {
            changeFragment(requireActivity(),
                    new AllahNamesCounterFragment(),
                    R.id.containerFragment,
                    savedInstanceState
            );
        });

        return binding.getRoot();
    }
}