package com.thedeadlines.mafiap2p.data.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.thedeadlines.mafiap2p.data.db.converters.DateConverter;
import com.thedeadlines.mafiap2p.data.db.game.GameEntity;

@Database(entities = {GameEntity.class}, version = 1)
@TypeConverters({DateConverter.class})
public abstract class AppDatabase extends RoomDatabase {

}
