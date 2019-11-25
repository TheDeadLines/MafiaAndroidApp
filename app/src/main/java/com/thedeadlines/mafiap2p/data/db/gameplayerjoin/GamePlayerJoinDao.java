package com.thedeadlines.mafiap2p.data.db.gameplayerjoin;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.thedeadlines.mafiap2p.data.db.BaseDao;
import com.thedeadlines.mafiap2p.data.db.game.GameEntity;
import com.thedeadlines.mafiap2p.data.db.player.PlayerEntity;

import java.util.List;

@Dao
public interface GamePlayerJoinDao extends BaseDao<GamePlayerJoinEntity> {
    @Query("SELECT * FROM players INNER JOIN game_player_join ON" +
            " players.uid = game_player_join.playerId WHERE game_player_join.gameId = :gameId")
    LiveData<List<PlayerEntity>> getPlayersForGame(final int gameId);

    @Query("SELECT * FROM games INNER JOIN game_player_join ON" +
            " games.uid = game_player_join.gameId WHERE game_player_join.gameId = :playerId")
    LiveData<List<GameEntity>> getGamesForPlayer(final int playerId);
}
