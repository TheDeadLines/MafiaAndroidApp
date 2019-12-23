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

    /* total mafia count */
    public int mafiaCount;

    /* total players count */
    public int playersCount;

    @Ignore
    public GameEntity(int uid, int hostId, Date mDate, int mafiaCount, int playersCount) {
        this.uid = uid;
        this.hostId = hostId;
        this.mDate = mDate;
        this.mafiaCount = mafiaCount;
        this.playersCount = playersCount;
    }

    public GameEntity(int hostId, Date mDate, int mafiaCount, int playersCount) {
        this.hostId = hostId;
        this.mDate = mDate;
        this.mafiaCount = mafiaCount;
        this.playersCount = playersCount;
    }


    @Ignore
    public GameEntity(Date mDate) {
        this.mDate = mDate;
    }
}
