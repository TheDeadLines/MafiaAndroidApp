package com.thedeadlines.mafiap2p.ui.fragments.player;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.thedeadlines.mafiap2p.AppConstants;
import com.thedeadlines.mafiap2p.R;
import com.thedeadlines.mafiap2p.common.WifiDirectManager;
import com.thedeadlines.mafiap2p.game.protocol.AccessTypes;

/**
 * A simple {@link Fragment} subclass.
 */
public class GamePlayerFragment extends Fragment implements Handler.Callback {
    private static final int SHOWN_CARD_STATE = 0;
    private static final int HIDDEN_CARD_STATE = 1;

    private static final String CURRENT_STATE = "currentState";
    private static final String SHOWN_CARD_TEXT = "Hide card";
    private static final String HIDDEN_CARD_TEXT = "Show card";

    private String cardName;
    private TextView mCardName;
    private Button mLeaveGameButton;
    private Button mToggleCardButton;
    private int currentState;
    private WifiDirectManager mWifiDirectManager;
    private NavController mNavController;

    public GamePlayerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWifiDirectManager = WifiDirectManager.getInstance(getContext());
        mWifiDirectManager.updateHandler(new Handler(this));
        if (getArguments() != null) {
            cardName = getArguments().getString(AppConstants.CARD);
        }
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
        mNavController = Navigation.findNavController(view);
        mLeaveGameButton = view.findViewById(R.id.leave_game_button);
        mLeaveGameButton.setOnClickListener(view1 -> {
            NavController controller = Navigation.findNavController(view1);
            controller.navigate(R.id.action_gamePlayerFragment_to_homeFragment);
        });

        mToggleCardButton = view.findViewById(R.id.toggle_card_button);
        mToggleCardButton.setOnClickListener(view12 -> toggleCurrentState());
        mCardName = view.findViewById(R.id.player_card);
        if (savedInstanceState != null) {
            currentState = savedInstanceState.getInt(CURRENT_STATE);
            bindCurrentState();
        } else {
            currentState = HIDDEN_CARD_STATE;
            bindCurrentState();
        }

        if (cardName != null) {
            mCardName.setText(cardName);
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

    @Override
    public boolean handleMessage(@NonNull Message msg) {
        switch (msg.what) {
            case AccessTypes
                    .BROADCAST:
                Toast.makeText(getContext(), "FINISH GAME MESSAGE", Toast.LENGTH_SHORT).show();
                mNavController.navigate(R.id.action_gamePlayerFragment_to_homeFragment);
                break;
        }
        return false;
    }
}
