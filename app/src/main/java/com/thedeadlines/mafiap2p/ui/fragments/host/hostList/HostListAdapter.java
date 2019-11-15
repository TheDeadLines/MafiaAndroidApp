package com.thedeadlines.mafiap2p.ui.fragments.host.hostList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.thedeadlines.mafiap2p.R;

import java.util.List;

public class HostListAdapter extends RecyclerView.Adapter<HostListHolder> {
    private List<HostListElement> hostListPlayers;
    private OnItemClickListener onItemClickListener;

    public HostListAdapter(List<HostListElement> hostListPlayers, OnItemClickListener onItemClickListener) {
        this.hostListPlayers = hostListPlayers;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public HostListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.host_list_item, parent, false);
        return new HostListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HostListHolder holder, int position) {
        HostListElement current = hostListPlayers.get(position);
        if (current != null) {
            holder.getPlayerName().setText(current.getPlayerName());
            holder.getPlayerOrderNumber().setText(current.getOrderNumString());
//            holder.getPlayerRole().setImageResource(current.get());
            final HostListElement validCurrent = hostListPlayers.get(holder.getAdapterPosition());
            holder.getListRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                     onItemClickListener.onItemClick(validCurrent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return hostListPlayers.size();
    }

}
