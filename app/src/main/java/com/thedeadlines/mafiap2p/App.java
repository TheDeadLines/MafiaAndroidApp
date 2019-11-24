package com.thedeadlines.mafiap2p;

import android.app.Application;
import android.content.IntentFilter;
import android.net.wifi.p2p.WifiP2pManager;

import com.thedeadlines.mafiap2p.data.GamePlayerJoinRepository;
import com.thedeadlines.mafiap2p.data.GameRepository;
import com.thedeadlines.mafiap2p.data.PlayerRepository;
import com.thedeadlines.mafiap2p.data.RolesRepository;
import com.thedeadlines.mafiap2p.data.db.AppDatabase;

import static android.net.wifi.p2p.WifiP2pManager.Channel;
import static android.net.wifi.p2p.WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION;
import static android.net.wifi.p2p.WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION;
import static android.net.wifi.p2p.WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION;
import static android.net.wifi.p2p.WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION;

public class App extends Application {
    private final IntentFilter mIntentFilter = new IntentFilter();

    WifiP2pManager manager;
    Channel mChannel;

    @Override
    public void onCreate() {
        super.onCreate();

        // Indicates a change in the Wi-Fi P2P status.
        mIntentFilter.addAction(WIFI_P2P_STATE_CHANGED_ACTION);

        // Indicates a change in the list of available peers.
        mIntentFilter.addAction(WIFI_P2P_PEERS_CHANGED_ACTION);


        // Indicates the state of connectivity has changed
        mIntentFilter.addAction(WIFI_P2P_CONNECTION_CHANGED_ACTION);

        // Indicates this device's details have changed.
        mIntentFilter.addAction(WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);

        manager = (WifiP2pManager) getSystemService(WIFI_P2P_SERVICE);
        if (manager != null) {
            mChannel = manager.initialize(getApplicationContext(), getMainLooper(), null);
        }
    }

    public AppDatabase getAppDatabase() {
        return AppDatabase.getInstance(this);
    }

    public GameRepository getGameRepository() {
        return GameRepository.getInstance(getAppDatabase());
    }

    public PlayerRepository getPlayerRepository() {
        return PlayerRepository.getInstance(getAppDatabase());
    }

    public GamePlayerJoinRepository getGamePlayerJoinRepository() {
        return GamePlayerJoinRepository.getInstance(getAppDatabase());
    }

    public RolesRepository getRolesRepository() {
        return RolesRepository.getInstance(getAppDatabase());
    }
}
