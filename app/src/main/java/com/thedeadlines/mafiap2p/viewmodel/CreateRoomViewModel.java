package com.thedeadlines.mafiap2p.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.thedeadlines.mafiap2p.App;
import com.thedeadlines.mafiap2p.data.RolesRepository;
import com.thedeadlines.mafiap2p.data.db.role.RoleEntity;

import java.util.List;

public class CreateRoomViewModel extends AndroidViewModel {
    private LiveData<Integer> mTotalPlayers;
    private LiveData<Integer> mMafiaPlayers;
    private MediatorLiveData<List<RoleEntity>> mObservableRoles;
    private RolesRepository mRepository;

    public CreateRoomViewModel(@NonNull Application application) {
        super(application);
        mObservableRoles = new MediatorLiveData<>();
        mRepository = ((App) application).getRolesRepository();
        LiveData<List<RoleEntity>> roles = mRepository.getRoles();
        mObservableRoles.addSource(roles, mObservableRoles::setValue);
    }

    public LiveData<Integer> getTotalPlayers() {
        return mTotalPlayers;
    }

    public LiveData<Integer> getMafiaPlayers() {
        return mMafiaPlayers;
    }

    public LiveData<List<RoleEntity>> getRoles() {
        return mObservableRoles;
    }

    public void insert(RoleEntity roleEntity) {
        mRepository.insert(roleEntity);
    }


    public void toggle(RoleEntity item) {
        mRepository.toggle(item);
    }
}
