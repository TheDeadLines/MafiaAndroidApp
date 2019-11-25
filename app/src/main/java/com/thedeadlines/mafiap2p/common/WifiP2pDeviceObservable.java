package com.thedeadlines.mafiap2p.common;

import android.net.wifi.p2p.WifiP2pDevice;

public interface WifiP2pDeviceObservable {

    void notifyObserver(WifiP2pDevice wifiP2pDevice);

}
