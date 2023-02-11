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
import com.example.alasmaulhusna.objects.names.info_names.NameInfo;

import java.util.ArrayList;
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

        holder.nameView1.setText(format("%s", newName.getNameDrawer()));

        holder.nameView1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                recyclerView.scrollToPosition(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return namesDrawer.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView nameView1;
        ViewHolder(View view){
            super(view);
            nameView1 = view.findViewById(R.id.itemDrawerNames);
        }
    }

}