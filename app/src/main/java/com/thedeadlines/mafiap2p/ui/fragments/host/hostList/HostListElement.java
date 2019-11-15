package com.thedeadlines.mafiap2p.ui.fragments.host.hostList;

public class HostListElement {
    private int orderNum;
    private String playerName;
    private String playerRole;

    public HostListElement(int orderNum, String playerName, String playerRole) {
        this.orderNum = orderNum;
        this.playerName = playerName;
        this.playerRole = playerRole;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public String getOrderNumString() {
        return Integer.toString(orderNum);
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getPlayerRole() {
        return playerRole;
    }
}
