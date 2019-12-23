package com.thedeadlines.mafiap2p.data.db.gamerolejoin;

import androidx.room.Entity;
import androidx.room.ForeignKey;

import com.thedeadlines.mafiap2p.data.db.game.GameEntity;
import com.thedeadlines.mafiap2p.data.db.role.RoleEntity;

@Entity(tableName = "game_role_join",
        primaryKeys = {"gameId", "roleId"},
        foreignKeys = {
                @ForeignKey(entity = GameEntity.class,
                        parentColumns = "uid",
                        childColumns = "gameId"),
                @ForeignKey(entity = RoleEntity.class,
                        parentColumns = "uid",
                        childColumns = "roleId")
        })
public class GameRoleJoinEntity {
    public final int gameId;
    public final int roleId;

    public GameRoleJoinEntity(int gameId, int roleId) {
        this.gameId = gameId;
        this.roleId = roleId;
    }
}
