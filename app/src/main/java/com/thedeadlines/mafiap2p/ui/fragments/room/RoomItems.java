package com.thedeadlines.mafiap2p.ui.fragments.room;

public class RoomItems {
    private String numberRoom;
    private String namePlayer;

    public  RoomItems(String number, String nameplayer){
        this.numberRoom= number;
        this.namePlayer = nameplayer;
    }

    public String getNumberPlayer(){
        return this.numberRoom;
    }

    public String getNamePlayer(){
        return this.namePlayer;
    }

}
