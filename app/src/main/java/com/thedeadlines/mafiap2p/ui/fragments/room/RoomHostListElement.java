package com.thedeadlines.mafiap2p.ui.fragments.room;

public class RoomHostListElement {
    private int playerId;
    private String playerName;
    private boolean isHost;
    private boolean isExpanded;

    public RoomHostListElement(int playerId, String playerName, boolean isHost) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.isHost = isHost;
        this.isExpanded = false;
    }

    public int getPlayerId() {
        return playerId;
    }

    public String getPlayerIdString() {
        return Integer.toString(playerId);
    }

    public String getPlayerName() {
        return playerName;
    }

    public boolean isHost() {
        return isHost;
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    public void toggleExpanded() {
        isExpanded = !isExpanded;
    }
}
