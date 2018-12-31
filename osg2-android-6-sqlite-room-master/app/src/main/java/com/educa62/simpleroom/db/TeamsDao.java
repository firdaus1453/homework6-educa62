package com.educa62.simpleroom.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.educa62.simpleroom.entity.Teams;

import java.util.List;

@Dao
public interface TeamsDao {

    @Insert
    void insert(List<Teams> teamsList);

    @Query("SELECT * FROM teams ORDER BY team ASC")
    List<Teams> select();

    @Delete
    void delete(Teams teams);

    @Update
    void update(Teams teams);
}
