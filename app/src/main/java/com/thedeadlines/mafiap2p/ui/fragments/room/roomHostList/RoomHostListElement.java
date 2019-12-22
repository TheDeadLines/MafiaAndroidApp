package com.thedeadlines.mafiap2p.ui.fragments.room.roomHostList;

import android.view.View;

import com.thedeadlines.mafiap2p.data.db.player.PlayerEntity;

public class RoomHostListElement {
    private PlayerEntity playerEntity;
    private boolean isHost;
    private int playerExpand;

    public RoomHostListElement(PlayerEntity playerEntity, boolean isHost, int playerExpand) {
        this.playerEntity = playerEntity;
        this.isHost = isHost;
        this.playerExpand = View.GONE;
    }

    public PlayerEntity getPlayerEntity() {
        return playerEntity;
    }
    public void setPlayerEntity(PlayerEntity playerEntity) {
        this.playerEntity = playerEntity;
    }

    public boolean isHost() {
        return isHost;
    }
    public void setHost(boolean isHost) {
        this.isHost = isHost;
    }

    public int getPlayerExpand() {
        return playerExpand;
    }
    public void setPlayerExpand(int playerExpand) {
        this.playerExpand = playerExpand;
    }
}
