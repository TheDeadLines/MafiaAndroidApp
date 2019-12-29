package com.thedeadlines.mafiap2p.data.db.gamerolejoin;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.thedeadlines.mafiap2p.data.db.BaseDao;
import com.thedeadlines.mafiap2p.data.db.gameplayerjoin.GamePlayerJoinEntity;
import com.thedeadlines.mafiap2p.data.db.role.RoleEntity;

import java.util.List;

@Dao
public interface GameRoleJoinDao extends BaseDao<GameRoleJoinEntity> {
    @Query("SELECT * FROM roles INNER JOIN game_role_join ON" +
            " roles.uid = game_role_join.roleId WHERE game_role_join.gameId = :gameId")
    LiveData<List<RoleEntity>> getRolesForGame(final int gameId);
}
