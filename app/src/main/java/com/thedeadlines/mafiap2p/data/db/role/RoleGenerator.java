package com.thedeadlines.mafiap2p.data.db.role;

import android.content.Context;

import com.thedeadlines.mafiap2p.R;

import java.util.ArrayList;
import java.util.List;

public class  RoleGenerator {
    public static List<RoleEntity> generateRoles(Context c) {
        List<RoleEntity> roleEntities = new ArrayList<>();

//        roleEntities.add(new RoleEntity("mafia"));
//        roleEntities.add(new RoleEntity("player"));

        roleEntities.add(new RoleEntity(c.getString(R.string.don)));
        roleEntities.add(new RoleEntity(c.getString(R.string.sheriff)));
        roleEntities.add(new RoleEntity(c.getString(R.string.doctor)));
        roleEntities.add(new RoleEntity(c.getString(R.string.maniac)));
        roleEntities.add(new RoleEntity(c.getString(R.string.madam)));
        return roleEntities;
    }
}
