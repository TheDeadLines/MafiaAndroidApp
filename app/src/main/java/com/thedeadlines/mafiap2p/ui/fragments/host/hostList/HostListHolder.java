package com.thedeadlines.mafiap2p.ui.fragments.host.hostList;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.thedeadlines.mafiap2p.R;

public class HostListHolder extends RecyclerView.ViewHolder {
    private TextView playerOrderNumber;
    private ImageView playerRole;
    private TextView playerName;
    private LinearLayout listRoot;


    HostListHolder(@NonNull View itemView) {
        super(itemView);
        playerOrderNumber = itemView.findViewById(R.id.player_order_number_inner);
        playerRole = itemView.findViewById(R.id.player_role_inner);
        playerName = itemView.findViewById(R.id.player_name_inner);
        listRoot = itemView.findViewById(R.id.host_list_item_root);
    }

    public TextView getPlayerOrderNumber() {
        return playerOrderNumber;
    }

    public ImageView getPlayerRole() {
        return playerRole;
    }

    public TextView getPlayerName() {
        return playerName;
    }

    public LinearLayout getListRoot() {
        return listRoot;
    }
}
