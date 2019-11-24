package com.thedeadlines.mafiap2p.data.db.player;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.thedeadlines.mafiap2p.data.db.BaseDao;

import java.util.List;

@Dao
public interface PlayerDao extends BaseDao<PlayerEntity> {
    @Query("SELECT * FROM players")
    LiveData<List<PlayerEntity>> getAll();
}
