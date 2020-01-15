package com.thedeadlines.mafiap2p.ui.fragments.room;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.List;

public class RoomRoles {
    private static final RoomRoles ourInstance = new RoomRoles();
    private List<String> roleNames = new ArrayList<>();
    private List<Drawable> roleDrawables = new ArrayList<>();
    public static RoomRoles getInstance() {
        return ourInstance;
    }

    private RoomRoles() {
    }

    public List<Drawable> getRoleDrawables() {
        return roleDrawables;
    }

    public void setRoleDrawables(List<Drawable> roleDrawables) {
        this.roleDrawables = roleDrawables;
    }

    public List<String> getRoleNames() {
        return roleNames;
    }

    public void setRoleNames(List<String> roleNames) {
        this.roleNames = roleNames;
    }
}
