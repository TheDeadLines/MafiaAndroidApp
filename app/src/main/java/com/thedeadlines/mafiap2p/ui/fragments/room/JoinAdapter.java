package com.thedeadlines.mafiap2p.ui.fragments.room;

import android.content.Context;
import android.net.wifi.p2p.WifiP2pDevice;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.thedeadlines.mafiap2p.R;

import java.util.ArrayList;
import java.util.List;

public class JoinAdapter extends RecyclerView.Adapter<JoinAdapter.ExampleViewHolder> {

    private JoinAdapter.ExampleViewHolder mConnectingViewHolder;
    private final List<WifiP2pDevice> mDevices;
    private final View.OnClickListener mListener;

    private final List<JoinItems> mExamplesList;


    public JoinAdapter(@Nullable View.OnClickListener listener) {
        mExamplesList = new ArrayList<>();
        mDevices = new ArrayList<>();

        if (listener == null) {
            listener = v -> {
            };
        }
        mListener = listener;
    }

    public void add(final WifiP2pDevice device) {
        if ((device.status == WifiP2pDevice.AVAILABLE || device.status == WifiP2pDevice.CONNECTED)
                && !contains(device)) {
            mDevices.add(device);


            Log.d("connect", device.deviceName);
            JoinItems element = new JoinItems("0", device.deviceName, "undefined_m", device);
            mExamplesList.add(element);


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

    @NonNull
    @Override
    public JoinAdapter.ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final Context context = parent.getContext();
        final LayoutInflater inflater = LayoutInflater.from(context);
        final View wifiP2pDeviceView = inflater.inflate(R.layout.item_room, parent, false);
        final JoinAdapter.ExampleViewHolder viewHolder = new ExampleViewHolder(wifiP2pDeviceView);
        viewHolder.setOnClickListener(v -> {
            if (mConnectingViewHolder == null) {
                mListener.onClick(v);
                mConnectingViewHolder = viewHolder;
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull JoinAdapter.ExampleViewHolder holder, int position) {
        JoinItems currentItem = mExamplesList.get(position);
        holder.setWifiP2pDevice(currentItem.getWifiP2pDevice());
        holder.numberRoomView.setText(currentItem.getNumberRoom());
        holder.nameRoomView.setText(currentItem.getNameRoom());
        holder.numberPlayersView.setText(currentItem.getNumberPlayers());
    }

    @Override
    public int getItemCount() {
        return mDevices.size();
    }

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {

        final TextView numberRoomView, nameRoomView, numberPlayersView;

        private WifiP2pDevice wifiP2pDevice;

        private final View itemView;

        public ExampleViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            numberRoomView = itemView.findViewById(R.id.number_room);
            nameRoomView = itemView.findViewById(R.id.name_room);
            numberPlayersView = itemView.findViewById(R.id.number_players);
        }

        public void setWifiP2pDevice(final WifiP2pDevice wifiP2pDevice) {
            this.wifiP2pDevice = wifiP2pDevice;
        }

        public void setOnClickListener(final View.OnClickListener onClickListener) {
            itemView.setOnClickListener(onClickListener);
        }

        public void setName(final CharSequence name) {
            this.nameRoomView.setText(name);
        }

        public void setNumberPlayers(final CharSequence status) {
            this.numberPlayersView.setText(status);
        }

        public WifiP2pDevice getWifiP2pDevice() {
            return wifiP2pDevice;
        }
    }

    public interface OnRoomListener {
        void onRoomClick(int position);
    }
}
