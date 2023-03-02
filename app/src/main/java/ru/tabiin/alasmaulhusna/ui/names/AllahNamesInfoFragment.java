package ru.tabiin.alasmaulhusna.ui.names;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import ru.tabiin.alasmaulhusna.R;
import ru.tabiin.alasmaulhusna.adapters.names.DrawerNamesAdapter;
import ru.tabiin.alasmaulhusna.adapters.names.NamesAdapter;
import ru.tabiin.alasmaulhusna.adapters.names.NamesInfoAdapter;
import ru.tabiin.alasmaulhusna.databinding.FragmentAllahNamesInfoBinding;
import ru.tabiin.alasmaulhusna.objects.names.drawer_names.DrawerNamesContent;
import ru.tabiin.alasmaulhusna.objects.names.info_names.NameInfo;
import ru.tabiin.alasmaulhusna.objects.names.names.Name;
import ru.tabiin.alasmaulhusna.util.MyDrawerLayout;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;


public class AllahNamesInfoFragment extends Fragment {
    FragmentAllahNamesInfoBinding binding;

    private String[] arabicName, transcriptName, translateName, infoName;
    public static WeakReference<AllahNamesInfoFragment> ctx = null;
    private List<NameInfo> namesInfo = new ArrayList<>();
    private List<Name> names = new ArrayList<>();
    private List<DrawerNamesContent> namesDrawer = new ArrayList<>();
    private NamesInfoAdapter namesInfoAdapter;
    private DrawerNamesAdapter drawerNamesAdapter;
    private NamesAdapter namesAdapter;

    private MyDrawerLayout mDrawerLayout;
    private ListView mDrawerList;

    public AllahNamesInfoFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        arabicName = new String[]{

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
                "الصَّبُورُﷻ "

        };

        transcriptName = new String[]{

                "",
                "Ар-Рахман ",
                "Ар-Рахим ",
                "Аль-Малик ",
                "Аль-Куддус ",
                "Ас-Салям ",
                "Аль-Му'мин ",
                "Аль-Мухаймин ",
                "Аль-Азиз ",
                "Аль-Джаббар ",
                "Аль-Мутакяббир ",
                "Аль-Халик ",
                "Аль-Бари ",
                "Аль-Мусаууир ",
                "Аль-Гаффар ",
                "Аль-Каххар ",
                "Аль-Уаххаб ",
                "Ар-Раззак ",
                "Аль-Фаттах ",
                "Аль-'Алим ",
                "Аль-Кабид ",
                "Аль-Басит ",
                "Аль-Хафид ",
                "Ар-Рафи' ",
                "Аль-Му'изз ",
                "Аль-Музилль ",
                "Ас-Сами' ",
                "Аль-Басыр ",
                "Аль-Хакам ",
                "Аль-Адлю ",
                "Аль-Лятыф ",
                "Аль-Хабир ",
                "Аль-Халим ",
                "Аль-'Азым ",
                "Аль-Гафур ",
                "Аш-Шакур ",
                "Аль-'Алийй ",
                "Аль-Кябир ",
                "Аль-Хафиз ",
                "Аль-Мукыт ",
                "Аль-Хасиб ",
                "Аль-Джалиль ",
                "Аль-Кярим ",
                "Ар-Ракыб ",
                "Аль-Муджиб ",
                "Аль-Уаси' ",
                "Аль-Хаким ",
                "Аль-Уадуд ",
                "Аль-Маджид ",
                "Аль-Ба'ис ",
                "Аш-Шахид ",
                "Аль-Хакк ",
                "Аль-Уакиль ",
                "Аль-Каууийй ",
                "Аль-Матин ",
                "Аль-Уалийй ",
                "Аль-Хамид ",
                "Аль-Мухсы ",
                "Аль-Мубди ",
                "Аль-Му'ыд ",
                "Аль-Мухти ",
                "Аль-Мумит ",
                "Аль-Хайй ",
                "Аль-Каййум ",
                "Аль-Уааджид ",
                "Аль-Мааджид ",
                "Аль-Уахид ",
                "Ас-Самад ",
                "Аль-Кадир ",
                "Аль-Муктадир ",
                "Аль-Мукаддим ",
                "Аль-Мууаххыр",
                "Аль-Аууаль ",
                "Аль-Ахир ",
                "Аз-Захир ",
                "Аль-Батын ",
                "Аль-Уали ",
                "Аль-Мута'али ",
                "Аль-Барр ",
                "Ат-Таууаб ",
                "Аль-Мунтакым ",
                "Аль-'Афувв ",
                "Ар-Рауф ",
                "Маликуль Мульк ",
                "Зуль Джаляли уаль икрам ",
                "Аль-Муксит ",
                "Аль-Джами' ",
                "Аль-Ганийй ",
                "Аль-Мугни ",
                "Аль-Маани' ",
                "Аз-Зарр ",
                "Ан-Нафи' ",
                "Ан-Нур ",
                "Аль-Хади ",
                "Аль-Бади' ",
                "Аль-Бакы ",
                "Аль-Уарис ",
                "Ар-Рашид ",
                "Ас-Сабур "

        };

