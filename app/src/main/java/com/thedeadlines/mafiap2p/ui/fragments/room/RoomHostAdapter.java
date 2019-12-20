package com.thedeadlines.mafiap2p.ui.fragments.room;

import android.content.Context;
import android.net.wifi.p2p.WifiP2pDevice;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.thedeadlines.mafiap2p.R;
import com.thedeadlines.mafiap2p.data.db.player.PlayerEntity;
import com.thedeadlines.mafiap2p.game.Player;

import java.util.List;

public class RoomHostAdapter extends RecyclerView.Adapter<RoomHostAdapter.ViewHolder> {

    private List<PlayerEntity> mRoomHostList;
    private RoomHostAdapter.ViewHolder mConnectingViewHolder;


    public RoomHostAdapter(@Nullable List<PlayerEntity> RoomHostList) {
        this.mRoomHostList = RoomHostList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView deviceNameTextView;
        private final TextView deviceStatusTextView;
        private TextView deviceNumber;
        private LinearLayout listRoot;

        private LinearLayout mInnerButtonsWrap;

        private TextView mRotater;

        private final View itemView;

        public ViewHolder(final View itemView) {
            super(itemView);
            this.itemView = itemView;
            deviceNameTextView = itemView.findViewById(R.id.room_host_list_name);
            deviceStatusTextView = itemView.findViewById(R.id.room_host_list_is_host);
            deviceNumber = itemView.findViewById(R.id.room_host_list_number_item);
            listRoot = itemView.findViewById(R.id.room_host_list_root);
            mInnerButtonsWrap = itemView.findViewById(R.id.inner_buttons_wrap);
            mRotater = itemView.findViewById(R.id.room_host_list_rotater);
        }

        public void setInnerButtonsWrap(int isExpanded) {
            int rot = isExpanded > 0 ? -1 : 1;
            this.mInnerButtonsWrap.setVisibility(isExpanded);
            this.mRotater.setRotation(rot*90);
        }

        public void toggleInnerButtonsWrap() {
            switch (mInnerButtonsWrap.getVisibility()) {
                case View.VISIBLE:
                    setInnerButtonsWrap(View.GONE);
                    break;
                case View.GONE:
                    setInnerButtonsWrap(View.VISIBLE);
                    break;
                default:
                    break;
            }
        }

        public void setOnClickListener(final View.OnClickListener onClickListener) {
            itemView.setOnClickListener(onClickListener);
        }

        public void setName(final CharSequence name) {
            this.deviceNameTextView.setText(name);
        }

        public void setDeviceNumber(String deviceNumber) {
            this.deviceNumber.setText(deviceNumber);
        }

        public void setStatus(final boolean status) {
            int isOwner = status ? View.VISIBLE : View.GONE;
            this.deviceStatusTextView.setVisibility(isOwner);
        }

        public LinearLayout getListRoot() {
            return listRoot;
        }
    }

    @NonNull
    @Override
    public RoomHostAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.room_host_list_item, parent, false);
        return new RoomHostAdapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RoomHostAdapter.ViewHolder viewHolder, int position) {
        PlayerEntity current = mRoomHostList.get(position);

        if (current != null) {
            viewHolder.setName(current.mName);
            viewHolder.setDeviceNumber(current.uidToString());

            // check if host
//                viewHolder.setStatus(current.isHost());


            viewHolder.setInnerButtonsWrap(View.GONE);

            final PlayerEntity validCurrent = mRoomHostList.get(viewHolder.getAdapterPosition());
            viewHolder.getListRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewHolder.toggleInnerButtonsWrap();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return (mRoomHostList == null) ? 0 : mRoomHostList.size() ;
    }

    public void setPlayers(List<PlayerEntity> mPlayers) {
        this.mRoomHostList = mPlayers;
        notifyDataSetChanged();
    }

    public boolean isEmpty() {
        return mRoomHostList.isEmpty();
    }

    public List<PlayerEntity> getRoomHostList() {
        return mRoomHostList;
    }
}