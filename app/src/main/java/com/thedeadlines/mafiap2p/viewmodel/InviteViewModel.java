package com.thedeadlines.mafiap2p.viewmodel;

import android.app.Application;
import android.content.Context;
import android.content.IntentFilter;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pManager;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.thedeadlines.mafiap2p.common.WifiDirectBroadcastReceiver;

import java.util.List;

public class InviteViewModel extends AndroidViewModel {
    private final static String TAG = InviteViewModel.class.getSimpleName();
    private WifiP2pManager wifiP2pManager;
    private WifiP2pManager.Channel channel;
    private Application application;
    private WifiDirectBroadcastReceiver broadcastReceiver;
    private IntentFilter intentFilter;

    private MutableLiveData<Boolean> isReady;

    private LiveData<List<WifiP2pDevice>> peerList;
    private boolean isConnected = false;


    public InviteViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
        wifiP2pManager = (WifiP2pManager) application.getApplicationContext().getSystemService(Context.WIFI_P2P_SERVICE);
        if (wifiP2pManager != null) {
            channel = wifiP2pManager.initialize(application.getApplicationContext(), application.getMainLooper(), null);
        }
//        WifiP2pManager.ConnectionInfoListener connectionInfoListener = new WifiP2pManager.ConnectionInfoListener() {
//            @Override
//            public void onConnectionInfoAvailable(WifiP2pInfo info) {
//                if (!info.groupFormed) return;
//                if (isConnected) return;
//                isConnected = true;
//
//                Log.d("new connection", info.toString());
//                final InetAddress address = info.groupOwnerAddress;
//                if (info.isGroupOwner) {
//                    Log.d(TAG, "It's server");
//                    new GroupOwnerSocketHandler(handler).start();
////
////                    messenger = server;
//                } else {
//                    Log.d(TAG, "It's client");
//
//                }
//            }
//        };

    }
}
