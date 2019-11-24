package com.thedeadlines.mafiap2p.data.db.player;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "players")
public class PlayerEntity {
    @PrimaryKey
    public int uid;

    public String mName;

    public PlayerEntity(int uid, String mName) {
        this.uid = uid;
        this.mName = mName;
    }
}
