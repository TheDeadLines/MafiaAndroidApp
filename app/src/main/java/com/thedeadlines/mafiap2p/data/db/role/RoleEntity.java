package com.thedeadlines.mafiap2p.data.db.role;


import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "roles")
public class RoleEntity {
    @PrimaryKey(autoGenerate = true)
    public int uid;

    public String name;

    public boolean checked;

    public RoleEntity(int uid, String name) {
        this.uid = uid;
        this.name = name;
        this.checked = false;
    }

    @Ignore
    public RoleEntity(String name) {
        this.name = name;
        this.checked = false;
    }
}
