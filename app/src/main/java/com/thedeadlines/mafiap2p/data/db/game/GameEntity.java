package com.thedeadlines.mafiap2p.data.db.game;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "games")
public class GameEntity {
    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "time")
    public Date mDate;

    @ColumnInfo(name = "host_id")
    public int hostId;

    public GameEntity(int uid, int hostId, Date mDate) {
        this.uid = uid;
        this.hostId = hostId;
        this.mDate = mDate;
    }

    @Ignore
    public GameEntity(Date mDate) {
        this.mDate = mDate;
    }
}
