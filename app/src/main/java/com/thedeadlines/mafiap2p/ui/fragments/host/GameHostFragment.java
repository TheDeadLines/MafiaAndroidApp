package com.thedeadlines.mafiap2p.ui.fragments.host;


import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.thedeadlines.mafiap2p.R;
import com.thedeadlines.mafiap2p.ui.fragments.host.hostList.HostListAdapter;
import com.thedeadlines.mafiap2p.ui.fragments.host.hostList.HostListElement;
import com.thedeadlines.mafiap2p.ui.fragments.host.hostList.OnItemClickListener;


public class GameHostFragment extends Fragment {
    public static final int COLUMN_NUMBER = 2;
    public static final String HOST_PLAYER_LIST_STATE = "hostPlayerListState";
    public static final String HOST_PLAYER_LIST_NUMBER = "hostPlayerListNumber";
    public static final String HOST_PLAYER_LIST_NAME = "hostPlayerListName";
    public static final String HOST_PLAYER_LIST_ROLE = "hostPlayerListRole";


    // TODO: 14.11.19 put in constants
    private static final int SHOWN_LIST_STATE = 0;
    private static final int HIDDEN_LIST_STATE = 1;

    private static final String CURRENT_STATE = "currentState";
    private static final String SHOWN_LIST_TEXT = "<";
    private static final String HIDDEN_LIST_TEXT = ">";


    private Button mFinishGameButton;
    private Button mToggleListButton;
    private ImageView mHostImage;
    private RecyclerView mRecyclerView;
    private int currentState;


    private HostListAdapter mListViewAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Parcelable mListState;


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
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mHostImage = view.findViewById(R.id.host_image);

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


        OnItemClickListener clickListener = new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull HostListElement item) {
                Bundle bundle = new Bundle();
                bundle.putInt(HOST_PLAYER_LIST_NUMBER, item.getOrderNum());
                bundle.putString(HOST_PLAYER_LIST_NAME, item.getPlayerName());
                bundle.putString(HOST_PLAYER_LIST_ROLE, item.getPlayerRole());

                Navigation.findNavController(view).navigate(R.id.action_gameHostFragment_to_gameHostChosenPlayerFragment, bundle);
            }
        };

        mRecyclerView = view.findViewById(R.id.player_list);
        mLayoutManager = new GridLayoutManager(getContext(), COLUMN_NUMBER);

        if (savedInstanceState != null) {
            mListState = savedInstanceState.getParcelable(HOST_PLAYER_LIST_STATE);
            mLayoutManager.onRestoreInstanceState(mListState);
        }

        mListViewAdapter = new HostListAdapter(DataGenerator.getInstance().getList(), clickListener);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mListViewAdapter);


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
        outState.putParcelable(HOST_PLAYER_LIST_STATE, mListState);
    }

    private void toggleCurrentState() {
        switch (currentState) {
            case HIDDEN_LIST_STATE:
                currentState = SHOWN_LIST_STATE;
                mHostImage.setVisibility(View.GONE);
                mRecyclerView.setVisibility(View.VISIBLE);
                mToggleListButton.setText(SHOWN_LIST_TEXT);
                break;
            case SHOWN_LIST_STATE:
                currentState = HIDDEN_LIST_STATE;
                mHostImage.setVisibility(View.VISIBLE);
                mRecyclerView.setVisibility(View.GONE);
                mToggleListButton.setText(HIDDEN_LIST_TEXT);
                break;
            default:
                break;
        }
    }

    private void bindCurrentState() {
        switch (currentState) {
            case HIDDEN_LIST_STATE:
                mHostImage.setVisibility(View.VISIBLE);
                mRecyclerView.setVisibility(View.GONE);
                mToggleListButton.setText(HIDDEN_LIST_TEXT);
                break;
            case SHOWN_LIST_STATE:
                mHostImage.setVisibility(View.GONE);
                mRecyclerView.setVisibility(View.VISIBLE);
                mToggleListButton.setText(SHOWN_LIST_TEXT);
                break;
            default:
                break;
        }
    }
}
