package com.thedeadlines.mafiap2p.ui.fragments.room;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.thedeadlines.mafiap2p.R;

public class JoinFragment extends Fragment {
    private RecyclerView mRoomList;
    private TextView mJoinText;

    public JoinFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_join, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRoomList = view.findViewById(R.id.rooms_list);
        mRoomList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController controller = Navigation.findNavController(view);
                controller.navigate(R.id.action_joinFragment_to_roomFragment);
            }
        });
        mJoinText = view.findViewById(R.id.join_text);
        mJoinText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                NavController controller = Navigation.findNavController(view);
                controller.navigate(R.id.action_joinFragment_to_roomFragment);
            }
        });
    }
}
