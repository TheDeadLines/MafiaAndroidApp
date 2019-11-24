package com.thedeadlines.mafiap2p.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.thedeadlines.mafiap2p.App;
import com.thedeadlines.mafiap2p.data.GamePlayerJoinRepository;
import com.thedeadlines.mafiap2p.data.db.gameplayerjoin.GamePlayerJoinEntity;

import java.util.List;

public class GamePlayerJoinViewModel extends AndroidViewModel {
    private final MediatorLiveData<List<GamePlayerJoinEntity>> mObservableGamePlayers;
    private final GamePlayerJoinRepository mRepository;

    public GamePlayerJoinViewModel(@NonNull Application application) {
        super(application);
        mObservableGamePlayers = new MediatorLiveData<>();
        mRepository = ((App) application).getGamePlayerJoinRepository();
        LiveData<List<GamePlayerJoinEntity>> gamePlayers = mRepository.getGamePlayers();
        mObservableGamePlayers.addSource(gamePlayers, mObservableGamePlayers::setValue);
    }

    public LiveData<List<GamePlayerJoinEntity>> getGamePlayers() {
        return mObservableGamePlayers;
    }

    public void insert(GamePlayerJoinEntity entity) {
        mRepository.insert(entity);
    }
}
