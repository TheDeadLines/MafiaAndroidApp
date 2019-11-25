package com.thedeadlines.mafiap2p.ui.fragments.room;

import com.thedeadlines.mafiap2p.data.db.player.PlayerEntity;

public interface OnRoomHostItemClickListener {
    void onItemClick(PlayerEntity item, int pos);
};
