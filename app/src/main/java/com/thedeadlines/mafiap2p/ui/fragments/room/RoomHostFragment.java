package com.thedeadlines.mafiap2p.ui.fragments.room;


import android.net.wifi.p2p.WifiP2pDevice;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.thedeadlines.mafiap2p.R;
import com.thedeadlines.mafiap2p.common.WifiDirectManager;
import com.thedeadlines.mafiap2p.data.db.player.PlayerEntity;
import com.thedeadlines.mafiap2p.viewmodel.PlayersViewModel;

import java.util.List;


public class RoomHostFragment extends Fragment {
    private Button mStartButton;
    private Button mBackButton;
    private Button mSettingsButton;
    private RecyclerView mRecyclerView;
    private PlayersViewModel mPlayersViewModel;
    private RoomHostAdapter mAdapter;

    private RecyclerView.LayoutManager mLayoutManager;

    private WifiDirectManager mWifiDirectManager;

    public RoomHostFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPlayersViewModel = ViewModelProviders.of(this).get(PlayersViewModel.class);
        mPlayersViewModel.getPlayers().observe(this, playerEntities -> {
            Log.d("observer", "gotcha" + mPlayersViewModel.getPlayers().getValue().get(0).mName);
            if (playerEntities != null) {
                mAdapter.setPlayers(playerEntities);
            }
        });

        mWifiDirectManager = WifiDirectManager.getInstance(getContext());

        mWifiDirectManager.updateWifiP2pDeviceObservable(wifiP2pDevice -> add(wifiP2pDevice));
        mWifiDirectManager.createGroup(mWifiDirectManager.getDeviceName());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_room_host, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        mWifiDirectManager = WifiDirectManager.getInstance(getContext());

        mStartButton = view.findViewById(R.id.room_host_start_game_button);
        mStartButton.setOnClickListener(view1 -> {
            NavController controller = Navigation.findNavController(view1);

            if (!mAdapter.isEmpty()) {
                mWifiDirectManager.formGroup();
                mWifiDirectManager.updateJoinListener(null);
            }

            controller.navigate(R.id.action_roomHostFragment_to_gameHostFragment);
        });

        mBackButton = view.findViewById(R.id.room_host_back_button);
        mBackButton.setOnClickListener(view2 -> {
            NavController controller = Navigation.findNavController(view2);
            controller.navigate(R.id.action_roomHostFragment_to_createRoomFragment);
        });


        mSettingsButton = view.findViewById(R.id.room_host_settings_button);
        mSettingsButton.setOnClickListener(view3 -> {
            NavController controller = Navigation.findNavController(view3);
            controller.navigate(R.id.action_roomHostFragment_to_settingsFragment);
        });

        mRecyclerView = view.findViewById(R.id.room_host_list);
        mLayoutManager = new LinearLayoutManager(getContext());
        mAdapter = new RoomHostAdapter(mPlayersViewModel.getPlayers().getValue());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);


        mPlayersViewModel.insert(new PlayerEntity(1, mWifiDirectManager.getDeviceName()));
        Log.d("testNow", mWifiDirectManager.getDeviceName());
    }

    public void add(final WifiP2pDevice device) {
        if ((device.status == WifiP2pDevice.AVAILABLE || device.status == WifiP2pDevice.CONNECTED)
            /*&& !contains(device) */) {
            mPlayersViewModel.insert(new PlayerEntity(mPlayersViewModel.getPlayers().getValue().size() + 1, device.deviceName));
        }
    }

//    public void onFailure() {
//        remove(mConnectingViewHolder.getWifiP2pDevice());
//        mConnectingViewHolder = null;
//    }
//
//    private boolean contains(final WifiP2pDevice device) {
//        for (final WifiP2pDevice p2pDevice :
//                mDevices) {
//            if (device.deviceAddress.equals(p2pDevice.deviceAddress)) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public void remove(final WifiP2pDevice device) {
//        for (final WifiP2pDevice p2pDevice :
//                mDevices) {
//            if (p2pDevice.deviceAddress.equals(device.deviceAddress)) {
//                mDevices.remove(p2pDevice);
//                break;
//            }
//        }
//        notifyDataSetChanged();
//    }


    @Override
    public void onResume() {
        super.onResume();
        getActivity().registerReceiver(mWifiDirectManager.getWifiDirectBroadcastReceiver(),
                mWifiDirectManager.getIntentFilter());
    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().unregisterReceiver(mWifiDirectManager.getWifiDirectBroadcastReceiver());
    }
}