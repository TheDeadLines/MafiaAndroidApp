package com.thedeadlines.mafiap2p.data;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.thedeadlines.mafiap2p.data.db.AppDatabase;
import com.thedeadlines.mafiap2p.data.db.game.GameDao;
import com.thedeadlines.mafiap2p.data.db.game.GameEntity;

import java.util.List;

public class GameRepository {
    private static GameRepository sInstance;
    private final AppDatabase mDatabase;
    private MediatorLiveData<List<GameEntity>> mObservableGames;

    public static GameRepository getInstance(final AppDatabase database) {
        if (sInstance == null) {
            synchronized (GameRepository.class) {
                if (sInstance == null) {
                    sInstance = new GameRepository(database);
                }
            }
        }
        return sInstance;
    }

    private GameRepository(AppDatabase mDatabase) {
        this.mDatabase = mDatabase;

        mObservableGames = new MediatorLiveData<>();
        mObservableGames.addSource(mDatabase.gameDao().getAll(), gameEntities -> {
            if (mDatabase.getDatabaseCreated().getValue() != null) {
                mObservableGames.postValue(gameEntities);
            }
        });
    }

    public LiveData<List<GameEntity>> getGames() {
        return mObservableGames;
    }

    public void insert(GameEntity game) {
        new GameRepository.InsertAsyncTask(mDatabase.gameDao()).execute(game);
    }

    private static class InsertAsyncTask extends AsyncTask<GameEntity, Void, Void> {
        private GameDao gameDao;

        private InsertAsyncTask(GameDao gameDao) {
            this.gameDao = gameDao;
        }

        @Override
        protected Void doInBackground(GameEntity... gameEntities) {
            gameDao.insert(gameEntities[0]);
            return null;
        }
    }
}
