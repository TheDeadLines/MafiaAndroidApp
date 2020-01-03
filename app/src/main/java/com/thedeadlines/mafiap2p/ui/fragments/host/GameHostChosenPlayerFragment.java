package com.thedeadlines.mafiap2p.ui.fragments.host;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.thedeadlines.mafiap2p.R;
import com.thedeadlines.mafiap2p.ui.fragments.host.hostList.HostListElement;


public class GameHostChosenPlayerFragment extends Fragment {
    private TextView mPlayerOrderNumber;
    private ImageView mPlayerRole;
    private TextView mPlayerName;
    private Button mStartTimerButton;
    private Button mKickButton;
    private Button mBackButton;

    public GameHostChosenPlayerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game_host_chosen_player, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mPlayerName = view.findViewById(R.id.player_name);
        mPlayerRole = view.findViewById(R.id.player_role);
        mPlayerOrderNumber = view.findViewById(R.id.player_order_number);

        mBackButton = view.findViewById(R.id.back_button);
        mBackButton.setOnClickListener(view13 -> {
            NavController controller = Navigation.findNavController(view13);
            controller.navigate(R.id.action_gameHostChosenPlayerFragment_to_gameHostFragment);
        });

        mKickButton = view.findViewById(R.id.kick_button);
        mKickButton.setOnClickListener(view1 -> {
            //
        });

        mStartTimerButton = view.findViewById(R.id.start_timer_button);
        mStartTimerButton.setOnClickListener(view12 -> {
            //
        });

        Bundle args = getArguments();
        if (args != null) {
            int number = args.getInt(GameHostFragment.HOST_PLAYER_LIST_NUMBER);
            String name = args.getString(GameHostFragment.HOST_PLAYER_LIST_NAME);
            String role = args.getString(GameHostFragment.HOST_PLAYER_LIST_ROLE);
            HostListElement el = new HostListElement(number, name, role);
            mPlayerOrderNumber.setText(el.getOrderNumString());
            mPlayerName.setText(el.getPlayerName());
//            mPlayerRole.setImage?
        }
    }
}
