package com.thedeadlines.mafiap2p.data;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.thedeadlines.mafiap2p.data.db.AppDatabase;
import com.thedeadlines.mafiap2p.data.db.gameplayerjoin.GamePlayerJoinDao;
import com.thedeadlines.mafiap2p.data.db.gameplayerjoin.GamePlayerJoinEntity;

import java.util.List;

public class GamePlayerJoinRepository {
    private static GamePlayerJoinRepository sInstance;
    private final AppDatabase mDatabase;
    private MediatorLiveData<List<GamePlayerJoinEntity>> mObservableGamePlayers;

    public static GamePlayerJoinRepository getInstance(final AppDatabase database) {
        if (sInstance == null) {
            synchronized (GameRepository.class) {
                if (sInstance == null) {
                    sInstance = new GamePlayerJoinRepository(database);
                }
            }
        }
        return sInstance;
    }

    private GamePlayerJoinRepository(AppDatabase mDatabase) {
        this.mDatabase = mDatabase;

        mObservableGamePlayers = new MediatorLiveData<>();
        //TODO: set observable data
//        mObservableGamePlayers.addSource(mDatabase.gamePlayerJoinDao().get, gameEntities -> {
//            if (mDatabase.getDatabaseCreated().getValue() != null) {
//                mObservableGamePlayers.postValue(gameEntities);
//            }
//        });
    }

    public LiveData<List<GamePlayerJoinEntity>> getGamePlayers() {
        return mObservableGamePlayers;
    }

    public void insert(GamePlayerJoinEntity game) {
        new GamePlayerJoinRepository.InsertAsyncTask(mDatabase.gamePlayerJoinDao()).execute(game);
    }

    private static class InsertAsyncTask extends AsyncTask<GamePlayerJoinEntity, Void, Void> {
        private GamePlayerJoinDao gameDao;

        private InsertAsyncTask(GamePlayerJoinDao gameDao) {
            this.gameDao = gameDao;
        }

        @Override
        protected Void doInBackground(GamePlayerJoinEntity... gameEntities) {
            gameDao.insert(gameEntities[0]);
            return null;
        }
    }
}
