package com.educa62.simpleroom.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "players")
public class Player {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id_;

    @ColumnInfo(name = "id_team")
    private int id_team;

    @ColumnInfo(name = "name")
    private String name_;

    @ColumnInfo(name = "gender")
    private String gender_;

    @ColumnInfo(name = "country")
    private String country_;

    public int getId_() {
        return id_;
    }

    public void setId_(int id_) {
        this.id_ = id_;
    }

    public int getId_team() {
        return id_team;
    }

    public void setId_team(int id_team) {
        this.id_team = id_team;
    }

    public String getName_() {
        return name_;
    }

    public void setName_(String name_) {
        this.name_ = name_;
    }

    public String getGender_() {
        return gender_;
    }

    public void setGender_(String gender_) {
        this.gender_ = gender_;
    }

    public String getCountry_() {
        return country_;
    }

    public void setCountry_(String country_) {
        this.country_ = country_;
    }

}
