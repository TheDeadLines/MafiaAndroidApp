package com.thedeadlines.mafiap2p.data.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Update;

@Dao
public interface BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(T object);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insert(T... collection);

    @Update
    void update(T object);

    @Update
    void update(T... collection);


    @Delete
    void delete(T object);

    @Delete
    void delete(T... collection);
}

