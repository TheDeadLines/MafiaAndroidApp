package com.thedeadlines.mafiap2p.common;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.net.wifi.p2p.WifiP2pManager;
import android.net.wifi.p2p.WifiP2pManager.Channel;
import android.net.wifi.p2p.WifiP2pManager.ConnectionInfoListener;
import android.util.Log;

public class WifiDirectBroadcastReceiver extends BroadcastReceiver {
    private final static String TAG = WifiDirectBroadcastReceiver.class.getSimpleName();
    private final WifiP2pManager manager;
    private final Channel channel;
    private final ConnectionInfoListener infoListener;
    private final WifiP2pManager.PeerListListener peerListListener;


    public WifiDirectBroadcastReceiver(final WifiP2pManager manager,
                                       final Channel channel,
                                       final ConnectionInfoListener infoListener,
                                       final WifiP2pManager.PeerListListener peerListener) {
        super();
        this.manager = manager;
        this.channel = channel;
        this.infoListener = infoListener;
        this.peerListListener = peerListener;
    }

    @Override
    public void onReceive(final Context context, final Intent intent) {
        final String action = intent.getAction();
        if (WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION.equals(action)) {
            if (manager != null) {
                final NetworkInfo networkInfo = intent
                        .getParcelableExtra(WifiP2pManager.EXTRA_NETWORK_INFO);

                if (networkInfo.isConnected()) {
                    manager.requestConnectionInfo(channel, infoListener);
                } else {
                    // It's a disconnect
                }
            }
        } else if (WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION.equals(action)) {
            if (manager != null) {
                Log.d(TAG, "REQUESTING PEERS");
                manager.requestPeers(channel, peerListListener);
            }
        } else if (WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION.equals(action)) {
            if (manager != null) {
                Log.d(TAG, "State changed");
            }
        }
    }
}
