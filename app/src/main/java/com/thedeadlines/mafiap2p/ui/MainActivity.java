package com.thedeadlines.mafiap2p.ui;

import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.thedeadlines.mafiap2p.R;

public class MainActivity extends AppCompatActivity implements WifiP2pManager.ChannelListener {
    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onChannelDisconnected() {

    }
}
