package com.thedeadlines.mafiap2p.ui.fragments.room;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.thedeadlines.mafiap2p.R;

import java.util.ArrayList;
import java.util.List;

public class RoomFragment extends Fragment implements RoomAdapter.OnPlayerListener {

    private  static  final  String TAG = "RoomFragment";

    private Button buttonBack;
    private Button buttonStart;
    private RecyclerView mPlayerList;

    List<RoomItems> playerList = new ArrayList<>();

    public RoomFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_room, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setInitialPlayer("01", "Player 1");
        setInitialPlayer("02", "Player 2");
        setInitialPlayer("03", "Player 3");

        mPlayerList = view.findViewById(R.id.players_list);
        RoomAdapter adapter = new RoomAdapter(playerList, this);
        mPlayerList.setAdapter(adapter);

        buttonStart = view.findViewById(R.id.start_game_button);
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController controller = Navigation.findNavController(view);
                controller.navigate(R.id.action_roomFragment_to_gameHostFragment);
            }
        });

        buttonBack = view.findViewById(R.id.buttonBackRoom);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController controller = Navigation.findNavController(view);
                controller.navigate(R.id.action_roomFragment_to_homeFragment);
            }
        });
    }

    private  void setInitialPlayer(String numberPlayer, String namePlayer){
        playerList.add(new RoomItems(numberPlayer,namePlayer));
    }

    @Override
    public void onPlayerClick(int position) {
        Log.i(TAG, "clickedPlayer: " + position);
    }
}
