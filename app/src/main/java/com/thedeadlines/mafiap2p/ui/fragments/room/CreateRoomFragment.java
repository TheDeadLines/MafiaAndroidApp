package com.thedeadlines.mafiap2p.ui.fragments.room;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.thedeadlines.mafiap2p.R;

public class CreateRoomFragment extends Fragment {
    private Button mCreateRoomButton;
    private Button mBackButton;
    private RecyclerView mRolesListView;


    public CreateRoomFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_room, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mCreateRoomButton = view.findViewById(R.id.start_room_button);
        mBackButton = view.findViewById(R.id.create_room_back_button);
        mRolesListView = view.findViewById(R.id.roles_switches);

        mCreateRoomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController controller = Navigation.findNavController(view);
                controller.navigate(R.id.action_createRoomFragment_to_roomFragment);
            }
        });

        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController controller = Navigation.findNavController(view);
                controller.navigate(R.id.action_createRoomFragment_to_homeFragment);
            }
        });
    }
}
