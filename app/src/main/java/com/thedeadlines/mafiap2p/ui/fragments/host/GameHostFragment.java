package com.thedeadlines.mafiap2p.ui.fragments.host;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.thedeadlines.mafiap2p.R;

public class GameHostFragment extends Fragment {
    // TODO: 14.11.19 put in constants
    private static final int SHOWN_LIST_STATE = 0;
    private static final int HIDDEN_LIST_STATE = 1;

    private static final String CURRENT_STATE = "currentState";
    private static final String SHOWN_LIST_TEXT = "<";
    private static final String HIDDEN_LIST_TEXT = ">";


    private Button mFinishGameButton;
    private Button mToggleListButton;
    private int currentState;

    public GameHostFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game_host, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mFinishGameButton = view.findViewById(R.id.finish_game_button);
        mFinishGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController controller = Navigation.findNavController(view);
                controller.navigate(R.id.action_gameHostFragment_to_homeFragment);
            }
        });

        mToggleListButton = view.findViewById(R.id.toggle_list_button);
        mToggleListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleCurrentState();
            }
        });

        if (savedInstanceState != null) {
            currentState = savedInstanceState.getInt(CURRENT_STATE);
            bindCurrentState();
        } else {
            currentState = HIDDEN_LIST_STATE;
            bindCurrentState();
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(CURRENT_STATE, currentState);
    }

    // TODO: 14.11.19 basic class or interface for gamehost and gameplayer? depends on future architecture and their methods

    private void toggleCurrentState() {
        switch (currentState) {
            case HIDDEN_LIST_STATE:
                currentState = SHOWN_LIST_STATE;
                mToggleListButton.setText(SHOWN_LIST_TEXT);
                break;
            case SHOWN_LIST_STATE:
                currentState = HIDDEN_LIST_STATE;
                mToggleListButton.setText(HIDDEN_LIST_TEXT);
                break;
            default:
                break;
        }
    }

    private void bindCurrentState() {
        switch (currentState) {
            case HIDDEN_LIST_STATE:
                mToggleListButton.setText(HIDDEN_LIST_TEXT);
                break;
            case SHOWN_LIST_STATE:
                mToggleListButton.setText(SHOWN_LIST_TEXT);
                break;
            default:
                break;
        }
    }
}
