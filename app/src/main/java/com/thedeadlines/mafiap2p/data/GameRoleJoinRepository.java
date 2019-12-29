package com.thedeadlines.mafiap2p.data;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.thedeadlines.mafiap2p.data.db.AppDatabase;
import com.thedeadlines.mafiap2p.data.db.gamerolejoin.GameRoleJoinDao;
import com.thedeadlines.mafiap2p.data.db.gamerolejoin.GameRoleJoinEntity;

import java.util.List;

public class GameRoleJoinRepository {
    private static GameRoleJoinRepository sInstance;
    private final AppDatabase mDatabase;
    private MediatorLiveData<List<GameRoleJoinEntity>> mObservableGameRoles;

    public static GameRoleJoinRepository getInstance(final AppDatabase database) {
        if (sInstance == null) {
            synchronized (GameRepository.class) {
                if (sInstance == null) {
                    sInstance = new GameRoleJoinRepository(database);
                }
            }
        }
        return sInstance;
    }

    private GameRoleJoinRepository(AppDatabase mDatabase) {
        this.mDatabase = mDatabase;

        mObservableGameRoles = new MediatorLiveData<>();
        //TODO: set observable data
//        mObservableGameRoles.addSource(mDatabase.gameRoleJoinDao().get, gameEntities -> {
//            if (mDatabase.getDatabaseCreated().getValue() != null) {
//                mObservableGameRoles.postValue(gameEntities);
//            }
//        });
    }

    public LiveData<List<GameRoleJoinEntity>> getGameRoles() {
        return mObservableGameRoles;
    }

    public void insert(GameRoleJoinEntity game) {
        new GameRoleJoinRepository.InsertAsyncTask(mDatabase.gameRoleJoinDao()).execute(game);
    }

    private static class InsertAsyncTask extends AsyncTask<GameRoleJoinEntity, Void, Void> {
        private GameRoleJoinDao gameDao;

        private InsertAsyncTask(GameRoleJoinDao gameDao) {
            this.gameDao = gameDao;
        }

        @Override
        protected Void doInBackground(GameRoleJoinEntity... gameEntities) {
            gameDao.insert(gameEntities[0]);
            return null;
        }
    }
}
