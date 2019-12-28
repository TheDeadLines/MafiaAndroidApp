package com.thedeadlines.mafiap2p.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.thedeadlines.mafiap2p.App;
import com.thedeadlines.mafiap2p.data.RolesRepository;
import com.thedeadlines.mafiap2p.data.db.role.RoleEntity;

import java.util.ArrayList;
import java.util.List;

public class GameRoleJoinViewModel extends AndroidViewModel {
    private MediatorLiveData<Integer> mTotalPlayers;
    private MediatorLiveData<Integer> mMafiaPlayers;
    private MediatorLiveData<List<RoleEntity>> mObservableRoles;
    private RolesRepository mRepository;

    public GameRoleJoinViewModel(@NonNull Application application) {
        super(application);
        mObservableRoles = new MediatorLiveData<>();
        mMafiaPlayers = new MediatorLiveData<>();
        mTotalPlayers = new MediatorLiveData<>();

        mRepository = ((App) application).getRolesRepository();
        LiveData<List<RoleEntity>> roles = mRepository.getRoles();
        mObservableRoles.addSource(roles, mObservableRoles::setValue);

    }

    public LiveData<Integer> getPlayersCount() {
        return mTotalPlayers;
    }

    public LiveData<Integer> getMafiasCount() {
        return mMafiaPlayers;
    }

    public LiveData<List<RoleEntity>> getRoles() {
        return mObservableRoles;
    }

    public List<RoleEntity> getCheckedRoles() {
        List<RoleEntity> all = getRoles().getValue();
        List<RoleEntity> checked = new ArrayList<>();
        if (all != null)
            for (RoleEntity entity : all) {
                if (entity.checked)
                    checked.add(entity);
            }
        return checked;
    }

    public List<RoleEntity> getRolesByGameId(int gameId) {
//        TODO
//        getRolesForGame()

        return new ArrayList<>();
    }

    public void insert(RoleEntity roleEntity) {
        mRepository.insert(roleEntity);
    }

    public void toggle(RoleEntity item) {
        mRepository.toggle(item);
    }

    public void update(List<RoleEntity> entities) {
        mRepository.update(entities);
    }
}
