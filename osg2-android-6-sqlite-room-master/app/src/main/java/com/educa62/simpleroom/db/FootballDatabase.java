package com.educa62.simpleroom.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.educa62.simpleroom.entity.Teams;

@Database(entities = Teams.class, version = 1)
public abstract class FootballDatabase extends RoomDatabase {

    public abstract TeamsDao teamsDao();

    private static FootballDatabase INSTANCE;

    public static FootballDatabase createDatabase(Context context) {
        synchronized (FootballDatabase.class) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context, FootballDatabase.class, "db_football").allowMainThreadQueries().build();
            }
        }
        return INSTANCE;
    }
}
