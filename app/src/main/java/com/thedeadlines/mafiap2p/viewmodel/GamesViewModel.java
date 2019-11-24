package com.thedeadlines.mafiap2p.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.thedeadlines.mafiap2p.App;
import com.thedeadlines.mafiap2p.data.GameRepository;
import com.thedeadlines.mafiap2p.data.db.game.GameEntity;

import java.util.List;

public class GamesViewModel extends AndroidViewModel {
    private final MediatorLiveData<List<GameEntity>> mObservableGames;
    private final GameRepository mRepository;

    public GamesViewModel(@NonNull Application application) {
        super(application);
        mObservableGames = new MediatorLiveData<>();
        mRepository = ((App) application).getGameRepository();
        LiveData<List<GameEntity>> games = mRepository.getGames();
        mObservableGames.addSource(games, mObservableGames::setValue);
    }

    public LiveData<List<GameEntity>> getGames() {
        return mObservableGames;
    }

    public void insert(GameEntity game) {
        mRepository.insert(game);
    }
}
