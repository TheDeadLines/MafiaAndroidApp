package com.thedeadlines.mafiap2p.ui.fragments.host;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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

import java.util.ArrayList;
import java.util.List;

public class GameHostChosenPlayerFragment extends Fragment {
    // to delete
    public static class DataGenerator {
        List<HostListElement> list;

        private static final DataGenerator sInstance = new DataGenerator();

        static DataGenerator getInstance() {
            return sInstance;
        }

        List<HostListElement> getList() {
            return list;
        }

        DataGenerator() {
            list = new ArrayList<>();
            for (int i = 0; i < 6; i++) {
                HostListElement e = new HostListElement(i, "simple name" + 1, "role" + 1);
                list.add(e);
            }
        }
    }



    private final int COLUMN_NUMBER = 2;
    private final String HOST_PLAYER_LIST_STATE = "hostPlayerListState";

    private HostListAdapter mListViewAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Parcelable mListState;

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

        mBackButton = view.findViewById(R.id.back_button);
        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController controller = Navigation.findNavController(view);
                controller.navigate(R.id.action_gameHostChosenPlayerFragment_to_gameHostFragment);
            }
        });

        mKickButton = view.findViewById(R.id.kick_button);
        mKickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //
            }
        });

        mStartTimerButton = view.findViewById(R.id.start_timer_button);
        mStartTimerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //
            }
        });


        RecyclerView mRecyclerView = view.findViewById(R.id.host_list_item_root);

        mLayoutManager = new GridLayoutManager(getContext(), COLUMN_NUMBER);

        if (savedInstanceState != null) {
            mListState = savedInstanceState.getParcelable(HOST_PLAYER_LIST_STATE);
            mLayoutManager.onRestoreInstanceState(mListState);
        }

        mListViewAdapter = new HostListAdapter(DataGenerator.getInstance().getList());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mListViewAdapter);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(HOST_PLAYER_LIST_STATE, mListState);
    }


}
