package com.thedeadlines.mafiap2p.ui.fragments.room;

import android.net.wifi.p2p.WifiP2pDevice;

public class JoinItems {
    private String numberRoom;
    private String nameRoom;
    private String numberPlayers;

    private WifiP2pDevice wifiP2pDevice;

    public  JoinItems(String number, String room, String player, WifiP2pDevice wifiP2pDevice) {
        this.numberRoom= number;
        this.nameRoom = room;
        this.numberPlayers = player;

        this.wifiP2pDevice = wifiP2pDevice;
    }

    public String getNumberRoom(){
        return this.numberRoom;
    }

    public String getNameRoom(){
        return this.nameRoom;
    }

    public String getNumberPlayers(){
        return this.numberPlayers;
    }

    public WifiP2pDevice getWifiP2pDevice() {
        return wifiP2pDevice;
    }
}
