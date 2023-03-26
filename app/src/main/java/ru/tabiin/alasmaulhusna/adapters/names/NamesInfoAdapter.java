package ru.tabiin.alasmaulhusna.adapters.names;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import ru.tabiin.alasmaulhusna.objects.names.info_names.NameInfo;
import ru.tabiin.alasmaulhusna.R;
import ru.tabiin.alasmaulhusna.objects.names.info_names.NameInfo;

public class NamesInfoAdapter extends RecyclerView.Adapter<NamesInfoAdapter.ViewHolder> {
    public static final ru.tabiin.alalasmaulhusna.ui.names.AllahNamesInfoFragment CTX =
            ru.tabiin.alalasmaulhusna.ui.names.AllahNamesInfoFragment.ctx.get();

    private LayoutInflater inflater;
    private List<NameInfo> namesInfo;

    public NamesInfoAdapter(Context context, List<NameInfo> namesInfo) {
        this.namesInfo = namesInfo;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.names_info_item,
                parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        NameInfo nameInfo = namesInfo.get(position);
        holder.arabicName.setText(nameInfo.getArabicName());
        holder.transcriptName.setText(nameInfo.getTranscriptName());
        holder.translateName.setText(nameInfo.getTranslateName());
        holder.infoName.setText(nameInfo.getInfoName());

        if (namesInfo.get(position).getTranscriptName() == null ||
                namesInfo.get(position).getTranscriptName() == "") {
            holder.transcriptName.setVisibility(View.GONE);
        }

        holder.namesInfoCard.setOnClickListener(v -> {
            ClipboardManager clipboard = (ClipboardManager)
                    v.getContext().getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("", nameInfo.getArabicName() + "\n"
            + nameInfo.getTranscriptName() + "\n" + nameInfo.getTranslateName() + "\n"
            + nameInfo.getInfoName());
            clipboard.setPrimaryClip(clip);

            Snackbar.make(v, "Информация об имени " + nameInfo.getArabicName()
                            + " скопирована в буфер обмена",
                    Snackbar.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return namesInfo.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final MaterialCardView namesInfoCard;
        final TextView arabicName;
        final TextView transcriptName;
        final TextView translateName;
        final TextView infoName;
        ViewHolder(View view){
            super(view);
            namesInfoCard = view.findViewById(R.id.namesInfoCard);
            arabicName = view.findViewById(R.id.arabicName);
            transcriptName = view.findViewById(R.id.transcriptName);
            translateName = view.findViewById(R.id.translateName);
            infoName = view.findViewById(R.id.infoName);
        }
    }
}
