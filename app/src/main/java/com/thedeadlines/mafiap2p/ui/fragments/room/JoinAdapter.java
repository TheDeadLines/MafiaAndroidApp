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

public class JoinAdapter extends RecyclerView.Adapter<JoinAdapter.ExampleViewHolder> {


    private  LayoutInflater inflater;
    private List<JoinItems> mExamplesList;
    private OnRoomListener mOnRoomListener;

    public  JoinAdapter(List<JoinItems> examplelist, OnRoomListener OnRoomListener){
        this.mExamplesList = examplelist;
        this.mOnRoomListener = OnRoomListener;
    }

    @NonNull
    @Override
    public JoinAdapter.ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_room, parent, false);
        return new ExampleViewHolder(view, mOnRoomListener);
    }

    @Override
    public void onBindViewHolder(@NonNull JoinAdapter.ExampleViewHolder holder, int position) {
        JoinItems currentItem = mExamplesList.get(position);
        holder.numberRoomView.setText(currentItem.getNumberRoom());
        holder.nameRoomView.setText(currentItem.getNameRoom());
        holder.numberPlayersView.setText(currentItem.getNumberPlayers());
    }

    @Override
    public int getItemCount() {
        return mExamplesList.size();
    }

    public static class ExampleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        final TextView numberRoomView, nameRoomView, numberPlayersView;
        OnRoomListener onRoomListener;

        public ExampleViewHolder(@NonNull View itemView, OnRoomListener onRoomListener) {
            super(itemView);
            numberRoomView = itemView.findViewById(R.id.number_room);
            nameRoomView = itemView.findViewById(R.id.name_room);
            numberPlayersView = itemView.findViewById(R.id.number_players);
            this.onRoomListener = onRoomListener;
            itemView.setOnClickListener(this);
            itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController controller = Navigation.findNavController(view);
                controller.navigate(R.id.action_joinFragment_to_roomFragment);
            }
        });
        }

        @Override
        public void onClick(View view) {
            onRoomListener.onRoomClick(getAdapterPosition());
        }
    }

    public interface OnRoomListener{
        void onRoomClick(int position);
    }
}
