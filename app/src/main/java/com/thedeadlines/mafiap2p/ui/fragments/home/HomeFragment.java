package com.thedeadlines.mafiap2p.ui.fragments.home;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.thedeadlines.mafiap2p.R;

public class HomeFragment extends Fragment {
    public static final String TAG = HomeFragment.class.getSimpleName();

    private ImageButton mSettingsButton;
    private Button mCreateRoomButton;
    private Button mJoinRoomButton;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mCreateRoomButton = view.findViewById(R.id.create_room_button);
        mJoinRoomButton = view.findViewById(R.id.join_room_button);
        mSettingsButton = view.findViewById(R.id.settings_button);

        mCreateRoomButton.setOnClickListener(view12 -> {
            NavController controller = Navigation.findNavController(view12);
            controller.navigate(R.id.action_homeFragment_to_createRoomFragment);
        });

        mJoinRoomButton.setOnClickListener(view1 -> {
            NavController controller = Navigation.findNavController(view1);
            controller.navigate(R.id.action_homeFragment_to_joinFragment);
        });

        mSettingsButton.setOnClickListener(view13 -> {
            NavController controller = Navigation.findNavController(view13);
            controller.navigate(R.id.action_homeFragment_to_settingsFragment);
        });
    }
}
