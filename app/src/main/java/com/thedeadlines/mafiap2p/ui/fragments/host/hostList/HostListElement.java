package com.thedeadlines.mafiap2p.ui.fragments.host.hostList;

import com.thedeadlines.mafiap2p.R;

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

    public int getPlayerRoleResource() {
        switch (playerRole) {
            case ("mafia"):
                return R.drawable.mafia_mafia;
            case ("citizen"):
                return R.drawable.mafia_citizen;
            case ("comissar"):
                return R.drawable.mafia_comissar;
            case ("doctor"):
                return R.drawable.mafia_doctor;
            case ("kurt"):
                return R.drawable.mafia_kurt;
            case ("maniac"):
                return R.drawable.mafia_maniac;
        }

        return -1;
    }
}
