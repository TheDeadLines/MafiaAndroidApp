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
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.thedeadlines.mafiap2p.R;
import com.thedeadlines.mafiap2p.common.WifiDirectManager;

import java.util.ArrayList;
import java.util.List;

public class RoomFragment extends Fragment {
    private static final String TAG = RoomFragment.class.getSimpleName();
    private PeersAdapter mPeersAdapter;
    private Button mStartButton;
    private Button mCancelButton;
    private RecyclerView mConnectionList;
    private WifiDirectManager mWifiDirectManager;

    public RoomFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWifiDirectManager = WifiDirectManager.getInstance(getContext());
        mPeersAdapter = getPeersAdapter(v -> {
            final PeersAdapter.ViewHolder viewHolder =
                    (PeersAdapter.ViewHolder) mConnectionList.getChildViewHolder(v);
            mWifiDirectManager.invite(viewHolder.getWifiP2pDevice());
        });
        mWifiDirectManager.updatePeerListListener(peers -> {
            List<WifiP2pDevice> prs = new ArrayList<>();
            prs.addAll(peers.getDeviceList());
            mPeersAdapter.set(prs);
            if (prs.size() == 0) {
                Log.d(TAG, "NO DEVICES FOUND");
            } else {
                for (WifiP2pDevice device : prs) {
                    Log.d(TAG, "Found device: " + device.deviceName);
                }
            }
        });
        mWifiDirectManager.updateWifiP2pDeviceObservable(wifiP2pDevice -> mPeersAdapter.add(wifiP2pDevice));
        mWifiDirectManager.createGroup(mWifiDirectManager.getDeviceName());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_room, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mConnectionList = view.findViewById(R.id.host_players_list);
        mConnectionList.setAdapter(mPeersAdapter);
        mConnectionList.setLayoutManager(new LinearLayoutManager(getContext()));
        mStartButton = view.findViewById(R.id.start_game_button);
        mStartButton.setOnClickListener(view1 -> {
            mWifiDirectManager.formGroup();
            mWifiDirectManager.updateJoinListener(null);
            Log.d(TAG, "start game message sending");
            mPeersAdapter.sendMessages(mWifiDirectManager);

            NavController controller = Navigation.findNavController(view1);
            controller.navigate(R.id.action_roomFragment_to_gameHostFragment);
            mWifiDirectManager.stopDiscovery();
        });
        mCancelButton = view.findViewById(R.id.cancel_game_button);
        mCancelButton.setOnClickListener(v -> {
            mWifiDirectManager.formGroup();
            NavController controller = Navigation.findNavController(v);
            controller.navigate(R.id.action_roomFragment_to_homeFragment);
        });
        mWifiDirectManager.startDiscovery();

    }

    public PeersAdapter getPeersAdapter(@Nullable final View.OnClickListener listener) {
        return new PeersAdapter(listener);
    }


    @Override
    public void onResume() {
        super.onResume();
        getContext().registerReceiver(mWifiDirectManager.getWifiDirectBroadcastReceiver(),
                mWifiDirectManager.getIntentFilter());
    }

    @Override
    public void onPause() {
        super.onPause();
        getContext().unregisterReceiver(mWifiDirectManager.getWifiDirectBroadcastReceiver());
    }


}
