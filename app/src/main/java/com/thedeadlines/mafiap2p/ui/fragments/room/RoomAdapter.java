package com.thedeadlines.mafiap2p.ui.fragments.room;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.thedeadlines.mafiap2p.R;

import java.util.List;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.RoomViewHolder> {

    private List<RoomItems> mPlayerRoomList;
    private OnPlayerListener mOnPlayerListener;

    public  RoomAdapter(List<RoomItems> playerList, OnPlayerListener onPlayerListener){
        this.mPlayerRoomList = playerList;
        this.mOnPlayerListener = onPlayerListener;
    }

    @NonNull
    @Override
    public RoomAdapter.RoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_player_in_room, parent, false);
        return new RoomViewHolder(view, mOnPlayerListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomAdapter.RoomViewHolder holder, int position) {
        RoomItems currentItem = mPlayerRoomList.get(position);
        holder.numberPlayersView.setText(currentItem.getNumberPlayer());
        holder.namePlayersView.setText(currentItem.getNamePlayer());
    }

    @Override
    public int getItemCount() {
        return mPlayerRoomList.size();
    }

    public static class RoomViewHolder extends RecyclerView.ViewHolder{

        final TextView numberPlayersView, namePlayersView;
        OnPlayerListener onPlayerListener;

        public RoomViewHolder(@NonNull View itemView, OnPlayerListener onPlayerListener){
            super(itemView);
            numberPlayersView = itemView.findViewById(R.id.number_players);
            namePlayersView = itemView.findViewById(R.id.name_player);
            this.onPlayerListener = onPlayerListener;
        }
    }

    public interface OnPlayerListener{
        void onPlayerClick(int position);
    }

}
