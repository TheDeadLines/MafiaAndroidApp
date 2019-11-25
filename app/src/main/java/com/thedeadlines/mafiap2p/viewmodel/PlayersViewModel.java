package com.thedeadlines.mafiap2p.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.thedeadlines.mafiap2p.App;
import com.thedeadlines.mafiap2p.data.PlayerRepository;
import com.thedeadlines.mafiap2p.data.db.player.PlayerEntity;

import java.util.List;

public class PlayersViewModel extends AndroidViewModel {
    private final MediatorLiveData<List<PlayerEntity>> mObservablePlayers;
    private final PlayerRepository mRepository;

    public PlayersViewModel(@NonNull Application application) {
        super(application);
        mObservablePlayers = new MediatorLiveData<>();
        mRepository = ((App) application).getPlayerRepository();
        LiveData<List<PlayerEntity>> players = mRepository.getPlayers();
        mObservablePlayers.addSource(players, mObservablePlayers::setValue);
    }

    public LiveData<List<PlayerEntity>> getPlayers() {
        return mObservablePlayers;
    }

    public void insert(PlayerEntity player) {
        mRepository.insert(player);
    }
}
