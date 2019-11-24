package com.thedeadlines.mafiap2p.ui.fragments.room;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.thedeadlines.mafiap2p.R;
import com.thedeadlines.mafiap2p.ui.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class JoinFragment extends Fragment implements JoinAdapter.OnRoomListener {

    private  static  final  String TAG = "JoinFragment";

    private Button buttonBack;
    private RecyclerView mRoomList;
    private  int maxPlayers = 7;
    private  String mNumberRoom;
    private  String mNameRoom;
    private  int mNumberPlayer;

    List<JoinItems> exampleList = new ArrayList<>();


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

//        if (savedInstanceState != null) {
//            mNumberRoom = savedInstanceState.getString("nuRoom");
//            mNameRoom = savedInstanceState.getString("naRoom");
//            mNumberPlayer = savedInstanceState.getInt("naRoom");
//            setInitialRooms(mNumberRoom,mNameRoom,mNumberPlayer);
//        }

        setInitialRooms("01","Room1",7);
        setInitialRooms("02","ANfrtid",2);
        setInitialRooms("03","S1ft4",5);


        mRoomList = view.findViewById(R.id.rooms_list);
        JoinAdapter adapter = new JoinAdapter(exampleList, this);
        mRoomList.setAdapter(adapter);
        mRoomList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController controller = Navigation.findNavController(view);
                controller.navigate(R.id.action_joinFragment_to_roomFragment);
            }
        });

        buttonBack = view.findViewById(R.id.buttonBackJoin);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController controller = Navigation.findNavController(view);
                controller.navigate(R.id.action_joinFragment_to_homeFragment);
            }
        });
    }

    private  void setInitialRooms(String numberRoom, String nameRoom, int numberPlayer){

        if (numberPlayer >= maxPlayers){
            exampleList.add(new JoinItems(numberRoom,nameRoom,"players: " + numberPlayer + "(full)"));
        } else{
            exampleList.add(new JoinItems(numberRoom,nameRoom,"players: " + numberPlayer));
        }
    }

    @Override
    public void onRoomClick(int position) {
        Log.i(TAG, "clicked: " + position);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("nuRoom", mNumberRoom);
        outState.putString("naRoom", mNameRoom);
        outState.putInt("nuPlayer", mNumberPlayer);
    }
}
