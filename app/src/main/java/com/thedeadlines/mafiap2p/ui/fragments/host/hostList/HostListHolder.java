package com.thedeadlines.mafiap2p.ui.fragments.host.hostList;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.thedeadlines.mafiap2p.R;

public class HostListHolder extends RecyclerView.ViewHolder {
    private TextView playerOrderNumber;
    private TextView playerRole;
    private TextView playerName;
    private LinearLayout listRoot;


    HostListHolder(@NonNull View itemView) {
        super(itemView);
        playerOrderNumber = itemView.findViewById(R.id.player_order_number);
        playerRole = itemView.findViewById(R.id.player_role);
        playerName = itemView.findViewById(R.id.player_name);
        listRoot = itemView.findViewById(R.id.host_list_item_root);
    }

    public TextView getPlayerOrderNumber() {
        return playerOrderNumber;
    }

    public TextView getPlayerRole() {
        return playerRole;
    }

    public TextView getPlayerName() {
        return playerName;
    }

    public LinearLayout getListRoot() {
        return listRoot;
    }
}
