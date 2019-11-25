package com.thedeadlines.mafiap2p.ui.fragments.room;

public class JoinItems {
    private String numberRoom;
    private String nameRoom;
    private String numberPlayers;

    public  JoinItems(String number, String room, String player){
        this.numberRoom= number;
        this.nameRoom = room;
        this.numberPlayers = player;
    }

    public String getNumberRoom(){
        return this.numberRoom;
    }

    public String getNameRoom(){
        return this.nameRoom;
    }

    public String getNumberPlayers(){
        return this.numberPlayers;
    }
}
