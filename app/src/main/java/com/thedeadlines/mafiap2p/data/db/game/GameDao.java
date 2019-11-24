package com.thedeadlines.mafiap2p.data.db.game;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.thedeadlines.mafiap2p.data.db.BaseDao;

import java.util.List;

@Dao
public interface GameDao extends BaseDao<GameEntity> {
    @Query("SELECT * FROM games")
    LiveData<List<GameEntity>> getAll();
}