        translateName = new String[]{

                "Аллах",
                "Милостивый",
                "Милосердный ",
                "Властелин всего сущего ",
                "Святой ",
                "Миротворящий ",
                "Дарующий стабильность и веру ",
                "Хранитель ",
                "Могущественный ",
                "Подчиняющий, управляющий всеми ",
                "Превосходящий ",
                "Созидатель ",
                "Создатель ",
                "Придающий всему форму и облик ",
                "Скрывающий чужие грехи ",
                "Господствующий ",
                "Дарующий ",
                "Наделяющий ",
                "Раскрывающий ",
                "Всезнающий ",
                "Уменьшающий блага ",
                "Увеличивающий блага ",
                "Унижающий нечестивцев ",
                "Возвышающий уверовавших ",
                "Возвеличивающий ",
                "Ослабляющий ",
                "Всеслышащий ",
                "Всевидящий ",
                "Судья ",
                "Справедливый ",
                "Облегчающий ",
                "Сведущий ",
                "Кроткий ",
                "Величайший ",
                "Всепрощающий ",
                "Благодарный ",
                "Высочайший ",
                "Великий ",
                "Хранитель ",
                "Поддерживающий своих рабов ",
                "Учитывающий все ",
                "Величественный ",
                "Щедрый ",
                "Наблюдающий ",
                "Отвечающий ",
                "Всеобъемлющий ",
                "Мудрый ",
                "Любящий, Любимый ",
                "Славный ",
                "Воскрешающий ",
                "Свидетель ",
                "Истинный ",
                "Покровитель ",
                "Сильнейший ",
                "Непоколебимый ",
                "Друг ",
                "Достойный хвалы ",
                "Считающий ",
                "Основатель ",
                "Возвращающий все живое к смерти, а затем вновь к жизни ",
                "Оживляющий ",
                "Умертвляющий ",
                "Вечно живой ",
                "Не зависимый ни от кого и ни от чего ",
                "Богатый, имеющий все ",
                "Благородный ",
                "Единый ",
                "Самодостаточный ",
                "Всемогущий ",
                "Делающий все наилучшим образом ",
                "Продвигающий ",
                "Отодвигающий ",
                "Первый ",
                "Последний ",
                "Явный ",
                "Скрытый ",
                "Правящий ",
                "Высочайший ",
                "Благостный ",
                "Принимающий покаяние ",
                "Мстящий ",
                "Прощающий грешников ",
                "Мягкий ",
                "Царь царей ",
                "Обладатель величия и щедрости ",
                "Справедливый ",
                "Собирающий ",
                "Богатый, ни в чем не нуждающийся ",
                "Обогащающий ",
                "Удерживающий ",
                "Разрушитель ",
                "Благоволящий ",
                "Просветляющий ",
                "Ведущий верным путем ",
                "Изобретающий ",
                "Остающийся навечно ",
                "Наследник всего сущего ",
                "Направляющий на путь истины ",
                "Терпеливейший "

        };

