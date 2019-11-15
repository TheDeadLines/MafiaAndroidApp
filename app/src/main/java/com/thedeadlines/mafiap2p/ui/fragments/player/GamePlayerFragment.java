package com.thedeadlines.mafiap2p.ui.fragments.player;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.thedeadlines.mafiap2p.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class GamePlayerFragment extends Fragment {
    private static final int SHOWN_CARD_STATE = 0;
    private static final int HIDDEN_CARD_STATE = 1;

    private static final String CURRENT_STATE = "currentState";
    private static final String SHOWN_CARD_TEXT = "Hide card";
    private static final String HIDDEN_CARD_TEXT = "Show card";

    private Button mLeaveGameButton;
    private Button mToggleCardButton;
    private int currentState;


    public GamePlayerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game_player, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mLeaveGameButton = view.findViewById(R.id.leave_game_button);
        mLeaveGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController controller = Navigation.findNavController(view);
                controller.navigate(R.id.action_gamePlayerFragment_to_homeFragment);
            }
        });

        mToggleCardButton = view.findViewById(R.id.toggle_card_button);
        mToggleCardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleCurrentState();
            }
        });

        if (savedInstanceState != null) {
            currentState = savedInstanceState.getInt(CURRENT_STATE);
            bindCurrentState();
        } else {
            currentState = HIDDEN_CARD_STATE;
            bindCurrentState();
        }


    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(CURRENT_STATE, currentState);
    }

    private void toggleCurrentState() {
        switch (currentState) {
            case HIDDEN_CARD_STATE:
                currentState = SHOWN_CARD_STATE;
                mToggleCardButton.setText(SHOWN_CARD_TEXT);
                break;
            case SHOWN_CARD_STATE:
                currentState = HIDDEN_CARD_STATE;
                mToggleCardButton.setText(HIDDEN_CARD_TEXT);
                break;
            default:
                break;
        }
    }

    private void bindCurrentState() {
        switch (currentState) {
            case HIDDEN_CARD_STATE:
                mToggleCardButton.setText(HIDDEN_CARD_TEXT);
                break;
            case SHOWN_CARD_STATE:
                mToggleCardButton.setText(SHOWN_CARD_TEXT);
                break;
            default:
                break;
        }
    }
}
