package com.thedeadlines.mafiap2p.ui.fragments.room;


import android.content.Intent;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.thedeadlines.mafiap2p.R;
import com.thedeadlines.mafiap2p.common.WifiDirectManager;
import com.thedeadlines.mafiap2p.ui.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class JoinFragment extends Fragment {

    private  static  final  String TAG = "JoinFragment";

    private Button buttonBack;
    private RecyclerView mRoomList;
    private  int maxPlayers = 7;
    private  String mNumberRoom;
    private  String mNameRoom;
    private  int mNumberPlayer;


    private JoinAdapter mPeersAdapter;
    private RecyclerView mPeersRecyclerView;
    private WifiDirectManager mWifiDirectManager;


    public JoinFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_join, container, false);
    }

    public JoinAdapter getPeersAdapter(@Nullable final View.OnClickListener listener) {
        final JoinAdapter adapter = new JoinAdapter(listener);
        return adapter;
    }

    public RecyclerView getPeersRecyclerView(final JoinAdapter adapter) {
        final RecyclerView recyclerView = getActivity().findViewById(R.id.rooms_list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return recyclerView;
    }

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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        if (savedInstanceState != null) {
//            mNumberRoom = savedInstanceState.getString("nuRoom");
//            mNameRoom = savedInstanceState.getString("naRoom");
//            mNumberPlayer = savedInstanceState.getInt("naRoom");
//            setInitialRooms(mNumberRoom,mNameRoom,mNumberPlayer);
//        }

//        setInitialRooms("01","Room1",7);
//        setInitialRooms("02","ANfrtid",2);
//        setInitialRooms("03","S1ft4",5);


        mRoomList = view.findViewById(R.id.rooms_list);
//        JoinAdapter adapter = new JoinAdapter(exampleList);
//        mRoomList.setAdapter(mPeersAdapter);
        mRoomList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                NavController controller = Navigation.findNavController(view);
//                controller.navigate(R.id.action_joinFragment_to_roomFragment);
            }
        });

        buttonBack = view.findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController controller = Navigation.findNavController(view);
                controller.navigate(R.id.action_joinFragment_to_homeFragment);
            }
        });



        mWifiDirectManager = WifiDirectManager.getInstance(getContext());

        mPeersAdapter = getPeersAdapter(v -> {
            final JoinAdapter.ExampleViewHolder viewHolder =
                    (JoinAdapter.ExampleViewHolder) mPeersRecyclerView.getChildViewHolder(v);
            mWifiDirectManager.join(viewHolder.getWifiP2pDevice());
        });
        mPeersRecyclerView = getPeersRecyclerView(mPeersAdapter);
        mWifiDirectManager.updateJoinListener(new WifiP2pManager.ActionListener() {

            @Override
            public void onSuccess() {
                // TODO: 5/27/17 MembersActivity
                mWifiDirectManager.updateJoinListener(null);
                mWifiDirectManager.stopDiscovery();

                mRoomList.callOnClick();
            }

            @Override
            public void onFailure(final int reason) {
                mPeersAdapter.onFailure();
            }
        });
        mWifiDirectManager.updateWifiP2pDeviceObservable(
                wifiP2pDevice -> mPeersAdapter.add(wifiP2pDevice)
        );
        mWifiDirectManager.startDiscovery();
    }

//    private  void setInitialRooms(String numberRoom, String nameRoom, int numberPlayer){
//
//        if (numberPlayer >= maxPlayers){
//            exampleList.add(new JoinItems(numberRoom,nameRoom,"players: " + numberPlayer + "(full)"));
//        } else{
//            exampleList.add(new JoinItems(numberRoom,nameRoom,"players: " + numberPlayer));
//        }
//    }

//    @Override
//    public void onRoomClick(int position) {
//        Log.i(TAG, "clicked: " + position);
//    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("nuRoom", mNumberRoom);
        outState.putString("naRoom", mNameRoom);
        outState.putInt("nuPlayer", mNumberPlayer);
    }
}
