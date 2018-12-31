package com.educa62.simpleroom.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.educa62.simpleroom.db.Constant;
import com.educa62.simpleroom.db.FootballDatabase;
import com.educa62.simpleroom.adapter.MainAdapter;
import com.educa62.simpleroom.R;
import com.educa62.simpleroom.entity.Teams;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView rvMain;
    private FootballDatabase footballDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.fabMain).setOnClickListener(this);

        rvMain = findViewById(R.id.rvMain);

        footballDatabase = FootballDatabase.createDatabase(this);

    }

    @Override
    protected void onResume() {
        super.onResume();

        rvMain.setLayoutManager(new LinearLayoutManager(this));
        rvMain.setAdapter(new MainAdapter(this, footballDatabase.teamsDao().select()));

    }

    @Override
    public void onClick(View v) {
        startActivityForResult(new Intent(this, AddDetailActivity.class), 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {

            List<Teams> teamsList = new ArrayList<>();

            Teams teams = new Teams();

            teams.setTeam_(data.getStringExtra(Constant.TAG_TEAM));
            teams.setYear_(data.getStringExtra(Constant.TAG_YEAR));
            teams.setWebsite_(data.getStringExtra(Constant.TAG_WEBSITE));
            teams.setGender_(data.getStringExtra(Constant.TAG_GENDER));
            teams.setCountry_(data.getStringExtra(Constant.TAG_COUNTRY));

            teamsList.add(teams);

            footballDatabase.teamsDao().insert(teamsList);

        }
    }
}