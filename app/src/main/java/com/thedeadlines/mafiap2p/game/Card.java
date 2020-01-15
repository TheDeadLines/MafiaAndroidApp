package com.thedeadlines.mafiap2p.game;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

public class Card implements Serializable {
    private String whichId;
    private String name;
    private Drawable image;


    public Card(String whichId, String name, Drawable image) {
        this.whichId = whichId;
        this.name = name;
        this.image = image;
    }


    public String getName() {
        return name;
    }

    public Drawable getImage() {
        return image;
    }

    public String getWhichId() {
        return whichId;
    }
}
