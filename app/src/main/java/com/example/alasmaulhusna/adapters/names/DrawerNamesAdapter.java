package com.example.alasmaulhusna.adapters.names;

import static java.lang.String.format;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.alasmaulhusna.R;
import com.example.alasmaulhusna.objects.names.drawer_names.DrawerNamesContent;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class DrawerNamesAdapter extends RecyclerView.Adapter<DrawerNamesAdapter.ViewHolder> {
    private LayoutInflater inflater;
    private List<DrawerNamesContent> namesDrawer;
    private RecyclerView recyclerView;

    public DrawerNamesAdapter(Context context, List<DrawerNamesContent> namesDrawer) {
        this.namesDrawer = namesDrawer;
        this.inflater = LayoutInflater.from(context);
    }

    public DrawerNamesAdapter(Context context, List<DrawerNamesContent> namesDrawer,
                              RecyclerView recyclerView) {
        this.namesDrawer = namesDrawer;
        this.recyclerView = recyclerView;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.drawer_names_item,
                parent, false);
        return new DrawerNamesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        DrawerNamesContent newName = namesDrawer.get(position);

        holder.itemDrawerName.setText(format("%s", newName.getNameDrawer()));

        holder.cardDrawerNameItem.setOnClickListener(view ->
                recyclerView.scrollToPosition(holder.getAdapterPosition()));
    }

    @Override
    public int getItemCount() {
        return namesDrawer.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView itemDrawerName;
        final MaterialCardView cardDrawerNameItem;
        ViewHolder(View view){
            super(view);
            itemDrawerName = view.findViewById(R.id.itemDrawerNames);
            cardDrawerNameItem = view.findViewById(R.id.cardDrawerNameItem);
        }
    }

}