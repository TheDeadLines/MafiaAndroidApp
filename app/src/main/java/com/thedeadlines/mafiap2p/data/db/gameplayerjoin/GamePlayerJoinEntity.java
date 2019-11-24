package com.thedeadlines.mafiap2p.data.db.gameplayerjoin;

import androidx.room.Entity;
import androidx.room.ForeignKey;

import com.thedeadlines.mafiap2p.data.db.game.GameEntity;
import com.thedeadlines.mafiap2p.data.db.player.PlayerEntity;

@Entity(tableName = "game_player_join",
        primaryKeys = {"gameId", "playerId"},
        foreignKeys = {
                @ForeignKey(entity = GameEntity.class,
                        parentColumns = "uid",
                        childColumns = "gameId"),
                @ForeignKey(entity = PlayerEntity.class,
                        parentColumns = "uid",
                        childColumns = "playerId")
        })
public class GamePlayerJoinEntity {
    public final int gameId;
    public final int playerId;

    public GamePlayerJoinEntity(int gameId, int playerId) {
        this.gameId = gameId;
        this.playerId = playerId;
    }
}
