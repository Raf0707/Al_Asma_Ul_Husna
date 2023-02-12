package com.example.alasmaulhusna.ui.counter;

import static android.content.Context.MODE_PRIVATE;

import static com.example.alasmaulhusna.util.UtilFragment.changeFragment;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.alasmaulhusna.R;
import com.example.alasmaulhusna.databinding.FragmentAllahNamesCounterBinding;
import com.example.alasmaulhusna.util.OnSwipeTouchListener;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.concurrent.TimeUnit;

public class AllahNamesCounterFragment extends Fragment {

    FragmentAllahNamesCounterBinding binding;

    private Handler handler;
    private String[] textsArabic;
    private String[] textPage;
    private String[] textCount;
    private String[] ya;
    private String[] save;
    private int currentPage = 0;
    private int currentCount = 0;
    private ConstraintLayout myLayout;
    private TextView arabic;
    private TextView page;
    private TextView salavatCounter;
    private SeekBar seekBar;
    private Button back;
    private SharedPreferences sPref;
    private View vview;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAllahNamesCounterBinding.inflate(getLayoutInflater());

        View view = binding.getRoot();

        myLayout = binding.myLayout;
        arabic = binding.arabic;
        page = binding.page;
        salavatCounter = binding.salavatCounter;
        handler = new Handler();
        seekBar = binding.seekBar;
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                saveText();
                currentPage = progress;
                textPage[currentPage] = Integer.toString(progress);
                seekBar.setProgress(progress);
                loadText();
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                saveText();
                loadText();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                saveText();
                loadText();
            }

        });

        initArabic();
        initPage();
        initCounter();
        initSave();
        setRes();


        Thread t = new Thread(() -> {
            try{
                TimeUnit.MILLISECONDS.sleep(100);
                handler.post(r);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        });

        t.start();

        view.setOnTouchListener(new OnSwipeTouchListener(view.getContext()) {

            @Override
            public void onSwipeRight() {
                saveText();
                currentPage--;
                savePage();
                loadText();
                if (currentCount < 0) currentCount = 0;
                if (currentPage < 0) currentPage = 99;
                seekBar.setProgress(currentPage);
            }

            @Override
            public void onSwipeLeft() {
                saveText();
                currentPage++;
                if (currentPage > 99) {
                    currentPage = 0;
                }
                savePage();
                loadText();

                seekBar.setProgress(currentPage);
            }

            @Override
            public void onSwipeDown() {
                saveText();
                currentCount++;
                textCount[currentPage] = Integer.toString(currentCount);
                salavatCounter.setText(textCount[currentPage]);
                saveText();
                loadText();

            }

            @Override
            public void onSwipeUp() {
                saveText();
                currentCount--;
                if (currentCount < 0) currentCount = 0;
                textCount[currentPage] = Integer.toString(currentCount);
                salavatCounter.setText(textCount[currentPage]);
                saveText();
                loadText();

            }

            @Override
            public void onClick() {
                saveText();
                currentCount++;
                textCount[currentPage] = Integer.toString(currentCount);
                salavatCounter.setText(textCount[currentPage]);
                saveText();
                loadText();
            }

            @Override
            public void onDoubleClick() {
                saveText();
                currentCount++;
                textCount[currentPage] = Integer.toString(currentCount);
                salavatCounter.setText(textCount[currentPage]);
                saveText();
                loadText();

            }

            @Override
            public void onLongClick() {
                saveText();
                onAlert();
                saveText();
                loadText();

            }
        });

        binding.openTutorialBtn.setOnClickListener(view1 -> changeFragment(requireActivity(),
                new TutorialFragment(),
                R.id.containerFragment,
                savedInstanceState
        ));

        loadPage();
        loadText();

        return binding.getRoot();
    }

    public void initArabic() {
        textsArabic = new String[] {

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
                ""
        };

    }

    public void initPage() {
        textPage = new String[] {
                "0",
                "1",
                "2",
                "3",
                "4",
                "5",
                "6",
                "7",
                "8",
                "9",
                "10",
                "11",
                "12",
                "13",
                "14",
                "15",
                "16",
                "17",
                "18",
                "19",
                "20",
                "21",
                "22",
                "23",
                "24",
                "25",
                "26",
                "27",
                "28",
                "29",
                "30",
                "31",
                "32",
                "33",
                "34",
                "35",
                "36",
                "37",
                "38",
                "39",
                "40",
                "41",
                "42",
                "43",
                "44",
                "45",
                "46",
                "47",
                "48",
                "49",
                "50",
                "51",
                "52",
                "53",
                "54",
                "55",
                "56",
                "57",
                "58",
                "59",
                "60",
                "61",
                "62",
                "63",
                "64",
                "65",
                "66",
                "67",
                "68",
                "69",
                "70",
                "71",
                "72",
                "73",
                "74",
                "75",
                "76",
                "77",
                "78",
                "79",
                "80",
                "81",
                "82",
                "83",
                "84",
                "85",
                "86",
                "87",
                "88",
                "89",
                "90",
                "91",
                "92",
                "93",
                "94",
                "95",
                "96",
                "97",
                "98",
                "99",
                "100"
        };
    }

    public void initCounter() {
        textCount = new String[] {
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0",
                "0"
        };

    }
    public void initSave() {
        save = new String[] {
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                ""
        };
    }

    public void setRes(){
        salavatCounter.setText(textCount[currentPage]);
    }



    Runnable r = new Runnable() {
        public void run(){
            if(currentPage < 0) currentPage = 99;
            if(currentPage > 99) currentPage = 0;
            arabic.setText(textsArabic[currentPage]);
            page.setText(textPage[currentPage + 1]);
            handler.postDelayed(r,100);
        }
    };

    public void saveText() {
        sPref = getActivity().getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString( "Прочитал" + currentPage, salavatCounter.getText().toString());
        ed.apply();
        currentCount = Integer.parseInt(salavatCounter.getText().toString());
    }

    public void loadText() {
        sPref = getActivity().getPreferences(MODE_PRIVATE);
        String salavat = sPref.getString("Прочитал" + currentPage, salavatCounter.getText().toString());
        salavatCounter.setText(salavat);
        currentCount = Integer.parseInt(sPref.getString("Прочитал" + currentPage, salavatCounter.getText().toString()));
    }

    public void savePage() {
        sPref = getActivity().getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putInt("page", currentPage);
        ed.apply();
    }

    public void loadPage() {
        sPref = getActivity().getPreferences(MODE_PRIVATE);
        currentPage = (sPref.getInt("page", currentPage));
        if (currentPage > 99) {
            currentPage = 99;
        }
        seekBar.setProgress(currentPage);
        page.setText(Integer.toString(currentPage));
    }

    public void onAlert() {
        new MaterialAlertDialogBuilder(requireContext(),
                R.style.AlertDialogTheme)
                .setTitle("Reset")
                .setMessage("Очистить счетчик?")
                .setPositiveButton("Да", (dialogInterface, i) -> {
                    currentCount = 0;
                    textCount[currentPage] = Integer.toString(currentCount);
                    salavatCounter.setText(textCount[currentPage]);
                    saveText();
                    loadText();
                    savePage();
                    loadPage();
                })
                .setNeutralButton("Отмена",
                        (dialogInterface, i) ->
                                dialogInterface.cancel())
                .show();
    }


    @Override
    public void onDestroy() {
        savePage();
        saveText();
        loadText();
        loadPage();
        super.onDestroy();
    }
}