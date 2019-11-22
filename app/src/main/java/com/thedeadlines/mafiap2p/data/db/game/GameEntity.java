package com.thedeadlines.mafiap2p.data.db.game;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class GameEntity {
    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "room_id")
    public int mRoomId;

    @ColumnInfo(name = "time")
    public Date mDate;

}
