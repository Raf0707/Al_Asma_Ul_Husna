package ru.tabiin.alasmaulhusna.domain.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import ru.tabiin.alasmaulhusna.domain.dao.CounterDao;
import ru.tabiin.alasmaulhusna.domain.models.CounterItem;

@Database(entities = {CounterItem.class}, version = 1)
public abstract class CounterDatabase extends RoomDatabase {
    public abstract CounterDao counterDao();
    private static CounterDatabase INSTANCE;
    public static synchronized CounterDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            CounterDatabase.class, "counter_database")
                    .allowMainThreadQueries().build(); //.fallbackToDestructiveMigration()
        }
        return INSTANCE;
    }
}

