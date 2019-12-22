package com.thedeadlines.mafiap2p.ui.fragments.room.roomHostList;

import android.net.wifi.p2p.WifiP2pDevice;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.thedeadlines.mafiap2p.R;
import com.thedeadlines.mafiap2p.data.db.player.PlayerEntity;

import java.util.ArrayList;
import java.util.List;

public class RoomHostAdapter extends RecyclerView.Adapter<RoomHostListHolder> {

    private List<RoomHostListElement> mRoomHostList;

    public RoomHostAdapter(@Nullable List<PlayerEntity> playerEntities) {
        mRoomHostList = new ArrayList<>();

        if (playerEntities != null) {
            for (int i = 0; i < playerEntities.size(); i++) {
                this.mRoomHostList.add(i, new RoomHostListElement(playerEntities.get(i), false, View.GONE));
            }
        }
    }

    @NonNull
    @Override
    public RoomHostListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.room_host_list_item, parent, false);
        return new RoomHostListHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RoomHostListHolder viewHolder, int position) {
        RoomHostListElement current = mRoomHostList.get(position);

        if (current != null) {
            viewHolder.setName(current.getPlayerEntity().mName);
            viewHolder.setDeviceNumber(current.getPlayerEntity().uidToString());
            viewHolder.setInnerButtonsWrap(current.getPlayerExpand());

            viewHolder.setStatus(current.isHost());

            final RoomHostListElement validCurrent = mRoomHostList.get(viewHolder.getAdapterPosition());

            viewHolder.getListRoot().setOnClickListener(v -> {
                viewHolder.toggleInnerButtonsWrap();
                validCurrent.setPlayerExpand(viewHolder.getmInnerButtonsWrapState());
            });
        }
    }

    @Override
    public int getItemCount() {
        return (mRoomHostList == null) ? 0 : mRoomHostList.size() ;
    }

    public void setPlayers(List<PlayerEntity> mPlayers) {
        if (mPlayers != null) {
            for (int i = 0; i < mPlayers.size(); i++) {
                if (this.mRoomHostList != null) {
                    if (this.mRoomHostList.size() - 1 < i) {
                        this.mRoomHostList.add(i, new RoomHostListElement(mPlayers.get(i), false, View.GONE));
                    } else {
                        this.mRoomHostList.get(i).setPlayerEntity(mPlayers.get(i));
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

    public boolean isEmpty() {
        return mRoomHostList.isEmpty();
    }

    public List<RoomHostListElement> getRoomHostList() {
        return mRoomHostList;
    }

    public int getHostIndex() {
        for (int i = 0; i < mRoomHostList.size(); i++) {
            if (mRoomHostList.get(i).isHost()) {
                return i;
            }
        }

        return -1;
    }

    public void setCurrentHost(int index, RoomHostListElement roomHostListElement) {
        if (roomHostListElement != null) {
            mRoomHostList.add(index, roomHostListElement);
        }
        mRoomHostList.get(index).setHost(true);
    }
}
