package com.educa62.simpleroom.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.educa62.simpleroom.entity.Player;
import com.educa62.simpleroom.entity.Teams;

import java.util.List;

@Dao
public interface TeamsDao {

    @Insert
    void insert(List<Teams> teamsList);

    @Query("SELECT * FROM teams ORDER BY team ASC")
    List<Teams> select();

    @Query("SELECT * FROM teams WHERE id = :id_team ORDER BY team ASC")
    List<Teams> selectTeam(int id_team);

    @Delete
    void delete(Teams teams);

    @Update
    void update(Teams teams);

    // Dao player
    @Insert
    void insertPlayer(List<Player> playerList);

    @Query("SELECT * FROM players WHERE id_team = :id_team  ORDER BY name ASC")
    List<Player> selectPlayer(int id_team);

    @Delete
    void deletePlayer(Player player);

    @Update
    void updatePlayer(Player player);

}
