package com.educa62.simpleroom.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.educa62.simpleroom.R;
import com.educa62.simpleroom.adapter.MainAdapter;
import com.educa62.simpleroom.adapter.MainPlayerAdapter;
import com.educa62.simpleroom.db.Constant;
import com.educa62.simpleroom.db.FootballDatabase;
import com.educa62.simpleroom.entity.Player;
import com.educa62.simpleroom.entity.Teams;

import java.util.ArrayList;
import java.util.List;

public class MainPlayerActivity extends AppCompatActivity implements View.OnClickListener{

    private RecyclerView rvMain;
    private FootballDatabase footballDatabase;
    private int idTeam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_player);

        findViewById(R.id.fabMainPlayer).setOnClickListener(this);

        rvMain = findViewById(R.id.rvMainPlayer);

        footballDatabase = FootballDatabase.createDatabase(this);

        idTeam = getIntent().getIntExtra("id_team",0);
        Log.d("isi idteam",String.valueOf(idTeam));

    }

    @Override
    protected void onResume() {
        super.onResume();

        rvMain.setLayoutManager(new LinearLayoutManager(this));
        rvMain.setAdapter(new MainPlayerAdapter(this, footballDatabase.teamsDao().selectPlayer(idTeam)));
    }

    @Override
    public void onClick(View view) {
        startActivityForResult(new Intent(this, AddDetailPlayerActivity.class), 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {

            List<Player> playerList = new ArrayList<>();

            Player player = new Player();

            player.setId_team(data.getIntExtra(Constant.TAG_ID_TEAM,0));
            player.setName_(data.getStringExtra(Constant.TAG_NAME));
            player.setGender_(data.getStringExtra(Constant.TAG_GENDER));
            player.setCountry_(data.getStringExtra(Constant.TAG_COUNTRY));

            playerList.add(player);

            footballDatabase.teamsDao().insertPlayer(playerList);

        }
    }
}
