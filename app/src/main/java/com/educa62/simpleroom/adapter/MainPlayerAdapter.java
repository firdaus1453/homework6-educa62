package com.educa62.simpleroom.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.educa62.simpleroom.R;
import com.educa62.simpleroom.db.Constant;
import com.educa62.simpleroom.entity.Player;
import com.educa62.simpleroom.ui.AddDetailActivity;
import com.educa62.simpleroom.ui.AddDetailPlayerActivity;

import java.util.List;

public class MainPlayerAdapter extends RecyclerView.Adapter<MainPlayerAdapter.MainHolder> {

    private final Context mainContext;
    private final List<Player> playersList;
    private Bundle bundle;

    public MainPlayerAdapter(Context mainContext, List<Player> playersList) {
        this.mainContext = mainContext;
        this.playersList = playersList;
        Log.d("isi idteam",String.valueOf(playersList));
    }

    @NonNull
    @Override
    public MainHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MainHolder(LayoutInflater.from(mainContext).inflate(R.layout.item_main, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final MainHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.tvItemMain.setText(playersList.get(position).getName_());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle = new Bundle();
                bundle.putInt(Constant.TAG_ID, playersList.get(position).getId_());
                bundle.putInt(Constant.TAG_ID_TEAM, playersList.get(position).getId_team());
                bundle.putString(Constant.TAG_NAME, playersList.get(position).getName_());
                bundle.putString(Constant.TAG_GENDER, playersList.get(position).getGender_());
                bundle.putString(Constant.TAG_COUNTRY, playersList.get(position).getCountry_());
                mainContext.startActivity(new Intent(mainContext, AddDetailPlayerActivity.class).putExtras(bundle));
            }
        });

        holder.overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(view.getContext(), view);
                popupMenu.inflate(R.menu.popup_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.edit:
                                bundle = new Bundle();
                                bundle.putInt(Constant.TAG_ID, playersList.get(position).getId_());
                                bundle.putInt(Constant.TAG_ID_TEAM, playersList.get(position).getId_team());
                                bundle.putString(Constant.TAG_NAME, playersList.get(position).getName_());
                                bundle.putString(Constant.TAG_GENDER, playersList.get(position).getGender_());
                                bundle.putString(Constant.TAG_COUNTRY, playersList.get(position).getCountry_());
                                mainContext.startActivity(new Intent(mainContext, AddDetailPlayerActivity.class).putExtras(bundle));
                                break;
                        }
                        return true;
                    }
                });
                popupMenu.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return playersList.size();
    }

    class MainHolder extends RecyclerView.ViewHolder {
        final TextView tvItemMain;
        private ImageButton overflow;

        MainHolder(View itemView) {
            super(itemView);
            tvItemMain = itemView.findViewById(R.id.tvItemMain);
            overflow = itemView.findViewById(R.id.overflow);
        }
    }
}
