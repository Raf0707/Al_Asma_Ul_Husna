package ru.tabiin.alasmaulhusna.ui.names;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.tabiin.alasmaulhusna.R;
import ru.tabiin.alasmaulhusna.databinding.FragmentNamesBannerContainerBinding;

public class NamesBannerContainerFragment extends Fragment {
    FragmentNamesBannerContainerBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentNamesBannerContainerBinding.inflate(getLayoutInflater());

        binding.materialCardAllahNames.setOnClickListener(v -> {
            /*
            getFragmentManager().beginTransaction().remove(getFragmentManager()
                            .findFragmentByTag("bannerContainer"));
            getFragmentManager().popBackStackImmediate();

            getFragmentManager().beginTransaction().show(getFragmentManager().
                    findFragmentById(R.id.namesAllahDrawerInfo)).commit();

             */
            Navigation.findNavController(getView())
                    .navigate(R.id.action_namesBannerContainerFragment_to_allahNamesInfoFragment3);
        });

        return binding.getRoot();
    }
}