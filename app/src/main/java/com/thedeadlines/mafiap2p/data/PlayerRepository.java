package com.thedeadlines.mafiap2p.data;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.thedeadlines.mafiap2p.data.db.AppDatabase;
import com.thedeadlines.mafiap2p.data.db.player.PlayerDao;
import com.thedeadlines.mafiap2p.data.db.player.PlayerEntity;

import java.util.List;

public class PlayerRepository {
    private static PlayerRepository sInstance;
    private final AppDatabase mDatabase;
    private MediatorLiveData<List<PlayerEntity>> mObservablePlayers;


    public static PlayerRepository getInstance(final AppDatabase database) {
        if (sInstance == null) {
            synchronized (PlayerRepository.class) {
                if (sInstance == null) {
                    sInstance = new PlayerRepository(database);
                }
            }
        }
        return sInstance;
    }

    private PlayerRepository(AppDatabase mAppDatabase) {
        this.mDatabase = mAppDatabase;
        mObservablePlayers = new MediatorLiveData<>();
        mObservablePlayers.addSource(mDatabase.playerDao().getAll(), playerEntities -> {
            if (mDatabase.getDatabaseCreated().getValue() != null) {
                mObservablePlayers.postValue(playerEntities);
            }
        });
    }

    public LiveData<List<PlayerEntity>> getPlayers() {
        return mObservablePlayers;
    }

    public void insert(PlayerEntity player) {
        new InsertAsyncTask(mDatabase.playerDao()).execute(player);
    }

    private static class InsertAsyncTask extends AsyncTask<PlayerEntity, Void, Void> {
        private PlayerDao playerDao;

        private InsertAsyncTask(PlayerDao playerDao) {
            this.playerDao = playerDao;
        }

        @Override
        protected Void doInBackground(PlayerEntity... players) {
            playerDao.insert(players[0]);
            return null;
        }
    }
}
