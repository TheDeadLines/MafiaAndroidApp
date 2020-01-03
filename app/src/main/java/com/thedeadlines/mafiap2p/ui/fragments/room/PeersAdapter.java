package com.thedeadlines.mafiap2p.ui.fragments.room;

import android.content.Context;
import android.net.wifi.p2p.WifiP2pDevice;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.thedeadlines.mafiap2p.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PeersAdapter extends RecyclerView.Adapter<PeersAdapter.ViewHolder> {
    private static final String TAG = PeersAdapter.class.getSimpleName();
    private PeersAdapter.ViewHolder mConnectingViewHolder;
    private final List<WifiP2pDevice> mDevices;
    private final View.OnClickListener mListener;
    private static final Map<Integer, String> mStatusStringMap = new HashMap<>();

    static {
        mStatusStringMap.put(0, "Connected");
        mStatusStringMap.put(1, "Invited");
        mStatusStringMap.put(2, "Failed");
        mStatusStringMap.put(3, "Available");
        mStatusStringMap.put(4, "Unavailable");
    }

    public PeersAdapter(@Nullable View.OnClickListener listener) {
        mDevices = new ArrayList<>();
        if (listener == null) {
            listener = v -> {
            };
        }
        mListener = listener;
    }

    public void set(final List<WifiP2pDevice> list) {
        mDevices.clear();
        mDevices.addAll(list);
        notifyDataSetChanged();
    }

    public void add(final WifiP2pDevice device) {
        Log.i(TAG, "Trying to add device " + device);
        if ((device.status == WifiP2pDevice.AVAILABLE || device.status == WifiP2pDevice.CONNECTED)
                && !contains(device)) {
            mDevices.add(device);
            Log.i(TAG, "Can see new device " + device.deviceName + " addr " + device.deviceAddress);
            notifyDataSetChanged();
        }
    }

    public void onFailure() {
        remove(mConnectingViewHolder.getWifiP2pDevice());
        mConnectingViewHolder = null;
    }

    private boolean contains(final WifiP2pDevice device) {
        for (final WifiP2pDevice p2pDevice :
                mDevices) {
            if (device.deviceAddress.equals(p2pDevice.deviceAddress)) {
                return true;
            }
        }
        return false;
    }

    public void remove(final WifiP2pDevice device) {
        for (final WifiP2pDevice p2pDevice :
                mDevices) {
            if (p2pDevice.deviceAddress.equals(device.deviceAddress)) {
                mDevices.remove(p2pDevice);
                break;
            }
        }
        notifyDataSetChanged();
    }

    public boolean isEmpty() {
        return mDevices.isEmpty();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private WifiP2pDevice wifiP2pDevice;
        private final TextView deviceNameTextView;
        private final TextView deviceStatusTextView;

        private final View itemView;

        public ViewHolder(final View itemView) {
            super(itemView);
            this.itemView = itemView;
            deviceNameTextView = itemView.findViewById(R.id.tv_name);
            deviceStatusTextView = itemView.findViewById(R.id.tv_status);
        }

        public void setWifiP2pDevice(final WifiP2pDevice wifiP2pDevice) {
            this.wifiP2pDevice = wifiP2pDevice;
        }

        public void setOnClickListener(final View.OnClickListener onClickListener) {
            itemView.setOnClickListener(onClickListener);
        }

        public void setName(final CharSequence name) {
            this.deviceNameTextView.setText(name);
        }

        public void setStatus(final CharSequence status) {
            this.deviceStatusTextView.setText(status);
        }

        public WifiP2pDevice getWifiP2pDevice() {
            return wifiP2pDevice;
        }

    }

    @NonNull
    @Override
    public PeersAdapter.ViewHolder onCreateViewHolder(final ViewGroup parent,
                                                      final int viewType) {
        final Context context = parent.getContext();
        final LayoutInflater inflater = LayoutInflater.from(context);
        final View wifiP2pDeviceView = inflater.inflate(R.layout.item_wifi_group, parent, false);
        final PeersAdapter.ViewHolder viewHolder = new ViewHolder(wifiP2pDeviceView);
        viewHolder.setOnClickListener(v -> {
            if (mConnectingViewHolder == null) {
                mListener.onClick(v);
                viewHolder.setStatus("CONNECTING");
                mConnectingViewHolder = viewHolder;
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final PeersAdapter.ViewHolder viewHolder, final int position) {
        final WifiP2pDevice wifiP2pDevice = mDevices.get(position);
        viewHolder.setWifiP2pDevice(wifiP2pDevice);
        viewHolder.setName(wifiP2pDevice.deviceName);
        viewHolder.setStatus(mStatusStringMap.get(wifiP2pDevice.status));
    }

    @Override
    public int getItemCount() {
        return mDevices.size();
    }
}
