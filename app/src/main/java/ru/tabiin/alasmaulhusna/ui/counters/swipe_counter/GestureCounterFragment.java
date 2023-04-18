package ru.tabiin.alasmaulhusna.ui.counters.swipe_counter;

import static ru.tabiin.alasmaulhusna.util.UtilFragment.changeFragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import ru.tabiin.alasmaulhusna.R;
import ru.tabiin.alasmaulhusna.databinding.FragmentGestureCounterBinding;
import ru.tabiin.alasmaulhusna.domain.models.CounterItem;
import ru.tabiin.alasmaulhusna.ui.counter.TutorialFragment;
import ru.tabiin.alasmaulhusna.ui.counters.main.MainSwipeFragment;
import ru.tabiin.alasmaulhusna.ui.counters.view_model.CounterViewModel;
import ru.tabiin.alasmaulhusna.ui.settings.SettingsFragment;
import ru.tabiin.alasmaulhusna.util.OnSwipeTouchListener;

public class GestureCounterFragment extends Fragment {

    private FragmentGestureCounterBinding binding;
    private Handler handler;
    private int counter = 0;
    private String selectMode = "Circle counter";
    private CounterItem counterItem;
    private CounterViewModel counterViewModel;
    private MainSwipeFragment mainSwipeFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentGestureCounterBinding
                .inflate(inflater, container, false);

        counterViewModel = new ViewModelProvider(this,
                (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory
                        .getInstance(getActivity().getApplication()))
                .get(CounterViewModel.class);

        Bundle bundle = getArguments();
        if (bundle != null) {
            String title = bundle.getString("title");
            int target = bundle.getInt("target");
            int progress = bundle.getInt("progress");
            int id = bundle.getInt("id");

            binding.counterTitle.setText(title);
            binding.counterTarget.setText(Integer.toString(target));
            counter = progress;
            binding.gestureCounter.setText(Integer.toString(counter));


            counterItem = new CounterItem(id, title, target, progress);

        }

        mainSwipeFragment = new MainSwipeFragment();

        handler = new Handler();

        binding.openSettingsBtn.setOnClickListener(view -> {
            changeFragment(requireActivity(),
                    new SettingsFragment(),
                    R.id.containerFragment,
                    savedInstanceState
            );

            /**
             * сделать сохранение
             */
            counterItem.title = binding.counterTitle.getText().toString();
            counterItem.target = Integer.parseInt(binding.counterTarget.getText().toString());
            counterItem.progress = counter;
            counterViewModel.update(counterItem);
        });

        binding.openCounterListBtn.setOnClickListener(view -> {
            changeFragment(requireActivity(),
                    mainSwipeFragment,
                    R.id.containerFragment,
                    savedInstanceState
            );
            /**
             * сделать сохранение
             */
            counterItem.title = binding.counterTitle.getText().toString();
            counterItem.target = Integer.parseInt(binding.counterTarget.getText().toString());
            counterItem.progress = counter;
            counterViewModel.update(counterItem);
        });

        binding.openTutorialBtn.setOnClickListener(view -> {
            changeFragment(requireActivity(),
                    new TutorialFragment(),
                    R.id.containerFragment,
                    savedInstanceState
            );
            /**
             * сделать сохранение
             */
            counterItem.title = binding.counterTitle.getText().toString();
            counterItem.target = Integer.parseInt(binding.counterTarget.getText().toString());
            counterItem.progress = counter;
            counterViewModel.update(counterItem);
        });

        binding.counterGestureView.setOnTouchListener(
                new OnSwipeTouchListener(
                        binding.counterGestureView.getContext()) {

                    @Override
                    public void onClick() {
                        counter++;
                        binding.gestureCounter.setText(Integer.toString(counter));
                        /**
                         * сделать сохранение
                         */
                        counterItem.title = binding.counterTitle.getText().toString();
                        counterItem.target = Integer.parseInt(binding.counterTarget
                                .getText().toString());
                        counterItem.progress = counter;
                        counterViewModel.update(counterItem);
                    }

                    @Override
                    public void onSwipeDown() {
                        counter--;
                        binding.gestureCounter.setText(Integer.toString(counter));
                        /**
                         * сделать сохранение
                         */
                        counterItem.title = binding.counterTitle.getText().toString();
                        counterItem.target = Integer.parseInt(binding.counterTarget
                                .getText().toString());
                        counterItem.progress = counter;
                        counterViewModel.update(counterItem);
                    }

                    @Override
                    public void onLongClick() {
                        if (counter != 0 &&
                                binding.gestureCounter.getText().toString() != "0")
                            onMaterialAlert();
                        /**
                         * сделать сохранение
                         */
                        counterItem.title = binding.counterTitle.getText().toString();
                        counterItem.target = Integer.parseInt(binding.counterTarget
                                .getText().toString());
                        counterItem.progress = counter;
                        counterViewModel.update(counterItem);
                    }

                });

        /*
        Thread t = new Thread(() -> {
            try{
                TimeUnit.MILLISECONDS.sleep(100);
                handler.post(r);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        });

        t.start();

         */

        return binding.getRoot();
    }

    /*
    Runnable r = new Runnable() {
        public void run(){
            binding.gestureCounter.setText(Integer.toString(counter));
            handler.postDelayed(r,100);
        }
    };

     */

    public void onMaterialAlert() {
        new MaterialAlertDialogBuilder(requireContext(),
                R.style.AlertDialogTheme)
                .setTitle("Reset")
                .setMessage("Вы уверены, что хотите обновить счетчик?")
                .setPositiveButton("Да", (dialogInterface, i) -> {
                    counter = 0;
                    binding.gestureCounter
                            .setText(new StringBuilder()
                                    .append("0"));
                })
                .setNeutralButton("Отмена",
                        (dialogInterface, i) ->
                                dialogInterface.cancel())
                .show();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        /**
         * сделать сохранение
         */
        counterItem.title = binding.counterTitle.getText().toString();
        counterItem.target = Integer.parseInt(binding.counterTarget.getText().toString());
        counterItem.progress = counter;
        counterViewModel.update(counterItem);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onStop() {
        /**
         * сделать сохранение
         */
        counterItem.title = binding.counterTitle.getText().toString();
        counterItem.target = Integer.parseInt(binding.counterTarget.getText().toString());
        counterItem.progress = counter;
        counterViewModel.update(counterItem);
        super.onStop();
    }

    @Override
    public void onPause() {
        /**
         * сделать сохранение
         */
        counterItem.title = binding.counterTitle.getText().toString();
        counterItem.target = Integer.parseInt(binding.counterTarget.getText().toString());
        counterItem.progress = counter;
        counterViewModel.update(counterItem);
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        /**
         * сделать сохранение
         */
        counterItem.title = binding.counterTitle.getText().toString();
        counterItem.target = Integer.parseInt(binding.counterTarget.getText().toString());
        counterItem.progress = counter;
        counterViewModel.update(counterItem);
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        /**
         * сделать сохранение
         */
        counterItem.title = binding.counterTitle.getText().toString();
        counterItem.target = Integer.parseInt(binding.counterTarget.getText().toString());
        counterItem.progress = counter;
        counterViewModel.update(counterItem);
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        counterItem.title = binding.counterTitle.getText().toString();
        counterItem.target = Integer.parseInt(binding.counterTarget.getText().toString());
        counterItem.progress = counter;
        counterViewModel.update(counterItem);
        super.onDetach();
    }

}
