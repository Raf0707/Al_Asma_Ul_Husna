package com.example.alasmaulhusna.adapters.names;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.example.alasmaulhusna.R;
import com.example.alasmaulhusna.objects.names.names.Name;
import com.example.alasmaulhusna.ui.names.AllahNamesFragment;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class NamesAdapter extends RecyclerView.Adapter<NamesAdapter.ViewHolder> {

    public static final AllahNamesFragment MAIN = AllahNamesFragment.ctx.get();

    private LayoutInflater inflater;
    private List<Name> names;
    private TextView name;
    private RecyclerView recyclerView;

    public NamesAdapter(Context context, List<Name> names) {
        this.names = names;
        this.inflater = LayoutInflater.from(context);
    }

    public NamesAdapter(Context context, List<Name> names, RecyclerView recyclerView) {
        this.names = names;
        this.recyclerView = recyclerView;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem,
                parent, false);
        return new NamesAdapter.ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Name name = names.get(position);
        holder.nameView.setText(name.getName());
        holder.cardNameItem.setOnClickListener(v -> {
            ClipboardManager clipboard = (ClipboardManager)
                    v.getContext().getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("", name.getName());
            clipboard.setPrimaryClip(clip);

            Snackbar.make(v, "Имя " + name.getName()
                            + " скопировано в буфер обмена",
                    Snackbar.LENGTH_SHORT)
                    .show();
        });
    }

    @Override
    public int getItemCount() {
        return names.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final TextView nameView;
        final MaterialCardView cardNameItem;
        ViewHolder(View view){
            super(view);
            nameView = view.findViewById(R.id.arabicNameTextView);
            cardNameItem = view.findViewById(R.id.cardNameItem);
        }
    }
}