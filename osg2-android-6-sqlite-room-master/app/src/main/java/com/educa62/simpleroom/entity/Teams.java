package com.educa62.simpleroom.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "teams")
public class Teams {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id_;

    @ColumnInfo(name = "team")
    private String team_;

    @ColumnInfo(name = "year")
    private String year_;

    @ColumnInfo(name = "website")
    private String website_;

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

    public String getTeam_() {
        return team_;
    }

    public void setTeam_(String team_) {
        this.team_ = team_;
    }

    public String getYear_() {
        return year_;
    }

    public void setYear_(String year_) {
        this.year_ = year_;
    }

    public String getWebsite_() {
        return website_;
    }

    public void setWebsite_(String website_) {
        this.website_ = website_;
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
