package com.thedeadlines.mafiap2p.ui.fragments.room.roomHostList;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.thedeadlines.mafiap2p.R;

public class RoomHostListHolder extends RecyclerView.ViewHolder {

    private final TextView deviceNameTextView;
    private final TextView deviceStatusTextView;
    private TextView deviceNumber;
    private int mInnerButtonsWrapState;

    private LinearLayout listRoot;
    private LinearLayout mInnerButtonsWrap;

    private TextView mRotater;

    private final View itemView;

    RoomHostListHolder(final View itemView) {
        super(itemView);
        this.itemView = itemView;
        deviceNameTextView = itemView.findViewById(R.id.room_host_list_name);
        deviceStatusTextView = itemView.findViewById(R.id.room_host_list_is_host);
        deviceNumber = itemView.findViewById(R.id.room_host_list_number_item);
        listRoot = itemView.findViewById(R.id.room_host_list_root);
        mInnerButtonsWrap = itemView.findViewById(R.id.inner_buttons_wrap);
        mRotater = itemView.findViewById(R.id.room_host_list_rotater);
    }

    public void setInnerButtonsWrap(int playerExpand) {
        int rot = playerExpand > 0 ? -1 : 1;
        this.mInnerButtonsWrapState = playerExpand;
        this.mInnerButtonsWrap.setVisibility(playerExpand);
        this.mRotater.setRotation(rot*90);
    }

    public void toggleInnerButtonsWrap() {
        switch (mInnerButtonsWrap.getVisibility()) {
            case View.VISIBLE:
                setInnerButtonsWrap(View.GONE);
                break;
            case View.GONE:
                setInnerButtonsWrap(View.VISIBLE);
                break;
            default:
                break;
        }
    }

    public void restoreInnerButtonsWrap() {
        switch (mInnerButtonsWrapState) {
            case View.VISIBLE:
                setInnerButtonsWrap(View.GONE);
                break;
            case View.GONE:
                setInnerButtonsWrap(View.VISIBLE);
                break;
            default:
                break;
        }
    }

    public void setOnClickListener(final View.OnClickListener onClickListener) {
        itemView.setOnClickListener(onClickListener);
    }

    public void setName(final CharSequence name) {
        this.deviceNameTextView.setText(name);
    }

    public void setDeviceNumber(String deviceNumber) {
        this.deviceNumber.setText(deviceNumber);
    }

    public void setStatus(final boolean status) {
        int isOwner = status ? View.VISIBLE : View.GONE;
        this.deviceStatusTextView.setVisibility(isOwner);
    }

    public LinearLayout getListRoot() {
        return listRoot;
    }

    public int getmInnerButtonsWrapState(){
        return mInnerButtonsWrapState;
    }
}
