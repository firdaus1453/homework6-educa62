package com.educa62.simpleroom.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.educa62.simpleroom.R;
import com.educa62.simpleroom.db.Constant;
import com.educa62.simpleroom.db.FootballDatabase;
import com.educa62.simpleroom.entity.Teams;

public class AddDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private TextInputEditText tietTeam;
    private TextInputEditText tietYear;
    private TextInputEditText tietWebsite;
    private TextInputEditText tietGender;
    private TextInputEditText tietCountry;

    private Button bEdit;
    private Button bDelete;
    private Button bDone;
    private Button bAdd;

    private Bundle bundle;

    private Teams teams;

    private FootballDatabase footballDB;

    private Intent intent;

    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_detail);

        tietTeam = findViewById(R.id.tietTeam);
        tietYear = findViewById(R.id.tietYear);
        tietWebsite = findViewById(R.id.tietWebsite);
        tietGender = findViewById(R.id.tietGender);
        tietCountry = findViewById(R.id.tietCountry);

        bEdit = findViewById(R.id.bEdit);
        bDelete = findViewById(R.id.bDelete);
        bDone = findViewById(R.id.bDone);
        bAdd = findViewById(R.id.bAdd);

        bEdit.setOnClickListener(this);
        bDelete.setOnClickListener(this);
        bDone.setOnClickListener(this);
        bAdd.setOnClickListener(this);

        bundle = getIntent().getExtras();

        teams = new Teams();

        footballDB = FootballDatabase.createDatabase(this);

        intent = new Intent();

        if (bundle == null) {
            addActivity();
        } else {
            detailActivity();
        }
    }

    private void addActivity() {

        setTitle("Add New Data");

        bDelete.setVisibility(View.GONE);
        bEdit.setVisibility(View.GONE);
        bDone.setVisibility(View.GONE);
        bAdd.setVisibility(View.VISIBLE);
    }

    private void detailActivity() {

        setTitle("Detail Data");

        int id_ = bundle.getInt(Constant.TAG_ID);
        String sTeam = bundle.getString(Constant.TAG_TEAM);
        String sYear = bundle.getString(Constant.TAG_YEAR);
        String sWebsite = bundle.getString(Constant.TAG_WEBSITE);
        String sGender = bundle.getString(Constant.TAG_GENDER);
        String sCountry = bundle.getString(Constant.TAG_COUNTRY);

        bDelete.setVisibility(View.VISIBLE);
        bEdit.setVisibility(View.VISIBLE);
        bDone.setVisibility(View.GONE);
        bAdd.setVisibility(View.GONE);

        tietTeam.setEnabled(false);
        tietYear.setEnabled(false);
        tietWebsite.setEnabled(false);
        tietGender.setEnabled(false);
        tietCountry.setEnabled(false);

        tietTeam.setText(sTeam);
        tietYear.setText(sYear);
        tietWebsite.setText(sWebsite);
        tietGender.setText(sGender);
        tietCountry.setText(sCountry);

        teams.setId_(id_);
        teams.setTeam_(sTeam);
        teams.setYear_(sYear);
        teams.setWebsite_(sWebsite);
        teams.setGender_(sGender);
        teams.setCountry_(sCountry);
    }

    @Override
    public void onClick(View v) {

        String sTeamX = tietTeam.getText().toString();
        String sYearX = tietYear.getText().toString();
        String sWebsiteX = tietWebsite.getText().toString();
        String sGenderX = tietGender.getText().toString();
        String sCountryX = tietCountry.getText().toString();

        boolean empty = sTeamX.isEmpty() || sYearX.isEmpty() || sWebsiteX.isEmpty() || sGenderX.isEmpty() || sCountryX.isEmpty();

        switch (v.getId()) {
            case R.id.bEdit:

                setTitle("Edit");

                tietTeam.setEnabled(true);
                tietYear.setEnabled(true);
                tietWebsite.setEnabled(true);
                tietGender.setEnabled(true);
                tietCountry.setEnabled(true);

                bEdit.setVisibility(View.GONE);
                bDelete.setVisibility(View.GONE);
                bDone.setVisibility(View.VISIBLE);
                bAdd.setVisibility(View.GONE);

                break;
            case R.id.bDelete:
                alertDialog = new AlertDialog
                        .Builder(this)
                        .setCancelable(false)
                        .setMessage("Are you sure to delete " + sTeamX + " ?")
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {

                                Toast.makeText(getApplicationContext(), "done", Toast.LENGTH_SHORT).show();

                                footballDB.teamsDao().delete(teams);

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
            case R.id.bDone:
                if (!empty) {

                    teams.setTeam_(sTeamX);
                    teams.setYear_(sYearX);
                    teams.setWebsite_(sWebsiteX);
                    teams.setGender_(sGenderX);
                    teams.setCountry_(sCountryX);

                    footballDB.teamsDao().update(teams);

                    Toast.makeText(this, "done", Toast.LENGTH_SHORT).show();

                    finish();

                } else {

                    Toast.makeText(this, "cannot be empty", Toast.LENGTH_SHORT).show();

                }
                break;
            case R.id.bAdd:
                if (!empty) {

                    intent.putExtra(Constant.TAG_TEAM, sTeamX);
                    intent.putExtra(Constant.TAG_YEAR, sYearX);
                    intent.putExtra(Constant.TAG_WEBSITE, sWebsiteX);
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
}
