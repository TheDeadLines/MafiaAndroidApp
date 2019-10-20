package com.thedeadlines.mafiap2p.ui.fragments.host;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thedeadlines.mafiap2p.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class GameHostFragment extends Fragment {


    public GameHostFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game_host, container, false);
    }

}
