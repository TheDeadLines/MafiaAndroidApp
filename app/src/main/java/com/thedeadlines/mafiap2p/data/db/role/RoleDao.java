package com.thedeadlines.mafiap2p.data.db.role;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.thedeadlines.mafiap2p.data.db.BaseDao;

import java.util.List;

@Dao
public interface RoleDao extends BaseDao<RoleEntity> {
    @Query("SELECT * FROM roles")
    LiveData<List<RoleEntity>> getAll();
}
