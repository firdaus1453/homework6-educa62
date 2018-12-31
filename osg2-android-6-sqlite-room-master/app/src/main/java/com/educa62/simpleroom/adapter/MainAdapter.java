package com.educa62.simpleroom.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.educa62.simpleroom.db.Constant;
import com.educa62.simpleroom.R;
import com.educa62.simpleroom.entity.Teams;
import com.educa62.simpleroom.ui.AddDetailActivity;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainHolder> {

    private final Context mainContext;
    private final List<Teams> teamsList;
    private Bundle bundle;

    public MainAdapter(Context mainContext, List<Teams> teamsList) {
        this.mainContext = mainContext;
        this.teamsList = teamsList;
    }

    @NonNull
    @Override
    public MainHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MainHolder(LayoutInflater.from(mainContext).inflate(R.layout.item_main, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final MainHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.tvItemMain.setText(teamsList.get(position).getTeam_());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle = new Bundle();
                bundle.putInt(Constant.TAG_ID, teamsList.get(position).getId_());
                bundle.putString(Constant.TAG_TEAM, teamsList.get(position).getTeam_());
                bundle.putString(Constant.TAG_YEAR, teamsList.get(position).getYear_());
                bundle.putString(Constant.TAG_WEBSITE, teamsList.get(position).getWebsite_());
                bundle.putString(Constant.TAG_GENDER, teamsList.get(position).getGender_());
                bundle.putString(Constant.TAG_COUNTRY, teamsList.get(position).getCountry_());
                mainContext.startActivity(new Intent(mainContext, AddDetailActivity.class).putExtras(bundle));
            }
        });
    }

    @Override
    public int getItemCount() {
        return teamsList.size();
    }

    class MainHolder extends RecyclerView.ViewHolder {
        final TextView tvItemMain;

        MainHolder(View itemView) {
            super(itemView);
            tvItemMain = itemView.findViewById(R.id.tvItemMain);
        }
    }
}
