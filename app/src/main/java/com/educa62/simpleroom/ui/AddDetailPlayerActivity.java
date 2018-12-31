package com.educa62.simpleroom.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.educa62.simpleroom.R;
import com.educa62.simpleroom.db.Constant;
import com.educa62.simpleroom.db.FootballDatabase;
import com.educa62.simpleroom.entity.Player;
import com.educa62.simpleroom.entity.Teams;

import java.util.ArrayList;
import java.util.List;

public class AddDetailPlayerActivity extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemSelectedListener {

    private TextInputEditText tietNamePlayer;
    private TextInputEditText tietTeamPlayer;
    private TextInputEditText tietGender;
    private TextInputEditText tietCountry;

    private Button bEdit;
    private Button bDelete;
    private Button bDone;
    private Button bAdd;
    private Spinner spinnerTeam;

    private Bundle bundle;

    private Player player;
    private List<Teams> teamList;
    private String[] nameTeamList;
    private int[] idTeamList;

    private FootballDatabase footballDB;

    private Intent intent;

    private AlertDialog alertDialog;

    private int iTeam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_detail_player);

        tietNamePlayer = findViewById(R.id.tietNamePlayer);
//        tietTeamPlayer = findViewById(R.id.tietTeamPlayer);
        tietGender = findViewById(R.id.tietGenderPlayer);
        tietCountry = findViewById(R.id.tietCountryPlayer);

        bEdit = findViewById(R.id.bEditPlayer);
        bDelete = findViewById(R.id.bDeletePlayer);
        bDone = findViewById(R.id.bDonePlayer);
        bAdd = findViewById(R.id.bAddPlayer);
        spinnerTeam = findViewById(R.id.spinner);

        bEdit.setOnClickListener(this);
        bDelete.setOnClickListener(this);
        bDone.setOnClickListener(this);
        bAdd.setOnClickListener(this);
        spinnerTeam.setOnItemSelectedListener(this);

        bundle = getIntent().getExtras();

        player = new Player();

        footballDB = FootballDatabase.createDatabase(this);

        intent = new Intent();

        getDataTeam();

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,nameTeamList);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerTeam.setAdapter(dataAdapter);


        if (bundle == null) {
            addActivity();
        } else {
            detailActivity();
        }
    }

    private void getDataTeam() {
        teamList = footballDB.teamsDao().select();
        nameTeamList = new String[teamList.size()];
        idTeamList = new int[teamList.size()];
        for (int i = 0; i < teamList.size(); i++){
            nameTeamList[i] = teamList.get(i).getTeam_();
            idTeamList[i] = teamList.get(i).getId_();
        }
    }

    private void detailActivity() {
        setTitle("Detail Data Player");

        int id_ = bundle.getInt(Constant.TAG_ID);
        String sNamePlayer = bundle.getString(Constant.TAG_NAME);
        String sGender = bundle.getString(Constant.TAG_GENDER);
        String sCountry = bundle.getString(Constant.TAG_COUNTRY);

        String sTeam = teamList.get(0).getTeam_();

        bDelete.setVisibility(View.VISIBLE);
        bEdit.setVisibility(View.VISIBLE);
        bDone.setVisibility(View.GONE);
        bAdd.setVisibility(View.GONE);

//        tietTeamPlayer.setEnabled(false);
        tietNamePlayer.setEnabled(false);
        tietGender.setEnabled(false);
        tietCountry.setEnabled(false);

//        tietTeamPlayer.setText(sTeam);
        tietNamePlayer.setText(sNamePlayer);
        tietGender.setText(sGender);
        tietCountry.setText(sCountry);

        player.setId_(id_);
        player.setId_team(iTeam);
        player.setName_(sNamePlayer);
        player.setGender_(sGender);
        player.setCountry_(sCountry);
    }

    private void addActivity() {
        setTitle("Add New Data");

        bDelete.setVisibility(View.GONE);
        bEdit.setVisibility(View.GONE);
        bDone.setVisibility(View.GONE);
        bAdd.setVisibility(View.VISIBLE);
    }



    @Override
    public void onClick(View view) {

//        String sTeamX = tietTeamPlayer.getText().toString();
        String sNamePlayerX = tietNamePlayer.getText().toString();
        String sGenderX = tietGender.getText().toString();
        String sCountryX = tietCountry.getText().toString();

        boolean empty = sNamePlayerX.isEmpty() || sGenderX.isEmpty() || sCountryX.isEmpty();

        switch (view.getId()) {
            case R.id.bEditPlayer:

//                tietTeamPlayer.setEnabled(true);
                tietNamePlayer.setEnabled(true);
                tietGender.setEnabled(true);
                tietCountry.setEnabled(true);

                bEdit.setVisibility(View.GONE);
                bDelete.setVisibility(View.GONE);
                bDone.setVisibility(View.VISIBLE);
                bAdd.setVisibility(View.GONE);
                break;
            case R.id.bDeletePlayer:
                alertDialog = new AlertDialog
                        .Builder(this)
                        .setCancelable(false)
                        .setMessage("Are you sure to delete " + sNamePlayerX + " ?")
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {

                                Toast.makeText(getApplicationContext(), "done", Toast.LENGTH_SHORT).show();

                                footballDB.teamsDao().deletePlayer(player);

                                finish();

                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                alertDialog.cancel();
                            }
                        })
                        .show();
                break;
            case R.id.bDonePlayer:
                if (!empty) {

                    player.setName_(sNamePlayerX);
                    player.setId_team(iTeam);
                    player.setGender_(sGenderX);
                    player.setCountry_(sCountryX);

                    footballDB.teamsDao().updatePlayer(player);

                    Toast.makeText(this, "done", Toast.LENGTH_SHORT).show();

                    finish();

                } else {

                    Toast.makeText(this, "cannot be empty", Toast.LENGTH_SHORT).show();

                }
                break;

            case R.id.bAddPlayer:
                if (!empty) {

                    intent.putExtra(Constant.TAG_ID_TEAM, iTeam);
                    intent.putExtra(Constant.TAG_NAME, sNamePlayerX);
                    intent.putExtra(Constant.TAG_GENDER, sGenderX);
                    intent.putExtra(Constant.TAG_COUNTRY, sCountryX);

                    Toast.makeText(this, "done", Toast.LENGTH_SHORT).show();

                    setResult(RESULT_OK, intent);

                    finish();

                } else {

                    Toast.makeText(this, "cannot be empty", Toast.LENGTH_SHORT).show();

                    setResult(RESULT_CANCELED, intent);

                }
                break;
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        int item = adapterView.getSelectedItemPosition();
        iTeam = idTeamList[item];
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
