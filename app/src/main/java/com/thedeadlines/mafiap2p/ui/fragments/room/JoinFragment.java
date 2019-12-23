package com.thedeadlines.mafiap2p.ui.fragments.room;


import android.net.wifi.p2p.WifiP2pManager;
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

public class JoinFragment extends Fragment implements JoinAdapter.OnRoomListener {

    private static final String TAG = JoinFragment.class.getSimpleName();

    private PeersAdapter mPeersAdapter;
    private WifiDirectManager mWifiDirectManager;
    private Button buttonBack;
    private RecyclerView mRoomList;
    private int maxPlayers = 7;
    private String mNumberRoom;
    private String mNameRoom;
    private int mNumberPlayer;


    public JoinFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mWifiDirectManager = WifiDirectManager.getInstance(getContext());
        mWifiDirectManager.updateWifiP2pDeviceObservable(wifiP2pDevice -> mPeersAdapter.add(wifiP2pDevice));
        mWifiDirectManager.createGroup(mWifiDirectManager.getDeviceName());
        mPeersAdapter = getPeersAdapter(v -> {
            final PeersAdapter.ViewHolder viewHolder =
                    (PeersAdapter.ViewHolder) mRoomList.getChildViewHolder(v);
            mWifiDirectManager.join(viewHolder.getWifiP2pDevice());
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_join, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        mRoomList = view.findViewById(R.id.rooms_list);
        mRoomList.setAdapter(mPeersAdapter);
        mRoomList.setLayoutManager(new LinearLayoutManager(getContext()));
        mWifiDirectManager.updateJoinListener(new WifiP2pManager.ActionListener() {
            @Override
            public void onSuccess() {
                mWifiDirectManager.updateJoinListener(null);
                mWifiDirectManager.stopDiscovery();
                NavController controller = Navigation.findNavController(view);
                controller.navigate(R.id.action_joinFragment_to_roomFragment);
            }

            @Override
            public void onFailure(int reason) {
                mPeersAdapter.onFailure();
            }
        });
        mWifiDirectManager.updateWifiP2pDeviceObservable(
                wifiP2pDevice -> mPeersAdapter.add(wifiP2pDevice)
        );
        mWifiDirectManager.startDiscovery();
        buttonBack = view.findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(view12 -> {
            NavController controller = Navigation.findNavController(view12);
            controller.navigate(R.id.action_joinFragment_to_homeFragment);
        });
    }

    @Override
    public void onRoomClick(int position) {
        Log.i(TAG, "clicked: " + position);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("nuRoom", mNumberRoom);
        outState.putString("naRoom", mNameRoom);
        outState.putInt("nuPlayer", mNumberPlayer);
    }

    public PeersAdapter getPeersAdapter(@Nullable final View.OnClickListener listener) {
        return new PeersAdapter(listener);
    }
}
