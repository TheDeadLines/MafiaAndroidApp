package com.thedeadlines.mafiap2p.data;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.thedeadlines.mafiap2p.data.db.AppDatabase;
import com.thedeadlines.mafiap2p.data.db.game.GameEntity;
import com.thedeadlines.mafiap2p.data.db.role.RoleDao;
import com.thedeadlines.mafiap2p.data.db.role.RoleEntity;

import java.util.List;

public class RolesRepository {
    private static RolesRepository sInstance;
    private final AppDatabase mDatabase;
    private MediatorLiveData<List<RoleEntity>> mObservableRoles;


    public static RolesRepository getInstance(final AppDatabase database) {
        if (sInstance == null) {
            synchronized (PlayerRepository.class) {
                if (sInstance == null) {
                    sInstance = new RolesRepository(database);
                }
            }
        }
        return sInstance;
    }

    private RolesRepository(AppDatabase mAppDatabase) {
        this.mDatabase = mAppDatabase;
        mObservableRoles = new MediatorLiveData<>();

        mObservableRoles.addSource(mDatabase.roleDao().getAll(), roleEntities -> {
            if (mDatabase.getDatabaseCreated().getValue() != null) {
                mObservableRoles.postValue(roleEntities);
            }
        });
    }

    public LiveData<List<RoleEntity>> getRoles() {
        return mObservableRoles;
    }

    public void insert(RoleEntity role) {
        new RolesRepository.InsertAsyncTask(mDatabase.roleDao()).execute(role);
    }

    public void toggle(RoleEntity role) {
        role.checked = !role.checked;
        new UpdateAsyncTask(mDatabase.roleDao()).execute(role);

    }

    private boolean canUseMoreRoles(int gameId) {
        return unassignedAvailableCount(gameId) > 0;
    }

    private int unassignedAvailableCount(int gameId) {
        GameEntity currentGame = mDatabase.gameDao().getById(gameId).getValue();
        if (currentGame == null) {
            return 0;
        }

        int mafiaCount = currentGame.mafiaCount;
        int totalCount = currentGame.playersCount;
        LiveData<Integer> countRoles = mDatabase.roleDao().getCheckedRolesForGame();
        if (countRoles == null) {
            return totalCount - mafiaCount;
        }
        Integer usedRolesValue = countRoles.getValue();
        if (usedRolesValue == null) {
            return totalCount - mafiaCount;
        }
        int usedRolesCount = usedRolesValue;
        return totalCount - mafiaCount - usedRolesCount;
    }

    private static class InsertAsyncTask extends AsyncTask<RoleEntity, Void, Void> {
        private RoleDao roleDao;

        private InsertAsyncTask(RoleDao roleDao) {
            this.roleDao = roleDao;
        }

        @Override
        protected Void doInBackground(RoleEntity... roles) {
            roleDao.insert(roles[0]);
            return null;
        }
    }

    private static class UpdateAsyncTask extends AsyncTask<RoleEntity, Void, Void> {
        private RoleDao roleDao;

        private UpdateAsyncTask(RoleDao roleDao) {
            this.roleDao = roleDao;
        }

        @Override
        protected Void doInBackground(RoleEntity... roles) {
            roleDao.update(roles[0]);
            return null;
        }
    }
}
