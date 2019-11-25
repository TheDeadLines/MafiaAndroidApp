package com.thedeadlines.mafiap2p.ui.fragments.room;


import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.thedeadlines.mafiap2p.R;
import com.thedeadlines.mafiap2p.data.db.role.RoleEntity;
import com.thedeadlines.mafiap2p.game.GameConstants;
import com.thedeadlines.mafiap2p.ui.fragments.room.createroom.OnItemClickListener;
import com.thedeadlines.mafiap2p.ui.fragments.room.createroom.RolesAdapter;
import com.thedeadlines.mafiap2p.viewmodel.RolesViewModel;

public class CreateRoomFragment extends Fragment {
    public static final String TAG = CreateRoomFragment.class.getSimpleName();
    private static final String ROLES_LIST_STATE = "roles-list-state";

    private Button mCreateRoomButton;
    private Button mBackButton;
    private RecyclerView mRolesListView;
    private NumberPicker mPlayersNumberPicker;
    private NumberPicker mMafiaNumberPicker;

    private RolesViewModel mRolesViewModel;
    private RolesAdapter mRolesAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Parcelable mListState;

    public CreateRoomFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRolesViewModel = ViewModelProviders.of(this).get(RolesViewModel.class);
        mRolesViewModel.getRoles().observe(this, roleEntities -> {
            if (roleEntities != null) {
                mRolesAdapter.setList(roleEntities);
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_room, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mCreateRoomButton = view.findViewById(R.id.start_room_button);
        mBackButton = view.findViewById(R.id.create_room_back_button);
        mRolesListView = view.findViewById(R.id.roles_switches);
        mPlayersNumberPicker = view.findViewById(R.id.player_number_picker);
        mMafiaNumberPicker = view.findViewById(R.id.mafia_number_picker);

        OnItemClickListener<RoleEntity> clickListener = item -> {
            // TODO: process click on switch toggle
//            Toast.makeText(getContext(), "Clicked: " + item.name, Toast.LENGTH_SHORT).show();
        };
        mLayoutManager = new LinearLayoutManager(getContext());
        if (savedInstanceState != null) {
            mListState = savedInstanceState.getParcelable(ROLES_LIST_STATE);
            mLayoutManager.onRestoreInstanceState(mListState);
        }

        mRolesAdapter = new RolesAdapter(mRolesViewModel.getRoles().getValue(), clickListener);

        mRolesListView.setLayoutManager(mLayoutManager);
        mRolesListView.setAdapter(mRolesAdapter);

        mCreateRoomButton.setOnClickListener(view1 -> {
            //TODO: save game settings
            NavController controller = Navigation.findNavController(view1);
            controller.navigate(R.id.action_createRoomFragment_to_roomFragment);
        });

        mBackButton.setOnClickListener(view12 -> {
            NavController controller = Navigation.findNavController(view12);
            controller.navigate(R.id.action_createRoomFragment_to_homeFragment);
        });

        setupPlayersNumberPicker();
        setupMafiaNumberPicker();
    }

    private void setupPlayersNumberPicker() {
        mPlayersNumberPicker.setMaxValue(GameConstants.PLAYERS_MAX_SIZE);
        mPlayersNumberPicker.setMinValue(GameConstants.PLAYERS_MIN_SIZE);
        mPlayersNumberPicker.setValue(GameConstants.DEFAULT_PLAYERS_COUNT);
    }

    private void setupMafiaNumberPicker() {
        mMafiaNumberPicker.setMaxValue(GameConstants.MAFIA_MAX_SIZE);
        mMafiaNumberPicker.setMinValue(GameConstants.MAFIA_MIN_SIZE);
        mMafiaNumberPicker.setValue(GameConstants.DEFAULT_MAFIA_COUNT);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mLayoutManager != null) {
            mListState = mLayoutManager.onSaveInstanceState();
            outState.putParcelable(ROLES_LIST_STATE, mListState);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            mListState = savedInstanceState.getParcelable(ROLES_LIST_STATE);
        }
    }
}