        infoName = new String[]{
                getString(R.string.infoAllah),
                getString(R.string.infoRahman),
                getString(R.string.infoRahim),
                getString(R.string.infoMalik),
                getString(R.string.infoQuddus),
                getString(R.string.infoSalam),
                getString(R.string.infoMumin),
                getString(R.string.infoMuhaimin),
                getString(R.string.infoAziz),
                getString(R.string.infoJabbar),
                getString(R.string.infoMutakabbir),
                getString(R.string.infoKhalick),
                getString(R.string.infoBary),
                getString(R.string.infoMusawwir),
                getString(R.string.infoGaffar),
                getString(R.string.infoKahhar),
                getString(R.string.infoWahhab),
                getString(R.string.infoRazzak),
                getString(R.string.infoFattah),
                getString(R.string.infoAlim),
                getString(R.string.infoKabid),
                getString(R.string.infoBasit),
                getString(R.string.infoKhafid),
                getString(R.string.infoRafi),
                getString(R.string.infoMuizz),
                getString(R.string.infoMuzzil),
                getString(R.string.infoSami),
                getString(R.string.infoBasir),
                getString(R.string.infoHakam),
                getString(R.string.infoAdl),
                getString(R.string.infoLatif),
                getString(R.string.infoHabir),
                getString(R.string.infoHalim),
                getString(R.string.infoAzim),
                getString(R.string.infoGafur),
                getString(R.string.infoShakur),
                getString(R.string.infoAli),
                getString(R.string.infoKabir),
                getString(R.string.infoHafith),
                getString(R.string.infoMukit),
                getString(R.string.infoHasib),
                getString(R.string.infoJalil),
                getString(R.string.infoKarim),
                getString(R.string.infoRakib),
                getString(R.string.infoMujib),
                getString(R.string.infoWasi),
                getString(R.string.infoHakim),
                getString(R.string.infoWadud),
                getString(R.string.infoMajiid),
                getString(R.string.infoBaith),
                getString(R.string.infoShahid),
                getString(R.string.infoHackkk),
                getString(R.string.infoWakil),
                getString(R.string.infoKaui),
                getString(R.string.infoMatin),
                getString(R.string.infoWali),
                getString(R.string.infoHamid),
                getString(R.string.infoMuhsi),
                getString(R.string.infoMubdi),
                getString(R.string.infoMuid),
                getString(R.string.infoMuhyiy),
                getString(R.string.infoMumit),
                getString(R.string.infoHayy),
                getString(R.string.infoKayyum),
                getString(R.string.infoWajid),
                getString(R.string.infoMajid),
                getString(R.string.infoWahid),
                getString(R.string.infoSamad),
                getString(R.string.infoKadir),
                getString(R.string.infoMuktadir),
                getString(R.string.infoMukaddim),
                getString(R.string.infoMuwahhir),
                getString(R.string.infoAwwal),
                getString(R.string.infoAhir),
                getString(R.string.infoZahir),
                getString(R.string.infoBatin),
                getString(R.string.infoWaali),
                getString(R.string.infoMutaali),
                getString(R.string.infoBarr),
                getString(R.string.infoTawwab),
                getString(R.string.infoMuntakim),
                getString(R.string.infoAfuww),
                getString(R.string.infoRauf),
                getString(R.string.infoMalikAlMulk),
                getString(R.string.infoZulJalaliWalIkram),
                getString(R.string.infoMuksit),
                getString(R.string.intoJami),
                getString(R.string.infoGani),
                getString(R.string.infoMugni),
                getString(R.string.infoMani),
                getString(R.string.infoDarr),
                getString(R.string.infoNafi),
                getString(R.string.infoNuur),
                getString(R.string.infoHady),
                getString(R.string.infoBady),
                getString(R.string.infoBaki),
                getString(R.string.infoWarith),
                getString(R.string.infoRashid),
                getString(R.string.infoSabur),
        };

        ctx = new WeakReference<>(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAllahNamesInfoBinding.inflate(getLayoutInflater());

        namesAdapter = new NamesAdapter(requireActivity(), names);
        namesInfoAdapter = new NamesInfoAdapter(requireActivity(), namesInfo);
        binding.drawerListItem.setAdapter(namesInfoAdapter);
        binding.drawerListItem.setHasFixedSize(false);

        drawerNamesAdapter = new DrawerNamesAdapter(getContext(), namesDrawer, binding.drawerListItem);
        binding.nameDrawerInfo.setAdapter(drawerNamesAdapter);
        binding.nameDrawerInfo.setHasFixedSize(false);

        initName();
        init();
        initDrawer();

        binding.drawerListItem.addOnScrollListener(
                new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                        if (dy < 0 && !binding.fabNamesInfo.isShown())
                            binding.fabNamesInfo.show();
                        else if (dy > 0 && binding.fabNamesInfo.isShown())
                            binding.fabNamesInfo.hide();
                    }
                }
        );

        /*
        binding.fabNamesInfo.setOnClickListener(v -> {
            binding.drawerLayoutInfo.openDrawer(GravityCompat.START);
        });

         */

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initName();
        init();
    }


        public void init() {
        for (int i = 0; i < arabicName.length; ++i) {
            namesInfo.add(new NameInfo(arabicName[i], transcriptName[i],
                    translateName[i], infoName[i]));
        }
    }

    public void initName() {
        for(String n : arabicName){
            names.add(new Name(n));
        }
    }

    public void initDrawer() {
        for(String i : arabicName){
            namesDrawer.add(new DrawerNamesContent(i));
        }
    }
}