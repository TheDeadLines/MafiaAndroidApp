package com.thedeadlines.mafiap2p.background;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class BroadcastService extends Service {
    public BroadcastService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
