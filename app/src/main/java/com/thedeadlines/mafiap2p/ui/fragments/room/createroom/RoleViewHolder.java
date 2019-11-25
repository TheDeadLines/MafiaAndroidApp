package com.thedeadlines.mafiap2p.ui.fragments.room.createroom;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.thedeadlines.mafiap2p.R;
import com.thedeadlines.mafiap2p.data.db.role.RoleEntity;

public class RoleViewHolder extends RecyclerView.ViewHolder {
    private Switch mSwitch;
    private LinearLayout mRoot;

    public RoleViewHolder(@NonNull View itemView) {
        super(itemView);
        mSwitch = itemView.findViewById(R.id.role_switch);
        mRoot = itemView.findViewById(R.id.role_root);
    }

    public void bind(@NonNull final RoleEntity roleEntity, @NonNull final OnItemClickListener<RoleEntity> clickListener) {
        getSwitch().setText(roleEntity.name);
        getSwitch().setOnClickListener(view -> clickListener.onItemClick(roleEntity));
    }

    public Switch getSwitch() {
        return mSwitch;
    }

    public LinearLayout getRoot() {
        return mRoot;
    }
}
