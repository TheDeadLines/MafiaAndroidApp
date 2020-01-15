package com.thedeadlines.mafiap2p.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.thedeadlines.mafiap2p.App;
import com.thedeadlines.mafiap2p.AppConstants;
import com.thedeadlines.mafiap2p.R;
import com.thedeadlines.mafiap2p.data.GameRepository;
import com.thedeadlines.mafiap2p.data.RolesRepository;
import com.thedeadlines.mafiap2p.data.db.game.GameEntity;
import com.thedeadlines.mafiap2p.data.db.role.RoleEntity;
import com.thedeadlines.mafiap2p.ui.fragments.room.RoomRoles;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CreateRoomViewModel extends AndroidViewModel {
    private MediatorLiveData<Integer> mTotalPlayers;
    private MediatorLiveData<Integer> mMafiaPlayers;
    private MediatorLiveData<List<RoleEntity>> mObservableRoles;
    private RolesRepository mRepository;
    private GameRepository mGameRepository;

    public CreateRoomViewModel(@NonNull Application application) {
        super(application);
        mObservableRoles = new MediatorLiveData<>();
        mMafiaPlayers = new MediatorLiveData<>();
        mTotalPlayers = new MediatorLiveData<>();

        mRepository = ((App) application).getRolesRepository();
        mGameRepository = ((App) application).getGameRepository();
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

    public void insert(RoleEntity roleEntity) {
        mRepository.insert(roleEntity);
    }


    public void toggle(RoleEntity item) {
        mRepository.toggle(item);
    }

    public void update(List<RoleEntity> entities) {
        mRepository.update(entities);
    }

    public int createGame() {
        Integer totalPlayers = mTotalPlayers.getValue();
        Integer mafiaPlayers = mMafiaPlayers.getValue();
        if (totalPlayers != null && mafiaPlayers != null) {
            List<RoleEntity> checkedRoles = getCheckedRoles();
            RoomRoles.getInstance().getRoleDrawables().clear();
            RoomRoles.getInstance().getRoleNames().clear();
            for (int i = 0; i < mafiaPlayers; i++) {
                RoomRoles.getInstance().getRoleNames().add(getApplication().getApplicationContext().getString(R.string.mafia));
            }

            for (int i = 0; i < totalPlayers - mafiaPlayers - checkedRoles.size(); i++) {
                RoomRoles.getInstance().getRoleNames().add(getApplication().getApplicationContext().getString(R.string.citizen));
            }

            for (int i = 0; i < checkedRoles.size(); i++) {
                RoomRoles.getInstance().getRoleNames().add(checkedRoles.get(i).name);
            }

            GameEntity game = new GameEntity(AppConstants.MY_PLAYER_DATABASE_ID,
                    new Date(), mafiaPlayers, totalPlayers);
            mGameRepository.insert(game);
            return game.uid;
        }
        return -1;
    }
}
