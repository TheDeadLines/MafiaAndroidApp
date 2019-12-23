package com.thedeadlines.mafiap2p.ui.fragments.room;


import android.os.Bundle;
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

public class RoomFragment extends Fragment {
    private PeersAdapter mPeersAdapter;
    private Button mStartButton;
    private RecyclerView mConnectionList;
    private WifiDirectManager mWifiDirectManager;

    public RoomFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mWifiDirectManager = WifiDirectManager.getInstance(getContext());
        mWifiDirectManager.updateWifiP2pDeviceObservable(wifiP2pDevice -> mPeersAdapter.add(wifiP2pDevice));
        mWifiDirectManager.createGroup(mWifiDirectManager.getDeviceName());
        mPeersAdapter = getPeersAdapter(null);
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
        mConnectionList.setLayoutManager(new LinearLayoutManager(getContext()));
        mConnectionList.setAdapter(mPeersAdapter);
        mStartButton = view.findViewById(R.id.start_game_button);
        mStartButton.setOnClickListener(view1 -> {
            mWifiDirectManager.formGroup();
            mWifiDirectManager.updateJoinListener(null);
            NavController controller = Navigation.findNavController(view1);
            controller.navigate(R.id.action_roomFragment_to_gameHostFragment);
        });

    }

    public PeersAdapter getPeersAdapter(@Nullable final View.OnClickListener listener) {
        return new PeersAdapter(listener);
    }

}
