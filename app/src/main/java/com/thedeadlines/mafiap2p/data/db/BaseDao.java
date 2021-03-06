package com.thedeadlines.mafiap2p.data.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Update;

import java.util.List;

@Dao
public interface BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert(T object);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long[] insert(List<T> collection);

    @Update
    void update(T object);

    @Update
    void update(T... collection);


    @Delete
    void delete(T object);

    @Delete
    void delete(T... collection);
}

