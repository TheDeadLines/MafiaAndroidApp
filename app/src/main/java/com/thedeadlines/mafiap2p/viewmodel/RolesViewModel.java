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

public class RolesViewModel extends AndroidViewModel {
    private final MediatorLiveData<List<RoleEntity>> mObservableRoles;
    private final RolesRepository mRepository;

    public RolesViewModel(@NonNull Application application) {
        super(application);
        mObservableRoles = new MediatorLiveData<>();
        mRepository = ((App) application).getRolesRepository();
        LiveData<List<RoleEntity>> roles = mRepository.getRoles();
        mObservableRoles.addSource(roles, mObservableRoles::setValue);
    }

    public LiveData<List<RoleEntity>> getRoles() {
        return mObservableRoles;
    }

    public void insert(RoleEntity roleEntity) {
        mRepository.insert(roleEntity);
    }
}
