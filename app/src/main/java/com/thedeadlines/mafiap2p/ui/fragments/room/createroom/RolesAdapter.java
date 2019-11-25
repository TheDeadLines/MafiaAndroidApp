package com.thedeadlines.mafiap2p.ui.fragments.room.createroom;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.thedeadlines.mafiap2p.R;
import com.thedeadlines.mafiap2p.data.db.role.RoleEntity;

import java.util.List;

public class RolesAdapter extends RecyclerView.Adapter<RoleViewHolder> {
    private List<RoleEntity> mList;
    private OnItemClickListener<RoleEntity> mOnClickListener;

    public RolesAdapter(@NonNull List<RoleEntity> mList, OnItemClickListener<RoleEntity> clickListener) {
        this.mList = mList;
        this.mOnClickListener = clickListener;
    }

    @NonNull
    @Override
    public RoleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.role_list_item, parent, false);
        return new RoleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoleViewHolder holder, int position) {
        RoleEntity current = mList.get(position);
        if (current != null) {
            holder.bind(current, mOnClickListener);
        }
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public void setList(List<RoleEntity> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }
}
